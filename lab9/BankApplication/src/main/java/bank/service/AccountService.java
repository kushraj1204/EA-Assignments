package bank.service;

import bank.dto.AccountDto;
import bank.dto.request.AccountCreateRequest;
import bank.dto.request.DepositRequest;
import bank.dto.request.FundTransferRequest;
import bank.dto.request.WithdrawRequest;

import java.util.Collection;

public interface AccountService {

    public AccountDto createAccount(AccountCreateRequest accountCreateRequest);

    public AccountDto getAccount(long accountNumber);

    public Collection<AccountDto> getAllAccounts();

    public void depositMoney (DepositRequest request);

    public void deposit (long accountNumber, double amount);

    public void depositEuros (long accountNumber, double amount);

    void withdrawMoney(WithdrawRequest request);

    public void withdraw (long accountNumber, double amount);

    public void withdrawEuros (long accountNumber, double amount);

    void transferFunds(FundTransferRequest request);
}
