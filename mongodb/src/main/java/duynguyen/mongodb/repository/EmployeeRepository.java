package duynguyen.mongodb.repository;

import duynguyen.mongodb.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, Integer> {
    Employee findByEmpNo(String Emp_No);
    List<Employee> findByFullNameLike(String Full_Name);
    List<Employee> findByHireDateGreaterThan(Date hireDate);

    @Query("{fullName:'?0'}")
    List<Employee> findCustomByFullName(String Full_Name);
}
