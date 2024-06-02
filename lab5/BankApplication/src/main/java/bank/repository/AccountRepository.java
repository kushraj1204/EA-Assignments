package bank.repository;

import bank.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account,Long> {

    public Optional<Account> findByAccountNumber(long accountNumber);

/*	public void saveAccount(Account account);

	public void updateAccount(Account account);

	public Account loadAccount(long accountNumber);

	public Collection<Account> getAccounts();*/

}
