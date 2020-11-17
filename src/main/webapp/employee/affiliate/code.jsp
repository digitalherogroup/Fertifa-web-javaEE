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
<html>
<head>
    <c:set value="${pageContext.request.contextPath}" var="root"/>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <jsp:include page="${root}/includes/BundleInclude.jsp"/>
    <title>Register Fertifa</title>
</head>
<body>
<div class="login-container">
    <div class="login-content col">
        <div class="login-content__logo"><a href="${root}/Splash.jsp"> <img src="<%=request.getContextPath()%>/img/logo.svg"
                                                                            alt=""></a></div>
        <h1 class="login-content__title">Fertifa is the mentor throughout your fertility journey.</h1>
    </div>
    <div class="login-form col">
        <h2 class="login-form__title" >Code</h2>
        <%
            if (request.getAttribute("message") != null) {
        %>
        <div style="max-width: 420px;">
            <%=request.getAttribute("message")%>
        </div>

        <%
            }
        %>
        <form action="${root}/employee/CheckUserCode" method="post" class="login-form__content needs-validation" novalidate>
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
            <input type="hidden" value="${requestScope.AffiliateObject.id}" name="affiliateId"/>
            <input type="hidden" value="${requestScope.AffiliateObject.email}" name="affiliateEmail"/>
            <input type="hidden" value="${requestScope.UsersObject.id}" name="id"/>
            <input type="hidden" value="${requestScope.TokenCode}" name="TokenCode"/>
        </form>
        <div class="form-check form-element text-center pl-0">
            <form action="${root}/employee/affiliate/add" method="post">
                <button type="submit" class="form-check-label resend-code-style pl-0">Resend Code</button>
                <input type="hidden" value="${requestScope.AffiliateObject.id}" name="affiliateId"/>
                <input type="hidden" value="${requestScope.AffiliateObject.email}" name="affiliateEmail"/>
                <input type="hidden" value="${requestScope.UsersObject.id}" name="id"/>
            </form>
        </div>
    </div>
</div>
<script>

    var roomIdFromFrontUser = ${requestScope.UsersObject.id};
    var selfId = ${requestScope.UsersObject.id};
    var sessionRoomFromFront = "jok2f4fud_9";
</script>
<jsp:include page="${root}/includes/BundleJsInclude.jsp"/>
</body>
</html>

