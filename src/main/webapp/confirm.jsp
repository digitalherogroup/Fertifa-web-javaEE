<%--
  Created by IntelliJ IDEA.
  User: rooter
  Date: 1/13/20
  Time: 2:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<jsp:forward page="CheckInvitation">
    <jsp:param name="TokenidToCheck" value='<%=request.getParameter("tokenid")%>'/>
    <jsp:param name="CompanyToCheck" value='<%=request.getParameter("company")%>'/>
</jsp:forward>
</body>
</html>
