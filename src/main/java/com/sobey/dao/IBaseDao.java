/*
 * 文件名：IBaseDao.java
 * 版权：Copyright by www.sobye.com
 * 描述：
 * 修改人：[liruilang]
 * 修改时间：2015年12月22日
 */

package com.sobey.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author [liruilang]
 * @version 2015年12月22日-下午6:08:01
 * @see IBaseDao
 */

public interface IBaseDao<T> {
    void save(T t);

    void delete(Serializable id);

    void update(T t);

    List<T> query();

    T get(Serializable id);
}
