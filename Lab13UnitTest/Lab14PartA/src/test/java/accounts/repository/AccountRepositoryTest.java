package accounts.repository;

import accounts.domain.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author kush
 */
@RunWith(SpringRunner.class)
@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TestEntityManager testEntityManager;


    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByAccountHolderPositive() {
        Account account=new Account("1387868687524001",1249,"Kush Raj Rimal");
        testEntityManager.persist(account);
        testEntityManager.flush();
        Account accountResult=accountRepository.findByAccountHolder("Kush Raj Rimal");
        assertEquals(accountResult.getAccountNumber(),"1387868687524001");
    }

    @Test
    void findByAccountHolderNegative() {
        Account account=new Account("1387868687524001",1249,"Kush Raj Rimal");
        testEntityManager.persist(account);
        testEntityManager.flush();
        Account accountResult=accountRepository.findByAccountHolder("Madhusodan Chakraborty");
        assertNull(accountResult);
    }

    @Test
    void findByAccountNumberPositive() {
        Account account=new Account("1387868687524001",1249,"Kush Raj Rimal");
        testEntityManager.persist(account);
        testEntityManager.flush();
        Optional<Account> accountResult=accountRepository.findByAccountNumber("1387868687524001");
        assertTrue(accountResult.isPresent());
    }
    @Test
    void findByAccountNumberNegative() {
        Account account=new Account("1387868687524001",1249,"Kush Raj Rimal");
        testEntityManager.persist(account);
        testEntityManager.flush();
        Optional<Account> accountResult=accountRepository.findByAccountNumber("12345");
        assertFalse(accountResult.isPresent());
    }

    @Test
    void findAllByBalanceGreaterThanA() {
        Account account=new Account("1387868687524001",1249,"Kush Raj Rimal");
        Account account1=new Account("1387868687524002",1500,"Madhusodan");
        testEntityManager.persist(account);
        testEntityManager.persist(account1);
        testEntityManager.flush();
        List<Account> accountResult=accountRepository.findAllByBalanceGreaterThan(1300);
        assertEquals (accountResult.size(),1);
    }

    @Test
    void findAllByBalanceGreaterThanB() {
        Account account=new Account("1387868687524001",1249,"Kush Raj Rimal");
        Account account1=new Account("1387868687524002",1500,"Madhusodan");
        testEntityManager.persist(account);
        testEntityManager.persist(account1);
        testEntityManager.flush();
        List<Account> accountResult=accountRepository.findAllByBalanceGreaterThan(1600);
        assertEquals (accountResult.size(),0);
    }
}