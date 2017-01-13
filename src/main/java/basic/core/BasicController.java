package basic.core;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public class BasicController<T>{
	private static final Logger logger = LogManager.getLogger(BasicController.class);
	
	@SuppressWarnings("unchecked")
	Class<T> clazz = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	
	public BasicService<T> getBasicService(){
		throw new RuntimeException("无法找到service对象!");
	}
	/**
	 * @param queryParams
	 */
	public void dealQueryParams(QueryParams queryParams){
	}
	
	@RequestMapping(method = RequestMethod.GET, produces="application/json")
	@ResponseBody
    public Map<String,Object> show(QueryParams params,HttpServletRequest request,HttpServletResponse response) {  
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			//预处理查询参数
			params.parseFromRaw();
			dealQueryParams(params);
			Long size = getBasicService().querySize(params);
			List<T> list = getBasicService().queryList(params);
			result.put("success", true);
	    	result.put("total", size);
	    	result.put("result", list);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			result.put("success", false);
			result.put("message", e.getMessage());
		}
		return result;
    }  
	
	@RequestMapping(value = "/create", method = {RequestMethod.GET,RequestMethod.POST}, produces="application/json")
	@ResponseBody
	public Map<String,Object> create(@ModelAttribute T record,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			record = getBasicService().create(record);
			result.put("success", true);
	    	result.put("result", record);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			result.put("success", false);
			result.put("message", e.getMessage());
		}
		return result;
	}
}
