package orkaTraks.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ChangePassword {

    @Id
    private Integer id;
    private Integer empId;
    private String oldPassword;
    private String newPassword;




}
