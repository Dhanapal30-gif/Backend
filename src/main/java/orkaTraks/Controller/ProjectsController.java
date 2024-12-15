package orkaTraks.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import orkaTraks.Entity.*;
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

    @PostMapping("/api/projectController/projectdetailupload")
    public ResponseEntity<List<projectDashboard>> saveAccountDetail(@RequestBody List<projectDashboard> projects) {
        try {
            List<projectDashboard> savedProject = projectsService.saveProjects(projects);
            if (savedProject == null || savedProject.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProject); // Return the saved tasks
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Return 500 on error

        }

    }
    @PostMapping("/api/projectController/projectStageupload")
    public ResponseEntity<List<ProjectStage>> saveAccountStage(@RequestBody List<ProjectStage> projects) {
        try {
            List<ProjectStage> savedProject = projectsService.saveStage(projects);
            if (savedProject == null || savedProject.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProject); // Return the saved tasks
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Return 500 on error

        }

    }
    @PostMapping("/api/projectController/projectRiskFactorupload")
    public ResponseEntity<List<ProjecrRiskFactor>> saveAccountRiskFactor(@RequestBody List<ProjecrRiskFactor> projects) {
        try {
            List<ProjecrRiskFactor> savedProject = projectsService.saveRiskFactor(projects);
            if (savedProject == null || savedProject.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProject); // Return the saved tasks
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Return 500 on error

        }

    }
    @PostMapping("/api/projectController/projectServiceupload")
    public ResponseEntity<List<ProjectService>> saveAccountService(@RequestBody List<ProjectService> projects) {
        try {
            List<ProjectService> savedProject = projectsService.saveService(projects);
            if (savedProject == null || savedProject.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProject); // Return the saved tasks
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Return 500 on error

        }

    }
    @GetMapping("/api/projectController/getallProjectsdeatil")
    public ResponseEntity<List<projectDashboard>> getProjectDashboard(){
        List <projectDashboard> getDashboard=projectsService.getProjectDashboardDetail();

        if (getDashboard.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(getDashboard, HttpStatus.OK);
    }

    @GetMapping("/api/projectController/getallProjectsStage")
    public ResponseEntity<List<ProjectStage>> getProjectStage(){
        List <ProjectStage> getDashboard=projectsService.getProjectStageDetail();

        if (getDashboard.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(getDashboard, HttpStatus.OK);
    }

    @GetMapping("/api/projectController/getallProjectsRiskFactor")
    public ResponseEntity<List<ProjecrRiskFactor>> getProjectRiskFactor(){
        List <ProjecrRiskFactor> getDashboard=projectsService.getProjectRiskFactorDetail();

        if (getDashboard.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(getDashboard, HttpStatus.OK);
    }

    @GetMapping("/api/projectController/getallProjectsService")
    public ResponseEntity<List<ProjectService>> getProjectService(){
        List <ProjectService> getDashboard=projectsService.getProjectServiceDetail();

        if (getDashboard.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(getDashboard, HttpStatus.OK);
    }

    @PutMapping("/api/projectsContoller/update")
    public ResponseEntity<List<projectDashboard>> updateTask(@RequestBody List<projectDashboard> projectDeatil) {
        try {
            List<projectDashboard> updatedProjectDetial = projectsService.updateProjectDetail(projectDeatil); // Use updatedTasks to store the result

            return ResponseEntity.status(HttpStatus.OK).body(updatedProjectDetial); // Return the updated tasks with 200 OK status
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Return 500 on error
        }
    }


}

