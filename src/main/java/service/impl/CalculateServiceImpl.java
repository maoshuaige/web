package service.impl;

import javax.jws.WebService;

import service.ICalculateService;

@WebService(endpointInterface = "service.ICalculateService")  
public class CalculateServiceImpl implements ICalculateService {

	public int add(int num1, int num2) {
		// TODO Auto-generated method stub
		 return num1 + num2;  
	}  
    
}  
