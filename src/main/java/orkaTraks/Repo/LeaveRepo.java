package orkaTraks.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import orkaTraks.Entity.EmpLeave;

import java.util.List;

public interface LeaveRepo extends JpaRepository<EmpLeave, Integer> {
    List<EmpLeave> findByEmpId(Integer empId);
}
