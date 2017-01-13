package basic.core;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

import basic.util.CustomObjectMapper;
/**
 * 
 * @author zj
 *
 */
public class QueryParams implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8783296702813703743L;
	private static final Logger logger = LogManager.getLogger(QueryParams.class);
	
	private String filterParams ;
	private String sortParams ;
	
	private List<Filter> filters;  // 过滤条件的集合
	private List<Sorter> sorters;  // 排序参数的集合
	
	private int page = -1;  //第几页
	private int rows = -1;  //每页行数
	
	/**
	 * 获取Filter
	 * @param property filter property
	 * @param property filter operator
	 * @return filter 对象
	 */
	public Filter getFilter(String property,String operator){
		Filter filterExample = new Filter();
		filterExample.setProperty(property);
		filterExample.setOperator(operator);
		
		Filter filter;
		for(int i = 0; i < filters.size(); i ++){
			filter = filters.get(i);
			if(filter != null && filter.equalsWithoutValue(filterExample)) {
				return filter;
			}
		}
		return null;
	}
	
	/**
	 * 获取Filter
	 * @param property filter property
	 * @return filter 对象
	 */
	public List<Filter> getFilterByProperty(String property){
		List<Filter> matchFilters = new ArrayList<Filter>();
		Filter filter = null;
		for(int i = 0; i < filters.size(); i ++){
			filter = filters.get(i);
			if(filter != null && filter.getProperty()!=null && filter.getProperty().equalsIgnoreCase(property)) {
				matchFilters.add(filter);
			}
		}
		
		return matchFilters;
	}
	
	/**
	 * 新增Filter
	 * @param id  filter id
	 * @param filter filter对象
	 */
	public void addFilter(Filter filter){
		if(filters == null) filters = new ArrayList<Filter>();
			
		Filter filterTmp = null;
		for(int i = 0; i < filters.size(); i ++){
			filterTmp = filters.get(i);
			if(filterTmp != null && filterTmp.equalsWithoutValue(filter)){
				filters.set(i, filter);
			}	
		}
		filters.add(filter);
	}
	
	/**
	 * 删除Filter
	 * @param id filter id
	 * @return 被删除的Filter对象
	 */
	public Filter removeFilter(Filter toRemove){
		Filter filter = null;
		for(int i = 0; i < filters.size(); i ++){
			filter = filters.get(i);
			if(filter != null && filter.equalsWithoutValue(toRemove)){
				filters.remove(i);
				return filter;
			}	
		}
		
		return null;
	}
	
	public String getFilterParams() {
		return filterParams;
	}

	public void setFilterParams(String filterParams) {
		this.filterParams = filterParams;
	}

	public String getSortParams() {
		return sortParams;
	}

	public void setSortParams(String sortParams) {
		this.sortParams = sortParams;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public List<Filter> getFilters() {
		return filters;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}
	
	public List<Sorter> getSorters() {
		return sorters;
	}

	public void setSorters(List<Sorter> sorters) {
		this.sorters = sorters;
	}

	public QueryParams() {
	}
	
	public void parseFromRaw(){
		try{
			JsonFactory  jsonFacotry = new JsonFactory();
			CustomObjectMapper objectMapper = new CustomObjectMapper();
			//从json字符串转换为 Filter对象
			if(filterParams !=null) {
				if(filters == null) filters = new ArrayList<Filter>();
				JsonParser jsonParse = jsonFacotry.createJsonParser(filterParams);
				jsonParse.nextToken();
				while (jsonParse.nextToken() == JsonToken.START_OBJECT) {
					Filter filter = objectMapper.readValue(jsonParse, Filter.class);
					if(!filters.contains(filter)) filters.add(filter);
				}
				jsonParse.close();
			}
			//从json字符串转换为 Sorter对象
			if(sortParams !=null) {
				if(sorters == null) sorters = new ArrayList<Sorter>();
				JsonParser jsonParse = jsonFacotry.createJsonParser(sortParams);
				jsonParse.nextToken();
				while (jsonParse.nextToken() == JsonToken.START_OBJECT) {
					Sorter sorter = objectMapper.readValue(jsonParse, Sorter.class);
					if(!sorters.contains(sorter)) sorters.add(sorter);
				}
				jsonParse.close();
			}
		}catch(Exception e){
			logger.error("json parse error!", e);
			filters = null ;
			sorters = null ;
		}
	}
	

	public void warpToRaw(){
		try{
			CustomObjectMapper objectMapper = new CustomObjectMapper();
			//从 Filter对象转换为json字符串
			if(filters != null) {
				filterParams = objectMapper.writeValueAsString(filters);
			}else{
				filterParams = null;
			}
			//从 Sorter对象转换为json字符串
			if(sorters !=null) {
				sortParams = objectMapper.writeValueAsString(sorters);
			}else{
				sortParams = null;
			}
		}catch(Exception e){
			logger.error("json wrap error!", e);
			filterParams = null;
			sortParams = null ;
		}
	}
	/**
	 *  获取Sorter
	 * @param property sorter property
	 * @return sorter 对象
	 */
	public Sorter getSorter(String property){
		Sorter sorterExample = new Sorter();
		sorterExample.setProperty(property);
		
		Sorter sorter;
		for(int i = 0; i < sorters.size(); i ++){
			sorter = sorters.get(i);
			if(sorter != null && sorter.equalsSorter(sorterExample)) {
				return sorter;
			}
		}
		
		return null;
	}
	
	/**
	 * 获取Sorter
	 * @param property sorter property
	 * @return sorter 对象
	 */
	public List<Sorter> getSorterByProperty(String property){
		List<Sorter> matchSorters = new ArrayList<Sorter>();
		Sorter sorter = null;
		for(int i = 0; i < sorters.size(); i ++){
			sorter = sorters.get(i);
			if(sorter != null && sorter.getProperty()!=null && sorter.getProperty().equalsIgnoreCase(property)) {
				matchSorters.add(sorter);
			}
		}
		return matchSorters;
	}
	
	/**
	 * 新增Sorter
	 * @param id  sorter id
	 * @param sorter sorter对象
	 */
	public void addSorter(Sorter sorter){
		if(sorters == null) sorters = new ArrayList<Sorter>();
		Sorter obj = null;
		for(int i = 0; i < sorters.size(); i ++){
			obj = sorters.get(i);
			if(obj != null && obj.equalsSorter(sorter)){
				sorters.set(i, sorter);
			}	
		}
		sorters.add(sorter);
	}
	
	public Sorter removeSorter(Sorter sorter){
		Sorter obj = null;
		for(int i = 0 ; i < sorters.size() ; i ++){
			obj = sorters.get(i);
			if(obj != null && obj.equalsSorter(sorter)){
				sorters.remove(i);
				return obj ;
			} 
		}
		return null;
	}
	
}
