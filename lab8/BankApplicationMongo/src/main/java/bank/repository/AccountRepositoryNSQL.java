package bank.repository;

import bank.domain.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface AccountRepositoryNSQL extends MongoRepository<Account,Long> {
    public Optional<Account> findByAccountNumber(long accountNumber);


}
