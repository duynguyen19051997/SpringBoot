package duynguyen.mongodb.custom;

import duynguyen.mongodb.model.Employee;

import java.util.Date;

public interface EmployeeRepositoryCustom {
    int getMaxEmpId();

    int updateEmployee(String empNo, String fullName, Date hireDate);
}
