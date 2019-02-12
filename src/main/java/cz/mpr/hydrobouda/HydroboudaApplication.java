package cz.mpr.hydrobouda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import cz.mpr.hydrobouda.property.FileStorageProperties;

/**
 * Application main class.
 * 
 * @author MPR
 * @version 1.0
 *
 */
@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class HydroboudaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HydroboudaApplication.class, args);
	}
}
