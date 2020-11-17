<%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 2/14/2020
  Time: 12:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<div class="order-testing-toast custom-toast fide hide toast" style="z-index: 999" role="alert" aria-live="assertive" aria-atomic="true" data-delay="5000">
    <div class="toast-header">
        <button type="button" class="ml-2 mb-1 ml-auto close" data-dismiss="toast" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <div class="toast-body"></div>

</div>
<c:set value="${sessionScope.session_success_message}" var="SuccessNotification" scope="request"/>
<c:set value="${sessionScope.session_error_message}" var="ErrorNotification" scope="request"/>

<%!
    static String notificationSuccess;
    static String nonotificationError;
%><%--
<p>${sessionScope.session_success_message}</p>
<p>${sessionScope.session_error_message}</p>
<%
    request.getSession().getAttribute("session_success_message");
%>--%>
<%
    if (request.getAttribute("SuccessNotification") != null) {
        notificationSuccess = String.valueOf(request.getAttribute("SuccessNotification"));

%>

<script>
    $(function () {
        console.log('success');
        $('.order-testing-toast').addClass('success').find('.toast-body').html('<%=notificationSuccess%>');
        $('.order-testing-toast').toast('show');

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
        console.log('error');
        $('.order-testing-toast').addClass('error').find('.toast-body').html('<%=nonotificationError%>');
        $('.order-testing-toast').toast('show');
    });

</script>
<%
    }
%>


