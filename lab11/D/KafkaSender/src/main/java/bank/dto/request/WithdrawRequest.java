package bank.dto.request;

/**
 * @author kush
 */
public class WithdrawRequest {
    private long accountNumber;
    private double amount;
    private CurrencyCode currencyCode;

    public WithdrawRequest() {
    }

    public WithdrawRequest(long accountNumber, double amount, CurrencyCode currencyCode) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.currencyCode = currencyCode;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(CurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
    }
}
