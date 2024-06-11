package bank.dto.event;

import bank.domain.Account;

/**
 * @author kush
 */
public class DepositEvent {
    private String message;
    private Account account;

    public void setMessage(String message) {
        this.message = message;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public DepositEvent() {
    }

    public DepositEvent(String message, Account account) {
        this.message = message;
        this.account = account;
    }

    public DepositEvent(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}