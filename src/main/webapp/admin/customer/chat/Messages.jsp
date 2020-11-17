<%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 1/22/2020
  Time: 12:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <%
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader ("Expires", 0);
        if(session.getAttribute("adminEmail")==null){
            response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard");

        }
    %>
    <jsp:include page="../../../includes/Header.jsp"/>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<!-- Site wrapper -->
<div class="wrapper">
    <!-- Navbar -->
    <jsp:include page="../../../includes/NavigationBar.jsp"/>

    <!-- Main Sidebar Container -->
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <!-- Brand Logo -->
        <jsp:include page="../../../includes/LogoAndBrand.jsp"/>

        <!-- Sidebar -->
        <div class="sidebar">
            <!-- Sidebar user (optional) -->
            <div class="user-panel mt-3 pb-3 mb-3 d-flex">
                <div class="image">
                    <c:if test="${requestScope.AdminsObject != null}">
                            <c:set value="${requestScope.AdminsObject.firstName}" var="FirstNameToCut" scope="request"/>
                            <c:set value="${requestScope.AdminsObject.lastName}" var="LastNameToCut" scope="request"/>
                            <%
                                String firstNameCutted = String.valueOf(request.getAttribute("FirstNameToCut"));
                                String SecondNameCutted = String.valueOf(request.getAttribute("LastNameToCut"));
                                String finalFirstName = firstNameCutted.substring(0, 1);
                                String finalSecondName = SecondNameCutted.substring(0, 1);
                            %>
                            <span class="img-size-30 mr-3 img-circle empty-avatar"><%=finalFirstName.toUpperCase()%><%=finalSecondName.toUpperCase()%></span>

                    </c:if>
                </div>
                <div class="info">
                    <c:if test="${requestScope.AdminsObject != null}">
                            <a href="#" class="d-block">${requestScope.AdminsObject.firstName} ${requestScope.AdminsObject.lastName}</a>

                    </c:if>

                </div>
            </div>
            <jsp:include page="../../../includes/Sidebar.jsp"/>
            <!-- /.sidebar-menu -->
        </div>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>com.fertifa.app.messages</h1>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="container-fluid">

                <div class="chat-area-content">
                    <div class="row">
                        <div class="col-12 col-sm-6 col-lg-4">
                            <div class="card card-primary card-outline card-tabs">
                                <div class="card-header p-0 pt-1 tab-session-actions">
                                    <ul class="nav nav-tabs" id="custom-tabs-one-tab" role="tablist">
                                        <li class="nav-item">
                                            <a class="nav-link custom-sessions-tab-link active"
                                               id="custom-tabs-one-chat-tab" data-selected="3" data-toggle="pill"
                                               href="#custom-tabs-one-chat" role="tab"
                                               aria-controls="custom-tabs-one-chat" aria-selected="true">Chat</a>
                                        </li>
                                        <%-- <li class="nav-item">
                                             <a class="nav-link custom-sessions-tab-link"
                                                id="custom-tabs-one-request-tab" data-selected="2" data-toggle="pill"
                                                href="#custom-tabs-one-request" role="tab"
                                                aria-controls="custom-tabs-one-request" aria-selected="false">com.fertifa.app.Request</a>
                                         </li>--%>
                                        <li class="nav-item">
                                            <a class="nav-link custom-sessions-tab-link"
                                               id="custom-tabs-one-notification-tab" data-selected="1"
                                               data-toggle="pill" href="#custom-tabs-one-notification" role="tab"
                                               aria-controls="custom-tabs-one-notification" aria-selected="false">Notification</a>
                                        </li>
                                    </ul>
                                    <button type="button"
                                            class="btn btn-block bg-gradient-secondary generate-new-session-chat btn-sm">
                                        <i class="fas fa-plus"></i>
                                    </button>
                                </div>


                                <div class="card-body tab-sessions-list">
                                    <div class="tab-content" id="custom-tabs-one-tabContent">
                                        <div class="tab-pane custom-tab-content-tab fade active show"
                                             id="custom-tabs-one-chat" role="tabpanel"
                                             aria-labelledby="custom-tabs-one-chat-tab" data-selected-content="3">
                                            <ul class="sessions-list products-list product-list-in-card pl-2 pr-2">
                                                <c:if test="${requestScope.ChatSessionListString != null}">
                                                    <c:forEach items="${requestScope.ChatSessionListString}"
                                                               var="ChatAdmin">

                                                        <li class="item">
                                                            <c:set value="${ChatAdmin.chatToId}" var="chater"
                                                                   scope="request"/>
                                                            <c:choose>
                                                            <c:when test="${ChatAdmin.chatToId == 1}">
                                                            <a href="${pageContext.request.contextPath}/admin/customer/chat/conversation?id=${ChatAdmin.chatFromId}&sessionid=${ChatAdmin.chatSessionToken}"
                                                               class="block-href get-session-item"
                                                               data-session="jdhgfjf876">
                                                                </c:when>
                                                                <c:otherwise>
                                                                <a href="${pageContext.request.contextPath}/admin/customer/chat/conversation?id=${ChatAdmin.chatToId}&sessionid=${ChatAdmin.chatSessionToken}"
                                                                   class="block-href get-session-item"
                                                                   data-session="jdhgfjf876">
                                                                    </c:otherwise>
                                                                    </c:choose>

                                                                    <c:set value="${ChatAdmin.important_for_admin}"
                                                                           var="important"/>
                                                                    <c:choose>
                                                                        <c:when test="${important != 0}">
                                                                            <div class="product-img">
                                                                                <input class="star select-star-important"
                                                                                       data-sessionId="${ChatAdmin.chatSessionToken}"
                                                                                       type="checkbox"
                                                                                       title="bookmark page" checked>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="product-img">
                                                                                <input class="star select-star-important"
                                                                                       data-sessionId="${ChatAdmin.chatSessionToken}"
                                                                                       type="checkbox"
                                                                                       title="bookmark page">
                                                                            </div>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                    <div class="product-info">
                                                                            ${ChatAdmin.chatSessionName}

                                                                        <span class="float-right">${ChatAdmin.stringDate}</span>
                                                                        <span class="product-description">
                                                        ID ${ChatAdmin.chatSessionToken}
                                                    </span>
                                                                    </div>
                                                                </a>
                                                        </li>
                                                    </c:forEach>
                                                </c:if>

                                            </ul>
                                        </div>

                                        <div class="tab-pane custom-tab-content-tab fade tab-sessions-list"
                                             id="custom-tabs-one-notification" role="tabpanel"
                                             aria-labelledby="custom-tabs-one-notification-tab"
                                             data-selected-content="1">
                                            <ul class="sessions-list products-list product-list-in-card pl-2 pr-2">
                                                <c:if test="${requestScope.ChatSessionListNotification != null}">
                                                    <c:forEach items="${requestScope.ChatSessionListNotification}"
                                                               var="ChatAdmin">
                                                        <li class="item">
                                                            <c:set value="${ChatAdmin.chatToId}" var="chater"
                                                                   scope="request"/>
                                                            <c:choose>
                                                            <c:when test="${ChatAdmin.chatToId == 1}">
                                                            <a href="${pageContext.request.contextPath}/admin/customer/chat/conversation?id=${ChatAdmin.chatFromId}&sessionid=${ChatAdmin.chatSessionToken}"
                                                               class="block-href get-session-item"
                                                               data-session="jdhgfjf876">
                                                                </c:when>
                                                                <c:otherwise>
                                                                <a href="${pageContext.request.contextPath}/admin/customer/chat/conversation?id=${ChatAdmin.chatToId}&sessionid=${ChatAdmin.chatSessionToken}"
                                                                   class="block-href get-session-item"
                                                                   data-session="jdhgfjf876">
                                                                    </c:otherwise>
                                                                    </c:choose>

                                                                    <c:set value="${ChatAdmin.important_for_admin}"
                                                                           var="important"/>
                                                                    <c:choose>
                                                                        <c:when test="${important != 0}">
                                                                            <div class="product-img">
                                                                                <input class="star select-star-important"
                                                                                       data-sessionId="${ChatAdmin.chatSessionToken}"
                                                                                       type="checkbox"
                                                                                       title="bookmark page" checked>
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="product-img">
                                                                                <input class="star select-star-important"
                                                                                       data-sessionId="${ChatAdmin.chatSessionToken}"
                                                                                       type="checkbox"
                                                                                       title="bookmark page">
                                                                            </div>
                                                                        </c:otherwise>
                                                                    </c:choose>

                                                                    <div class="product-info">
                                                                            ${ChatAdmin.chatSessionName}


                                                                        <span class="float-right">${ChatAdmin.getCreationDate}</span>
                                                                        <span class="product-description">
                                                        ID ${ChatAdmin.chatSessionToken}
                                                    </span>
                                                                    </div>
                                                                </a>
                                                        </li>
                                                    </c:forEach>
                                                </c:if>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.card -->
                            </div>
                        </div>
                        <div class="col-12 col-sm-6 col-lg-8">
                            <div class="card direct-chat direct-chat-primary direct-custom">
                                <form id="messForm">
                                    <div class="card-header">
                                        <h3 class="card-title">Direct Chat</h3>
                                    </div>
                                    <!-- /.card-header -->
                                    <div class="card-body">
                                        <div class="default-unselect-text">Please select an existing chat channel or
                                            create a new one
                                        </div>
                                    </div>
                                    <!-- /.card-body -->

                                    <!-- /.card-footer-->
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

            <div class="card custom-card fertifa-session-box">
                <div class="card-header custom-card__header">
                    <button class="custom-card__header-close close-session-box">
                        <i class="fas fa-times"></i>
                    </button>
                </div>
                <div class="card-body custom-card__body">
                    <div class="form-group">
                        <label for="sessionTitle" class="custom-card__body-label">Conversation Title</label>
                        <input type="text" class="form-control form-control-sm" id="sessionTitle"
                               placeholder="Title...">
                    </div>
                </div>
                <div class="card-footer custom-card__footer session">
                    <button id="createSession"
                            class="btn btn-info btn-sm custom-card__footer-start start-conversation-session">Start Chat
                    </button>
                </div>
            </div>
            <!-- .messanger box -->
        </section>
        <%!
            static int Id;
        %>
        <c:set value='<%=request.getParameter("id")%>' var="UserId" scope="request"/>
        <%
            Id = Integer.parseInt(String.valueOf(request.getAttribute("UserId")));
        %>
        <script>

            var roomIdFromFrontAdmin = ${requestScope.AdminsObject.id};
            var selfId =  ${requestScope.AdminsObject.id};
            var sessionRoomFromFront = 'jok2f4fud_9';
            var roomIdFromFrontUser = <%=Id%>;
        </script>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

</div>
<!-- ./wrapper -->

<jsp:include page="../../../includes/Footer.jsp"/>

<script src="<%=request.getContextPath()%>/js/sessions.js"></script>

</body>
</html>

