package basic.exception;
/**
 * 
 * @author zj
 *自定义controller层异常类
 */
public class FileExportException extends Exception{
	public FileExportException() {
    	super();
    }

    public FileExportException(String message) {
    	super(message);
    }

    public FileExportException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileExportException(Throwable cause) {
        super(cause);
    }
}
