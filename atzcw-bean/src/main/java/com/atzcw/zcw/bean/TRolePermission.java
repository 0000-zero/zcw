package com.atzcw.zcw.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TRolePermission {
    private Integer id;

    private Integer roleid;

    private Integer permissionid;

}