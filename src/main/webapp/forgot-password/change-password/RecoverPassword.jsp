<%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 1/14/2020
  Time: 1:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html >
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

    <jsp:include page="../../includes/BundleInclude.jsp"/>

    <title>Forget Fertifa</title>
</head>
<body>
<div class="login-container">
    <div class="login-content col">
        <div class="login-content__logo"><a href="../../splash.jsp"><img src="<%=request.getContextPath()%>/WEB-INF/jsp/img/logo.svg"
                                                                         alt=""> </a></div>
        <h1 class="login-content__title">An innovative solution to an essential workplace benefit</h1>
    </div>
    <div class="login-form col">
        <h2 class="login-form__title">New password</h2>
        <form action="${pageContext.request.contextPath}/change-password" method="post" class="login-form__content needs-validation" novalidate>
            <div class="form-group form-element">
                <input
                        type="password"
                        name="password"
                        id="password"
                        class="form-control custom-rounded-input form-element__input"
                        placeholder="Password"
                        required
                        value=""
                />
                <div class="invalid-feedback">Please fill in password.</div>
            </div>
            <div class="form-group form-element">
                <input
                        type="password"
                        name="confirm_password"
                        id="confirm_password"
                        class="form-control custom-rounded-input form-element__input"
                        placeholder="Confirm Password"
                        required
                        value=""
                />
                <div id="validate_confirm_pass" class="invalid-feedback">Please fill in password.</div>
            </div>
            <div class="form-group form-element text-center">
                <button type="submit" class="btn btn-primary custom-rounded-btn light-green form-element__button">CONFIRM</button>
            </div>
            <input type="hidden" value='<%=request.getParameter("Email")%>' name="email"/>
        </form>
    </div>
</div>
<script>
    var selfId = 0;
    var roomIdFromFrontUser = 0;
    var sessionRoomFromFront = "__";
</script>
<jsp:include page="../../includes/BundleJsInclude.jsp"/><jsp:include page="../../includes/CookieManagerUser.jsp"/>
</body>
</html>

