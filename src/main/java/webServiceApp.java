import javax.xml.ws.Endpoint;

import service.impl.HelloWorldImpl;


public class webServiceApp {
	public static void main(String[] args) {  
        System.out.println("web service start");  
         HelloWorldImpl implementor = new HelloWorldImpl();  
        String address = "http://localhost:8080/helloWorld";  
        Endpoint.publish(address, implementor);  
        System.out.println("web service started");  
   }  
}
