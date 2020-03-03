package duynguyen.springboot.controller;

import duynguyen.springboot.model.bean.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private static List<Person> persons = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    static {
        persons.add(new Person("Bill", "Gates"));
        persons.add(new Person("Steve", "Jobs"));
    }

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @ResponseBody
    @GetMapping("/")
    public String actuator(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String host = request.getServerName();

        String endpointBasePath = "/actuator";
        StringBuilder sb = new StringBuilder();
        sb.append("<h2>Sprig Boot Actuator</h2>");
        sb.append("<ul>");

        String url = "http://" + host + ":8090" + contextPath + endpointBasePath;

        sb.append("<li><a href='").append(url).append("'>").append(url).append("</a></li>");

        sb.append("</ul>");

        return sb.toString();
    }

    @ResponseBody
    @GetMapping("/logging")
    public String logging() {
        LOGGER.info("This is INFO");
        LOGGER.trace("This is TRACE");
        LOGGER.debug("This is DEBUG");
        LOGGER.warn("This is WARN");
        LOGGER.error("This is ERROR");
        return "Hi, show logging in the console or file";
    }

    @GetMapping({"/", "/index"})
    public String index(ModelMap modelMap) {
        List<String> stringList = new ArrayList<>();
        stringList.add("Anh duy");
        stringList.add("Khanh Linh");
        stringList.add("Ban be");

        modelMap.addAttribute("code", "Happy new year");
        modelMap.addAttribute("list", stringList);
        modelMap.addAttribute("message", message);
        modelMap.addAttribute("variables", "Nguyen Vu Anh Duy");
        return "index";
    }

    @GetMapping("/person-list")
    public String personList(Model model) {
        model.addAttribute("persons", persons);
        return "person-list";
    }

    @GetMapping("/add-person")
    public String showAddPersonPage(ModelMap modelMap) {
        Person personForm = new Person();
        modelMap.addAttribute("personForm", personForm);
        modelMap.addAttribute("happy", "Happy new year");
        return "add-person";
    }

    @PostMapping("/add-person")
    public String savePerson(ModelMap modelMap, //
                             @ModelAttribute("personForm") Person person) {
        String firstName = person.getFirstName();
        String lastName = person.getLastName();
        if (firstName != null && firstName.length() > 0 //
                && lastName != null && lastName.length() > 0) {
            Person newPerson = new Person(firstName, lastName);
            persons.add(newPerson);
            return "redirect:/person-list";
        }
        modelMap.addAttribute("errorMessage", errorMessage);
        return "add-person";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/products")
    public String products() {
        return "products";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
