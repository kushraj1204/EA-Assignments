package bank.integration.logging;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerImpl implements Logger {
org.slf4j.Logger log = LoggerFactory.getLogger(Logger.class);
	public void log(String logString) {
		log.info(logString);
	}

}
