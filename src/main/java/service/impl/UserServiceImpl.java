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
        users.put("����", new User(1,"����","�Ϻ��ֶ�","zs@163.com"));  
        users.put("��˹", new User(2,"��˹","�Ϻ��ֶ�","ls@163.com"));  
        users.put("����", new User(3,"����","�Ϻ��ֶ�","ww@163.com"));  
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
