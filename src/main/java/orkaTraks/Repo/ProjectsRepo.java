package orkaTraks.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import orkaTraks.Entity.EmployeeDetails;
import orkaTraks.Entity.ProjectManagement;

public interface ProjectsRepo extends JpaRepository<ProjectManagement,Integer> {
}
