<%--
  Created by IntelliJ IDEA.
  User: Shant
  Date: 4/4/2020
  Time: 11:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body onload="frm.submit()">
<%--<jsp:forward page="/com.fertifa.app.affiliate/check/register" >

    <jsp:param name="Affiliateid" value='<%=request.getParameter("id")%>' />

</jsp:forward>--%>
<form id="frm" action="${pageContext.request.contextPath}/affiliate/check/register" method="post">
    <input type="hidden" name="id" value='<%=request.getParameter("id")%>'>
</form>
</body>
</html>
