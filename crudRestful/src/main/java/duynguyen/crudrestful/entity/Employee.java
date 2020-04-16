package duynguyen.crudrestful.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "employees")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int empId;

    @Column(name = "full_name", length = 255, nullable = true)
    private String fullName;

    @Column(name = "position", length = 255, nullable = true)
    private String position;
}
