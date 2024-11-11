package orkaTraks.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import orkaTraks.Entity.Task;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task,Integer>{
    List<Task> findByEmpId(Integer empId);

    Integer findById(Task task);

    //List<Task> findByEmpId(Long empId);
}
