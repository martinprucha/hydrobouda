package cz.mpr.hydrobouda.exception;

/**
 * Custom file storage exception.
 * 
 * @author MPR
 * @version 1.0
 *
 */
public class FileStorageException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}