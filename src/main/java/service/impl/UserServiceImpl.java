package service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;

import inits.User;
import service.UserServiceFacade;

@WebService
public class UserServiceImpl implements UserServiceFacade {
	
	private static Map<String,User> users = null;  
    
    private static void init(){  
        users = new HashMap<String,User>();  
        users.put("张三", new User(1,"张三","上海浦东","zs@163.com"));  
        users.put("李斯", new User(2,"李斯","上海浦东","ls@163.com"));  
        users.put("王五", new User(3,"王五","上海浦东","ww@163.com"));  
    }  
	
    public User getUserByName(String name) {
		// TODO Auto-generated method stub
		if(users == null){  
            init();  
        }  
        return users.get(name);
	}

	public void setUser(User user) {
		// TODO Auto-generated method stub

	}

}
