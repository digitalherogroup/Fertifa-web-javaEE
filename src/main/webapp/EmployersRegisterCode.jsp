<%--
  Created by IntelliJ IDEA.
  User: rooter
  Date: 1/13/20
  Time: 4:19 PM
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


    <jsp:include page="includes/BundleInclude.jsp"/>

    <title>Register Fertifa</title>
</head>
<body>
<div class="login-container">
    <div class="login-content col">
        <div class="login-content__logo"><a href="splash.jsp"> <img src="<%=request.getContextPath()%>/img/logo.svg"
                                                                    alt=""></a></div>
        <h1 class="login-content__title">An innovative solution to an essential workplace benefit</h1>
    </div>
    <div class="login-form col">
        <%
            if (request.getAttribute("message") != null) {
        %>
        <%=request.getAttribute("message")%>
        <%
            }
        %>
        <h2 class="login-form__title">Code</h2>
        <form action="CheckUserCode" method="post" class="login-form__content needs-validation" novalidate>
            <div class="form-group form-element">
                <input
                        type="text"
                        name="code"
                        class="form-control custom-rounded-input form-element__input"
                        placeholder="Code"
                        required

                />
                <div class="invalid-feedback">Please Paste the Code.</div>
            </div>

            <div class="form-group form-element text-center">
                <button type="submit"
                        class="btn btn-primary custom-rounded-btn light-green form-element__button">
                    Check Code
                </button>
            </div>
            <input type="hidden" value="${requestScope.UserId}" name="id"/>
            <input type="hidden" value="${requestScope.userEmail}" name="email"/>
            <input type="hidden" value="${requestScope.Token}" name="Token"/>
        </form>
        <div class="form-check form-element text-center pl-0">
            <form action="RegisterUsers?id=${requestScope.UserId}" method="post">
                <button type="submit" class="form-check-label resend-code-style pl-0">Resend Code</button>
            </form>
        </div>
    </div>
</div>
<script>

    var roomIdFromFrontUser = ${requestScope.UserId};
    var selfId = ${requestScope.UserId};
    var sessionRoomFromFront = "jok2f4fud_9";
</script>
<jsp:include page="includes/BundleJsInclude.jsp"/><%--<jsp:include page="includes/CookieManagerUser.jsp"/>--%>
</body>
</html>

