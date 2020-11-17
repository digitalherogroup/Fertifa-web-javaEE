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

    <jsp:include page="${pageContext.request.contextPath}/includes/BundleInclude.jsp"/>

    <title>Register Fertifa</title>
</head>
<body>
<div class="login-container">
    <div class="login-content col">
        <div class="login-content__logo">
           <img src="<%=request.getContextPath()%>/WEB-INF/jsp/img/logo.svg" alt=""/> </div>
        <h1 class="login-content__title">An innovative solution to an essential workplace benefit</h1>
    </div>
    <div class="login-form col">
        <h2 class="login-form__title">Register</h2>

        <form action="${pageContext.request.contextPath}/affiliate/add" method="post" class="login-form__content needs-validation" novalidate>
            <div class="form-group form-element">
                <input disabled
                       type="text"

                       class="form-control custom-rounded-input form-element__input"
                       placeholder="First name"
                       required
                       value="${requestScope.affiliateDetails.firstName}"
                />
            </div>
            <div class="form-group form-element">
                <input disabled
                       type="text"

                       class="form-control custom-rounded-input form-element__input"
                       placeholder="Last name"
                       required
                       value="${requestScope.affiliateDetails.lastName}"
                />
            </div>
            <div class="form-group form-element">
                <input disabled
                       type="email"

                       class="form-control custom-rounded-input form-element__input"
                       placeholder="Email"
                       required
                       value='${requestScope.affiliateDetails.email}'
                />
            </div>
            <div class="form-group d-flex align-items-center form-element">
                <div class="custom-control custom-radio mr-3">
                    <input class="form-check-input" type="radio" name="gender" id="male" value="male" required/>
                    <label class="form-check-label mb-0" for="male">Male</label>
                </div>
                <div class="custom-control custom-radio mr-3">
                    <input class="form-check-input" type="radio" name="gender" id="female" value="female"
                           required/>
                    <label class="form-check-label mb-0" for="female">Female</label>
                </div>
               <%-- <select class="selectpicker custom-selectpick custom-rounded-dropdown mr-0" required>
                    <option value="" name="age" selected>Age</option>
                    <c:forEach items="${requestScope.AgeList}" var="ages">
                        <option value="18">${ages}</option>
                    </c:forEach>
                </select>--%>
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
                <input type="checkbox" class="form-check-input" id="aggried" required>
                <label class="form-check-label" for="aggried">I agree with <a href="${pageContext.request.contextPath}/affiliate/terms/TermsCondition.jsp" target="_blank">Fertifa Terms & Conditions</a></label>
            </div>
            <div class="form-group form-element text-center">
                <button type="submit" class="btn btn-primary custom-rounded-btn light-green form-element__button">SIGN UP</button>
                <input type="hidden" name="id" value="${requestScope.affiliateDetails.id}">
                <input type="hidden" name="email" value="${requestScope.affiliateDetails.email}">
                <input type="hidden" name="fistName" value="${requestScope.affiliateDetails.firstName}">
                <input type="hidden" name="lastName" value="${requestScope.affiliateDetails.lastName}">
            </div>
        </form>
    </div>
</div>
<script>

    var roomIdFromFrontUser = ${requestScope.affiliateDetails.id};
    var selfId = ${requestScope.affiliateDetails.id};
    var sessionRoomFromFront = "jok2f4fud_9";
</script>
<jsp:include page="${pageContext.request.contextPath}/includes/BundleJsInclude.jsp"/>
<%--<jsp:include page="<%=request.getContextPath()%>/includes/CookieManagerUser.jsp"/>--%>
</body>
</html>

