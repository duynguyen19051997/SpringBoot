package duynguyen.securityjwt.controller;


import duynguyen.securityjwt.entity.Employee;
import duynguyen.securityjwt.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@RestController
@ResponseBody
@RequestMapping(value = "/api/v1/employee", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public List<Employee> findAll() {
        List<Employee> result = employeeService.findAll();
        return result;
    }

    @PostMapping("")
    public Employee create(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

}
