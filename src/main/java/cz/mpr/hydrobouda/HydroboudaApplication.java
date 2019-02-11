package cz.mpr.hydrobouda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import cz.mpr.hydrobouda.property.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class HydroboudaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HydroboudaApplication.class, args);
	}
}
