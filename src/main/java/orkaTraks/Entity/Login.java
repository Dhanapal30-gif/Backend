package orkaTraks.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Login {

    private Integer empId;
    private String password;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
}
