package bank.repository;

import bank.domain.Account;

import java.util.Collection;


public interface AccountRepository {

	public void saveAccount(Account account);

	public void updateAccount(Account account);

	public Account loadAccount(long accountNumber);

	public Collection<Account> getAccounts();

}
