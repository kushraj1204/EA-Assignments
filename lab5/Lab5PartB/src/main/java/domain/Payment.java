package domain;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Payment {

	@Column(name = "PAYDATE")
	private String payDate;

	@Column(name = "AMOUNT")
	private double amount;

	public Payment() {
	}

	public Payment(String payDate, double amount) {
		this.payDate = payDate;
		this.amount = amount;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Payment{" +
				"payDate='" + payDate + '\'' +
				", amount=" + amount +
				'}';
	}
}
