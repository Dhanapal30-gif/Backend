package orkaTraks.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import orkaTraks.Entity.ProjectManagement;
import orkaTraks.Entity.TaskManagement;
import orkaTraks.Repo.ProjectsRepo;
import orkaTraks.Repo.TaskManagementRepo;
import java.util.stream.Collectors;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectsService {
    @Autowired
    private ProjectsRepo projectsRepo;

    @Autowired
    private TaskManagementRepo taskManagementRepo;
    public List<ProjectManagement> getProject() {
//        List<ProjectManagement> ongoingProjects = new ArrayList<>();
//        List<ProjectManagement> projects = projectsRepo.findAll();
//        for (ProjectManagement itProject : projects) {
//            if ("ongoing".equals(itProject.getStatus())) {
//                ongoingProjects.add(itProject);
//            }
//        }
//        return ongoingProjects;
        updateProjectStatus();
        return projectsRepo.findAll();
    }
    public void updateProjectStatus() {
        List<TaskManagement> getTaskStatus = taskManagementRepo.findAll();
        List<ProjectManagement> projects = projectsRepo.findAll();

        // Iterate through each project to update its status
        for (ProjectManagement project : projects) {
            // Get the tasks related to the project based on projectNo
            List<TaskManagement> projectTasks = getTaskStatus.stream()
                    .filter(task -> task.getProjectNo().equals(project.getProjectNo())) // Match by projectNo
                    .collect(Collectors.toList());

            // Check the statuses of the tasks
            boolean hasOngoing = projectTasks.stream().anyMatch(task -> task.getStatus().equalsIgnoreCase("ongoing"));
            boolean allCompleted = projectTasks.stream().allMatch(task -> task.getStatus().equalsIgnoreCase("completed"));
            boolean hasNotStarted = projectTasks.stream().anyMatch(task -> task.getStatus().equalsIgnoreCase("not started"));

            // Update project status based on task statuses
            if (projectTasks.isEmpty()) {
                // No tasks exist for this project; keep the current status or set a default
                // You can choose to set a default status or leave it unchanged
                project.setStatus("not started"); // Example: set to "not started" if no tasks exist
            } else if (hasOngoing) {
                project.setStatus("ongoing");
            } else if (allCompleted) {
                project.setStatus("completed");
            } else if (hasNotStarted) {
                project.setStatus("not started");
            } else {
                // If there are tasks but none match the above statuses
                project.setStatus("not started"); // Set to a default status
            }

            // Save the updated project status
            projectsRepo.save(project);
        }
    }




}
