package orkaTraks.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import orkaTraks.Entity.EmployeeDetails;
import orkaTraks.Entity.ProjectManagement;
import orkaTraks.Service.ProjectsService;

import java.util.List;

@RestController
public class ProjectsController {
    @Autowired
    private ProjectsService projectsService;
    @GetMapping("/api/projectController/getProject")
    public ResponseEntity<List<ProjectManagement>>getProject(){
        List<ProjectManagement>getProject=projectsService.getProject();
        return ResponseEntity.ok(getProject);
    }

    @GetMapping("/api/projectController/getProjectDetail")
    public ResponseEntity<List<ProjectManagement>> getProjectDeatil(){
        List <ProjectManagement> getEmployee=projectsService.getProjectDetail();

        if (getEmployee.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(getEmployee, HttpStatus.OK);
    }
}
