package orkaTraks.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import orkaTraks.Entity.EmployeeDetails;
import orkaTraks.Entity.Login;

import java.util.Optional;

@Repository
public interface LoginRepo extends JpaRepository<EmployeeDetails,Integer> {
    //Optional<EmployeeDetails> findByEmpId(Integer empId);
    EmployeeDetails findByEmpId(Integer empId);

    EmployeeDetails findByPassword(String password);
}
