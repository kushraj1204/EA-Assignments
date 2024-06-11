package kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import bank.dto.request.AccountCreateRequest;
import bank.dto.request.CurrencyCode;
import bank.dto.request.DepositRequest;
import bank.dto.request.WithdrawRequest;

@SpringBootApplication
@EnableKafka
public class Application implements CommandLineRunner {

    @Autowired
    Sender sender;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        sender.send("createAccount",new AccountCreateRequest(567654324, "Madhusodan Chakraborty"));
        System.out.println("Message has been sent for account creation");

        sender.send("depositMoney",new DepositRequest(567654324, 25,CurrencyCode.EURO));
        System.out.println("Message has been sent for money deposit");

        sender.send("withdrawMoney",new WithdrawRequest( 567654324,5, CurrencyCode.USD));
        System.out.println("Message has been sent for money withdrawal");
    }

}
