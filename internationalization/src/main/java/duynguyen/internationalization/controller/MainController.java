package duynguyen.internationalization.controller;

import duynguyen.internationalization.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Value("${label.username}")
    private String username;

    @Value("${label.password}")
    private String password;

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @GetMapping("login")
    public String home() {
        return "login";
    }

    @PostMapping("login")
    public String home(@ModelAttribute("user") User user) {
        try {
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());

            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                return "redirect:/success";
            }
            return "login";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "login";
        }
    }
}
