<%@ page import="com.fertifa.app.adminSide.CookiesManager.AdminCookiManager" %><%--
  Created by IntelliJ IDEA.
  User: Shant
  Date: 3/31/2020
  Time: 10:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        if (AdminCookiManager.isUserLogged(request, response)) {
            response.sendRedirect(request.getContextPath() + "admin/admin-dashboard");
        }else{
    %>
    <meta http-equiv="refresh" content="2;url=admin/admin-dashboard"/>
    <%}%>
    <title>Title</title>
</head>
<body>

</body>
</html>