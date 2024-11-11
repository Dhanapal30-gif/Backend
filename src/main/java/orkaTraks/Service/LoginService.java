package orkaTraks.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import orkaTraks.Entity.EmployeeDetails;
import orkaTraks.Entity.Login;
import orkaTraks.Repo.LoginRepo;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {
    @Autowired
    public LoginRepo loginRepo;


    public Map<String, Object> loginDetail(Login login) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Fetch the employee details using the employee ID
            EmployeeDetails emp = loginRepo.findByEmpId(login.getEmpId());  // Use the appropriate method to find by empId

            if (emp != null) {
                // Check if the password matches
                if (emp.getPassword().equals(login.getPassword())) {
                    // If login is successful, return the employee details
                    response.put("message", "Login successful!");
                    response.put("empId", String.valueOf(emp.getEmpId()));  // empId from EmployeeDetails table
                    response.put("empName", emp.getFirstName() + " " + emp.getLastName());  // Full name
                } else {
                    response.put("message", "Error: Password does not match");
                }
            } else {
                response.put("message", "Error: Employee ID does not match");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", "Error: An error occurred during login. Please try again later.");
        }

        return response;
    }
    }
