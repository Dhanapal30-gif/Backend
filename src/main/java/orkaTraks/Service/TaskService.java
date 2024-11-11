package orkaTraks.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import orkaTraks.Entity.EmployeeDetails;
import orkaTraks.Entity.Task;
import orkaTraks.Entity.TaskManagement;
import orkaTraks.Repo.TaskManagementRepo;
import orkaTraks.Repo.TaskRepo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service

public class TaskService {


    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private TaskManagementRepo taskManagementRepo;

    public List<Task> saveTask(List<Task> tasks) {
        return taskRepo.saveAll(tasks); // Use the repository to save the task
        //return taskRepo.saveAll(tasks);
    }


    public List<Task> getTasksByEmpId(Integer empId) {
        // Call the repository method to find tasks based on empId and id
        return taskRepo.findByEmpId(empId);
    }

    public List<Task> updateTask(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return Collections.emptyList(); // Return an empty list if no tasks are provided
        }

        // Assuming microTask is a field within Task, we get it from the first task in the list
        String microTask = tasks.get(0).getMicroTask(); // Adjust getMicroTask() as necessary

        TaskManagement getMicroTask = taskManagementRepo.findByMicroTask(microTask);

        Integer existingID = tasks.get(0).getId();
        System.out.println("existingID: " + existingID);

        Optional<Task> existingTask = taskRepo.findById(existingID); // Retrieve the existing task

        // Check if the existing task is present
        if (existingTask.isPresent()) {
            // Check if the microTask matches
            if (tasks.get(0).getMicroTask().equals(getMicroTask.getMicroTask())) {
                // Assuming you need to update status, use getStatus() or another method as appropriate
                getMicroTask.setStatus(tasks.get(0).getStatus());
                taskManagementRepo.save(getMicroTask);
            }
            return taskRepo.saveAll(tasks); // Save all tasks if the existing task is found
        } else {
            return Collections.emptyList(); // Return an empty list if the task does not exist
        }
    }



}
