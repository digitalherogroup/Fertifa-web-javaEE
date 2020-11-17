<%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 1/27/2020
  Time: 4:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<jsp:forward page="CheckInvitationUser">
    <jsp:param name="TokenidToCheck" value='<%=request.getParameter("tokenid")%>'/>
    <jsp:param name="FirstToCheck" value='<%=request.getParameter("first")%>'/>
    <jsp:param name="LastToCheck" value='<%=request.getParameter("last")%>'/>
</jsp:forward>
</body>
</html>
