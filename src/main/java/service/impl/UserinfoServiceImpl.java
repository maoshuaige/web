package service.impl;

import java.io.Serializable;

import javax.ws.rs.core.Response;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;






import dao.UserinfoDao;
import service.UserinfoService;
import inits.Userinfo;
import basic.core.BasicDao;
import basic.core.BasicServiceImpl;
import basic.exception.BasicDaoException;

@Service(value="userinfoService")
@Transactional
@Component
public class UserinfoServiceImpl extends BasicServiceImpl<Userinfo> implements UserinfoService {
	
	@Autowired
	private UserinfoDao userinfoDao;
	
	public BasicDao<Userinfo>  getBasicDao() {
		return userinfoDao;
	}
	
	@Transactional
	public Userinfo createUserinfo(Userinfo user) {
		// TODO Auto-generated method stub
		try {
			 long i = userinfoDao.queryIdMax(user,"userid");
			 i = i+1;
			 user.setUserid(i);
			 userinfoDao.create(user);
		} catch (BasicDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	
	public Userinfo  getUserinfo() {
		// TODO Auto-generated method stub
		Userinfo user = null;
		try {
			user = userinfoDao.queryById((long)3);
			System.out.println(user.getCollege().getCollegeid()+" "+user.getCollege().getCollegename()+" ");
		} catch (BasicDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] filterNames = new String[1];
		filterNames[0]  = "userinfo";
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setIgnoreDefaultExcludes(false);    
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);    //防止自包含
		if(filterNames != null){
			//这里是核心，过滤掉不想使用的属性
			jsonConfig .setExcludes(filterNames) ;
		 }
		JSONObject jsonObj = JSONObject.fromObject(user.getCollege(), jsonConfig);
				
		/*System.out.println(jsonArray.toString());
        return Response.ok(jsonArray.toString()).build(); */
		
		return user;
	}

}
