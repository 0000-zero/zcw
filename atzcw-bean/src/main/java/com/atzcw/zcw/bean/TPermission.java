package com.atzcw.zcw.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TPermission {
    private Integer id;

    private String name;

    private String title;

    private String icon;

    private Integer pid;


}