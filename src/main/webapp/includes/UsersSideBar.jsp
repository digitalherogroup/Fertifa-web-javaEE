<%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 1/27/2020
  Time: 11:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="root"/>
<button class="sidebar__toggle close-sidebar-menu"><i class="fas fa-times"></i></button>
<div class="sidebar__sticky" id="slim-scroll">
    <div class="side-logo">
        <a href="${root}/employee/employee-dashboard" class="side-logo__link">
            <img src="${root}/img/logo.svg" alt="Logo"/>
        </a>
    </div>
    <div class="sidebar__buttons">
        <a href="${root}/employee/book-appointment" class="btn btn-info custom-rounded-btn green">BOOK AN APPOINTMENT</a>
        <a href="${root}/employee/order-testing" class="btn btn-info custom-rounded-btn green-line">ORDER TESTS</a>
    </div>
    <ul class="nav flex-row first" >
        <li class="nav__item <%=request.getAttribute("message")%>" >
            <a class="nav-link active" target="_blank" href="https://chat.fertifabenefits.com/api/v1/${EmployeeObject.id}">
                <img src="${root}/img/message.svg" alt=""/>
                Messages
            </a>
        </li>
        <li class="nav__item <%=request.getAttribute("ShoppingCart")%>">
            <a class="nav-link active" href="${root}/employee/shoppingcart">
                <img src="${root}/img/cart.svg" alt="" style="margin-left: -5px;"/>
                Shopping Cart
                <input type="hidden" name="">
            </a>
        </li>
        <li class="nav__item <%=request.getAttribute("MyJourney")%>">
            <a class="nav-link" href="${root}/employee/journey">
                <img src="${root}/img/journey.svg" alt=""/>
                My Journey
            </a>
        </li>
        <li class="nav__item <%=request.getAttribute("health")%>">
            <a class="nav-link" href="${root}/employee/health-history">
                <img src="${root}/img/health.svg" alt=""/>
                My Health History
            </a>
        </li>
        <%--   <li class="nav__item <%=request.getAttribute("orders")%>">
               <a class="nav-link" href="${root}/employee/orders">
                   <img src="${root}/img/orders.svg" alt=""/>
                   My Orders
               </a>
           </li>--%>
        <li class="nav__item <%=request.getAttribute("benefits")%>">
            <a class="nav-link" href="${root}/employee/benefits">
                <img src="${root}/img/benefits.svg" alt=""/>
                My Benefits
            </a>
        </li>
    </ul>
    <ul class="nav flex-column <%=request.getAttribute("help")%>">
        <li class="nav__item">
            <a class="nav-link" href="${root}/employee/helpcontact?Id=5">
                <img src="${root}/img/help.svg" alt=""/>
                Help & Contact
            </a>
        </li>
        <li class="nav__item <%=request.getAttribute("education")%>">
            <a class="nav-link" href="${root}/employee/education">
                <img src="${root}/img/education.svg" alt=""/>
                Education
            </a>
        </li>
        <li class="nav__item <%=request.getAttribute("chat")%>">
            <a class="nav-link" href="${root}/employee/feedbackemployee">
                <img src="${root}/img/chat.svg" alt=""/>
                Submit Feedback
            </a>
        </li>
    </ul>
    <ul class="nav flex-row first" >
        <li class="nav__item <%=request.getAttribute("message")%>" >
            <a class="nav-link active" href="${root}/employee/myaccount">
                <img src="${root}/img/message.svg" alt=""/>
                My account
            </a>
        </li>
    </ul>
    <div class="footer">
        <a class="nav-link footer__logout " href="${root}/employee/logout">
            <img src="${root}/img/exit.svg" alt=""/>
            Log out
        </a>
        <div class="footer__content">
            <p>v. 1.3.0</p>
            <p>
                2020 © Fertifa Ltd.<br/>
                All rights reserved
            </p>
        </div>
    </div>
</div>

<%--<script>
    var logout_request = 'logout_request';
</script>--%>
