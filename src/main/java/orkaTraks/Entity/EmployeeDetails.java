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
    private Integer phoneNumber;
    private String email;
    private String password;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

}
