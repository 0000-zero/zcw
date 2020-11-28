package com.atzcw.zcw.mapper;

import com.atzcw.zcw.bean.TAdmin;
import com.atzcw.zcw.service.MapperService;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * @author zero
 * @create 2020-11-27 21:45
 */
public class AdminMapper implements MapperService<TAdmin> {
    @Override
    public boolean save(TAdmin entity) {
        return false;
    }

    @Override
    public boolean saveBatch(Collection<TAdmin> entityList) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<TAdmin> entityList) {
        return false;
    }

    @Override
    public boolean removeById(Serializable id) {
        return false;
    }

    @Override
    public boolean removeByMap(Map<String, Object> columnMap) {
        return false;
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return false;
    }

    @Override
    public boolean updateById(TAdmin entity) {
        return false;
    }
}
