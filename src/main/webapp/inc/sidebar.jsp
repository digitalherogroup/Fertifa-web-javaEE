<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Shant
  Date: 4/21/2020
  Time: 10:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set value="${pageContext.request.contextPath}" var="root"/>
<nav id="mobile-sidebar" class="bg-light sidebar">
    <div class="sidebar__sticky" id="slim-scroll">
        <div class="side-logo">
            <button id="dismissSidebar" class="sidebar__toggle close-sidebar-menu">
                <img src="${root}/img/cross-white.svg" alt="" />
            </button>
            <a href="${root}/employee/employee-dashboard" class="side-logo__link">
                <img src="${root}/WEB-INF/jsp/img/logo.svg" alt="Logo" />
            </a>
        </div>
        <div class="sidebar__buttons">
            <a href="" class="btn btn-info custom-rounded-btn green">BOOK AN APPOINTMENT</a>
            <a href="" class="btn btn-info custom-rounded-btn green-line-dark">ORDER TESTINGS</a>
        </div>
        <ul class="nav flex-row first">
            <li class="nav__item">
                <a class="nav-link active" href="#"> <img src="${root}/WEB-INF/jsp/img/message.svg" alt="" /> com.fertifa.app.Messages </a>
            </li>
            <li class="nav__item">
                <a class="nav-link active" href="#">
                    <img src="${root}/WEB-INF/jsp/img/cart.svg" alt="" style="margin-left: -5px;" /> Shopping Cart
                </a>
            </li>
            <li class="nav__item ${requestScope.journey}">
                <a class="nav-link" href="#">
                    <img src="${root}/WEB-INF/jsp/img/journey.svg" alt="" /> My Journey </a>
            </li>
            <li class="nav__item">
                <a class="nav-link" href="#"> <img src="${root}/WEB-INF/jsp/img/health.svg" alt="" /> My Health History </a>
            </li>
            <li class="nav__item">
                <a class="nav-link" href="#"> <img src="${root}/WEB-INF/jsp/img/orders.svg" alt="" /> My Orders </a>
            </li>
            <li class="nav__item">
                <a class="nav-link" href="#"> <img src="${root}/WEB-INF/jsp/img/benefits.svg" alt="" /> My Benefits </a>
            </li>
        </ul>
        <ul class="nav flex-column">
            <li class="nav__item">
                <a class="nav-link" href="#"> <img src="${root}/WEB-INF/jsp/img/help.svg" alt="" /> Help & Contact </a>
            </li>
            <li class="nav__item">
                <a class="nav-link" href="#"> <img src="${root}/WEB-INF/jsp/img/education.svg" alt="" /> Education </a>
            </li>
            <li class="nav__item">
                <a class="nav-link" href="#"> <img src="${root}/WEB-INF/jsp/img/chat.svg" alt="" /> Submit Feedback </a>
            </li>
        </ul>
        <div class="footer">
            <a class="nav-link footer__logout" href="${root}/employee/logout"> <img src="${root}/WEB-INF/jsp/img/exit.svg" alt="" /> Log out </a>
            <div class="footer__content">
                <p>v. 1.3.0</p>
                <p>
                    2020 © Fertifa Ltd. <br />
                    All rights reserved
                </p>
            </div>
        </div>
    </div>
</nav>
