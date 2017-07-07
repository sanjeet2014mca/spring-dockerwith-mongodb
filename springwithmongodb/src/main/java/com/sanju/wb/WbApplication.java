package com.sanju.wb;
import org.apache.catalina.startup.Bootstrap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;
/*
@Configuration
@EnableAutoConfiguration
 **@ComponentScan({"com.example.demo", "controller", "service"})**

 */
@Configuration	
@SpringBootApplication
//@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
public class WbApplication {
	//private static final Logger logger = LoggerFactory.getLogger(WbApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(WbApplication.class, args);
	}
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Bootstrap.class);
	}
}
