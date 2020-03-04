package duynguyen.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {

    @GetMapping("test-thymeleaf")
    public String testThymeleaf() {
        return "th_testThymeleaf";
    }

    @GetMapping("test-free-marker")
    public String testFreeMarker() {
        return "testFreeMarker";
    }

    @GetMapping("test-jsp")
    public String testJsp() {
        return "testJsp";
    }

}
