<%--
  Created by IntelliJ IDEA.
  User: wen
  Date: 2020/11/23
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <%@include file="/WEB-INF/pages/include/base_css.jsp"%>

    <style>
        .tree li {
            list-style-type: none;
            cursor: pointer;
        }

        .tree-closed {
            height: 40px;
        }

        .tree-expanded {
            height: auto;
        }
    </style>
</head>

<body>


<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <%-- 顶部状态栏 --%>
    <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 错误页面</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <%@include file="/WEB-INF/pages/include/manager_logibar.jsp"%>
            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="查询">
            </form>
        </div>
    </div>
</nav>


<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">

            <%-- 菜单数据栏 --%>
            <div class="tree">
                <%@include file="/WEB-INF/pages/include/manager_menu.jsp"%>
            </div>

        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">错误页面</h1>

            <div class="row placeholders">

                <%-- 错误页面 --%>
                <h2>您的权限不足，请联系管理员！！！</h2>
                <h2> ${errorMsg} </h2>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/pages/include/base_js.jsp"%>
<script type="text/javascript">


    $(function () {
        $(".list-group-item").click(function () {
            if ($(this).find("ul")) {
                $(this).toggleClass("tree-closed");
                if ($(this).hasClass("tree-closed")) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });
    });
</script>
</body>
</html>
