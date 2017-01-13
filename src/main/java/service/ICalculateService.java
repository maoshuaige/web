package service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService 
public interface ICalculateService {
    @WebMethod(operationName = "add")  
    @WebResult(name = "result")  
    public int add(@WebParam(name = "num1") int num1, @WebParam(name = "num2") int num2);  
}
