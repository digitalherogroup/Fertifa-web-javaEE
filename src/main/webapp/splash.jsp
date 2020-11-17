<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 2/22/2020
  Time: 9:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set value="${pageContext.request.contextPath}" var="root"/>
<!DOCTYPE html>
<html >
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />


    <jsp:include page="${root}/includes/BundleInclude.jsp"/>

    <title>Login Fertifa</title>
</head>
<body>
<div class="login-container">
    <div class="splash-content">
        <div class="splash-content__block">
            <h1 class="splash-content__title">Log in</h1>
            <div class="splash-content__buttons">
                <a href="${root}/employer" class="custom-rounded-btn green">As Employer</a>
                <a href="${root}/employee" class="custom-rounded-btn green">As Employee</a>
            </div>
            <div class="splash-content__description">
                Don't have an account yet?
                <a href="https://fertifa.com/#contact" target="_blank">Contact us.</a>
            </div>
        </div>
    </div>
</div>

<script>
    var selfId = 964;
    var roomIdFromFrontUser = 964;
    var sessionRoomFromFront = "jok2f4fud_9";
</script>
<jsp:include page="${root}/includes/BundleJsInclude.jsp"/><%--<jsp:include page="includes/CookieManagerUser.jsp"/>--%>
<script>
    $(document).ready(function() {
        //$('.order-testing-toast').toast('show');
    });
</script>
<div
        class="order-testing-toast custom-toast fide hide toast"
        role="alert"
        aria-live="assertive"
        aria-atomic="true"
        data-delay="5000"
>
    <div class="toast-header">
        <button type="button" class="ml-2 mb-1 ml-auto close" data-dismiss="toast" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <div class="toast-body"></div>
</div>
</body>
</html>
