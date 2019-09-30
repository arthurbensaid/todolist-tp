package fr.sij.tp.dao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {
	
	@Bean
	public String dataSourceName() {
		System.out.println("On me demande ma datasource");
		return "url de ma base";
	}
}