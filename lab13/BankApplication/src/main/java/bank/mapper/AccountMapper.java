package bank.mapper;

import bank.domain.Account;
import bank.domain.Customer;
import bank.dto.AccountDto;
import bank.dto.AccountEntryDto;
import bank.dto.CustomerDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kush
 */
public class AccountMapper {
    public static AccountDto accountToAccountDto(Account account){
        AccountDto ac= new AccountDto(account.getAccountNumber());
        ac.setCustomer(new CustomerDto(account.getCustomer().getName()));
        account.getEntryList().forEach(x->
                ac.addAccountEntry(new AccountEntryDto(x.getDate(),x.getAmount(),x.getDescription(),
                        x.getFromAccountNumber(),x.getFromPersonName())));
        return ac;
    }

    public static List<AccountDto> accountToAccountDto(List<Account> accounts){
        List<AccountDto> accountDtos=new ArrayList<>();
        accounts.forEach(x->accountDtos.add(accountToAccountDto(x)));
        return accountDtos;
    }

}
