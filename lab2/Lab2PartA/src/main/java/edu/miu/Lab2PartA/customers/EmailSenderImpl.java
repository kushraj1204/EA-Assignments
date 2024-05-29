package edu.miu.Lab2PartA.customers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderImpl implements EmailSender {

	@Value("${mail.server.name}")
	private String outgoingMailServer;

	private Logger logger;

	public EmailSenderImpl(Logger logger) {
		this.logger = logger;
	}

	public String getOutgoingMailServer() {
		return outgoingMailServer;
	}

	public void sendEmail (String email, String message){
		System.out.println("EmailSender: sending '"+message+"' to "+email );
		logger.log("Email is sent: message= "+message +" , emailaddress ="+ email  );
	}

}
