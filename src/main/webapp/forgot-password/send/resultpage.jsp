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
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>


    <jsp:include page="${root}/includes/BundleInclude.jsp"/>

    <title>Forget Fertifa</title>
</head>
<body>

<div class="login-container">
    <div class="login-content col">
        <div class="login-content__logo"><a href="../Splash.jsp"><img src="<%=request.getContextPath()%>/WEB-INF/jsp/img/logo.svg"
                                                                      alt=""> </a></div>
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
     <%--   <form action="${pageContext.request.contextPath}/forgot-password/send" method="post" class="login-form__content needs-validation" novalidate>
          &lt;%&ndash;  <div class="form-group form-element">
                <input type="email" name="email" class="form-control custom-rounded-input form-element__input"
                       placeholder="Email" value="" required/>
                <div class="invalid-feedback">Please provide a valid email.</div>
            </div>

            <div class="form-group form-element text-center">
                <button type="submit" class="btn btn-primary custom-rounded-btn light-green form-element__button">SEND
                </button>
            </div>&ndash;%&gt;

        </form>--%>
        <div class="form-check form-element text-center">
            <span class="form-check-label pl-0">Or back to <a href="${root}/Splash.jsp">Log in page</a></span>
        </div>
    </div>
</div>
<script>
    var selfId = 0;
    var roomIdFromFrontUser = 0;
    var sessionRoomFromFront = "__";
</script>
<jsp:include page="${root}/includes/BundleJsInclude.jsp"/><%--<jsp:include page="../../includes/CookieManagerUser.jsp"/>--%>
</body>
</html>
