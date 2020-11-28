<%--
  Created by IntelliJ IDEA.
  User: wen
  Date: 2020/11/25
  Time: 19:40
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
    </style>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 许可维护</a></div>
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
                <div class="panel-heading"><i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                            class="glyphicon glyphicon-question-sign"></i></div>
                </div>
                <div class="panel-body">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">帮助</h4>
            </div>
            <div class="modal-body">
                <div class="bs-callout bs-callout-info">
                    <h4>没有默认类</h4>
                    <p>警告框没有默认类，只有基类和修饰类。默认的灰色警告框并没有多少意义。所以您要使用一种有意义的警告类。目前提供了成功、消息、警告或危险。</p>
                </div>
                <div class="bs-callout bs-callout-info">
                    <h4>没有默认类</h4>
                    <p>警告框没有默认类，只有基类和修饰类。默认的灰色警告框并没有多少意义。所以您要使用一种有意义的警告类。目前提供了成功、消息、警告或危险。</p>
                </div>
            </div>
            <!--
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary">Save changes</button>
            </div>
            -->
        </div>
    </div>
</div>
<%@include file="/WEB-INF/pages/include/base_js.jsp" %>
<script type="text/javascript">

    /* 获取menu数据 */
    $.ajax({
        "type": "get",
        "url": "${PATH}/menu/getMenus",
        "success":function (info) {
            //在请求成功的方法中编写的变量和函数都属于局部变量和函数，作用域在左边{开始到右边}结束的地方
            console.log(info.data);
            var pmenus = info.data;
            //给pmenus添加根节点
            pmenus.push({id:0 , name:"系统权限菜单" , icon:"glyphicon glyphicon-plane"});


            //ztree的配置对象
            var setting = {
                view:{
                    //当ztree树生成时，每创建一个树的节点对象  ztree都会调用一次此方法
                    addDiyDom: function (treeId , treeNode) {//参数1：ztree树容器的id ， 参数2：每次生成的额树节点对象
                        //在方法中可view: {以对节点对象的属性和属性值进行修改[dom操作 ]
                        console.log(treeNode);
                        //删除自动生成的图标：只需要将图标的span标签移除即可
                        $("#"+treeNode.tId+"_ico").remove();
                        //生成自定义图标的span 插入到显示标题的span标签前即可
                        $("#"+treeNode.tId+"_span").before("<span class='"+treeNode.icon+"'></span>");

                    },
                    //鼠标悬停生成按钮组
                    addHoverDom: function(treeId, treeNode){
                        var aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")
                        /*aObj.attr("href", "javascript:;");*/
                        if (treeNode.editNameFlag || $("#btnGroup"+treeNode.tId).length>0) return;
                        var s = '<span id="btnGroup'+treeNode.tId+'">';
                        if ( treeNode.level == 0 ) {//根节点  只能添加子节点
                            //添加
                            s += '<a onclick="addMenu('+treeNode.id+')" class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="javascript:void(0);" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
                        } else if ( treeNode.level == 1 ) {//枝节点
                            //更新
                            s += '<a onclick="updateMenu('+treeNode.id+')" class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="javascript:void(0);" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
                            if (treeNode.children.length == 0) {
                                //删除
                                s += '<a onclick="delMenu('+treeNode.id+')" class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="javascript:void(0);" >&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
                            }
                            //添加
                            s += '<a onclick="addMenu('+treeNode.id+')" class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="javascript:void(0);" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
                        } else if ( treeNode.level == 2 ) {
                            //更新
                            s += '<a onclick="updateMenu('+treeNode.id+')" class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="javascript:void(0);" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
                            //删除
                            s += '<a onclick="delMenu('+treeNode.id+')" class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="javascript:void(0);">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
                        }

                        s += '</span>';
                        aObj.after(s);
                    },
                    //鼠标离开删除按钮组
                    removeHoverDom: function(treeId, treeNode){
                        $("#btnGroup"+treeNode.tId).remove();
                    }
                },
                data: {
                    simpleData: {
                        enable: true,
                        pIdKey: "pid"
                    },
                    key: {
                        //禁止树节点点击跳转
                        url: "xasdasdasdasdasdas"
                    }
                }
            };
            //ztree数据源
            var zNodes = pmenus;
            //容器
            //ztree解析数据生成ztree树
            //参数1：ul容器 ， 参数2：配置，参数3：数据源
            var $ztreeObj = $.fn.zTree.init($("#treeDemo") , setting , zNodes);
            $ztreeObj.expandAll(true);

        }
    });
    /* 获取menu数据 */

    $("a:contains('菜单维护')").parents("ul :hidden").show();//设置ul显示
    $(".list-group-item a:contains('菜单维护')").css("color", "red");//高亮显示
    $("a:contains('菜单维护')").parents(".list-group-item").removeClass("tree-closed");


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

        var setting = {
            view: {
                selectedMulti: false,
                addDiyDom: function (treeId, treeNode) {
                    var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")
                    if (treeNode.icon) {
                        icoObj.removeClass("button ico_docu ico_open").addClass("fa fa-fw " + treeNode.icon).css("background", "");
                    }
                },
                addHoverDom: function (treeId, treeNode) {
                    var aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")
                    aObj.attr("href", "javascript:;");
                    if (treeNode.editNameFlag || $("#btnGroup" + treeNode.tId).length > 0) return;
                    var s = '<span id="btnGroup' + treeNode.tId + '">';
                    if (treeNode.level == 0) {
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
                    } else if (treeNode.level == 1) {
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="#" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
                        if (treeNode.children.length == 0) {
                            s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" >&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
                        }
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
                    } else if (treeNode.level == 2) {
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="#" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
                    }

                    s += '</span>';
                    aObj.after(s);
                },
                removeHoverDom: function (treeId, treeNode) {
                    $("#btnGroup" + treeNode.tId).remove();
                }
            },
            async: {
                enable: true,
                url: "tree.txt",
                autoParam: ["id", "name=n", "level=lv"]
            },
            callback: {
                onClick: function (event, treeId, json) {

                }
            }
        };
        //$.fn.zTree.init($("#treeDemo"), setting); //异步访问数据

        var d = [{
            "id": 1,
            "pid": 0,
            "seqno": 0,
            "name": "系统权限菜单",
            "url": null,
            "icon": "fa fa-sitemap",
            "open": true,
            "checked": false,
            "children": [{
                "id": 2,
                "pid": 1,
                "seqno": 0,
                "name": "控制面板",
                "url": "dashboard.htm",
                "icon": "fa fa-desktop",
                "open": true,
                "checked": false,
                "children": []
            }, {
                "id": 6,
                "pid": 1,
                "seqno": 1,
                "name": "消息管理",
                "url": "message/index.htm",
                "icon": "fa fa-weixin",
                "open": true,
                "checked": false,
                "children": []
            }, {
                "id": 7,
                "pid": 1,
                "seqno": 1,
                "name": "权限管理",
                "url": "",
                "icon": "fa fa-cogs",
                "open": true,
                "checked": false,
                "children": [{
                    "id": 8,
                    "pid": 7,
                    "seqno": 1,
                    "name": "用户管理",
                    "url": "user/index.htm",
                    "icon": "fa fa-user",
                    "open": true,
                    "checked": false,
                    "children": []
                }, {
                    "id": 9,
                    "pid": 7,
                    "seqno": 1,
                    "name": "角色管理",
                    "url": "role/index.htm",
                    "icon": "fa fa-graduation-cap",
                    "open": true,
                    "checked": false,
                    "children": []
                }, {
                    "id": 10,
                    "pid": 7,
                    "seqno": 1,
                    "name": "许可管理",
                    "url": "permission/index.htm",
                    "icon": "fa fa-check-square-o",
                    "open": true,
                    "checked": false,
                    "children": []
                }]
            }, {
                "id": 11,
                "pid": 1,
                "seqno": 1,
                "name": "资质管理",
                "url": "",
                "icon": "fa fa-certificate",
                "open": true,
                "checked": false,
                "children": [{
                    "id": 12,
                    "pid": 11,
                    "seqno": 1,
                    "name": "分类管理",
                    "url": "cert/type.htm",
                    "icon": "fa fa-th-list",
                    "open": true,
                    "checked": false,
                    "children": []
                }, {
                    "id": 13,
                    "pid": 11,
                    "seqno": 1,
                    "name": "资质管理",
                    "url": "cert/index.htm",
                    "icon": "fa fa-certificate",
                    "open": true,
                    "checked": false,
                    "children": []
                }]
            }, {
                "id": 15,
                "pid": 1,
                "seqno": 1,
                "name": "流程管理",
                "url": "process/index.htm",
                "icon": "fa fa-random",
                "open": true,
                "checked": false,
                "children": []
            }, {
                "id": 16,
                "pid": 1,
                "seqno": 1,
                "name": "审核管理",
                "url": "",
                "icon": "fa fa-check-square",
                "open": true,
                "checked": false,
                "children": [{
                    "id": 17,
                    "pid": 16,
                    "seqno": 1,
                    "name": "实名认证人工审核",
                    "url": "process/cert.htm",
                    "icon": "fa fa-check-circle-o",
                    "open": true,
                    "checked": false,
                    "children": []
                }]
            }]
        }];
        $.fn.zTree.init($("#treeDemo"), setting, d);
    });
</script>
</body>
</html>

