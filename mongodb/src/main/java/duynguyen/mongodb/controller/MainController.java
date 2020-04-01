package duynguyen.mongodb.controller;

import duynguyen.mongodb.custom.EmployeeRepositoryCustom;
import duynguyen.mongodb.model.Employee;
import duynguyen.mongodb.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    private static final String[] NAMES = {"Duy", "Linh", "Long"};

    @Autowired
    private EmployeeRepositoryCustom erc;

    @Autowired
    private EmployeeRepository er;

    @ResponseBody
    @GetMapping("/home")
    public String home() {
        String html = "";
        html += "<ul>";
        html += " <li><a href='/test-insert'>Test Insert</a></li>";
        html += " <li><a href='/show-all'>Show All Employee</a></li>";
        html += " <li><a href='/show'>Show All 'Duy'</a></li>";
        html += " <li><a href='/delete-all'>Delete All Employee</a></li>";
        html += " <li><a href='/update'>Update</a></li>";
        html += "</ul>";
        return html;
    }

    @ResponseBody
    @GetMapping("/test-insert")
    public String testInsert() {

        int id = erc.getMaxEmpId() + 1;
        int idx = (int) (id % NAMES.length);
        String fullName = NAMES[idx] + " " + id;
        String empNo = "E" + id;
        Employee employee = new Employee(id, empNo, fullName, new Date());

        er.insert(employee);

        return "Inserted: " + employee.toString();
    }

    @ResponseBody
    @GetMapping("/show-all")
    public String showAllEmployee() {
        List<Employee> employees = er.findAll();

        StringBuilder html = new StringBuilder();
        for (Employee emp : employees) {
            html.append(emp).append("<br>");
        }

        return html.toString();
    }

    @ResponseBody
    @GetMapping("/show")
    public String showFullName() {
        List<Employee> employees = er.findByFullNameLike("Duy");

        StringBuilder html = new StringBuilder();
        for (Employee emp : employees) {
            html.append(emp).append("<br>");
        }

        return html.toString();
    }

    @ResponseBody
    @GetMapping("/update")
    public String updateEmployee() {
        int id = erc.getMaxEmpId();
        Optional<Employee> employee = er.findById(id);
        erc.updateEmployee(employee.get().getEmpNo(), "Anh Duy", new Date());
        Optional<Employee> newEmployee = er.findById(id);
        return newEmployee.get().toString();
    }

    @ResponseBody
    @GetMapping("/delete-all")
    public String deleteAllEmployee() {
        er.deleteAll();

        return "Deleted!";
    }
}
