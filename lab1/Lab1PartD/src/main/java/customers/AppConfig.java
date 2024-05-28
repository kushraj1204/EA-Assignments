package customers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kush
 */
@Configuration
public class AppConfig {


    @Bean
    public Logger logger(){
        Logger logger=new LoggerImpl();
        return logger;
    }


}
