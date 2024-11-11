package orkaTraks.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import orkaTraks.Entity.EmployeeDetails;

import java.util.Optional;

@Repository
public interface RegRepo extends JpaRepository<EmployeeDetails,Integer> {

    Optional<EmployeeDetails> findByEmpId(Integer empId);

}
