package orkaTraks.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "Account")

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectNo;
    private String projectName;
    private String catagery;
    private String particulars;
    private Long  po_Amount;
    private Long  planedBudjet;
    private Long debit_Amount;
    private Long credit_Amount;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;
    private String exType;
    private String refernceProjectNo;
    private Long amountSpent;
    private Long emi;
    private Long outstandingAmount;
    private Long bankBalance;
}
