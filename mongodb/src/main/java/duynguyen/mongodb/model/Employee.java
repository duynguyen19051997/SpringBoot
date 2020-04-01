package duynguyen.mongodb.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "Employees")
public class Employee {
    @Id
    private int id;

    @Indexed(unique = true)
    @Field(value = "Emp_No")
    private String empNo;

    @Field(value = "Full_Name")
    private String fullName;

    @Field(value = "Hire_Date")
    private Date hireDate;
}
