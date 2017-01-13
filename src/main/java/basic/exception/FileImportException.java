package basic.exception;
/**
 * 
 * @author zj
 *自定义controller层异常类
 */
public class FileImportException extends Exception{
	public FileImportException() {
    	super();
    }

    public FileImportException(String message) {
    	super(message);
    }

    public FileImportException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileImportException(Throwable cause) {
        super(cause);
    }
}
