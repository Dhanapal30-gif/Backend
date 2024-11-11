package orkaTraks.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import orkaTraks.Entity.TaskManagement;

public interface TaskManagementRepo extends JpaRepository<TaskManagement, Integer> {
    TaskManagement findByMicroTask(String microTask);
}
