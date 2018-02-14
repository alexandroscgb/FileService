package co.com.bvc.fileservice.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author Hernán Quevedo
 * 
 */
@SpringBootApplication(scanBasePackages = { "co.com.bvc.fileservice" })
@ComponentScan(basePackages = { "co.com.bvc.fileservice" })
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class FileServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(FileServiceApplication.class, args);		
	}
}