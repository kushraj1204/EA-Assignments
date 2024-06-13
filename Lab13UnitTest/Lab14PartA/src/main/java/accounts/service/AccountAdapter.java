package accounts.service;

import accounts.domain.Account;

public class AccountAdapter {

	public static AccountResponse getAccountResponse(Account account) {
		AccountResponse accountResponse = new AccountResponse(account.getAccountNumber(), account.getBalance(), account.getAccountHolder());
		return accountResponse;
	}

}
