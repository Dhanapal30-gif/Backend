package orkaTraks.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orkaTraks.Entity.LeaveEvent;
import orkaTraks.Entity.ProjectManagement;
import orkaTraks.Entity.TaskManagement;
import orkaTraks.Service.LeaveEven;
import orkaTraks.Service.ProjectMangeService;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class MasterController {
    @Autowired
    private ProjectMangeService projectMangeService;

  @Autowired
    LeaveEven leaveEven;

    @PostMapping("/saveProject")
//    public ResponseEntity<String>saveProject(@RequestBody List<ProjectManagement> Project){
//        try{
//            String savePro = projectMangeService.saveProject(Project).toString();
//            if(savePro==null || savePro.isEmpty()){
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//            } else if (savePro.contains("Already added")) { // Assuming this is what your service returns on duplicates
//                return ResponseEntity.status(HttpStatus.CONFLICT).body(savePro);
//            } else{
//                return ResponseEntity.status(HttpStatus.CREATED).body(savePro);
//            }
//        }
//        catch (Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
    public ResponseEntity<String> saveProject(@RequestBody List<ProjectManagement> projectList) {
        try {
            String savePro = projectMangeService.saveProjectr(projectList);

            if (savePro == null || savePro.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No data provided for saving.");
            } else if (savePro.contains("Already added")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(savePro);
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body(savePro);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while saving the project.");
        }
    }

    @GetMapping("/api/getProject")
    public ResponseEntity<List<ProjectManagement>> getProject(){
        List<ProjectManagement> project = projectMangeService.getProject();
        return ResponseEntity.ok(project);
    }
    @PutMapping("/update")
    public ResponseEntity<List<ProjectManagement>> updateProject(@RequestBody List<ProjectManagement> project ){
        try{
            List<ProjectManagement> update=projectMangeService.updateProject(project);
            return ResponseEntity.status(HttpStatus.OK).body(update);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @PostMapping("/api/saveTaskManagement")
    public ResponseEntity<String>saveTaskManagement(@RequestBody List<TaskManagement> task){
        try{
            String savePro = projectMangeService.saveTask(task).toString();
            if(savePro==null || savePro.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            else{
                return ResponseEntity.status(HttpStatus.CREATED).body(savePro);
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/api/getTask")
    public ResponseEntity<List<TaskManagement>>getTask(){
        List<TaskManagement>getTask=projectMangeService.getTask();
        return ResponseEntity.ok(getTask);

    }

//    public ResponseEntity<List<TaskManagement>>getTaskManagement(){
//        List<TaskManagement>getTask=projectMangeService.getTaskManagement();
//        return ResponseEntity.ok(getTask);
//
//    }
@GetMapping("/api/getTaskManagemet")
    public ResponseEntity<Map<String, Map<String, List<String>>>> getTaskManagement() {
        Map<String, Map<String, List<String>>> taskMap = projectMangeService.getTaskManagement();
        return ResponseEntity.ok(taskMap);
    }

   @PostMapping("/api/leaveManage/saveHoliday")
   public ResponseEntity<List<LeaveEvent>>saveLeave(@RequestBody List<LeaveEvent> leaveEvent){
        try{
            List<LeaveEvent> leaveEventList=leaveEven.saveLeaveDetail(leaveEvent);
            return ResponseEntity.status(HttpStatus.CREATED).body(leaveEventList); // Return the saved tasks
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Return 500 on error

        }
   }

}
