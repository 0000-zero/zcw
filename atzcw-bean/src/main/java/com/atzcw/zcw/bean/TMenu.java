package com.atzcw.zcw.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TMenu {
    private Integer id;

    private Integer pid;

    private String name;

    private String icon;

    private String url;

    private List<TMenu> children;

}