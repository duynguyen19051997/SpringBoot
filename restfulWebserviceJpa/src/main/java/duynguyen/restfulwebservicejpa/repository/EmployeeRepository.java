package duynguyen.restfulwebservicejpa.repository;

import duynguyen.restfulwebservicejpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAll();

    Optional<Employee> findByEmpId(int empId);

    void deleteById(int empId);
}
