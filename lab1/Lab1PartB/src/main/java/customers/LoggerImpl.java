package customers;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LoggerImpl implements Logger{

	public void log(String logstring) {
		System.out.println("Logging "+LocalDateTime.now()+" "+logstring);		
	}

}
