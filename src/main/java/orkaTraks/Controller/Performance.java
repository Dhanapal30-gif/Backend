package orkaTraks.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import orkaTraks.Entity.Task;
import orkaTraks.Repo.Performanceser;
import orkaTraks.Service.PerformanceService;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class Performance {
    private static final Logger logger = Logger.getLogger(TaskController.class.getName());

    @Autowired
    private PerformanceService performanceservice;

    @GetMapping("/api/PerController/getWeekly/{empId}")
    public ResponseEntity<List<Task>>getTask(@PathVariable String empId){
        try {
            Integer employeeId = Integer.parseInt(empId); // Convert string to integer
            List<Task> tasks = performanceservice.getWeeklyTasksByEmpId(employeeId);

            logger.log(Level.INFO, "Fetching task records for employee ID: {0}", employeeId);
            logger.log(Level.INFO, "Task Records: {0}", tasks);

            return ResponseEntity.ok(tasks); // Return response
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Invalid employee ID format: " + empId, e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/api/PerController/getMonthly/{empId}")
    public ResponseEntity<Map<YearMonth, List<Task>>> getMonthly(@PathVariable String empId) {
        try {
            Integer employeeId = Integer.parseInt(empId); // Convert string to integer
            Map<YearMonth, List<Task>> tasksByMonth = performanceservice.getAllMonthlyTasksByEmpId(employeeId);

            logger.log(Level.INFO, "Fetching monthly task records for employee ID: {0}", employeeId);
            logger.log(Level.INFO, "Monthly Task Records: {0}", tasksByMonth);

            return ResponseEntity.ok(tasksByMonth); // Return response
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Invalid employee ID format: " + empId, e);
            return ResponseEntity.badRequest().body(null);
        }
    }

}
