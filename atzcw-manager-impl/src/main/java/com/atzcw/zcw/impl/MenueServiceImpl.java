package com.atzcw.zcw.impl;

import com.atzcw.zcw.bean.TMenu;
import com.atzcw.zcw.mapper.TMenuMapper;
import com.atzcw.zcw.service.MenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zero
 * @create 2020-11-24 8:31
 */
@Service
public class MenueServiceImpl implements MenueService {

    @Autowired
    TMenuMapper tMenuMapper;

    @Override
    public List<TMenu> getPmenus() {

        List<TMenu> tMenus = tMenuMapper.selectByExample(null);

        List<TMenu> collect = gettMenusChildern(tMenus, 0);

        return collect;
    }

    private List<TMenu> gettMenusChildern(List<TMenu> all, Integer pid) {

        List<TMenu> collect = all.stream().filter(a -> a.getPid() == pid).map(tm -> {
            tm.setChildren(gettMenusChildern(all, tm.getId()));
            return tm;
        }).collect(Collectors.toList());


        return collect;
    }

}
