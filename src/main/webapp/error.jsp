<%--
  Created by IntelliJ IDEA.
  User: rooter
  Date: 1/13/20
  Time: 4:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html >
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />


    <jsp:include page="includes/BundleInclude.jsp"/>

    <title>Login Fertifa</title>
</head>
<body>
<div id="notfound">
    <div class="notfound">
        <div class="notfound-404">
            <h1>404</h1>
        </div>
        <h2>Oops! Nothing was found</h2>
        <p>
            The page you are looking for might have been removed had its name changed or is temporarily unavailable.
            <a href="javascript:history.back()">Go back</a>
        </p>
    </div>
</div>

<script>
    var selfId = 9;
    var roomIdFromFrontUser = 9;
    var sessionRoomFromFront = "jok2f4fud_9";
</script>
<jsp:include page="includes/BundleJsInclude.jsp"/><jsp:include page="includes/CookieManagerUser.jsp"/>
</body>
</html>
