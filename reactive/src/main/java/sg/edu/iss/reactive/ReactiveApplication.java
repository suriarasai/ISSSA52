package sg.edu.iss.reactive;


import java.time.Duration;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;

import io.r2dbc.spi.ConnectionFactory;
import sg.edu.iss.reactive.product.Product;
import sg.edu.iss.reactive.product.ProductRepository;

@SpringBootApplication
public class ReactiveApplication {
	
	private static final Logger log = LoggerFactory.getLogger(ReactiveApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ReactiveApplication.class, args);
	}
	
	@Bean()
    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        // This will create our database table and schema
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        return initializer;
    }
    @Bean
    public CommandLineRunner demo(ProductRepository repository) {

        return (args) -> {
            // save a few customers
            repository.saveAll(Arrays.asList(new Product(1,"Speaker",1,20),
                new Product(2,"Mouse",1,30),
                new Product(3,"KeyBoard",1,40),
                new Product(4,"Camera",1,50),
                new Product(5,"Disk Drive",1,60)))
                .blockLast(Duration.ofSeconds(10));

            // fetch all customers
            log.info("Products found with findAll():");
            log.info("------------------------------");
            repository.findAll().doOnNext(product -> {
                log.info(product.toString());
            }).blockLast(Duration.ofSeconds(10));

            log.info("");
        };
    }

}
