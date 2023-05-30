package com.petzeydepartment;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;

@SpringBootApplication
public class PetzeyDepartmentApplication {
    
	@Value(value = "${swagger.url}")   
	public String url;

	public static void main(String[] args) {
		SpringApplication.run(PetzeyDepartmentApplication.class, args);
	}
    
	@Bean
	public OpenAPI customOpenAPI()
	{   
		Server productionserver = new Server(); 
	//Server localserver = new Server();   
	List<Server> servers = new ArrayList<>();
	productionserver.setUrl(url);   
	//localserver.setUrl(localURL);
	servers.add(productionserver);
	// servers.add(localserver); 
	OpenAPI openAPI = new OpenAPI();    
	openAPI.setServers(servers);   
	return openAPI; 
	}
}
