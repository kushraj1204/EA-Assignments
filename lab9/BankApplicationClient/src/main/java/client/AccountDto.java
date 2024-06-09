package client;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author kush
 */
public class AccountDto {
    private long accountNumber;

    private Collection<AccountEntryDto> entryList = new ArrayList<AccountEntryDto>();

    private CustomerDto customer;

    public AccountDto(long accountNumber, Collection<AccountEntryDto> entryList, CustomerDto customer) {
        this.accountNumber = accountNumber;
        this.entryList = entryList;
        this.customer = customer;
    }

    public AccountDto(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void addAccountEntry(AccountEntryDto ac){
        this.entryList.add(ac);

    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Collection<AccountEntryDto> getEntryList() {
        return entryList;
    }

    public void setEntryList(Collection<AccountEntryDto> entryList) {
        this.entryList = entryList;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public double getBalance() {
        double balance=0;
        for (AccountEntryDto entry : entryList) {
            balance+=entry.getAmount();
        }
        return balance;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "accountNumber=" + accountNumber +
                ", entryList=" + entryList +
                ", customer=" + customer +
                '}';
    }
}
