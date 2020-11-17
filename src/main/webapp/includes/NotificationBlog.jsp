<%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 2/4/2020
  Time: 3:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="notification-place">
    <div class="alert alert-secondary notification-alter-bar alert-dismissible fade show" style="display: none;" role="alert">
        <div class="inner-content">
        </div>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <a href="${pageContext.request.contextPath}/employer/chat" class="go-message-page">
        <img src="<%=request.getContextPath()%>/img/question-line.svg" alt="">
    </a>
</div>
