package basic.core;




import inits.Userinfo;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import basic.exception.BasicDaoException;
/**
 * 
 * @author zj
 * dao层的父类
 * @param <T>
 */

@Transactional
public class BasicDaoImpl<T> implements BasicDao<T> {
	
	private static final Logger logger = LogManager.getLogger(BasicDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	Class<T> clazz = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//申明事务
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public Serializable create(T entity) throws BasicDaoException {
		Serializable serializable = null ;
		Session session = getCurrentSession();		
		serializable = session.save(judegEntity(entity));
		session.flush();
		session.clear();
		return serializable;
	}
	
	public void createBatch(List<T> entitys) throws BasicDaoException{
		try{
			if(entitys != null){
				Session session = getCurrentSession();				
				for(int i = 0; i < entitys.size() ; i ++){
					T entity = entitys.get(i) ;
					if(entity != null){
						session.save(judegEntity(entity));
					}
					if((i + 1 ) % BasicDao.BATCH_SIZE == 0){
						session.flush();
						session.clear();
					}
				}
				session.flush();
				session.clear();
				
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			throw new BasicDaoException(e.getMessage());
		 }
	}
	
	
	public void delete(T entity) throws BasicDaoException{
		try{
			if(entity != null){
				Session session = getCurrentSession();				
				session.delete(entity);
				session.flush();
				session.clear();
				
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			throw new BasicDaoException(e.getMessage());
		}
	}
	
	
	public void update(T entity) throws BasicDaoException{
		try{
			if(entity != null){
				Session session = this.getCurrentSession();
				
				session.update(judegEntity(entity));
				session.flush();
				session.clear();
				
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			throw new BasicDaoException(e.getMessage());
		}
	}
	
	
	public void updateBatch(List<T> entitys) throws BasicDaoException {
		try{
			Session session = getCurrentSession();			
			for(int i = 0 ; i < entitys.size() ; i ++){
				if(entitys.get(i) != null){
					session.update(entitys.get(i));
				}
				if((i + 1 ) % BasicDao.BATCH_SIZE == 0){
					session.flush();
					session.clear();
				}
			}
			session.flush();
			session.clear();
			
		}catch(Exception e){
			logger.error(e.getMessage());
			throw new BasicDaoException(e.getMessage());
		}
	}

	
	public void saveOrUpdate(T entity) throws BasicDaoException{
		try{
			if(entity != null){
				Session session = getCurrentSession();
				
				session.saveOrUpdate(judegEntity(entity));
				session.flush();
				session.clear();
				
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			throw new BasicDaoException(e.getMessage());
		}
	}
	
	public T queryById(Serializable id) throws BasicDaoException{
		T entity = null ;
		try{
			Session session = getCurrentSession();	
			
			entity = (T) session.get(clazz, id);
			session.flush();
			session.clear();			
		}catch(Exception e){
			logger.error(e.getMessage());
			throw new BasicDaoException(e.getMessage());
		}
		return entity;
	}
	
	
	public void deleteById(Serializable id) throws BasicDaoException{
		try{
			Session session = getCurrentSession();			
			T entity = (T)session.get(clazz, id);
			session.delete(entity);
			session.flush();
			session.clear();			
		}catch(Exception e){
			logger.error(e.getMessage());
			throw new BasicDaoException(e.getMessage());
		}
	}
	
	
	public void deleteByIds(List<Serializable> ids) throws BasicDaoException{
		try {
			Session session = getCurrentSession();			
			for(int i = 0 ; i < ids.size() ; i ++){
				if(ids.get(i) != null){
					T entity = (T) session.get(clazz, ids.get(i));
					session.delete(entity);
				}
				if((i + 1 ) % BasicDao.BATCH_SIZE == 0){
					session.flush();
					session.clear();
				}
			}
			session.flush();
			session.clear();			
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BasicDaoException(e.getMessage());
		}
	}
	
	
	public void deleteBatch(List<T> entitys) throws BasicDaoException{
		try {
			Session session = getCurrentSession();			
			for(int i = 0 ; i < entitys.size() ; i ++){
				if(entitys.get(i) != null){
					session.delete(entitys.get(i));
				}
				if((i + 1 ) % BasicDao.BATCH_SIZE == 0){
					session.flush();
					session.clear();
				}
			}
			session.flush();
			session.clear();			
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BasicDaoException(e.getMessage());
		}
	}
	
	
	public void modifyByHql(String hql, Map<String, Object> params) throws BasicDaoException{
		try{
			Session session = getCurrentSession();			
			Query query = QueryTools.getQuery(session, hql, params);
			query.setCacheable(true);
			query.setCacheRegion("queryCacheRegion");
			query.executeUpdate();
			session.flush();
			session.clear();			
		}catch(Exception e){
			logger.error(e.getMessage());
			throw new BasicDaoException(e.getMessage());
		}
	}
	
	
	public Long querySize(QueryParams params) throws BasicDaoException{
		Long size = 0L ;
		try{
			Session session = getCurrentSession();			
			Criteria criteria = QueryTools.createCriteria(session, clazz, params, false, false);
			criteria.setProjection(Projections.rowCount());
			criteria.setCacheable(true);
			criteria.setCacheRegion("queryCacheRegion");
			size = (Long)criteria.uniqueResult();			
			session.flush();
			session.clear();		
		}catch(Exception e){
			logger.error(e.getMessage());
			throw new BasicDaoException(e.getMessage());
		}
		return size ;
	}

	
	public List<T> queryList(QueryParams params) throws BasicDaoException{
		List<T> list ;
		Session session = getCurrentSession();	
		//session.beginTransaction();
		try{
				
			//Criteria criteria = QueryTools.createCriteria(session, clazz, params, true, true);
			Criteria criteria =  session.createCriteria(clazz); 
			criteria.add(Restrictions.eq("username","11111"));
			criteria.setCacheable(true);
			//criteria.setCacheRegion("queryCacheRegion");
			list = (List<T>)criteria.list();			
			//session.flush();
			//session.clear();
			
		}catch(Exception e){
			logger.error(e.getMessage());
			throw new BasicDaoException(e.getMessage());
		}
		return list ;
	}

	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	

	
	/**
	 * 判断model是否继承自BasicModel 如果是的话 则填充。
	 * @param entity
	 * @return
	 */
	public T judegEntity(T entity){
		if(entity instanceof BasicModel){
			
		}
		return entity;		
	}

	public long queryIdMax(T entity,String str) throws BasicDaoException {
		// TODO Auto-generated method stub	
		Session session = getCurrentSession();			
		long c = (Long)session.createCriteria(clazz).setProjection( Projections.projectionList().add(Projections.max(str) ) )
				.uniqueResult() ;		
		return c;
	}

}
