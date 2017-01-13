package basic.exception;
/**
 * 
 * @author zj
 *自定义服务层异常。
 */
public class BasicServiceException extends Exception{
	
    public BasicServiceException() {
    	super();
    }

    public BasicServiceException(String message) {
    	super(message);
    }

    public BasicServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BasicServiceException(Throwable cause) {
        super(cause);
    }
}
