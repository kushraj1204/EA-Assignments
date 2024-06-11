package bank.integration.schedule;

import bank.Application;
import bank.domain.Account;
import bank.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author kush
 */
@Component
public class BankStatementPrinter {

    Logger logger = LoggerFactory.getLogger(BankStatementPrinter.class);

    @Autowired
    AccountRepository accountRepository;

    @Scheduled(fixedDelay = 20000L)
    public void printAccounts(){
    List<Account> accountList=accountRepository.findAll();
    accountList.forEach(x->logger.info(x.toString()));
    }
}


