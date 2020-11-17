<%--
  Created by IntelliJ IDEA.
  User: Shant
  Date: 1/14/2020
  Time: 12:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<jsp:forward page="/check-recovery">
    <jsp:param name="Email" value='<%=request.getParameter("Email")%>'/>
</jsp:forward>
</body>
</html>
