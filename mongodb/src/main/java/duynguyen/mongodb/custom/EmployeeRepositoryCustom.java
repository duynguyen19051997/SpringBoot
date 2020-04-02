package duynguyen.mongodb.custom;

import java.util.Date;

public interface EmployeeRepositoryCustom {
    int getMaxEmpId();

    int updateEmployee(String empNo, String fullName, Date hireDate);
}
