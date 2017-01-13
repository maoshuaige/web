package basic.core;

import java.io.Serializable;
import java.util.List;
import java.util.Map;






import org.hibernate.Session;
import org.hibernate.SessionFactory;

import basic.exception.BasicDaoException;
/**
 * 
 * @author zj
 *dao层父接口
 * @param <T> 
 */
public interface BasicDao<T> {
	
	public static final int BATCH_SIZE = 20 ;
	
	public abstract SessionFactory getSessionFactory();

	public abstract void setSessionFactory(SessionFactory sessionFactory);

	public abstract Session getCurrentSession();
	/**
	 * 等到当前登录者信息
	 * @return
	 */
	//public User getUser();
	
	public abstract Serializable create(T entity) throws BasicDaoException;
	
	public void createBatch(List<T> entitys) throws BasicDaoException;
	
	public void delete(T entity) throws BasicDaoException;
	
	public void update(T entity) throws BasicDaoException;
	
	public void updateBatch(List<T> entitys) throws BasicDaoException;

	public void saveOrUpdate(T entity) throws BasicDaoException;
	
	public T queryById(Serializable id) throws BasicDaoException;
	
	public void deleteById(Serializable id) throws BasicDaoException;
	
	public void deleteByIds(List<Serializable> ids) throws BasicDaoException;
	
	public long queryIdMax(T entity,String str) throws BasicDaoException;
	
	public void deleteBatch(List<T> entitys) throws BasicDaoException;
	
	public void modifyByHql(String hql,Map<String,Object> params) throws BasicDaoException;
	
	public abstract Long querySize(QueryParams params) throws BasicDaoException;

	public abstract List<T> queryList(QueryParams params) throws BasicDaoException;
}
