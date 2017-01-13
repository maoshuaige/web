package service;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import inits.User;

@WebService
public interface UserServiceFacade {
	public User getUserByName(@WebParam(name ="name") String name);  
	  
	
    public void setUser(User user);  
    
    
}
