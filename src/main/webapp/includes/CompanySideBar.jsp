<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<button class="sidebar__toggle close-sidebar-menu"><i class="fas fa-times"></i></button>
<div class="sidebar__sticky" id="slim-scroll">
    <div class="side-logo">
        <a href="${root}/employer/employer-dashboard" class="side-logo__link">
            <img src="${root}/img/logo.svg" alt="Logo"/>
        </a>
    </div>
    <ul class="nav flex-column first">
        <li class="nav__item <%=request.getAttribute("Messages")%>">
            <a class="nav-link" target="_blank" href="https://chat.fertifabenefits.com/api/v1/${EmployerObject.id}">
                <img src="${root}/img/message.svg" alt=""/>
                Messages
            </a>
        </li>
        <li class="nav__item <%=request.getAttribute("Enrollment")%>">
            <a class="nav-link " href="${root}/employer/enrolment">
                <img src="${root}/img/graph.svg" alt=""/>
                Enrolment data
            </a>
        </li>
        <li class="nav__item <%=request.getAttribute("Feedback")%>">
            <a class="nav-link" href="${root}/employer/feedback">
                <img src="${root}/img/chat.svg" alt=""/>
                Employer feedback
            </a>
        </li>
        <li class="nav__item <%=request.getAttribute("Performance")%>">
            <a class="nav-link" href="${root}/employer/performance">
                <img src="${root}/img/cost.svg" alt=""/>
                Cost performance
            </a>
        </li>
        <li class="nav__item <%=request.getAttribute("Tracker")%>">
            <a class="nav-link" href="${root}/employer/enrolmentdata">
                <img src="${root}/img/users.svg" alt=""/>
                Employer tracker
            </a>
        </li>
    </ul>
    <ul class="nav flex-column">
        <li class="nav__item <%=request.getAttribute("Contact")%>">
            <a class="nav-link"  href="${root}/employer/help">
                <img src="${root}/img/help.svg" alt=""/>
                Help & Contact
            </a>
        </li>
        <li class="nav__item <%=request.getAttribute("Partners")%>">
            <a class="nav-link"  href="${root}/employer/partners">
                <img src="${root}/img/like.svg" alt=""/>
                Fertifa partners
            </a>
        </li>
    </ul>
    <ul class="nav flex-column">
        <li class="nav__item <%=request.getAttribute("Affiliate")%>">
            <a class="nav-link"  href="${root}/employer/affiliate">
                <img src="${root}/img/help.svg" alt=""/>
                Affiliate
            </a>
        </li>
    </ul>

    <div class="footer">
        <a class="nav-link footer__logout click-logout-action-company"  href="${root}/employer/logout">
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
<script>
    var logout_request = 'logout_request_company';
</script>