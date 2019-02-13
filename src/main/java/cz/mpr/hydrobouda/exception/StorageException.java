package cz.mpr.hydrobouda.exception;

/**
 * Custom storage exception.
 * 
 * @author MPR
 * @version 1.0
 *
 */
public class StorageException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
