package com.hao.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository(value = "baseDao")
public class BaseDao {
	@Resource
	private HibernateTemplate template;

	/**
	 * 获取所有Entity集合
	 * 
	 * @param hql
	 *            :查询条件
	 * @return 泛型集合List<?>
	 */
	public List<?> getAllEntity(String hql) {
		List<?> users = template.find(hql);
		return users;
	}

	/**
	 * 获取符合某些条件的Entity
	 * 
	 * @param hql
	 *            :查询条件
	 * @return 某个对象，与hql中的对象相同
	 */
	public Object getEntity(String hql) {
		List<?> objs = template.find(hql);
		if (objs.size() == 0) {
			return null;
		} else {
			return objs.get(0);
		}
	}
	
	public void save(Object entity){
		try {
			template.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void update(Object entity){
		template.update(entity);
	}
}
