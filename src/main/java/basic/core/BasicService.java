package basic.core;

import java.io.Serializable;
import java.util.List;

import basic.exception.BasicServiceException;
/**
 * 
 * @author zj
 * 基本的service接口
 * @param <T>
 */
public interface BasicService<T> {
	/**
	 * 获取当前用户信息
	 * @return
	 */
	//public User getUser();
	
	public BasicDao<T> getBasicDao();
	
	/**
	 * 创建记录
	 * @param entity 需要创建的记录
	 * @return 创建完成的记录
	 */
	public T create(T entity) throws BasicServiceException;
	
	public void createBatch(List<T> entitys) throws BasicServiceException;
	
	public void delete(T entity) throws BasicServiceException;
	
	public void update(T entity) throws BasicServiceException;
	
	public void saveOrUpdate(T entity) throws BasicServiceException ;
	
	public T queryById(Serializable id) throws BasicServiceException;
	
	

	public Long querySize(QueryParams params) throws BasicServiceException;

	public List<T> queryList(QueryParams params) throws BasicServiceException;
}
