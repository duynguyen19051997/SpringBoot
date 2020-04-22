package duynguyen.securityjwt.service;

import duynguyen.securityjwt.constant.MessageConstant;
import duynguyen.securityjwt.entity.Employee;
import duynguyen.securityjwt.model.Result;
import duynguyen.securityjwt.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Result findAll() {
        Result result = new Result();
        result.setData(employeeRepository.findAll());
        return result;
    }

    public Result findAllPage(Pageable pageable) {
        Result result = new Result();
        result.setData(employeeRepository.findEmployeesPage(pageable));
        return result;
    }


    public Result findOne(int empId) {
        Result result = new Result();
        Optional<Employee> employee = employeeRepository.findByEmpId(empId);
        if (!employee.isPresent()) {
            result.setStatus(Result.Status.FAILED);
            result.setMassage(MessageConstant.ID_ERROR);
        } else {
            result.setData(employee.get());
        }
        return result;
    }

    public Result deleteOne(int empId) {
        Result result = new Result();
        Optional<Employee> employee = employeeRepository.findByEmpId(empId);
        if (employee.isPresent()) {
            employeeRepository.deleteById(empId);
            result.setMassage(MessageConstant.DELETE_SUCCESS);
            result.setData(employee.get());
        } else {
            result.setStatus(Result.Status.FAILED);
            result.setMassage(MessageConstant.DELETE_FAIL);
        }
        return result;
    }

    public Result saveOne(Employee employee) {
        Result result = new Result();
        result.setData(employeeRepository.save(employee));
        return result;
    }

    public Result updateOne(Employee employee) {
        Result result = new Result();
        Optional<Employee> oldEmployee = employeeRepository.findByEmpId(employee.getEmpId());
        if (oldEmployee.isPresent()) {
            employee.setCreatedAt(oldEmployee.get().getCreatedAt());
            result.setMassage(MessageConstant.UPDATE_SUCCESS);
            result.setData(employeeRepository.save(employee));
        } else {
            result.setStatus(Result.Status.FAILED);
            result.setMassage(MessageConstant.UPDATE_FAIL);
        }
        return result;
    }
}
