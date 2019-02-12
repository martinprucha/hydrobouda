package cz.mpr.hydrobouda.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Custom file storage configuration properties.
 * 
 * @author MPR
 * @version 1.0
 *
 */
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
