package orkaTraks.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import orkaTraks.Entity.Account;
import orkaTraks.Entity.Task;
import orkaTraks.Repo.Accounts_Repo;

import java.util.List;

@Service
public class Accounts_servie {
    @Autowired
    private Accounts_Repo accountsRepo;


    public List<Account> saveTask(List<Account> accounts) {
        return accountsRepo.saveAll(accounts);


    }



    public List<Account> getAccount() {
        return accountsRepo.findAll();
    }

    public void deleteaccountdata() {
        accountsRepo.deleteAll();
    }
}
