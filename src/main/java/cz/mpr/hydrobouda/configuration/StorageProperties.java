package cz.mpr.hydrobouda.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * File storge configuration properties.
 * 
 * @author MPR
 * @version 1.0
 *
 */
@ConfigurationProperties(prefix = "storage")
public class StorageProperties {

	private String uploadDir;

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}

}
