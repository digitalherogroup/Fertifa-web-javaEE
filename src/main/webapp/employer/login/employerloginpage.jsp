<%@ page import="com.fertifa.app.helpser.ChookiManager" %><%--
  Created by IntelliJ IDEA.
  User: Shant
  Date: 1/14/2020
  Time: 12:16 AM
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
    <%

        if("POST".equalsIgnoreCase(request.getMethod())){
            response.sendRedirect(request.getContextPath() + "/employer/employer-dashboard");
        }
    %>

    <jsp:include page="../../includes/BundleInclude.jsp"/>

    <title>Login Fertifa</title>
</head>
<body>
<div class="login-container">
    <div class="login-content col">
        <div class="login-content__logo"><a href="../../splash.jsp"> <img src="${pageContext.request.contextPath}/WEB-INF/jsp/img/logo.svg" alt=""></a> </div>
        <h1 class="login-content__title">An innovative solution to an essential workplace benefit</h1>
    </div>
    <div class="login-form col">
        <h2 class="login-form__title">Employer Login</h2>
        <%
            if (request.getSession().getAttribute("message") != null) {
        %>
        <%=request.getSession().getAttribute("message")%>
        <%
            }
            request.getSession().setAttribute("message",null);
        %>
            <form action="${pageContext.request.contextPath}/employer/employer-dashboard" method="post" class="login-form__content needs-validation" novalidate>

            <div class="form-group form-element">
                <input
                        type="email"
                        name="email"
                        class="form-control custom-rounded-input form-element__input"
                        placeholder="Email"
                        required
                        value=""
                />
                <div class="invalid-feedback">Please provide a valid email.</div>
            </div>
            <div class="form-group form-element">
                <input
                        type="password"
                        name="password"
                        class="form-control custom-rounded-input form-element__input"
                        placeholder="Password"
                        required
                        value=""
                />
                <div class="invalid-feedback">Please fill in password.</div>
            </div>
            <div class="form-group form-element text-center">
                <button type="submit" class="btn btn-primary custom-rounded-btn light-green form-element__button">SIGN IN</button>
            </div>
            <div class="form-check form-element form-check-label pl-0 text-center">
                <a href="${pageContext.request.contextPath}/forgot-password">Forgot your password?</a>
            </div>
        </form>
    </div>
</div>
<script>
    var selfId = 0;
    var roomIdFromFrontUser = 0;
    var sessionRoomFromFront = "__";
</script>
<jsp:include page="../../includes/BundleJsInclude.jsp"/><%--<jsp:include page="../../includes/CookieManagerUser.jsp"/>--%>
</body>
</html>

