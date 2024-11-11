package orkaTraks.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import orkaTraks.Entity.EmpLeave;
import orkaTraks.Entity.LeaveEvent;

@Repository
public interface LeaveEventRepo  extends JpaRepository<LeaveEvent, Integer> {
}
