<%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 1/19/2020
  Time: 11:40 PM
  To change this template use File | Settings | File Templates.
--%>
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

    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto">
        <!-- com.fertifa.app.Messages Dropdown Menu -->

        <!-- com.fertifa.app.Notifications Dropdown Menu -->
        <li class="nav-item dropdown">
           <%-- <a class="nav-link" data-toggle="dropdown" href="#">
                <i class="far fa-bell"></i>
                <span class="badge badge-warning navbar-badge">15</span>
            </a>--%>
            <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
                <span class="dropdown-item dropdown-header">15 com.fertifa.app.notification</span>
                <div class="dropdown-divider"></div>
                <a href="#" class="dropdown-item">
                    <i class="fas fa-envelope mr-2"></i> 4 new messages
                    <span class="float-right text-muted text-sm">3 mins</span>
                </a>
                <div class="dropdown-divider"></div>
                <a href="#" class="dropdown-item">
                    <i class="fas fa-users mr-2"></i> 8 friend requests
                    <span class="float-right text-muted text-sm">12 hours</span>
                </a>
                <div class="dropdown-divider"></div>
                <a href="#" class="dropdown-item">
                    <i class="fas fa-file mr-2"></i> 3 new reports
                    <span class="float-right text-muted text-sm">2 days</span>
                </a>
                <div class="dropdown-divider"></div>
                <a href="#" class="dropdown-item dropdown-footer">See All com.fertifa.app.notification</a>
            </div>
        </li>
        <%--<c:set value="${sessionScope.notices}" var="Notification" scope="request"/>--%>
        <c:set value="${sessionScope.successNotices}" var="SuccessNotification" scope="request"/>
        <c:set value="${sessionScope.errorNotices}" var="ErrorNotification" scope="request"/>
    </ul>
</nav>

<%!
   /* static String notification;*/
    static String notificationSuccess;
    static String nonotificationError;
%>
<%--<%
    if (request.getAttribute("Notification") != null) {
        notification = String.valueOf(request.getAttribute("Notification"));
%>
<script>
    $(function () {
        toastr.info('<%=notification%>')
    });

</script>
<%
    }
%>--%>
<%
    if (request.getAttribute("SuccessNotification")!= null) {
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
    if (request.getAttribute("ErrorNotification") != null) {
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
