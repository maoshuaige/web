import inits.User;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.UserServiceFacade;


public class UserServiceFacadeTest2 {
	public static void main(String[] args){  
        
        //����Spring�����ļ�  
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-client.xml");  
        //��Spring�����л�ȡ����  
        UserServiceFacade userService = context.getBean("userWsClient",UserServiceFacade.class);  
      //Զ�̵��ýӿ�  
        User u = userService.getUserByName("����");  
        if(u != null){  
            System.out.println(u);  
        }  
    }  
}
