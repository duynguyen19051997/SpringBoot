package duynguyen.mongodb.custom;

import duynguyen.mongodb.model.Employee;

public interface EmployeeRepositoryCustom {
    int getMaxEmpId();

    int updateEmployee(Employee employee);
}
