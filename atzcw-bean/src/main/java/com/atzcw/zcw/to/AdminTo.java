package com.atzcw.zcw.to;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author zero
 * @create 2020-11-24 14:48
 */
@Data
public class AdminTo {

    @NotEmpty(message = "账户不能为空")
    @Min(value = 3,message = "账户名至少三位")
    private String loginacct;

    @NotEmpty(message = "密码不能为空")
    private String userpswd;

    @NotEmpty(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,16}$",message = "用户名必须为3到16为数字或字母")
    private String username;

    @NotEmpty(message = "邮箱不能为空")
    private String email;



}
