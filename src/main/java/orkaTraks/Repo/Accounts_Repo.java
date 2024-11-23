package orkaTraks.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import orkaTraks.Entity.Account;

import java.util.List;


@Repository
public interface Accounts_Repo extends JpaRepository<Account,Integer> {

    List<Account> findAll();
}
