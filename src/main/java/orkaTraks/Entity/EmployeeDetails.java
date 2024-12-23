package orkaTraks.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Data
@Table(name = "EmployeeDetails")
@Entity
public class EmployeeDetails {
    private Integer empId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Long phoneNumber;
    private String email;
    private String password;
    private String userRole;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

}
