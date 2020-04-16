package duynguyen.crudrestful.repository;

import duynguyen.crudrestful.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAll();
    Employee findEmployeeByEmpId(int empId);
    Employee findEmployeeByFullName(String fullName);
    void removeEmployeeByEmpId(int empId);
    void deleteAll();
}
