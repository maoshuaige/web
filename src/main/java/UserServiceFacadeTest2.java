import inits.User;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.UserServiceFacade;


public class UserServiceFacadeTest2 {
	public static void main(String[] args){  
        
        //加载Spring配置文件  
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-client.xml");  
        //从Spring容器中获取对象  
        UserServiceFacade userService = context.getBean("userWsClient",UserServiceFacade.class);  
      //远程调用接口  
        User u = userService.getUserByName("张三");  
        if(u != null){  
            System.out.println(u);  
        }  
    }  
}
