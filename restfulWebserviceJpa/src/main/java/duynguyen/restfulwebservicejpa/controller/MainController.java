package duynguyen.restfulwebservicejpa.controller;

import duynguyen.restfulwebservicejpa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee/{page}/{size}")
    public String index(@PathVariable(value = "page", required = false) Integer page,
                        @PathVariable(value = "size", required = false) Integer size,
                        ModelMap modelMap) {
        Pageable pageable = PageRequest.of(page, size);
        modelMap.addAttribute("list", employeeService.findEmployee(pageable));
        return "index";
    }
}
