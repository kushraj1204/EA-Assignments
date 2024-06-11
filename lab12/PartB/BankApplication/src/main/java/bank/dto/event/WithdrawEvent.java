package bank.dto.event;

import bank.domain.Account;

/**
 * @author kush
 */
public class WithdrawEvent {
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

    public WithdrawEvent() {
    }

    public WithdrawEvent(String message, Account account) {
        this.message = message;
        this.account = account;
    }

    public WithdrawEvent(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}