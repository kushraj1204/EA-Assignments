package bank.repository;

import bank.domain.TraceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author kush
 */
public interface TraceRecordRepository extends JpaRepository<TraceRecord, Long> {
}
