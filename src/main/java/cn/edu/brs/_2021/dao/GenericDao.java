package cn.edu.brs._2021.dao;

import cn.edu.brs._2021.entity.User;
import cn.edu.brs._2021.entity.activity.StandardActivity;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.List;


public abstract interface GenericDao<T> {
    public abstract void insert(T entity);

    public abstract int update(T entity);

    public abstract int delete(T entity);

    public abstract int count(T entity);

    public abstract T get(T entity);

    public abstract List<T> selectAll();

}  