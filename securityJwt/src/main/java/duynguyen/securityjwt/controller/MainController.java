package duynguyen.securityjwt.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class MainController {

    @GetMapping("/check")
    public String checkPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode("123456");
    }
}
