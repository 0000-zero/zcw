<%--
  Created by IntelliJ IDEA.
  User: wen
  Date: 2020/11/24
  Time: 21:04
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
            <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 角色维护</a></div>
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
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input name="key" value="${param.key}" class="form-control has-success" type="text"
                                       placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button id="queryRolesBtn" type="button" class="btn btn-warning"><i
                                class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                            class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button id="addRoleModalBtn" type="button" class="btn btn-primary" style="float:right;"
                    ><i class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%-- 表单数据 --%>
                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <ul class="pagination">
                                        <%-- 分页导航栏信息 --%>
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

<%--  新增角色的模态框 --%>
<div class="modal fade" id="addRoleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="exampleModalLabel">新增角色</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">角色名:</label>
                        <input name="name" type="text" class="form-control" id="recipient-name">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="addRoleBtn" class="btn btn-primary">提交</button>
            </div>
        </div>
    </div>
</div>
<%--   更新角色的模态框 --%>
<div class="modal fade" id="updateRoleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="">更新角色</h4>
            </div>
            <div class="modal-body">
                <form>
                    <%-- 通过隐藏域携带要更新的角色id --%>
                    <input type="hidden" name="id"/>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">角色名:</label>
                        <input name="name" type="text" class="form-control" id="recipient-name2">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button id="updateRoleBtn" type="button" class="btn btn-primary">提交</button>
            </div>
        </div>
    </div>
</div>
<%--   给角色分配权限的模态框 --%>
<div class="modal fade" id="assignPermissionModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">权限分配</h4>
            </div>
            <div class="modal-body">
                <%-- 显示权限树的容器 : ztree的依赖文件已经引入 --%>
                <ul id="permissiontree" class="ztree"></ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button id="assignPermissionBtn" type="button" class="btn btn-primary">确认分配</button>
            </div>
        </div>
    </div>
</div>


