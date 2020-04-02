package duynguyen.mongodb.controller;

import duynguyen.mongodb.custom.EmployeeRepositoryCustom;
import duynguyen.mongodb.model.Employee;
import duynguyen.mongodb.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Controller
public class MainController {

    @Autowired
    private EmployeeRepositoryCustom erc;

    @Autowired
    private EmployeeRepository er;

    /*
     * Home Page
     * */
    @GetMapping("/home")
    public String home(ModelMap modelMap) {
        List<Employee> employees = er.findAll();
        modelMap.addAttribute("employees", employees);
        modelMap.addAttribute("message", "");
        return "home";
    }

    /*
     * Insert a new Employee
     * */
    @GetMapping("/insert")
    public String insertNewEmployee(ModelMap modelMap) {
        modelMap.addAttribute("employee", new Employee());
        return "insert";
    }

    /*
     * Insert a new Employee, method: POST
     * */
    @PostMapping("/insert")
    public String processInsertNewEmployee(@ModelAttribute("employee") Employee employee) {
        try {
            int id = erc.getMaxEmpId() + 1;
            employee.setId(id);
            employee.setHireDate(new Date());
            System.out.println(employee);
            er.insert(employee);
            return "redirect:/home";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "insert";
        }
    }

    /*
     * Update an employee
     * */
    @GetMapping("/update/{id}")
    public String updateEmployeeById(@PathVariable("id") Integer id, ModelMap modelMap) {
        try {
            Optional<Employee> employee = er.findById(id);
            modelMap.addAttribute("employee", employee.get());
            return "update";
        } catch (NumberFormatException e) {
            System.out.println("ID not found!");
            modelMap.addAttribute("message", "ID not found!");
            return "redirect:/home";
        }
    }

    /*
     * Update an employee, method: POST
     * */
    @PostMapping("/update")
    public String processUpdateAnEmployee(@ModelAttribute("employee") Employee employee, ModelMap modelMap) {
        try {
            employee.setHireDate(new Date());
            int result = erc.updateEmployee(employee);
            if (result > 0) {
                modelMap.addAttribute("message", "Update Success with id = " + employee.getId());
                return "redirect:/home";
            }
            modelMap.addAttribute("message", "Update Fail");
            return "redirect:/home";
        } catch (Exception e) {
            return "update";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id, ModelMap modelMap) {
        try {
            er.deleteById(id);
            modelMap.addAttribute("message", "Delete Success with id = " + id);
            return "redirect:/home";
        } catch (NumberFormatException e) {
            modelMap.addAttribute("message", "ID not Found");
            return "redirect:/home";
        }
    }

    @GetMapping("/delete-all")
    public String deleteAll(ModelMap modelMap) {
        er.deleteAll();
        modelMap.addAttribute("message", "Delete Done");
        return "redirect:/home";
    }

}
