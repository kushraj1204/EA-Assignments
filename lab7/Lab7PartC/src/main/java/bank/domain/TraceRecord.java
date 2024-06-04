package bank.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

/**
 * @author kush
 */
@Entity
public class TraceRecord {
    @Id
    @GeneratedValue
    private long id;

    private LocalDateTime currentDateTime;

    private String accountCreationResult;

    public TraceRecord(LocalDateTime currentDateTime, String accountCreationResult) {
        this.currentDateTime = currentDateTime;
        this.accountCreationResult = accountCreationResult;
    }

    public TraceRecord() {

    }

    public long getId() {
        return id;
    }
    public LocalDateTime getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(LocalDateTime currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    public String getAccountCreationResult() {
        return accountCreationResult;
    }

    public void setAccountCreationResult(String accountCreationResult) {
        this.accountCreationResult = accountCreationResult;
    }
}
