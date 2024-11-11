package orkaTraks.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import orkaTraks.Entity.ProjectManagement;
import orkaTraks.Entity.TaskManagement;
import orkaTraks.Repo.ProjectMnageRepo;
import orkaTraks.Repo.TaskManagementRepo;

import java.util.*;

@Service
public class ProjectMangeService {
    @Autowired
    private ProjectMnageRepo projectMangeRepo;
    @Autowired
    private TaskManagementRepo taskManagementRepo;

    public ResponseEntity<?> saveProject(List<ProjectManagement> projectList) {
        try {
            for (ProjectManagement project : projectList) {
                Optional<ProjectManagement> existingProject = projectMangeRepo.findByProjectNo(project.getProjectNo());

                if (existingProject.isPresent()) {
                    // Return "already added" message if project exists
                    return new ResponseEntity<>("Already added", HttpStatus.CONFLICT);
                }
            }

            // Save the new projects
            projectMangeRepo.saveAll(projectList);

            // Return success message if saved
            return new ResponseEntity<>("Successfully added", HttpStatus.CREATED); // Use 201 for created

        } catch (Exception e) {
            e.printStackTrace();
            // Return error message in case of any exception
            return new ResponseEntity<>("Error occurred while saving", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public List<ProjectManagement> getProject() {
        return projectMangeRepo.findAll();
    }

    public List<ProjectManagement> updateProject(List<ProjectManagement> project) {
        if (project.isEmpty()) {
            return Collections.emptyList();
        }
        Integer excistPId = project.get(0).getPId();
        Optional<ProjectManagement> excitProject = projectMangeRepo.findById(excistPId);
        if (excitProject.isPresent()) {
            return projectMangeRepo.saveAll(project);
        } else {
            return Collections.emptyList();
        }
    }

    public ResponseEntity<String> saveTask(List<TaskManagement> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            return ResponseEntity.badRequest().body("Task list cannot be null or empty.");
        }

            List savedTasks = taskManagementRepo.saveAll(tasks);
        return new ResponseEntity<>("Successfully added", HttpStatus.OK);
    }

    public List<TaskManagement> getTask() {
        return taskManagementRepo.findAll();
    }

    public Map<String, Map<String, List<String>>> getTaskManagement() {
        List<TaskManagement> allTasks = taskManagementRepo.findAll();

        // Map to group tasks by projectNo, with each project having a sub-map of task details
        Map<String, Map<String, List<String>>> projectTaskMap = new LinkedHashMap<>();

        for (TaskManagement task : allTasks) {
            String projectNo = task.getProjectNo();
            String projectName = task.getProjectName();
            String macroTask = task.getMacroTask();
            String microTask = task.getMicroTask();
            String status = task.getStatus();

            // Initialize project entry if not already present
            projectTaskMap.putIfAbsent(projectNo, new LinkedHashMap<>());
            Map<String, List<String>> taskDetails = projectTaskMap.get(projectNo);

            // Populate task details
            taskDetails.putIfAbsent("projectName", new ArrayList<>());
            taskDetails.putIfAbsent("macroTask", new ArrayList<>());
            taskDetails.putIfAbsent("microTask", new ArrayList<>());
            taskDetails.putIfAbsent("status", new ArrayList<>());


            taskDetails.get("projectName").add(projectName);
            taskDetails.get("macroTask").add(macroTask);
            taskDetails.get("microTask").add(microTask);
            taskDetails.get("status").add(status);

        }

        return projectTaskMap;
    }
    public String saveProjectr(List<ProjectManagement> projectList) {
        StringBuilder resultMessage = new StringBuilder();

        for (ProjectManagement project : projectList) {
            Optional<ProjectManagement> existingProject = projectMangeRepo.findByProjectNo(project.getProjectNo());

            if (existingProject.isPresent()) {
                resultMessage.append("Already added: ").append(project.getProjectNo()).append(", ");
            } else {
                // Save the project
                projectMangeRepo.save(project);
                resultMessage.append("Successfully added: ").append(project.getProjectNo()).append(", ");
            }
        }

        return resultMessage.length() > 0 ? resultMessage.toString() : null; // return null if nothing was processed
    }



}