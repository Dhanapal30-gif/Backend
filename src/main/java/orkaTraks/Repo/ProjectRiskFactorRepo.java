package orkaTraks.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import orkaTraks.Entity.ProjecrRiskFactor;
import orkaTraks.Entity.ProjectManagement;

public interface ProjectRiskFactorRepo extends JpaRepository<ProjecrRiskFactor,Integer> {
}
