package basic.core;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import basic.exception.BasicDaoException;
import basic.exception.BasicServiceException;


/**
 * 
 * @author zj
 * service父类
 * @param <T>
 */

public class BasicServiceImpl<T> implements BasicService<T> {
	private static final Logger logger = LogManager.getLogger(BasicServiceImpl.class);
	
	 
	
/*	@Override
	public User getUser() {
		return null;
	}*/

	public BasicDao<T> getBasicDao(){
		throw new  RuntimeException("无法找到Dao对象！！！");
	} 

	@Transactional
	public T create(T entity) throws BasicServiceException{
		try {
			getBasicDao().create(entity);
		} catch (BasicDaoException e) {
			throw new BasicServiceException(e);
		}
		return entity;
	}

	@Transactional
	public Long querySize(QueryParams params) throws BasicServiceException {
		long size = 0;
		try {
			size = getBasicDao().querySize(params);
		} catch (BasicDaoException e) {
			throw new BasicServiceException(e.getMessage());
		}
		return size ;
	}

	@Transactional
	public List<T> queryList(QueryParams params) throws BasicServiceException {
		List<T> list = null ;
		try {
			list = getBasicDao().queryList(params);
		} catch (BasicDaoException e) {
			throw new BasicServiceException(e.getMessage());
		}
		return list ;
	}

	
	public void createBatch(List<T> entitys) throws BasicServiceException{
		try {
			getBasicDao().createBatch(entitys);
		} catch (BasicDaoException e) {
			throw new BasicServiceException(e.getMessage());
		}
	}

	
	public void delete(T entity) throws BasicServiceException{
		try {
			getBasicDao().delete(entity);
		} catch (BasicDaoException e) {
			throw new BasicServiceException(e.getMessage());
		}
	}

	
	public void update(T entity) throws BasicServiceException {
		try {
			getBasicDao().update(entity);
		} catch (BasicDaoException e) {
			throw new BasicServiceException(e.getMessage());
		}
	}

	
	public void saveOrUpdate(T entity) throws BasicServiceException{
		try {
			getBasicDao().saveOrUpdate(entity);
		} catch (BasicDaoException e) {
			throw new BasicServiceException(e.getMessage());
		}
	}
	
	public T queryById(Serializable id) throws BasicServiceException{
		T entity = null ;
		try {
			
		
			entity = getBasicDao().queryById(id);
		} catch (BasicDaoException e) {
			throw new BasicServiceException(e.getMessage());
		}
		return entity;
	}
}
