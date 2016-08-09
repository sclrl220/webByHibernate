/*
 * 文件名：BaseDaoImpl.java 版权：Copyright by www.sobye.com 描述： 修改人：[liruilang] 修改时间：2015年12月22日
 */

package com.sobey.dao.impl;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sobey.dao.IBaseDao;


/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author [liruilang]
 * @version 2015年12月22日-下午6:10:25
 * @see BaseDaoImpl
 */

public class BaseDaoImpl<T> implements IBaseDao<T> {

    // 当前的T类型
    private Class<?> clazz = null;

    @Autowired
    private SessionFactory sessionFactory;

    public BaseDaoImpl() {
        ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
        clazz = (Class<?>)type.getActualTypeArguments()[0];
        System.out.println(clazz);
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(T t) {
        getSession().save(t);
    }

    @Override
    public void delete(Serializable id) {}

    @Override
    public void update(T t) {}

    @Override
    public List<T> query() {
        String hql = "FROM " + clazz.getSimpleName();
        @SuppressWarnings("unchecked")
        List<T> list = getSession().createQuery(hql).list();
        return list;
    }

    @Override
    public T get(Serializable id) {
        return null;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
