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

    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />


    <jsp:include page="includes/BundleInclude.jsp"/>

    <title>Register Fertifa</title>
</head>
<body>

<div class="login-container">
    <div class="login-content col">
        <div class="login-content__logo"><a href="splash.jsp"><img src="<%=request.getContextPath()%>/img/logo.svg"
                                                                   alt=""> </a></div>
        <h1 class="login-content__title">An innovative solution to an essential workplace benefit</h1>
    </div>
    <div class="login-form col">
        <h2 class="login-form__title">Register</h2>
        <form action="RegisterCompany" method="post" class="login-form__content needs-validation" novalidate>
            <div class="form-group form-element">
                <input disabled
                        type="text"
                        name="company"
                       class="form-control custom-rounded-input form-element__input"
                        placeholder="com.fertifa.app.company name"
                        required
                        value="${requestScope.Company}"
                />
                <div class="invalid-feedback">Please provide a company.</div>
            </div>
            <div class="form-group form-element">
                <input
                        type="text"
                        name="address"
                        class="form-control custom-rounded-input form-element__input"
                        placeholder="Address"
                        required
                        value=""
                />
                <div class="invalid-feedback">Please provide an address.</div>
            </div>
<%--            <div class="form-group form-element">--%>
<%--                <input type="text" name="domain" class="form-control custom-rounded-input form-element__input" placeholder="Domain" value="" />--%>
<%--            </div>--%>
            <div class="form-group form-element">
                <input disabled
                        type="email"
                        name="email"
                       class="form-control custom-rounded-input form-element__input"
                        placeholder="Email"
                        required
                        value="${requestScope.CompanyEmail}"
                />
                <div class="invalid-feedback">Please provide a valid email.</div>
            </div>
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
                <div id="validate_confirm_pass" class="invalid-feedback"></div>
            </div>
            <div class="form-check form-element text-center">
                <input type="checkbox" class="form-check-input" id="aggried" required />
                <label class="form-check-label" for="aggried" >I agree with
                    <a href="TermsCondition.jsp" target="_blank">Fertifa Terms & Conditions</a></label>

            </div>
            <div class="form-group form-element text-center">
                <button type="submit"
                        class="btn btn-primary custom-rounded-btn light-green form-element__button">
                    REGISTER</button>
            </div>
            <input type="hidden" value="${requestScope.ID}" name="id"/>
            <input type="hidden" value="${requestScope.CompanyEmail}" name="email"/>
            <input type="hidden" value="${requestScope.Company}" name="company"/>
            <input type="hidden" value="${requestScope.AffiliateId}" name="AffiliateId"/>
        </form>
    </div>
</div>
<script>
    var roomIdFromFrontUser = ${requestScope.ID};
    var selfId = ${requestScope.ID};
    var sessionRoomFromFront = "jok2f4fud_9";
</script>
<jsp:include page="includes/BundleJsInclude.jsp"/><jsp:include page="includes/CookieManagerUser.jsp"/>
</body>
</html>

