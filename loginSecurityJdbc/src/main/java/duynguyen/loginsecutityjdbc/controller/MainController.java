package duynguyen.loginsecutityjdbc.controller;

import duynguyen.loginsecutityjdbc.dao.AppUserDAO;
import duynguyen.loginsecutityjdbc.dao.UserRolesDAO;
import duynguyen.loginsecutityjdbc.model.AppUser;
import duynguyen.loginsecutityjdbc.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigInteger;
import java.security.Principal;

@Controller
public class MainController {
    @Autowired
    private AppUserDAO appUserDAO;

    @Autowired
    private UserRolesDAO userRolesDAO;

    @GetMapping("/sign-up")
    public String signUp(ModelMap modelMap) {
        AppUser appUser = new AppUser();
        modelMap.addAttribute("appUser", appUser);
        return "signup";
    }

    @PostMapping("/sign-up")
    public String processSignUp(@ModelAttribute("appUser") AppUser appUser, ModelMap modelMap) {
        try {
            AppUser checkUsername = appUserDAO.findUserByUsername(appUser.getUsername());
            if (checkUsername == null) {
                BigInteger userId = appUserDAO.insertNewUser(appUser);
                if (userRolesDAO.insertUserRoles(userId) > 0) {
                    return "redirect:/sign-in";
                }
            }
            return "signup";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "signup";
        }
    }

    @GetMapping("/sign-in")
    public String index() {
        return "index";
    }

    @GetMapping({"/", "/welcome"})
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

    @GetMapping("/admin")
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "adminPage";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "loginPage";
    }

    @GetMapping("/logout-successful")
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @GetMapping("/user-info")
    public String userInfo(Model model, Principal principal) {

        // (1) (en)
        // After user login successfully.
        // (vi)
        // Sau khi user login thanh cong se co principal
        String userName = principal.getName();

        System.out.println("User Name: " + userName);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "userInfoPage";
    }

    @GetMapping("/403")
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }
}
