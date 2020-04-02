package duynguyen.mongodb.custom;

import com.mongodb.client.result.UpdateResult;
import duynguyen.mongodb.model.Employee;
import duynguyen.mongodb.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private EmployeeRepository er;

    @Override
    public int getMaxEmpId() {
        List<Employee> list = mongoTemplate.findAll(Employee.class);
        if (list.size() > 0) {
            return list.get(list.size() - 1).getId();
        }
        return 0;
    }

    @Override
    public int updateEmployee(Employee employee) {
        Query query = new Query(Criteria.where("_id").is(employee.getId()));
        Update update = new Update();
        update.set("fullName", employee.getFullName());
        update.set("empNo", employee.getEmpNo());
        update.set("hireDate", employee.getHireDate());

        UpdateResult result = this.mongoTemplate.updateFirst(query, update, Employee.class);

        return (int) result.getModifiedCount();

    }
}
