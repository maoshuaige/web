package dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.UserinfoDao;
import inits.Userinfo;
import basic.core.BasicDaoImpl;

@Repository
@Transactional()
public class UserinfoDaoImpl extends BasicDaoImpl<Userinfo> implements UserinfoDao {

}
