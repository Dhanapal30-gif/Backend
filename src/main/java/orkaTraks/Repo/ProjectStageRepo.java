package orkaTraks.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import orkaTraks.Entity.ProjectManagement;
import orkaTraks.Entity.ProjectStage;

public interface ProjectStageRepo extends JpaRepository<ProjectStage,Integer> {
}
