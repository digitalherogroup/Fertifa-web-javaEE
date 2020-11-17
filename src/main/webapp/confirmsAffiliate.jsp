<%--
  Created by IntelliJ IDEA.
  User: Shant
  Date: 4/3/2020
  Time: 3:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:forward page="affiliate/check/check-confirmation">
    <jsp:param name="token" value='<%=request.getParameter("token")%>'/>
</jsp:forward>

</body>
</html>
