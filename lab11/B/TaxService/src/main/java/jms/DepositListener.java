package jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class DepositListener {
    @JmsListener(destination = "depositListener")
    public void receiveMessage(final String strInput) {
        System.out.println(strInput);
    }
}
