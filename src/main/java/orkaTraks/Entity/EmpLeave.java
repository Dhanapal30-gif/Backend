package orkaTraks.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class EmpLeave {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer leaveId;
    private String empName;
    private Integer empId;
    private String leaveType;
    private Date  startDate;
    private Date endDate ;
    private String reason;
    private String fHDay;
    private String status;



}
