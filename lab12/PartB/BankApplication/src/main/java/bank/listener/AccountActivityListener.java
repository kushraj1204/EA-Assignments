package bank.listener;

import bank.domain.TraceRecord;
import bank.dto.event.AddAccountEvent;
import bank.dto.event.DepositEvent;
import bank.integration.mailer.SendEmail;
import bank.repository.TraceRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import static bank.domain.AccountEvents.*;

/**
 * @author kush
 */
@Service
public class AccountActivityListener {
    Logger logger = LoggerFactory.getLogger(AccountActivityListener.class);
    final
    TraceRecordRepository traceRecordRepository;

    final
    SendEmail sendEmail;

    public AccountActivityListener(TraceRecordRepository traceRecordRepository, SendEmail sendEmail) {
        this.traceRecordRepository = traceRecordRepository;
        this.sendEmail = sendEmail;
    }

    @EventListener
    public void onAccountAddEvent(AddAccountEvent event) {
        logger.info("received add account event {}", event.getMessage());
        traceRecordRepository.save(new TraceRecord(event.getAccount().getAccountNumber(),ADD_ACCOUNT.name(),event.getAccount().getBalance()));
        sendEmail.send("customeremail@miu.edu","Account Created");

    }

    @EventListener
    public void onDeposit(DepositEvent event) {
        logger.info("received deposit event {}", event.getMessage());
        traceRecordRepository.save(new TraceRecord(event.getAccount().getAccountNumber(),DEPOSIT_MONEY.name(),event.getAccount().getBalance()));
        sendEmail.send("customeremail@miu.edu","Money deposited");

    }

    @EventListener
    public void onWithDraw(AddAccountEvent event) {
        logger.info("received withdraw event {}", event.getMessage());
        traceRecordRepository.save(new TraceRecord(event.getAccount().getAccountNumber(),WITHDRAW_MONEY.name(),event.getAccount().getBalance()));
        sendEmail.send("customeremail@miu.edu","Money withdrawn");
    }

    @EventListener
    public void onTransfer(AddAccountEvent event) {
        logger.info("received transfer event {}", event.getMessage());
        traceRecordRepository.save(new TraceRecord(event.getAccount().getAccountNumber(),TRANSFER_MONEY.name(),event.getAccount().getBalance()));
        sendEmail.send("customeremail@miu.edu","Money transferred");
    }
}
