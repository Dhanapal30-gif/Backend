package orkaTraks.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ProjectService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    private String projectNo;
    private String projectName;
    private String serviceFactor;
    private String serviceStatus;
}
