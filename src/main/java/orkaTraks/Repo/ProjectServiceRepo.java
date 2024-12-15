package orkaTraks.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import orkaTraks.Entity.ProjectManagement;
import orkaTraks.Entity.ProjectService;

public interface ProjectServiceRepo extends JpaRepository<ProjectService,Integer> {
}
