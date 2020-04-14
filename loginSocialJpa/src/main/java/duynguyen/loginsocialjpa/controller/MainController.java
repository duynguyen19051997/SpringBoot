package duynguyen.loginsocialjpa.controller;

import duynguyen.loginsocialjpa.dao.AppUserDAO;
import duynguyen.loginsocialjpa.entity.AppRole;
import duynguyen.loginsocialjpa.entity.AppUser;
import duynguyen.loginsocialjpa.form.AppUserForm;
import duynguyen.loginsocialjpa.utils.EncrytedPasswordUtils;
import duynguyen.loginsocialjpa.utils.SecurityUtil;
import duynguyen.loginsocialjpa.utils.WebUtils;
import duynguyen.loginsocialjpa.validator.AppUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private AppUserDAO appUserDAO;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    @Autowired
    private UsersConnectionRepository connectionRepository;

    @Autowired
    private AppUserValidator appUserValidator;



    @GetMapping({"/", "/welcome"})
    public String welcome(ModelMap modelMap) {
        modelMap.addAttribute("title", "Welcome");
        modelMap.addAttribute("message", "Login Social Network");
        return "welcome";
    }

    @GetMapping("/admin")
    public String admin(ModelMap modelMap, Principal principal) {
        String username = principal.getName();
        System.out.println("Username: " + username);
        UserDetails loginedUser = (UserDetails) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        modelMap.addAttribute("userInfo", userInfo);
        return "admin";
    }

    @GetMapping("/user-info")
    public String userInfo(ModelMap modelMap, Principal principal) {
        String username = principal.getName();
        System.out.println("Username: " + username);
        UserDetails loginedUser = (UserDetails) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        modelMap.addAttribute("userInfo", userInfo);
        return "userInfo";
    }

    @GetMapping("/403")
    public String accessDenied(ModelMap modelMap, Principal principal) {

        if (principal != null) {
            UserDetails loginedUser = (UserDetails) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            modelMap.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                + "<br> You do not have permission to access this page!";
            modelMap.addAttribute("message", message);

        }

        return "403";
    }

    @GetMapping("/login")
    public String index() {
        return "index";
    }

    // User login with social networking,
    // but does not allow the app to view basic information
    // application will redirect to page / signin.
    @GetMapping("/signin")
    public String signInPage(ModelMap modelMap) {
        return "redirect:/sign-up";
    }

    @GetMapping("/sign-up")
    public String signUp(WebRequest webRequest, ModelMap modelMap) {
        ProviderSignInUtils providerSignInUtils //
            = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);

        // Retrieve social networking information.
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(webRequest);
        //
        AppUserForm myForm = null;
        //
        System.out.println(connection);
        if (connection != null) {
            myForm = new AppUserForm(connection);
        } else {
            myForm = new AppUserForm();
        }
        modelMap.addAttribute("myForm", myForm);
        return "signUp";
    }

    @PostMapping("/sign-up")
    public String processSignUp(WebRequest webRequest, ModelMap modelMap,
                                @ModelAttribute("myForm") @Validated AppUserForm appUserForm,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "signUp";
        }
        List<String> roleNames = new ArrayList<String>();
        roleNames.add(AppRole.ROLE_USER);

        AppUser registered = null;

        try {
            registered = appUserDAO.registerNewUser(appUserForm, roleNames);
            System.out.println(registered);
        } catch (Exception ex) {
            ex.printStackTrace();
            modelMap.addAttribute("errorMessage", "Error " + ex.getMessage());
            return "signUp";
        }

        if (appUserForm.getSignInProvider() != null) {
            ProviderSignInUtils providerSignInUtils //
                = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);

            // (Spring Social API):
            // If user login by social networking.
            // This method saves social networking information to the UserConnection table.
            providerSignInUtils.doPostSignUp(registered.getUsername(), webRequest);
        }

        // After registration is complete, automatic login.
        SecurityUtil.logInUser(registered, roleNames);

        return "redirect:/user-info";
    }

    @GetMapping("/check/{pass}")
    public String checkPassword(ModelMap modelMap, @PathVariable("pass") String pass) {
        String password = EncrytedPasswordUtils.encrytePassword(pass);
        modelMap.addAttribute("password", password);
        return "check";
    }
}
