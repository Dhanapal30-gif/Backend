package orkaTraks.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import orkaTraks.Entity.projectDashboard;

public interface ProjectsDaRepo  extends JpaRepository<projectDashboard,Integer> {
}
