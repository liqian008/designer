package com.bruce.designer.dao;

import java.util.List;

public interface IBaseDao<T, Id>{
    
    public int save(T t);
    
    public int updateById(T t);
    
    public int deleteById(Id id);
    
    public T loadById(Id id);
    
    public List<T> queryAll();
    
}
