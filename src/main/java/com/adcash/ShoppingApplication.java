package com.adcash;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;

import com.adcash.entities.Category;
import com.adcash.entities.Product;
import com.adcash.repository.CategoryRepository;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableTransactionManagement
@EnableAspectJAutoProxy
@ComponentScan("com.adcash")
public class ShoppingApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(ShoppingApplication.class, args);
    }

    @Profile("local")
    @Bean
    CommandLineRunner initDatabase(CategoryRepository repository) {
    	
    	Category c = new Category("Electorics");
    	Set<Product> products = new HashSet<Product>();
    	products.add(new Product(c, "B&W Speaker"));
    	c.setProduct(products);  
    	
    	Category c1 = new Category((long) 1, "Electorics");
    	Set<Product> products1 = new HashSet<Product>();
    	products1.add(new Product(c1, "Samsung TV"));
    	c1.setProduct(products1);    	
        return args -> {      	
        	
            repository.save(c);
            repository.save(c1);
        };
    }
    
   
}