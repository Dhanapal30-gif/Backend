package orkaTraks.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import orkaTraks.Entity.ChangePassword;
import orkaTraks.Entity.EmployeeDetails;
import orkaTraks.Repo.RegRepo;

import java.util.List;
import java.util.Optional;

@Service
public class RegService {

    @Autowired
    public RegRepo regRepo;


    public String regDetail(EmployeeDetails regDetail) {
        try {
            Optional<EmployeeDetails> existingEmployee = regRepo.findByEmpId(regDetail.getEmpId());

            if (existingEmployee.isPresent()) {
                return "Error: Employee ID already exists. Registration failed.";
            }

            regRepo.save(regDetail);
            return "Registration successful!";
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return "Error: Something went wrong during registration.";
        }
    }


    public String changePassword(ChangePassword changePass) {
        // Find the employee by empId
        EmployeeDetails existingEmployee = regRepo.findByEmpId(changePass.getEmpId())
                .orElse(null);

        // If the employee doesn't exist, return an error
        if (existingEmployee == null) {
            return "Error: Employee not found";
        }

        // Directly set the new password without encryption
        //existingEmployee.setPassword(employeeDetails.getPassword());
         else if(existingEmployee.getPassword().equals(changePass.getOldPassword())){
             existingEmployee.setPassword(changePass.getNewPassword());
            regRepo.save(existingEmployee);
            return "Password changed successfully";
        }

        // Save the updated employee details
        regRepo.save(existingEmployee);

        // Return success message
        return "ERROR: Password do not matched";
    }

    public List<EmployeeDetails> getAllData() {
        return regRepo.findAll();
    }
}
