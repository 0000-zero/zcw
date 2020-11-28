<%--
  Created by IntelliJ IDEA.
  User: wen
  Date: 2020/11/24
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<ul style="padding-left:0px;" class="list-group">

    <%-- 遍历菜单栏 --%>
    <c:choose>
        <c:when test="${empty sessionScope.pmenus}">
            <h3 style="color: chocolate">查询菜单失败</h3>
        </c:when>
        <c:otherwise>
            <c:forEach var="pmenu" items="${sessionScope.pmenus}">
                <c:choose>
                    <c:when test="${empty pmenu.children}">
                        <%-- 没有子菜单的菜单 --%>
                        <li class="list-group-item tree-closed">
                            <a href="${PATH}/${pmenu.url}"><i class="${pmenu.icon}"></i> ${pmenu.name}
                            </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <%-- 有子菜单的菜单 --%>
                        <li class="list-group-item tree-closed">
                                            <span>
                                                <i class="${pmenu.icon}"></i> ${pmenu.name}
                                                <span class="badge" style="float:right">${pmenu.children.size()}</span>
                                            </span>

                            <ul style="margin-top:10px;display:none;">
                                    <%-- 子菜单 --%>
                                <c:forEach items="${pmenu.children}" var="child">
                                    <li style="height:30px;">
                                        <a href="${PATH}/${child.url}"><i
                                                class="${child.icon}"></i>${child.name}</a>
                                    </li>
                                </c:forEach>
                            </ul>

                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </c:otherwise>
    </c:choose>


</ul>