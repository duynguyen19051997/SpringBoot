package duynguyen.crudrestful.dao;

import duynguyen.crudrestful.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class EmployeeDAO {
    @Autowired
    private EntityManager entityManager;

    /*
     *  addNewEmployee(Employee employee)
     *  @param {Employee}
     *  @return {Employee}
     * */
    public Employee addNewEmployee(Employee employee) {
        this.entityManager.persist(employee);
        return employee;
    }
}
