package duynguyen.securityjwt.controller;


import duynguyen.securityjwt.constant.MessageConstant;
import duynguyen.securityjwt.entity.Employee;
import duynguyen.securityjwt.model.Result;
import duynguyen.securityjwt.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping(value = "/api/v1/employee", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public ResponseEntity<Result> findAll() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<Result> findALlPage(@PathVariable(value = "page", required = false) Integer page) {
        Pageable pageable = PageRequest.of(page, MessageConstant.SIZE_PAGE);
        return new ResponseEntity<>(employeeService.findAllPage(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findOne(@PathVariable(value = "id", required = false) Integer id) {
        return new ResponseEntity<>(employeeService.findOne(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Result> create(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.saveOne(employee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteOne(@PathVariable(value = "id", required = false) Integer id) {
        return new ResponseEntity<>(employeeService.deleteOne(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> updateOne(@PathVariable(value = "id", required = false) Integer id,
                                            @RequestBody Employee employee) {
        employee.setEmpId(id);
        return new ResponseEntity<>(employeeService.updateOne(employee), HttpStatus.OK);
    }

}
