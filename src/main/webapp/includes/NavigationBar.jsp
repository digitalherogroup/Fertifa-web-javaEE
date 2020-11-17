<%--
  Created by IntelliJ IDEA.
  User: rooter
  Date: 12/28/19
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" data-widget="pushmenu" href="#"><i class="fas fa-bars"></i></a>
        </li>
    </ul>

    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto">
        <!-- com.fertifa.app.Messages Dropdown Menu -->
        <li class="nav-item dropdown">
            <a class="nav-link" data-toggle="dropdown" href="#">
                <i class="far fa-comments"></i>
                <span id="notification-qnt-top-badge" class="badge badge-danger navbar-badge">0</span>
            </a>
            <div  id="notification-menu-list" class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
                <span class="dropdown-item dropdown-footer">No new notifications.</span>
            </div>
        </li>
        <!-- com.fertifa.app.Notifications Dropdown Menu -->

        <c:set value="${sessionScope.notices}" var="Notification" scope="request"/>
        <c:set value="${sessionScope.successNotices}" var="SuccessNotification" scope="request"/>
        <c:set value="${sessionScope.errorNotices}" var="ErrorNotification" scope="request"/>
    </ul>
</nav>

<%!
    static String notification;
    static String notificationSuccess;
    static String nonotificationError;
%>
<%
    if (request.getAttribute("Notification") != null && request.getAttribute("Notification") != "") {
        notification = String.valueOf(request.getAttribute("Notification"));
        System.out.println("notification " + notification);
%>
<script>
    $(function () {
        toastr.info('<%=notification%>')
    });

</script>
<%
    }
%>
<%
    if (request.getAttribute("SuccessNotification") != null && request.getAttribute("SuccessNotification") != "") {
        notificationSuccess = String.valueOf(request.getAttribute("SuccessNotification"));
%>
<script>
    $(function () {
        toastr.success('<%=notificationSuccess%>');
    });

</script>
<%
    }
%>

<%
    if (request.getAttribute("ErrorNotification") != null && request.getAttribute("ErrorNotification") != "") {
        nonotificationError = String.valueOf(request.getAttribute("ErrorNotification"));
%>
<script>
    $(function () {
        toastr.error('<%=nonotificationError%>');
    });

</script>
<%
    }
%>

<%--<script>
    console.log('${sessionScope.notices}');
</script>--%>
<%--
<p id="demo"> this is it <c:out value="${sessionScope.notices}"/></p>--%>
<%--<p id="demo"> this is it <c:out value='<%=request.getSession().getAttribute("notices")%>'/></p>--%>
<!-- /.navbar -->
