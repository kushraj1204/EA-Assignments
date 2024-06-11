package com.kush.lab12c;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class Lab12cApplication implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(Lab12cApplication.class);
	@Autowired
	ConfigProperties configProperties;

	/*@Value("${application.name}")
	private String applicationName;

	@Value("${application.version}")
	private String version;

	@Value("${server.url}")
	private String serverUrl;

	@Value("${server.port}")
	private int serverPort;

	@Value("${user.firstname}")
	private String userFirstname;

	@Value("${user.lastname}")
	private String userLastname;

	@Value("${user.username}")
	private String username;

	@Value("${user.password}")
	private String password;  // **Never store password in code!**

	@Value("${countries}")
	private String[] countries;*/


	public static void main(String[] args) {
		SpringApplication.run(Lab12cApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info(configProperties.toString());

		/*logger.info("Application Name: {} ", applicationName);
		logger.info("Version: {}", version);
		logger.info("Server URL: {} ", serverUrl);
		logger.info("Server Port: {}", serverPort);
		logger.info("User:");
		logger.info("  Firstname: {} ", userFirstname);
		logger.info("  Lastname: {}", userLastname);
		logger.info("  Username: {}", username);
		logger.info("  Password: {} ", password);
		logger.info("Countries:");
		for (String country : countries) {
			logger.info("  - {}", country);
		}
	}*/
	}
}
