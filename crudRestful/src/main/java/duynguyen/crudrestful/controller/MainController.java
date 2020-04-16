package duynguyen.crudrestful.controller;

import duynguyen.crudrestful.dao.EmployeeDAO;
import duynguyen.crudrestful.entity.Employee;
import duynguyen.crudrestful.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeDAO employeeDAO;

    @ResponseBody
    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getEmployees() {
        return "Result: " + employeeRepository.findAll();
    }


    @GetMapping(value = "/get-one/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getEmployeesById(@PathVariable("id") Integer id) {
        return "Result: " + employeeRepository.findEmployeeByEmpId(id);
    }

    @ResponseBody
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createNewEmployee(@RequestBody Employee employee) {
        Employee newEmp = employeeDAO.addNewEmployee(employee);
        return "Result " + newEmp;
    }

    @ResponseBody
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteOne(@PathVariable("id") Integer id) {
        employeeRepository.removeEmployeeByEmpId(id);
        return "done";
    }
}