<%@include file="/WEB-INF/pages/include/base_js.jsp" %>
<script type="text/javascript">

    //在查询分页数据的方法中  使用全局变量接收 查询到的当前页码
    var currentPageNum;
    var pages;
    var rId;

    /* 修改角色权限 */
    $("#assignPermissionBtn").click(function () {
        //角色roleid 选中的权限id
        //获取树对象
        var $treeObj = $.fn.zTree.getZTreeObj("permissiontree");
        var $treeCkeckedNodes = $treeObj.getCheckedNodes(true);
        var idsArr = new Array();
        $.each($treeCkeckedNodes, function () {
            idsArr.push(this.id);
        });
        var idsStr = idsArr.join();
        // alert(idsStr);

        $.ajax({
            type: "get",
            url: "${PATH}/role/changeCharacterPermission",
            data: {"roleId": rId, "permissionId": idsStr},
            success: function (data) {
                if (data.code == 0) {
                    layer.alert("修改成功");
                    $("#assignPermissionModal").modal("hide");
                }
            }
        });


    });
    /* 修改角色权限 */

    /* 权限分配 */
    function assignPermission(roleId) {
        // alert(roleId);
        rId = roleId;

        //从后端获取数据
        $.ajax({
            "type": "get",
            "url": "${PATH}/permission/getAllPermission",
            "success": function (info) {
                // console.log(info.data);
                var zNodes = info.data;
                //获取当前操作角色已有的权限
                $.ajax({
                    "type": "get",
                    "url": "${PATH}/permission/getCharacterHasPermission",
                    "data": {"roleId": roleId},
                    "success": function (pInfo) {
                        var haspids = pInfo.data;
                        console.log(pInfo);
                        //根绝ztree的文档发现数据源中每个节点的数据只要包含属性checked=true，ztree解析时默认会设置为选中状态
                        if(haspids!=null){

                            $.each(zNodes, function () {
                                if (haspids.indexOf(this.id) >= 0) {
                                    this.checked = true;
                                }
                            });
                        }


                        //解析数据

                        var setting = {
                            check: {
                                enable: true
                            },
                            data: {
                                key: {
                                    name: "title" //指定节点名称要使用的属性
                                },
                                simpleData: {
                                    enable: true,
                                    pIdKey: 'pid' //指定数据加载父子关系的字段
                                }
                            },
                            view: {
                                //参数1：ztree树容器的id ， 参数2：每次生成的树节点对象
                                addDiyDom: function (treeId, treeNode) {
                                    // console.log(treeId,treeNode);
                                    //移除默认的span
                                    $("#" + treeNode.tId + "_ico").remove();
                                    //创建自定义的图标标签
                                    $("#" + treeNode.tId + "_span").before("<span class='" + treeNode.icon + "'></span>")
                                }
                            }
                        };

                        var $ztreeObj = $.fn.zTree.init($("#permissiontree"), setting, zNodes);
                        $ztreeObj.expandAll(true);

                        //加载数据后显示模态框
                        $("#assignPermissionModal").modal("show");
                    }
                });


            }
        });


    }

    /* 权限分配 */

    /* 全选全不选 */
    $("table thead :checkbox").click(function () {
        $("table tbody :checkbox").prop("checked", this.checked);
    });
    $("table tbody").delegate(":checkbox", "click", function () {
        $("table thead :checkbox").prop("checked", $("tbody :checkbox:checked").length == $("tbody :checkbox").length);
    });
    /* 全选全不选 */

    /* 修改 */
    function showUpdateRoleModal(id) {
        // alert(id);

        //弹出模态框回显数据
        $.getJSON("${PATH}/role/getRoleInfo", {"id": id}, function (info) {
            // console.log(info);
            $("#updateRoleModal input[name='id']").val(info.data.id);
            $("#updateRoleModal input[name='name']").val(info.data.name);
            $("#updateRoleModal").modal("toggle");
        })
    }

    //点击提交按钮修改数据
    $("#updateRoleBtn").click(function () {
        $.ajax({
            "type": "post",
            "url": "${PATH}/role/updateRole",
            "data": $("#updateRoleModal form").serialize(),
            "success": function (info) {
                console.log(info);
                $("#updateRoleModal").modal("toggle");
                if (info.code == 0) {
                    //修改成功
                    layer.msg("修改成功");
                    getRoleData(currentPageNum);
                } else {
                    layer.msg("修改失败")
                }
            }
        });
    });
    /* 修改 */

    /* 新增role */
    $("#addRoleModalBtn").click(function () {
        //弹出模态框
        $("#addRoleModal").modal("toggle");
    });
    $("#addRoleBtn").click(function () {
        // alert($("#addRoleModal form").serialize());
        $.ajax({
            type: "post",
            url: "${PATH}/role/addRole",
            data: $("#addRoleModal form").serialize(),
            success: function (info) {
                if (info.code == 0) {
                    //新增成功
                    //关闭模态框
                    $("#addRoleModal").modal("toggle");
                    //提示保存成功
                    layer.msg("添加成功");
                    //跳转到最后一页
                    getRoleData(pages + 1);

                } else {
                    layer.msg("新增失败");
                }
            }
        });
    });

    /* 新增role */

    /* 单个删除 */
    $("table tbody").delegate(".delRoleBtn", "click", function () {
        //获取要删除的角色id
        var roleid = $(this).attr("roleid");
        // alert(roleid);
        layer.confirm("您真的删除此角色吗?", function () {
            $.ajax({
                type: "get",
                "url": "${PATH}/role/delRole",
                "data": {"roleId": roleid},
                "success": function (data) {
                    console.log(data)

                    if(data==403){
                        layer.msg("您访问的资源未授权，请联系管理员解析");
                        return;
                    }else{

                        if (data.code == 0) {
                            //服务器删除成功，刷新当前页
                            getRoleData(currentPageNum);//当前页页码 在异步请求分页数据时可以查询到
                            layer.msg("删除成功...");
                        }
                    }
                }
            });
            layer.closeAll();
        })

    });
    /* 单个删除 */

    getRoleData(1);

    /* 返送一个异步请求获取所有role数据 */
    function getRoleData(pn, key) {
        //返送请求前清空原有数据
        $("table tbody").empty();
        $("table tfoot ul").empty();

        $.ajax({
            "type": "get",
            "url": "${PATH}/role/getRoles",
            "data": {"pageNum": pn, "key": key},
            "success": function (info) {
                // console.log(data);
                //将数据加载到页面
                //表单数据
                resolveFromData(info.data);

                //分页导航栏数据
                resolvePageNavData(info.data);

            }
        });
    }

    /* 返送一个异步请求获取所有role数据 */



    /* 解析表单数据 */
    function resolveFromData(data) {
        $.each(data.list, function (index) {
            $('<tr>\n' +
                '    <td>' + (index + 1) + '</td>\n' +
                '    <td><input type="checkbox"></td>\n' +
                '    <td>' + this.name + '</td>\n' +
                '    <td>\n' +
                '        <button onclick="assignPermission(' + this.id + ')" type="button" class="btn btn-success btn-xs"><i\n' +
                '                class=" glyphicon glyphicon-check"></i></button>\n' +
                '        <button onclick="showUpdateRoleModal(' + this.id + ')" type="button" class="btn btn-primary btn-xs editRoleBtn"><i\n' +
                '                class=" glyphicon glyphicon-pencil"></i></button>\n' +
                '        <button roleid="' + this.id + '" type="button" class="btn btn-danger btn-xs delRoleBtn"><i\n' +
                '                class=" glyphicon glyphicon-remove"></i></button>\n' +
                '    </td>\n' +
                '</tr>').appendTo("table tbody");
        });
    }

    /* 解析表单数据 */

    /* 解析分页数据 */
    function resolvePageNavData(data) {

        currentPageNum = data.pageNum;
        pages = data.pageSize;
        //上一页
        if (data.hasPreviousPage) {
            //有上一页
            $('<li ><a class="navA" pageNum="' + (data.pageNum - 1) + '" href="">上一页</a></li>').appendTo("table tfoot ul");
        } else {
            $('<li class="disabled"><a href="javascript:void(0);">上一页</a></li>').appendTo("table tfoot ul");
        }
        //中间页
        $.each(data.navigatepageNums, function (index) {
            //是不是当前页
            if (this == data.pageNum) {
                $('<li class="active"><a  href="javascript:void(0);">' + this + ' <span class="sr-only">(current)</span></a></li>').appendTo("table tfoot ul");
            } else {
                $('<li class="navA"><a pageNum="' + this + '" class="navA" href="javascript:void(0);">' + this + '</a></li>').appendTo("table tfoot ul");
            }
        });
        //下一页
        if (data.hasNextPage) {
            //有下一页
            $('<li class=""><a class="navA" pageNum="' + (data.pageNum + 1) + '" href="javascript:void(0);">下一页</a></li>').appendTo("table tfoot ul");
        } else {
            $('<li class="disabled"><a href="javascript:void(0);">下一页</a></li>').appendTo("table tfoot ul");
        }

        //异步亲贵成功后创建的标签需要绑定事件，必须在绑定的标签生成之后，或者使用动态委派的方式实现
        //给分页导航栏的超链接绑定单击事件，单击异步获取分页role数据
        // $("tfoot li .navA").click(function () {
        //     alert("xxxx");
        // });

    }

    /* 解析分页数据 */

    /* 点击分页标签跳转 */
    //动态委派实现异步标签绑定
    $("tfoot ul").delegate(".navA", "click", function () {
        // alert($(this).attr("pageNum"));
        var pn = $(this).attr("pageNum");
        var key = $("input[name='key']").val();
        getRoleData(pn, key);
        return false;
    });
    /* 点击分页标签跳转 */

    /* 带条件的查询 */
    $("#queryRolesBtn").click(function () {
        var pn = 1;
        var key = $("input[name='key']").val();
        getRoleData(pn, key);
    });
    /* 带条件的查询 */


    /* -------------------------------------------------------- */
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

    $("tbody .btn-success").click(function () {
        window.location.href = "assignPermission.html";
    });

    $("a:contains('角色维护')").parents("ul :hidden").show();//设置ul显示
    $(".list-group-item a:contains('角色维护')").css("color", "red");//高亮显示
    $("a:contains('角色维护')").parents(".list-group-item").removeClass("tree-closed");

</script>
</body>

</html>

