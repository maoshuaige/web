package service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import inits.Userinfo;
import basic.core.BasicService;

@Path("/Userinfo")
public interface UserinfoService extends BasicService<Userinfo>  {

	public Userinfo createUserinfo(Userinfo user); 
	
	@GET
    @Path("/product")
    @Produces(MediaType.APPLICATION_JSON)
	public Userinfo  getUserinfo();
	
}
