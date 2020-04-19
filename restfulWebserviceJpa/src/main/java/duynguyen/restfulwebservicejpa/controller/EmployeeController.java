package duynguyen.restfulwebservicejpa.controller;

import duynguyen.restfulwebservicejpa.entity.Employee;
import duynguyen.restfulwebservicejpa.service.EmployeeService;
import duynguyen.restfulwebservicejpa.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping(value = {"/api/employee"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public ResponseEntity<ResultService> findAll() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultService> findOneById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ResultService> create(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.create(employee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResultService> delete(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(employeeService.delete(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultService> update(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        try {
            employee.setEmpId(id);
            return new ResponseEntity<>(employeeService.update(employee), HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
