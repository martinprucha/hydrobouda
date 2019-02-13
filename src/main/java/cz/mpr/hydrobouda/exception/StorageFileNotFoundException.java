package cz.mpr.hydrobouda.exception;

/**
 * Custom storage file not found exception.
 * 
 * @author MPR
 * @version 1.0
 *
 */
public class StorageFileNotFoundException extends StorageException {

	private static final long serialVersionUID = 1L;

	public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}