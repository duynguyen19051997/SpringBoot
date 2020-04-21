package duynguyen.securityjwt.controller;


import duynguyen.securityjwt.entity.Employee;
import duynguyen.securityjwt.service.EmployeeService;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
