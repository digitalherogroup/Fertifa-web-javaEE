<%--
  Created by IntelliJ IDEA.
  User: Shant
  Date: 4/4/2020
  Time: 4:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        httpResponse.setDateHeader("Expires", 0);
    %>
</head>
<body onload="frm.submit()">
<form id="frm" action="${pageContext.request.contextPath}/affiliate/dashboard" method="post">
    <input type="hidden" name="id" value='<%=request.getParameter("id")%>'>
</form>

</body>
</html>
