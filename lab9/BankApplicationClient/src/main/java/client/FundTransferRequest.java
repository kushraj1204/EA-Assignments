package client;

/**
 * @author kush
 */
public class FundTransferRequest {
    private long toAccountNumber;
    private double amount;
    private String description;

    public FundTransferRequest() {
    }

    public FundTransferRequest(long toAccountNumber, double amount, String description) {
        this.toAccountNumber = toAccountNumber;
        this.amount = amount;
        this.description = description;
    }


    public long getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(long toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
