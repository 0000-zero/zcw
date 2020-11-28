<%--
  Created by IntelliJ IDEA.
  User: wen
  Date: 2020/11/24
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="jquery/jquery-2.1.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="script/docs.min.js"></script>
<script src="layer/layer.js"></script>
<script src="script/docs.min.js"></script>
<script src="ztree/jquery.ztree.all-3.5.min.js"></script>

<script>
    $("#loginoutBut").click(function () {
//弹出确认框
        layer.confirm("是否确认注销？", {title: "确认注销", icon: 4}, function (index) {
            layer.close(index);
            window.location.href = "${PATH}/admin/loginout";
        })

    });

    //springsecurity 退出
    $("#logoutA").click(function () {
        layer.confirm("是否确认注销？",{title:"注销确认",icon: 4},function (index) {
            layer.close(index);
            $("#logoutForm").submit();
        })
    });


 </script>