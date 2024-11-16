package orkaTraks.Controller;


import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orkaTraks.Entity.EmpLeave;
import orkaTraks.Entity.LeaveEvent;
import orkaTraks.Entity.Task;
import orkaTraks.Service.LeveService;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController

public class LeaveController {
    @Autowired
    LeveService leaveService;
   // private static final Logger logger = LoggerFactory.getLogger(LeaveController.class); // Use the actual class name

    private static final Logger logger = Logger.getLogger(TaskController.class.getName());

    @PostMapping("/leave")
    public ResponseEntity<List<EmpLeave>> saveRegister(@RequestBody List<EmpLeave> leave) {
        try {
            List<EmpLeave> savedtask = leaveService.saveLeaveData(leave);
            if (savedtask == null || savedtask.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            else {
                // Call sendEmails() if savedtask is not null or empty
                leaveService.sendMail("sathishe.orka@gmail.com", savedtask);
                return ResponseEntity.status(HttpStatus.CREATED).body(savedtask); // Return the saved tasks
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Return 500 on error

        }

    }
    @GetMapping("/api/leaveController/getLeave")
    public ResponseEntity<List<EmpLeave>>getLeaveDetail(){
        List<EmpLeave>getLeave=leaveService.getLeaveDetails();
        return ResponseEntity.ok(getLeave);
    }
    @PutMapping("/api/leaveController/updateLeave")
    public ResponseEntity<List<EmpLeave>> UpdateLeave(@RequestBody List<EmpLeave> updateLeave){
        try {
            List<EmpLeave> updatedTasks = leaveService.updateLeaveStatus(updateLeave); // Use updatedTasks to store the result

            return ResponseEntity.status(HttpStatus.OK).body(updatedTasks); // Return the updated tasks with 200 OK status
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Return 500 on error
        }
    }

    @GetMapping("getLeave/{empId}")
    public ResponseEntity<List<EmpLeave>> getTaskData(@PathVariable String empId) {
        try {
            Integer employeeId = Integer.parseInt(empId); // Convert string to integer
            List<EmpLeave> getleave = leaveService.getLeave(employeeId);

            logger.log(Level.INFO, "Fetching task records for employee ID: {0}", employeeId);
            logger.log(Level.INFO, "Task Records: {0}", getleave);

            return ResponseEntity.ok(getleave); // Return response
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Invalid employee ID format: " + empId, e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("api/getHoliday")
    public ResponseEntity<List<LeaveEvent>> getHoliday() {
        try {
            List<LeaveEvent> getleave = leaveService.holiday();

            logger.log(Level.INFO, "Task Records: {0}", getleave);

            return ResponseEntity.ok(getleave); // Return response
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Invalid employee ID format: " + e);
            return ResponseEntity.badRequest().body(null);
        }
    }

}
