package customers;

import java.time.LocalDateTime;

public class LoggerImpl implements Logger{

	public void log(String logstring) {
		System.out.println("Logging "+LocalDateTime.now()+" "+logstring);		
	}

}
