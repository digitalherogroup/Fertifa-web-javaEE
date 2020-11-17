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
    <c:set var="root" value="http://m.second.fertifabenefits.com"/>
   <jsp:include page="http://m.second.fertifabenefits.com/inc/header.jsp"/>
</head>
<body>
<div class="overlay"></div>

<div class="login-container">
    <div class="login-container__background"></div>
    <div class="login-content__logo">
        <img src="${root}/img/logo.svg" alt="" />
    </div>
    <div class="login-form">
        <h1 class="login-form__title">Sign up</h1>
        <%
            if (request.getSession().getAttribute("message") != null) {
        %>
        <div class="alert alert-danger">
            <%=request.getSession().getAttribute("message")%>
        </div>
        <%
            }
            request.getSession().setAttribute("message",null);
        %>
        <form action="${root}/RegisterUsers" method="post" class="login-form__content needs-validation" novalidate>
            <div class="form-group form-element mb-4">
                <input disabled
                        type="text"
                        name="firstName"
                        class="form-control custom-rounded-input form-element__input"
                        placeholder="First Name"
                        required
                        value='${requestScope.UserFirstName}'
                />
            </div>
            <div class="form-group form-element mb-4">
                <input disabled
                        type="text"
                        name="lastName"
                        class="form-control custom-rounded-input form-element__input"
                        placeholder="Last Name"
                        required
                        value='${requestScope.UserLasttName}'
                />
            </div>
            <div class="form-group form-element mb-4">
                <input disabled
                        type="email"
                        name="email"
                        class="form-control custom-rounded-input form-element__input"
                        placeholder="Work e-mail"
                        required
                        value='${requestScope.UserEmail}'
                />
            </div>
            <div class="form-group d-flex align-items-center justify-content-center form-element mb-4 pl-3">
                <label class="custom-check mw-60 pl-0 mb-0 mr-2">
                    <input type="radio" class="form-check-input" name="gender" value="male" required />
                    <i class="dark-white"></i> Male
                </label>

                <label class="custom-check mw-80 pl-0 mb-0 mr-auto">
                    <input type="radio" class="form-check-input" name="gender" value="female" required />
                    <i class="dark-white"></i> Female
                </label>

                <select class="selectpicker custom-selectpick custom-rounded-dropdown mw-110 mr-0" name="age" required>
                        <option value="" name="age"  selected>Age</option>
                        <c:forEach items="${requestScope.AgeList}" var="ages">
                            <option value="${ages}">${ages}</option>
                        </c:forEach>
                </select>
            </div>

            <div class="form-group form-element mb-4">
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
            <div class="form-group form-element mb-4">
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
            <div class="form-group form-element text-center">
                <label class="form-check-label custom-check">
                    <input type="checkbox" class="form-check-input" id="aggried" required />
                    <i class="dark-white"></i>
                    <label for="aggried">I agree with</label>
                    <a href="${root}/privacy/" target="_blank">Fertifa Terms & Conditions</a>
                </label>
            </div>
            <div class="form-group form-element text-center">
                <button type="submit" class="btn btn-primary custom-rounded-btn purple form-element__button">
                    SIGN UP
                </button>
            </div>
            <input type="hidden" name="id" value="${requestScope.users.id}">
            <input type="hidden" name="email" value="${requestScope.UserEmail}">
            <input type="hidden" name="fistName" value="${requestScope.UserFirstName}">
            <input type="hidden" name="lastName" value="${requestScope.UserLasttName}">
        </form>
    </div>
    <div class="login-form__copyright pt-3">
        <span>2020 © Fertifa Limited. All rights reserved</span>
        <a href="${root}/privacy/" target="_blank" class="mb-4">Privacy policy</a>
    </div>
</div>

<script>
    var selfId = ${requestScope.users.id};
    var roomIdFromFrontUser = ${requestScope.users.id};
    var sessionRoomFromFront = "jok2f4fud_9";
</script>
<script src="${root}/js/mobile.bundle.js"></script>
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