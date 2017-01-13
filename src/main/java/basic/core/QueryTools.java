package basic.core;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
/**
 * 查询处理。
 * @author zj
 *
 */
public class QueryTools {
	
	private static final Logger logger = LogManager.getLogger(QueryTools.class);
	
	private static final SimpleDateFormat datetimeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static Criteria createCriteria(Session session ,Class clazz ,QueryParams qp,boolean sortFlag,boolean pageFlag){
		Criteria criteria = session.createCriteria(clazz);
		if(qp == null ){
			return null ;
		}else{
			Conjunction conjunction = Restrictions.conjunction();
			List<Filter> filters = qp.getFilters();
			if (filters != null && filters.size() > 0) {
				try{
					Iterator<Filter> iterator = filters.iterator();
					while(iterator.hasNext()){
						Filter filter = iterator.next();
						String prop = filter.getProperty();
						String oper = filter.getOperator();
						String value = filter.getValue();
						Object obj = null ;
						List<Object> objs = null ;
						if(value != null && !"".equals(value) && prop != null && !"".equals(prop)){
							Field field = clazz.getDeclaredField(prop);
							Class type = field.getType();
							if(oper.equalsIgnoreCase(Filter.OP_IN) || oper.equalsIgnoreCase(Filter.OP_NOT_IN)){
								objs = parseListValue(value, type);
							}else{
								obj = parseValue(value, type);
							}
							if(obj != null || (objs != null && !objs.isEmpty())){
								if(oper.equalsIgnoreCase(Filter.OP_EQUAL)){
									conjunction.add(Restrictions.eq(prop, obj));
								}else if(oper.equalsIgnoreCase(Filter.OP_GREAT_EQUAL)){
									conjunction.add(Restrictions.ge(prop, obj));
								}else if(oper.equalsIgnoreCase(Filter.OP_GREAT_THEN)){
									conjunction.add(Restrictions.gt(prop, obj));
								}else if(oper.equalsIgnoreCase(Filter.OP_LESS_EQUAL)){
									conjunction.add(Restrictions.le(prop, obj));
								}else if(oper.equalsIgnoreCase(Filter.OP_LESS_THEN)){
									conjunction.add(Restrictions.lt(prop, obj));
								}else if(oper.equalsIgnoreCase(Filter.OP_NOT_EQUAL)){
									conjunction.add(Restrictions.ne(prop, obj));
								}else if(oper.equalsIgnoreCase(Filter.OP_LIKE)){
									conjunction.add(Restrictions.like(prop, obj));
								}else if(oper.equalsIgnoreCase(Filter.OP_IN)){
									conjunction.add(Restrictions.in(prop, objs));
								}else if(oper.equalsIgnoreCase(Filter.OP_NOT_IN)){
									conjunction.add(Restrictions.not(Restrictions.in(prop, objs)));
								}else{
									throw new QueryException("不支持的过滤条件");
								}
							}
						}
					}
					criteria.add(conjunction);
				}catch(NoSuchFieldException e){
					logger.error("查询字段不存在！！！！");
					e.printStackTrace();
				}
			}
			
			//过滤条件Sorter构建排序条件
			List<Sorter> sorters = qp.getSorters();
			sorters = qp.getSorters();
			/**
			 * sortFlag 是否支持排序
			 */
			if(sortFlag && sorters != null && sorters.size() > 0){
				try{
					Iterator<Sorter> sorterIter = sorters.iterator();
					while(sorterIter.hasNext()){
						
						Sorter sorter = sorterIter.next();
						String prop = sorter.getProperty();
						String oper = sorter.getOperator();
						if(prop != null && oper!=null){
							Field propertyField = clazz.getDeclaredField(prop);
							if(oper.equalsIgnoreCase(Sorter.ASC)){
								criteria.addOrder(Order.asc(prop));
							}else if(oper.equalsIgnoreCase(Sorter.DESC)){
								criteria.addOrder(Order.desc(prop));
							}else{
								throw new QueryException("不支持的排序条件");
							}
						}
					}
				}catch(NoSuchFieldException e1){
					logger.error("Filter Property Not Founded",e1);
					throw new QueryException("查询字段不存在",e1);
				}catch(QueryException qe){
					throw qe;
				}catch(Exception e){
					throw new QueryException("查询条件错误",e);
				}
			}
			
			if(pageFlag){  //是否分页  
				int page = qp.getPage();
				int rows = qp.getRows();
				int start = (page - 1) * rows ;
				if(start > 0){
					criteria.setFirstResult(start);
				}
				if(rows != -1){
					criteria.setMaxResults(rows);
				}
			}
		}
		return criteria;
	}
	/**
	 * 
	 * @param session hibernate连接会话
	 * @param hql     
	 * @param params   
	 * @return Query 
	 */
	public static Query getQuery(Session session,String hql ,Map<String,Object> params){
		Query query = session.createQuery(hql);
		if(params != null && !params.isEmpty()){
			Iterator<Entry<String, Object>> entrys = params.entrySet().iterator();
			while(entrys.hasNext()){
				Map.Entry<String, Object> entry = entrys.next();
				if(entry.getKey() != null){
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
		}
		return query ;
	}
	
	public static String getHql(Class clazz , String pk){
		String hql = "";
		return hql;
	}
	/**
	 * 把参数字符串转换成参数列表
	 * @param valueRaw 参数字符串
	 * @param valueType 参数类型
	 * @return
	 */
	public static List<Object> parseListValue(String valueRaw , Class valueType){
		 List<Object> valueList = new ArrayList<Object>();
         Pattern pattern = Pattern.compile("\\[.*\\]");
         Matcher matcher = pattern.matcher(valueRaw);
         
         if(matcher.find()){
         	String matchValueRaw = matcher.group();
         	matchValueRaw = matchValueRaw.substring(1,matchValueRaw.length()-1);
         	String[] valueRawArray = matchValueRaw.split(",");
         	for(int i = 0; i < valueRawArray.length; i++){
         		String singleValueRaw = valueRawArray[i];
         		singleValueRaw = singleValueRaw.trim();
         		//System.out.println(valueRaw);
         		Object value = parseValue(singleValueRaw,valueType);
         		valueList.add(value);
         	}
         }
		 return valueList;
	}
	
	public static Object parseValue(String valueRaw,Class propertyType){
		Object value = null;
		try{		
			if(propertyType == Integer.class){
				value = Integer.parseInt(valueRaw);
			}else if(propertyType == Long.class){
				value = Long.parseLong(valueRaw);
			}else if(propertyType == Float.class){
				value = Float.parseFloat(valueRaw);
			}else if(propertyType == Double.class){
				value = Double.parseDouble(valueRaw);
			}else if(propertyType == Boolean.class){
				value = Boolean.parseBoolean(valueRaw);
			}else if(propertyType == Date.class){
				try{
					value = datetimeformat.parse(valueRaw);
				}catch(ParseException pe){
					value = dateformat.parse(valueRaw);
				}
			}else if(propertyType == String.class){
				value = valueRaw;
			}else{
				throw new QueryException("不支持的数据类型");
			}
			return value;
		}catch(QueryException qe){
			throw qe;
		}catch(Exception e){
			throw new QueryException("类型转换失败："+e.getMessage(),e);
		}
	}
	
}
