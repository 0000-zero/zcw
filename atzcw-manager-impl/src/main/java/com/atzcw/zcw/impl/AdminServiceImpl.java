package com.atzcw.zcw.impl;

import com.atzcw.zcw.bean.TAdmin;
import com.atzcw.zcw.bean.TAdminExample;
import com.atzcw.zcw.mapper.TAdminMapper;
import com.atzcw.zcw.service.AdminService;
import com.atzcw.zcw.to.AdminTo;
import com.atzcw.zcw.util.DateUtil;
import com.atzcw.zcw.util.MD5Util;
import com.atzcw.zcw.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author zero
 * @create 2020-11-23 21:27
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    TAdminMapper tAdminMapper;

    @Override
    public TAdmin doLogin(String loginacct, String userpswd) {



        String digest = MD5Util.digest(userpswd);

        TAdminExample exa = new TAdminExample();
        exa.createCriteria().andLoginacctEqualTo(loginacct).andUserpswdEqualTo(digest);

        List<TAdmin> admins = tAdminMapper.selectByExample(exa);

        if(CollectionUtils.isEmpty(admins) || admins.size() > 1){
            return null;
        }

        return admins.get(0);
    }

    @Override
    public List<TAdmin> getAllAdminsInfo(String key) {

        TAdminExample exa = new TAdminExample();

        if(!StringUtil.isEmpty(key)){

            //传入了条件就I按条件查询
            //如果Admin的账号  username、邮箱中包含了condition，那么就查询
            exa.createCriteria().andLoginacctLike("%" + key + "%");
            TAdminExample.Criteria o1 = exa.createCriteria().andUsernameLike("%" + key + "%");
            TAdminExample.Criteria o2 = exa.createCriteria().andEmailLike("%" + key + "%");

            exa.or(o1);
            exa.or(o2);

        }

        return tAdminMapper.selectByExample(exa);
    }

    @Override
    public void delAdminById(Integer adminId) {

        tAdminMapper.deleteByPrimaryKey(adminId);

    }

    @Autowired
    PasswordEncoder encoder;

    @Override
    public void saveAdmin(AdminTo adminTo) {

        TAdmin tAdmin = new TAdmin();

        BeanUtils.copyProperties(adminTo,tAdmin);

        //用户唯一校验

        TAdminExample exa = new TAdminExample();
        exa.createCriteria().andLoginacctEqualTo(tAdmin.getLoginacct());
        long l1 = tAdminMapper.countByExample(exa);
        if(l1 > 0L){
            //邮箱被占用，手动抛出异常
            throw  new RuntimeException("账户异常:账号被占用");
        }

        exa.clear();

        exa.createCriteria().andEmailEqualTo(tAdmin.getEmail());
        long l = tAdminMapper.countByExample(exa);
        if(l > 0L){
            //邮箱被占用，手动抛出异常
            throw  new RuntimeException("账户异常:邮箱被占用");
        }


        tAdmin.setUserpswd(encoder.encode(adminTo.getUserpswd()));
        tAdmin.setCreatetime(DateUtil.getFormatTime());

        int i = tAdminMapper.insertSelective(tAdmin);
        if(i != 1){
            throw new RuntimeException("新增失败");
        }
    }

//    @PreAuthorize("hasAnyAuthority('user:delete') and hasAnyRole('R4')")
    @Override
    public void batchDeleteAdmin(List<Integer> ids) {

        TAdminExample exa = new TAdminExample();
        exa.createCriteria().andIdIn(ids);

        tAdminMapper.deleteByExample(exa);
    }

    @Override
    public TAdmin getAdminById(Integer id) {

        return tAdminMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateAdmin(TAdmin tAdmin) {
        tAdminMapper.updateByPrimaryKeySelective(tAdmin);
    }


}
