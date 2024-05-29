package bank.service;

import bank.domain.Account;

import java.util.Collection;

public interface AccountService {

    public Account createAccount(long accountNumber, String customerName);

    public Account getAccount(long accountNumber);

    public Collection<Account> getAllAccounts();

    public void deposit (long accountNumber, double amount);

    public void withdraw (long accountNumber, double amount);

    public void depositEuros (long accountNumber, double amount);

    public void withdrawEuros (long accountNumber, double amount);

    public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description);

}
