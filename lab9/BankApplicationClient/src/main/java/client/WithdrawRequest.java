package client;


/**
 * @author kush
 */
public class WithdrawRequest {
    private double amount;
    private CurrencyCode currencyCode;

    public WithdrawRequest() {
    }

    public WithdrawRequest(double amount, CurrencyCode currencyCode) {
        this.amount = amount;
        this.currencyCode = currencyCode;
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
