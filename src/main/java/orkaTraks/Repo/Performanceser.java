package orkaTraks.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import orkaTraks.Entity.EmployeeDetails;
import orkaTraks.Entity.Task;

import java.util.List;

@Repository
public interface Performanceser extends JpaRepository<Task,Integer> {
    List<Task> findByEmpId(Integer empId);

}
