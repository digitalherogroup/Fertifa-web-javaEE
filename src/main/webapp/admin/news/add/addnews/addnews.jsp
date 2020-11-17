<%--
  Created by IntelliJ IDEA.
  User: Shant
  Date: 4/6/2020
  Time: 10:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <%
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        httpResponse.setDateHeader("Expires", 0);
    %>
    <title>Title</title>
</head>
<body onload="frm.submit()">
<form id="frm" action="${pageContext.request.contextPath}/admin/news/add" method="post">

</form>

</body>
</html>
