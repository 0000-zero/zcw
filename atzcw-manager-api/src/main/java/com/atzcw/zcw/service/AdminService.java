package com.atzcw.zcw.service;

import com.atzcw.zcw.bean.TAdmin;
import com.atzcw.zcw.to.AdminTo;

import java.util.List;

/**
 * @author zero
 * @create 2020-11-23 21:26
 */
public interface AdminService {
    TAdmin doLogin(String loginacct, String userpswd);

    List<TAdmin> getAllAdminsInfo(String key);

    void delAdminById(Integer adminId);

    void saveAdmin(AdminTo tAdmin);

    void batchDeleteAdmin(List<Integer> ids);

    TAdmin getAdminById(Integer id);

    void updateAdmin(TAdmin tAdmin);
}
