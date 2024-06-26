package bank.controller;

import bank.dto.AccountDto;
import bank.dto.request.AccountCreateRequest;
import bank.dto.request.FundTransferRequest;
import bank.dto.request.DepositRequest;
import bank.dto.request.WithdrawRequest;
import bank.dto.response.CustomErrorType;
import bank.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author kush
 */
@RestController
@RequestMapping("/accounts")
public class BankController {

    AccountService accountService;

    public BankController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping("")
    public ResponseEntity<?> addAccount(@RequestBody AccountCreateRequest accountCreateRequest) {
        AccountDto createdAccount=accountService.createAccount(accountCreateRequest);
        return new ResponseEntity<AccountDto>(createdAccount, HttpStatus.CREATED);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<?> getAccount(@PathVariable long accountNumber) {
        AccountDto accountDto = accountService.getAccount(accountNumber);
        if (accountDto == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Account with accountNumber= "
                    + accountNumber + " is not available"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<AccountDto>(accountDto, HttpStatus.OK);
    }

    @PostMapping("/{accountNumber}/deposit")
    public ResponseEntity<?> depositAmount(@PathVariable long accountNumber,@RequestBody DepositRequest request) {
        request.setAccountNumber(accountNumber);
        accountService.depositMoney(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{accountNumber}/withdraw")
    public ResponseEntity<?> withdrawAmount(@PathVariable long accountNumber,@RequestBody WithdrawRequest request) {
        request.setAccountNumber(accountNumber);
        accountService.withdrawMoney(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{accountNumber}/transfer")
    public ResponseEntity<?> transfer(@PathVariable long accountNumber,@RequestBody FundTransferRequest request) {
        request.setFromAccountNumber(accountNumber);
        accountService.transferFunds(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
