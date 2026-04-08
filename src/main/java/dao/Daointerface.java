package dao;

import java.util.ArrayList;

public interface Daointerface<T> {

    int insert(T t);

    int update(T t);

    int delete(T t);

    ArrayList<T> selectAll();

    T selectById(String id);

    ArrayList<T> selectByCondition(String condition);
}