package service.impl;

import inits.User;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam;
import javax.jws.WebService;

import service.HelloWorld;

@WebService(endpointInterface="service.HelloWorld",serviceName="HelloWorld")  
public class HelloWorldImpl implements HelloWorld {  
	public String sayHello(){  
        return "Hello world!";  
    }  
  
}  