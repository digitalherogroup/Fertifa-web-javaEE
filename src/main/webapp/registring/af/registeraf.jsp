<%--
  Created by IntelliJ IDEA.
  User: Shant
  Date: 4/13/2020
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!--http://localhost:8077/registring/af/registeraf.jsp?link=aWRfNjJfZW1haWxfc2toYXlhbGlhbkBnbWFpbC5jb21fY2xpY2tpZF9BNFpNX3JlZ2lkX085RFk2 -->
<jsp:forward page="/employee/affiliate/register">
    <jsp:param name="link" value='<%=request.getParameter("link")%>'/>
</jsp:forward>
</body>
</html>
