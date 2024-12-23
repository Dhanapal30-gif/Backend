package orkaTraks.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class projectDashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    private String projectNo;
    private String projectName;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date proStartDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date proPlanedDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date proActualDate;





}
