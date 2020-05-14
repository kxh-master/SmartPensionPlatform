package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class SpringbootApplication {
	
    public static void main( String[] args ){
    	System.setProperty("spring.devtools.restart.enabled", "false");
    	SpringApplication.run(SpringbootApplication.class, args);
        System.out.println( "Success!" );
    }
    
}