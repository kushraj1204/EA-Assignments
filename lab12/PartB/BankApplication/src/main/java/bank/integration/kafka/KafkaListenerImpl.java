package bank.integration.kafka;

import bank.dto.AccountDto;
import bank.dto.request.AccountCreateRequest;
import bank.dto.request.DepositRequest;
import bank.dto.request.WithdrawRequest;
import bank.service.AccountService;
import bank.service.CurrencyConverterImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerImpl {


    Logger logger = LoggerFactory.getLogger(KafkaListenerImpl.class);
    @Autowired
    AccountService accountService;

    @KafkaListener(topics = {"createAccount"})
    public void createAccount(@Payload String request) {
        try {
            AccountCreateRequest accountCreateRequest=new ObjectMapper().readValue(request,AccountCreateRequest.class);
            AccountDto accountDto=accountService.createAccount(accountCreateRequest);
            logger.info("Acount created {}",accountDto.toString());
        } catch (JsonProcessingException e) {
            logger.info("Error parsing request to object {}",request);
        }
    }

    @KafkaListener(topics = {"depositMoney"})
    public void depositMoney(@Payload String request) {
        try {
            DepositRequest depositRequest=new ObjectMapper().readValue(request,DepositRequest.class);
            accountService.depositMoney(depositRequest);
            logger.info("Amount deposited");
        } catch (JsonProcessingException e) {
            logger.info("Error parsing request to object {}",request);
        }
    }

    @KafkaListener(topics = {"withdrawMoney"})
    public void withdrawMoney(@Payload String request) {
        try {
            WithdrawRequest withdrawRequest=new ObjectMapper().readValue(request,WithdrawRequest.class);
            accountService.withdrawMoney(withdrawRequest);
            logger.info("Amount withdrawn");
        } catch (JsonProcessingException e) {
            logger.info("Error parsing request to object {}",request);
        }
    }
}
