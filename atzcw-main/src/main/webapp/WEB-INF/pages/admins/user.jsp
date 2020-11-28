<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wen
  Date: 2020/11/24
  Time: 9:34
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

    <%@include file="/WEB-INF/pages/include/base_css.jsp" %>

    <style>
        .tree li {
            list-style-type: none;
            cursor: pointer;
        }

        table tbody tr:nth-child(odd) {
            background: #F4F4F4;
        }

        table tbody td:nth-child(even) {
            color: #C00;
        }
    </style>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 用户维护</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">

            <%@include file="/WEB-INF/pages/include/manager_logibar.jsp" %>

            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="Search...">
            </form>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <%-- 菜单数据栏 --%>
            <div class="tree">

                <%@include file="/WEB-INF/pages/include/manager_menu.jsp" %>

            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form action="${PATH}/admin/index" class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input name="key" value="${param.key}" class="form-control has-success" type="text"
                                       placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button id="batchDelAdminBtn" type="button" class="btn btn-danger"
                            style="float:right;margin-left:10px;"><i
                            class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button type="button" class="btn btn-primary" style="float:right;"
                            onclick="window.location.href='${PATH}/admin/add.html'"><i
                            class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <%-- 列表数据 --%>
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%-- 数据展示 --%>
                            <c:forEach items="${pageInfo.list}" varStatus="vs" var="admin">
                                <tr>
                                    <td>${vs.count}</td>
                                    <td><input id="${admin.id}" type="checkbox"></td>
                                    <td>${admin.loginacct}</td>
                                    <td>${admin.username}</td>
                                    <td>${admin.email}</td>
                                    <td>
                                        <button type="button" class="btn btn-success btn-xs"><i
                                                class=" glyphicon glyphicon-check"></i></button>
                                        <button adminId="${admin.id}" type="button" class="btn btn-primary btn-xs editBtn"><i
                                                class=" glyphicon glyphicon-pencil"></i></button>
                                        <button adminId="${admin.id}" type="button"
                                                class="btn btn-danger btn-xs delAdmin"><i
                                                class=" glyphicon glyphicon-remove "></i></button>
                                    </td>
                                </tr>

                            </c:forEach>

                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <ul class="pagination">
                                        <%-- 分页信息 --%>

                                        <%-- 上一页 --%>
                                        <c:choose>
                                            <%-- 有上一页 --%>
                                            <c:when test="${pageInfo.hasPreviousPage}">
                                                <li>
                                                    <a href="${PATH}/admin/index?pageNum=${pageInfo.pageNum-1}&key=${param.key}">上一页</a>
                                                </li>
                                            </c:when>
                                            <%-- 没有上一页 --%>
                                            <c:otherwise>
                                                <li class="disabled"><a href="javascript:void(0);">上一页</a></li>
                                            </c:otherwise>
                                        </c:choose>

                                        <%-- 中间页 --%>
                                        <c:forEach items="${pageInfo.navigatepageNums}" var="pn">
                                            <c:choose>
                                                <%-- 当前页 --%>
                                                <c:when test="${pageInfo.pageNum == pn}">
                                                    <li class="active"><a href="javascript:void(0);">${pn}<span
                                                            class="sr-only">(current)</span></a></li>
                                                </c:when>
                                                <%-- 其他ye --%>
                                                <c:otherwise>
                                                    <li><a href="${PATH}/admin/index?pageNum=${pn}">${pn}</a></li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>

                                        <%-- 下一页 --%>
                                        <c:choose>
                                            <%-- 有下一页 --%>
                                            <c:when test="${pageInfo.hasNextPage}">
                                                <li>
                                                    <a href="${PATH}/admin/index?pageNum=${pageInfo.pageNum+1}&key=${param.key}">下一页</a>
                                                </li>
                                            </c:when>
                                            <%-- 没有下一页 --%>
                                            <c:otherwise>
                                                <li class="disabled"><a href="javascript:void(0);">下一页</a></li>
                                            </c:otherwise>
                                        </c:choose>

                                    </ul>
                                </td>
                            </tr>

                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%-- 脚本类 --%>
<%@include file="/WEB-INF/pages/include/base_js.jsp" %>

<script type="text/javascript">

    /* 跳转到编辑页面 */
    $(".editBtn").click(function(){
        var adminId = $(this).attr("adminId");
        window.location.href = "${PATH}/admin/edit.html?id="+adminId;
    });
    /* 跳转到编辑页面 */

    /* 全选 全不选 */
    //全选框绑定单击事件
    $("table thead input:checkbox").click(function () {
        $("table tbody :checkbox").prop("checked", this.checked);
    });
    //给子复选框绑定单击事件
    $("table tbody :checkbox").click(function () {
        //判断当前子复选框是不是被全选了，如果是则设置全选框选中，否则取消选中
        var totalCount = $("table tbody :checkbox").length;
        var checkedCount = $("table tbody :checkbox:checked").length;
        $("table thead input:checkbox").prop("checked", totalCount == checkedCount);

    });
    /* 全选 全不选 */

    /* 批量删除 */
    $("#batchDelAdminBtn").click(function () {
        //获取所有被选中选中项的id
        var $chechedIput = $("table tbody input:checkbox:checked");
        console.log($chechedIput);

        if($chechedIput.length==0){
            layer.msg("请选择要删除的选项！！！");
            return;
        }
        var idArr = new Array();
        $chechedIput.each(function () {
            idArr.push(this.id);
        });
        var idsStr = idArr.join(",");

        layer.confirm("是否确定删除这些admin"+idsStr,{"title":"批量删除"},function () {
            layer.closeAll();
            window.location.href = "${PATH}/admin/batchDelAdmin?ids="+idsStr;
        });

        // deleteAdmin(adminid);

    });

    /* 批量删除 */


    //删除
    $("tbody .delAdmin").click(function () {
        var adminid = $(this).attr("adminId");

        // deleteAdmin(idsStr);

        layer.confirm("是否确认删除", {"title": "删除确认"}, function () {
            layer.closeAll();
            <%--window.location.href = "${PATH}/admin/delAdmin?adminId=" + adminid;--%>
            window.location.href = "${PATH}/admin/batchDelAdmin?ids="+adminid;
        })

    });

    function deleteAdmin(idsStr){
        layer.confirm("是否确定删除这些admin"+idsStr,{"title":"批量删除"},function () {
            layer.closeAll();
            window.location.href = "${PATH}/admin/batchDelAdmin?ids="+idsStr;
        });
    }

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

    //通过dom操作查找当前页面对应的父菜单子菜单集合的ul自动展开+当前页面对应的菜单高亮显示
    //当前打开的是用户维护的页面，应该默认设置用户维护父菜单展开并设置用户维护高亮显示
    $("a:contains('用户维护')").parents("ul :hidden").show();//设置ul显示
    $(".list-group-item a:contains('用户维护')").css("color", "red");//高亮显示
    $("a:contains('用户维护')").parents(".list-group-item").removeClass("tree-closed");


    // $("tbody .btn-success").click(function () {
    //     window.location.href = "assignRole.html";
    // });
    // $("tbody .btn-primary").click(function () {
    //     window.location.href = "edit.html";
    // });
</script>
</body>
</html>
