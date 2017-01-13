package controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import inits.College;
import inits.Teacher;
import inits.Userinfo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import service.UserinfoService;
import basic.core.BasicController;
import basic.core.Filter;
import basic.core.QueryParams;
import basic.exception.BasicServiceException;

@Controller
@RequestMapping("/userinfo")
public class UserinfoController extends BasicController<Userinfo>  {
	
	
	@Resource
	private UserinfoService userinfoService;
		
	@RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	@ResponseBody
	public List<Userinfo> login(HttpServletRequest request) throws BasicServiceException {
/*		String username = request.getParameter("j_username");
		String password = request.getParameter("j_password");
		Userinfo user = new Userinfo();
		user.setUserid(2);
		user.setUsername(username);
		user.setUserpassword(password);
		
		//userinfoService.delete(user);
		
		Teacher t = new Teacher();
		t.setTeacherid((long)3);
		Set<Teacher> teacher = new HashSet<Teacher>();
		teacher.add(t);*/
		/*user.setTeacher(teacher);*/
		
		/*
		College college = new College();
		college.setCollegeid(1);
		user.setCollege(college);
		userinfoService.createUserinfo(user);
		Userinfo user2 = userinfoService.queryById((long)10);
		System.out.println(username+"===================laile=================="+password);
		System.out.println(user2.getCollege().getCollegename()+"===================laile=================="+password);
		QueryParams params = new QueryParams();*/
		


		
		QueryParams quer = new QueryParams();
		
		System.out.println(userinfoService.queryById((long)4));
		System.out.println(userinfoService.queryById((long)4));
		System.out.println(userinfoService.queryById((long)4));
		
		quer.addFilter(new Filter("username", "11111"));
		List<Userinfo> list = userinfoService.queryList(quer);
		System.out.println(list.toString());
		List<Userinfo> list2 = userinfoService.queryList(quer);
		System.out.println(list2.toString());
		List<Userinfo> list3 = userinfoService.queryList(quer);
		System.out.println(list3.toString());
		return null;
	}
	
	
}
