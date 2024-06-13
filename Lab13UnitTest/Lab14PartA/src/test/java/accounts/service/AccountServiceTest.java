package accounts.service;

import accounts.domain.Account;
import accounts.repository.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;

/**
 * @author kush
 */
@SpringBootTest
class AccountServiceTest {

    @TestConfiguration
    static class AccountServiceTestConfiguration {
        @Bean
        public AccountService accountService() {
            return new AccountService();
        }
    }
    @Autowired
    private AccountService accountService;
    @MockBean
    private AccountRepository accountRepository;


    @BeforeEach
    void setUp() {
        String accountNumber="1387868687524001";
        Account account=new Account("1387868687524001",1249,"Kush Raj Rimal");
        Account account2=new Account("1387868687524003",2000,"Madhusodan");
        Optional<Account> kushOptional = Optional.of(account);
        Mockito.when(accountRepository.findById(accountNumber))
                .thenReturn(kushOptional);
        Mockito.when(accountRepository.save(account2))
                .thenReturn(account2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAccountPositive() {
        String accountNumber="1387868687524001";
        AccountResponse found = accountService.getAccount(accountNumber);
        assertEquals(found.getAccountNumber(),accountNumber);
    }
    @Test
    void getAccountNegative() {
        String accountNumber="1387868687524002";
        AccountResponse found = accountService.getAccount(accountNumber);
        assertNull(found);
    }

    @Test
    void createAccountPositive() {
        accountService.createAccount("1387868687524003",2000,"Madhusodan");

        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountRepository).save(accountCaptor.capture());
        Account savedAccount = accountCaptor.getValue();

        Assertions.assertNotNull(savedAccount);
        assertEquals(savedAccount.getAccountNumber(), "1387868687524003");
        assertEquals(savedAccount.getBalance(), 2000);
        assertEquals(savedAccount.getAccountHolder(), "Madhusodan");
    }


}