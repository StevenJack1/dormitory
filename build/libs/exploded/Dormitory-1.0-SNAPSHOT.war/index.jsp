<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Yzf
  Date: 2017/7/18/018
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<shiro:hasRole name="student">--%>
  <%--<%@include file="student.jsp"%>--%>
<%--</shiro:hasRole>--%>
<shiro:hasRole name="dormitoryManager">
  <%@include file="dormitoryManager.jsp"%>
</shiro:hasRole>
<shiro:hasRole name="admin">
  <%@include file="admin.jsp"%>
</shiro:hasRole>
