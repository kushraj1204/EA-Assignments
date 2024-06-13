package accounts.controller;

import accounts.service.AccountResponse;
import accounts.service.AccountService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author kush
 */
@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    AccountService accountService;


    @BeforeEach
    void setUp() {
        when(accountService.getAccount("1387868687524001")).
                thenReturn(new AccountResponse("1387868687524001",2000,"Kush"));
        when(accountService.getAccount("randomvalue")).thenReturn(null);
        when(accountService.getAccount("1387868687524001")).
                thenReturn(new AccountResponse("1387868687524001",2000,"Kush"));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAccountSuccess() throws Exception {
        mockMvc.perform(get("/account/1387868687524001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber").value("1387868687524001"))
                .andExpect(jsonPath("$.balance").value(2000))
                .andExpect(jsonPath("$.accountHolder").value("Kush"));
    }

    @Test
    void getAccountNotFound() throws Exception {

        mockMvc.perform(get("/account/randomvalue"))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Account with account number randomvalue is not available"));
    }

    @Test
    void createAccountSuccess() throws Exception {
        String accountNumber = "1387868687524001";
        String amount = "2000";
        String accountHolder = "Kush";
        String expectedMessage = "Account with account number " + accountNumber + " is created";

        doNothing().when(accountService).createAccount(accountNumber, Double.parseDouble(amount), accountHolder);

        ResultActions resultActions = mockMvc.perform(get("/createaccount/{accountNumber}/{amount}/{accountHolder}", accountNumber, amount, accountHolder));

        resultActions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedMessage));
    }
}