package orkaTraks.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private Integer empId;
    private String projectNo;
    private String projectName;
    private String macroTask;
    private String microTask;
    private LocalDate startDate;
    private Date endDate;
    private Date secondCloseDate;
    private String remark;
    private String status;
    private String inCharge;


    public LocalDate getDate() {
        return startDate;
    }

    public void setDate(LocalDate startDate) {
        this.startDate = startDate;
    }



}
