package bank.integration.mailer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author kush
 */
@Service
public class SendEmail {
    Logger logger = LoggerFactory.getLogger(SendEmail.class);
    public void send(String email,String text){
        logger.info("Sending email {} to {}",email,text);
    }
}
