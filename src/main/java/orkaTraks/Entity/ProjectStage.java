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
public class ProjectStage {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    private String projectNo;
    private String projectName;
    private String stage;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date stageStartDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date stageEndDate;
    private String stageStatus;
}
