package com.atzcw.zcw;

import com.atzcw.zcw.bean.TAdmin;
import com.atzcw.zcw.mapper.TAdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author zero
 * @create 2020-11-23 18:34
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-bean.xml",
        "classpath:spring/spring-mybatis.xml","classpath:spring/spring-tx.xml"})
public class TestSpring {

    @Autowired
    TAdminMapper tAdminMapper;


    @Test
    public void test(){
        List<TAdmin> tAdmins = tAdminMapper.selectByExample(null);
        System.out.println(tAdmins);
    }

}
