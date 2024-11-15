package orkaTraks.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import orkaTraks.Entity.ProjectManagement;
import orkaTraks.Service.ProjectsService;

import java.util.List;

@RestController
@CrossOrigin(origins="https://orkatracks.onrender.com")
public class ProjectsController {
    @Autowired
    private ProjectsService projectsService;
    @GetMapping("/api/projectController/getProject")
    public ResponseEntity<List<ProjectManagement>>getProject(){
        List<ProjectManagement>getProject=projectsService.getProject();
        return ResponseEntity.ok(getProject);
    }
}
