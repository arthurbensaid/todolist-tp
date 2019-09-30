package fr.sij.tp.service;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {
	
	private static final org.slf4j.Logger logger= LoggerFactory.getLogger(LoggingService.class);

	public static void main(String[] args) {
		logger.trace("A TRACE MESSAGE");
		logger.debug("A DEBUG MESSAGE");
		logger.info("An INFO MESSAGE");
		logger.warn("A WARN MESSAGE");
		logger.error("An ERROR MESSAGE");
		
		SpringApplication.run(LoggingService.class, args);
		
	}

}
