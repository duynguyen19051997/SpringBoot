package duynguyen.crudrestful.controller;

import duynguyen.crudrestful.entity.Employee;
import duynguyen.crudrestful.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@ResponseBody
@RequestMapping(value = {"/api/v1/employee"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class MainController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee getOneById(@PathVariable("id") Integer id) {
        return employeeService.findById(id).get();
    }

    @PostMapping
    public Employee insertEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @DeleteMapping("/{id}")
    public String deleteOneById(@PathVariable("id") Integer id) {
        Optional<Employee> employee = employeeService.findById(id);
        if (employee.isPresent()) {
            employeeService.deleteById(id);
            return "Done";
        }
        return "Fail";
    }

    @PutMapping("/{id}")
    public Employee updateOne(@RequestBody Employee employee, @PathVariable("id") Integer id) {
        Optional<Employee> oldEmployee = employeeService.findById(id);
        if (oldEmployee.isPresent()) {
            employee.setEmpId(oldEmployee.get().getEmpId());
            employee.setCreatedAt(oldEmployee.get().getCreatedAt());
            return employeeService.save(employee);
        }
        return null;
    }

}
