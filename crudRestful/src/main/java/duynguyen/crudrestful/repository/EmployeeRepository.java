package duynguyen.crudrestful.repository;

import duynguyen.crudrestful.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
