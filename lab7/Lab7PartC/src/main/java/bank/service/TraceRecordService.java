package bank.service;

import bank.domain.TraceRecord;
import bank.repository.TraceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author kush
 */
@Service
public class TraceRecordService {

    @Autowired
    TraceRecordRepository traceRecordRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createTraceRecord(String text){
        traceRecordRepository.save(new TraceRecord(LocalDateTime.now(),text));
    }
}
