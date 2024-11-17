package orkaTraks.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import orkaTraks.Entity.TaskManagement;

import java.util.List;

public interface TaskManagementRepo extends JpaRepository<TaskManagement, Integer> {
    TaskManagement findByMicroTask(String microTask);

    List<TaskManagement> findByProjectNo(String projectNo);  // Corrected method name
}
