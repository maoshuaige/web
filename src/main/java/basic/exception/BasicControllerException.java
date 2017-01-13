package basic.exception;
/**
 * 
 * @author zj
 *自定义controller层异常类
 */
public class BasicControllerException extends Exception{
	public BasicControllerException() {
    	super();
    }

    public BasicControllerException(String message) {
    	super(message);
    }

    public BasicControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public BasicControllerException(Throwable cause) {
        super(cause);
    }
}
