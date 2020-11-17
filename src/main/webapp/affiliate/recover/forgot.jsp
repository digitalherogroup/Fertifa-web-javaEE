<%--
  Created by IntelliJ IDEA.
  User: Shant
  Date: 1/14/2020
  Time: 12:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html >
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>


    <jsp:include page="${pageContext.request.contextPath}/includes/BundleInclude.jsp"/>

    <title>Forget Fertifa</title>
</head>
<body>

<div class="login-container">
    <div class="login-content col">
        <div class="login-content__logo"><img src="<%=request.getContextPath()%>/WEB-INF/jsp/img/logo.svg"
                                                                   alt=""> </div>
        <h1 class="login-content__title">An innovative solution to an essential workplace benefit</h1>
    </div>
    <div class="login-form col">
        <h2 class="login-form__title">Forgot password</h2>

        <%
            if (request.getAttribute("message") != null) {
        %>
        <span class="alert alert-danger">
            <%=request.getAttribute("message")%></span>
        <%
            }
        %>
        <form action="${pageContext.request.contextPath}/affiliate/recover/recovering" method="post" class="login-form__content needs-validation" novalidate>
            <div class="form-group form-element">
                <input type="email" name="email" class="form-control custom-rounded-input form-element__input"
                       placeholder="Email" value="" required/>
                <div class="invalid-feedback">Please provide a valid email.</div>
            </div>

            <div class="form-group form-element text-center">
                <button type="submit" class="btn btn-primary custom-rounded-btn light-green form-element__button">SEND
                </button>
            </div>
            <div class="form-check form-element text-center">
                <span class="form-check-label pl-0">Or back to <a href="<%=request.getContextPath()%>/affiliate/login">Log in page</a></span>
            </div>
        </form>
    </div>
</div>
<script>
    var selfId = 0;
    var roomIdFromFrontUser = 0;
    var sessionRoomFromFront = "__";
</script>
<jsp:include page="${pageContext.request.contextPath}/includes/BundleJsInclude.jsp"/>
<%--<jsp:include page="includes/CookieManagerUser.jsp"/>--%>
</body>
</html>
