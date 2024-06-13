package accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import accounts.domain.Account;

import java.util.List;
import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, String>{
   Account findByAccountHolder(String accountHolder);
   Optional<Account> findByAccountNumber(String accountNumber);
   List<Account> findAllByBalanceGreaterThan(double balance);

}
