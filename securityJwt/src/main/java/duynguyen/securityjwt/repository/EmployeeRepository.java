package duynguyen.securityjwt.repository;

import duynguyen.securityjwt.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByEmpId(int empId);

    @Query(value = "SELECT e FROM Employee AS e ORDER BY e.empId DESC")
    Page<Employee> findEmployeesPage(Pageable pageable);
}
