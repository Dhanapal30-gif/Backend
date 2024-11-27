package orkaTraks.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orkaTraks.Entity.Account;
import orkaTraks.Entity.ProjectManagement;
import orkaTraks.Entity.Task;
import orkaTraks.Service.Accounts_servie;

import java.util.Collections;
import java.util.List;

@RestController

public class Accounts {
    @Autowired
    private Accounts_servie accoutsService;

    @PostMapping("/api/accountsController/accountManagement")
    public ResponseEntity<List<Account>> saveAccountDetail(@RequestBody List<Account> accounts) {
        try {
            List<Account> savedtask = accoutsService.saveTask(accounts);
            if (savedtask == null || savedtask.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(savedtask); // Return the saved tasks
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Return 500 on error

        }

    }


    @GetMapping("/api/Account/getallAccountdeatil")
    public ResponseEntity<List<Account>> getProject(){
        List<Account> project = accoutsService.getAccount();
        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/api/Account/deleteData_Api")
    public ResponseEntity<String> deletedata(){
        accoutsService.deleteaccountdata();
        return ResponseEntity.ok("Train deleted successfully.");

    }
}
