<%--
  Created by IntelliJ IDEA.
  User: Shant
  Date: 4/21/2020
  Time: 10:27 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="employee-incoming-call-modal-container"></div>
<script src="<%=request.getContextPath()%>/js/socket.io.js"></script>
<script src="${pageContext.request.contextPath}/js/user/communication/client/UserSignalingClient.js"></script>