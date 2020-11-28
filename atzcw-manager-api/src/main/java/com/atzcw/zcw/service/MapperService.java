package com.atzcw.zcw.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * @author zero
 * @create 2020-11-27 21:34
 */
public interface MapperService<T> {

    //保存单个
    boolean save(T entity);
    //批量保存
    boolean saveBatch(Collection<T> entityList);
    //保存或更新
    boolean saveOrUpdateBatch(Collection<T> entityList);


    //删除 通过id
    boolean removeById(Serializable id);
    //通过条件删除
    boolean removeByMap(Map<String,Object> columnMap);
    //通过自定义条件删除
//    boolean remove(Wrapper<T> wrapper);

    //通过id批量删除
    boolean removeByIds(Collection<? extends Serializable> idList);

    //更新  通过id
    boolean updateById(T entity);




}
