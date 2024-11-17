package orkaTraks.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orkaTraks.Entity.EmpLeave;
import orkaTraks.Entity.EmployeeDetails;
import orkaTraks.Entity.Task;
import orkaTraks.Service.TaskService;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.List;

@RestController
public class TaskController {
    private static final Logger logger = Logger.getLogger(TaskController.class.getName());

    @Autowired
    TaskService taskService;

    @PostMapping("/save_Task")
    public ResponseEntity<List<Task>> saveRegister(@RequestBody List<Task> tasks) {
        try {
            List<Task> savedtask = taskService.saveTask(tasks);
            if (savedtask == null || savedtask.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(savedtask); // Return the saved tasks
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Return 500 on error

        }

    }


    @GetMapping("/getTask/{empId}") // Make sure this matches the URL format
    public ResponseEntity<List<Task>> getTaskData(@PathVariable String empId) {
        try {
            Integer employeeId = Integer.parseInt(empId); // Convert string to integer
            List<Task> tasks = taskService.getTasksByEmpId(employeeId);

            logger.log(Level.INFO, "Fetching task records for employee ID: {0}", employeeId);
            logger.log(Level.INFO, "Task Records: {0}", tasks);

            return ResponseEntity.ok(tasks); // Return response
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Invalid employee ID format: " + empId, e);
            return ResponseEntity.badRequest().body(null);
        }
    }
    @PutMapping("/Update")
    public ResponseEntity<List<Task>> updateTask(@RequestBody List<Task> task) {
        try {
            List<Task> updatedTasks = taskService.updateTask(task); // Use updatedTasks to store the result

            return ResponseEntity.status(HttpStatus.OK).body(updatedTasks); // Return the updated tasks with 200 OK status
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Return 500 on error
        }
    }

    @GetMapping("/api/taskContoller/getAllTask")
    public ResponseEntity<List<Task>>getTaskDetail(@RequestParam String empId){
        try {
            Integer employeeId = Integer.parseInt(empId); // Convert string to integer
            List<Task> tasks = taskService.getTasksByEmpId(employeeId);

            logger.log(Level.INFO, "Fetching task records for employee ID: {0}", employeeId);
            logger.log(Level.INFO, "Task Records: {0}", tasks);

            return ResponseEntity.ok(tasks); // Return response
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Invalid employee ID format: " + empId, e);
            return ResponseEntity.badRequest().body(null);
        }

    }


}
