package duynguyen.restfulwebservicejpa.service;

import duynguyen.restfulwebservicejpa.entity.Employee;
import duynguyen.restfulwebservicejpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Page<Employee> findEmployee(Pageable pageable) {
        return employeeRepository.findEmployees(pageable);
    }

    public ResultService findAll() {
        ResultService result = new ResultService();
        result.setData(employeeRepository.findAll());
        return result;
    }

    public ResultService findById(int id) {
        ResultService result = new ResultService();
        Employee employee = employeeRepository.findByEmpId(id).orElse(null);
        result.setData(employee);
        return result;
    }

    public ResultService update(Employee newEmployee) {
        ResultService result = new ResultService();
        Employee oldEmployee = employeeRepository.findByEmpId(newEmployee.getEmpId()).orElse(null);
        if (oldEmployee == null) {
            result.setStatus(ResultService.Status.FAILED);
            result.setMassage("Id Not Found!");
        } else {
            newEmployee.setCreatedAt(oldEmployee.getCreatedAt());
            result.setMassage("Update Success!");
            result.setData(employeeRepository.save(newEmployee));
        }
        return result;
    }

    public ResultService create(Employee employee) {
        ResultService result = new ResultService();
        result.setData(employeeRepository.save(employee));
        return result;
    }

    public ResultService delete(int id) {
        ResultService result = new ResultService();
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            result.setStatus(ResultService.Status.FAILED);
            result.setMassage("Id not found!");
        } else {
            result.setData(employee);
            employeeRepository.deleteById(id);
            result.setMassage("Delete Success!");
        }
        return result;
    }
}
