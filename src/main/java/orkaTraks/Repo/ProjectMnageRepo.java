package orkaTraks.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import orkaTraks.Entity.EmpLeave;
import orkaTraks.Entity.ProjectManagement;

import java.util.Optional;

public interface ProjectMnageRepo extends JpaRepository<ProjectManagement, Integer> {
    Optional<ProjectManagement> findByProjectNo(String projectNo);

}
