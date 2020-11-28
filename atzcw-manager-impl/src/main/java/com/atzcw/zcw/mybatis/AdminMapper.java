package com.atzcw.zcw.mybatis;

import com.atzcw.zcw.bean.TAdmin;
import com.atzcw.zcw.bean.TAdminExample;
import com.atzcw.zcw.mapper.TAdminMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zero
 * @create 2020-11-27 21:49
 */
public class AdminMapper {

    @Autowired
    TAdminMapper tAdminMapper;

    @Autowired
    TAdmin tAdmin;

    public void count(){
        TAdminExample exa = new TAdminExample();
//        exa.createCriteria().

        tAdminMapper.countByExample(exa);
    }





}
