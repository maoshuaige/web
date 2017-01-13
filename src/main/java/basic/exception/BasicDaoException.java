package basic.exception;
/**
 * 
 * @author zj
 *自定义dao层异常
 */
public class BasicDaoException extends Exception{
	public BasicDaoException(String errMsg){
		 super(errMsg);
	}
	
	public BasicDaoException(){
		 super();
	}
	
	public BasicDaoException(String errMsg, Throwable throwable){
		 super(errMsg, throwable);
	}
}
