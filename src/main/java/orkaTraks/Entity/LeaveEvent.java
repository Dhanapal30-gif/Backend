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
public class LeaveEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    private LocalDate holiDay;
    private String holiDayName;

}
