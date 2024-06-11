package bank.integration.kafka;

import bank.dto.AccountDto;
import bank.dto.request.AccountCreateRequest;
import bank.dto.request.DepositRequest;
import bank.dto.request.WithdrawRequest;
import bank.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerImpl {
    @Autowired
    AccountService accountService;

/*
    @KafkaListener(topics = {"createAccount"})
    public void createAccount(@Payload AccountCreateRequest request) {
        AccountDto accountDto=accountService.createAccount(request);
        System.out.println("Acount created"+accountDto.toString());

    }

    @KafkaListener(topics = {"depositMoney"})
    public void depositMoney(@Payload DepositRequest request) {
        accountService.depositMoney(request);

    }

    @KafkaListener(topics = {"withdrawMoney"})
    public void withdrawMoney(@Payload WithdrawRequest request) {
        accountService.withdrawMoney(request);
    }
*/


    @KafkaListener(topics = {"createAccount"})
    public void createAccount(@Payload String request) {
        try {
            AccountCreateRequest accountCreateRequest=new ObjectMapper().readValue(request,AccountCreateRequest.class);
            AccountDto accountDto=accountService.createAccount(accountCreateRequest);
            System.out.println("Acount created"+accountDto.toString());
        } catch (JsonProcessingException e) {
            System.out.println("Error parsing request to object {}"+request);
        }

    }

    @KafkaListener(topics = {"depositMoney"})
    public void depositMoney(@Payload String request) {
        try {
            DepositRequest depositRequest=new ObjectMapper().readValue(request,DepositRequest.class);
            accountService.depositMoney(depositRequest);
            System.out.println("Amount deposited");
        } catch (JsonProcessingException e) {
            System.out.println("Error parsing request to object");
        }

    }

    @KafkaListener(topics = {"withdrawMoney"})
    public void withdrawMoney(@Payload String request) {
        try {
            WithdrawRequest withdrawRequest=new ObjectMapper().readValue(request,WithdrawRequest.class);
            accountService.withdrawMoney(withdrawRequest);
            System.out.println("Amount withdrawn");
        } catch (JsonProcessingException e) {
            System.out.println("Error parsing request to object");
        }
    }

}
