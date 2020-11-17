<%--
  Created by IntelliJ IDEA.
  User: Shant
  Date: 4/4/2020
  Time: 4:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        ((HttpServletResponse)response).setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        ((HttpServletResponse)response).setHeader("Pragma", "no-cache"); // HTTP 1.0.
        ((HttpServletResponse)response).setDateHeader("Expires", 0);
    %>
    <title>Title</title>
</head>
<body>
<p> Dash board</p>
</body>
</html>
