package orkaTraks.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orkaTraks.Entity.ChangePassword;
import orkaTraks.Entity.EmployeeDetails;
import orkaTraks.Entity.Login;
import orkaTraks.Service.LoginService;
import orkaTraks.Service.RegService;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins="https://orkatracks.onrender.com")
public class AuthController {

    @Autowired
    public RegService regService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/reg_Save")
    public ResponseEntity<String> saveRegister(@RequestBody EmployeeDetails regDetail) {
        String result = regService.regDetail(regDetail);
        System.out.println("jneiuruib"+regDetail.getID());
        if (result.startsWith("Error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Login login) {
        System.out.println("Login//// empId: " + login.getEmpId() + "   password: " + login.getPassword());

        Map<String, Object> result = loginService.loginDetail(login);  // Get response as a map

        // Check if there's an error in the result (for example, if the message contains "Error")
        if (result.get("message").toString().startsWith("Error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);  // Return error response
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);  // Return success response
    }

    @PutMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePassword changePass) {
        // Debugging to ensure the received data is correct
        System.out.println("changePassword////////////////// empId: " + changePass.getEmpId() + ", password: " + changePass.getOldPassword());

        // Call the service method to change the password
        String result = regService.changePassword(changePass);

        // If the service returns an error, return BAD_REQUEST
        if (result.startsWith("Error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        // Return CREATED status for successful password change
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/getAllData")
    public ResponseEntity<List<EmployeeDetails>> getAllData() {
        List<EmployeeDetails> employees = regService.getAllData(); // Assuming empDeatail is your service or repository
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


}
