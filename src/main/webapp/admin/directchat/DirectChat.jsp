<%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 1/22/2020
  Time: 12:32 PM
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
    <jsp:include page="../../includes/Header.jsp"/>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<!-- Site wrapper -->
<div class="wrapper">
    <!-- Navbar -->
    <jsp:include page="../../includes/NavigationBar.jsp"/>

    <!-- Main Sidebar Container -->
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <!-- Brand Logo -->
        <jsp:include page="../../includes/LogoAndBrand.jsp"/>

        <!-- Sidebar -->
        <div class="sidebar">
            <!-- Sidebar user (optional) -->
            <div class="user-panel mt-3 pb-3 mb-3 d-flex">
                <div class="image">
                    <c:if test="${requestScope.AdminsObjects != null}">
                            <c:set value="${requestScope.AdminsObjects.firstName}" var="FirstNameToCut" scope="request"/>
                            <c:set value="${requestScope.AdminsObjects.lastName}" var="LastNameToCut" scope="request"/>
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
                    <c:if test="${requestScope.AdminsObjects != null}">
                            <a href="#" class="d-block">${requestScope.AdminsObjects.firstName} ${requestScope.AdminsObjects.lastName}</a>
                    </c:if>

                </div>
            </div>
            <jsp:include page="../../includes/Sidebar.jsp"/>
            <!-- /.sidebar-menu -->
        </div>
        <!-- /.sidebar -->
    </aside>
    <%!
        static String typeChat;
        static String typeRequest;
        static String typeNotiy;

    %>
    <%
        typeChat = "";
        typeRequest = "";
        typeNotiy = "";
    %>
    <c:set value="${requestScope.Type}" var="type"/>

    <c:choose>
        <c:when test="${type == 3}">
            <% typeChat = " active show ";%>
        </c:when>
        <c:when test="${type == 1}">
            <% typeNotiy = " active show ";%>
        </c:when>
        <c:when test="${type == 2}">
            <% typeRequest = " active show ";%>
        </c:when>
        <c:when test="${type == 3}">
            <% typeChat = " active show ";%>
        </c:when>
    </c:choose>


    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>com.fertifa.app.messages</h1>
                        <%-- <h5 class="main-user-subtitle">Alexander Pierce <span>/ From company</span></h5>--%>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/admin/admin-dashboard">Dashboard</a></li>
                            <li class="breadcrumb-item active">com.fertifa.app.messages</li>
                        </ol>
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
                                            <a class="nav-link custom-sessions-tab-link <%=typeChat%>"
                                               id="custom-tabs-one-chat-tab" data-selected="3" data-toggle="pill"
                                               href="#custom-tabs-one-chat" role="tab"
                                               aria-controls="custom-tabs-one-chat" aria-selected="true">Chat</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link custom-sessions-tab-link <%=typeRequest%> "
                                               id="custom-tabs-one-request-tab" data-selected="2" data-toggle="pill"
                                               href="#custom-tabs-one-request" role="tab"
                                               aria-controls="custom-tabs-one-request" aria-selected="false">com.fertifa.app.request</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link custom-sessions-tab-link <%=typeNotiy%>"
                                               id="custom-tabs-one-notification-tab" data-selected="1"
                                               data-toggle="pill" href="#custom-tabs-one-notification" role="tab"
                                               aria-controls="custom-tabs-one-notification" aria-selected="false">Notification</a>
                                        </li>
                                    </ul>

                                </div>


                                <div class="card-body tab-sessions-list">
                                    <div class="tab-content" id="custom-tabs-one-tabContent">
                                        <div class="tab-pane custom-tab-content-tab fade <%=typeChat%>"
                                             id="custom-tabs-one-chat" role="tabpanel"
                                             aria-labelledby="custom-tabs-one-chat-tab" data-selected-content="3">
                                            <ul class="sessions-list products-list product-list-in-card ">
                                                <c:if test="${requestScope.SessionChatlistRequest != null}">
                                                    <c:forEach items="${requestScope.SessionChatlistRequest}"
                                                               var="ChatAdminDirectString">
                                                        <c:set value="${ChatAdminDirectString.chatCreationDate}"
                                                               var="CreationDate" scope="request"/>
                                                        <c:set value="${ChatAdminDirectString.important_for_admin}"
                                                               var="Important" scope="request"/>
                                                        <c:set value="${ChatAdminDirectString.chatSessionStatus}"
                                                               var="StatusAdmin1"/>


                                                        <c:if test="${requestScope.Token == ChatAdminDirectString.chatSessionToken}">
                                                            <li class="item pr-2 active">
                                                        </c:if>
                                                        <c:if test="${requestScope.Token != ChatAdminDirectString.chatSessionToken}">
                                                            <li class="item pr-2">
                                                        </c:if>

                                                        <a href="<%=request.getContextPath()%>/admin/directchat?id=${ChatAdminDirectString.uid}&sessionid=${ChatAdminDirectString.chatSessionToken}"
                                                           class="block-href get-session-item"
                                                           data-session="jdhgfjf876">
                                                            <div class="product-img ">
                                                                <c:choose>
                                                                    <c:when test="${Important != 0}">
                                                                        <input class="star select-star-important"
                                                                               data-sessionId="${ChatAdminDirectString.chatSessionToken}"
                                                                               type="checkbox"
                                                                               title="bookmark page" checked>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <input class="star select-star-important"
                                                                               data-sessionId="${ChatAdminDirectString.chatSessionToken}"
                                                                               type="checkbox"
                                                                               title="bookmark page">
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </div>
                                                            <div class="product-info">
                                                                <c:set value="${ChatAdminDirectString.companyName}"
                                                                       var="company"/>
                                                                <c:choose>
                                                                    <c:when test="${company == null}">
                                                                        <small>${ChatAdminDirectString.firstName} ${ChatAdminDirectString.lasetName} </small>
                                                                    </c:when>
                                                                    <c:when test="${company != null}">
                                                                        <small>${ChatAdminDirectString.companyName}</small>
                                                                    </c:when>
                                                                </c:choose>

                                                                - ${ChatAdminDirectString.chatSessionName}
                                                                <span class="float-right">${ChatAdminDirectString.stringDate}</span>
                                                                <span class="product-description"> ID ${ChatAdminDirectString.chatSessionToken}</span>
                                                            </div>
                                                        </a>

                                                        </li>

                                                    </c:forEach>
                                                </c:if>

                                            </ul>
                                        </div>
                                        <div class="tab-pane custom-tab-content-tab fade tab-sessions-list <%=typeRequest%> "
                                             id="custom-tabs-one-request" role="tabpanel"
                                             aria-labelledby="custom-tabs-one-request-tab" data-selected-content="2">
                                            <ul class="sessions-list products-list product-list-in-card">
                                                <c:if test="${requestScope.SessionChatlistNotification != null}">
                                                    <c:forEach items="${requestScope.SessionChatlistNotification}"
                                                               var="ChatAdminRequest">

                                                        <c:set value="${ChatAdminRequest.chatCreationDate}"
                                                               var="CreationDate" scope="request"/>

                                                        <c:set value="${ChatAdminRequest.chatCreationDate}"
                                                               var="CreationDate" scope="request"/>
                                                        <c:set value="${ChatAdminRequest.important_for_admin}"
                                                               var="Important" scope="request"/>


                                                        <c:if test="${requestScope.Token == ChatAdminRequest.chatSessionToken}">
                                                            <li class="item pr-2 active">
                                                        </c:if>
                                                        <c:if test="${requestScope.Token != ChatAdminRequest.chatSessionToken}">
                                                            <li class="item pr-2">
                                                        </c:if>

                                                        <a href="${pageContext.request.contextPath}/admin/directchat?id=${ChatAdminRequest.uid}&sessionid=${ChatAdminRequest.chatSessionToken}"
                                                           class="block-href get-session-item"
                                                           data-session="jdhgfjf876">
                                                            <div class="product-img">
                                                                <c:choose>
                                                                    <c:when test="${Important != 0}">
                                                                        <input class="star select-star-important"
                                                                               data-sessionId="${ChatAdminRequest.chatSessionToken}"
                                                                               type="checkbox"
                                                                               title="bookmark page" checked>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <input class="star select-star-important"
                                                                               data-sessionId="${ChatAdminRequest.chatSessionToken}"
                                                                               type="checkbox"
                                                                               title="bookmark page">
                                                                    </c:otherwise>
                                                                </c:choose>

                                                            </div>
                                                            <div class="product-info">
                                                                <c:set value="${ChatAdminRequest.companyName}"
                                                                       var="company"/>
                                                                <c:choose>
                                                                    <c:when test="${company ==null}">
                                                                        <small>${ChatAdminRequest.firstName} ${ChatAdminRequest.lasetName}- </small>

                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <small>${ChatAdminRequest.companyName}</small>

                                                                    </c:otherwise>
                                                                </c:choose>
                                                                <%!
                                                                    static String nameToShow;
                                                                %>
                                                                <%
                                                                    nameToShow = String.valueOf(request.getAttribute("name"));
                                                                %>
                                                                    ${ChatAdminRequest.chatSessionName}
                                                                <span class="float-right">${ChatAdminDirectString.stringDate}</span>
                                                                <span class="product-description"> ID ${ChatAdminRequest.chatSessionToken}</span>
                                                            </div>
                                                        </a>
                                                        </li>
                                                    </c:forEach>
                                                </c:if>
                                            </ul>
                                        </div>
                                        <div class="tab-pane custom-tab-content-tab fade tab-sessions-list <%=typeNotiy%>"
                                             id="custom-tabs-one-notification" role="tabpanel"
                                             aria-labelledby="custom-tabs-one-notification-tab"
                                             data-selected-content="1">
                                            <ul class="sessions-list products-list product-list-in-card">
                                                <c:if test="${requestScope.SessionChatlistString != null}">
                                                    <c:forEach items="${requestScope.SessionChatlistString}"
                                                               var="directChatNotofy">


                                                        <c:set value="${directChatNotofy.chatCreationDate}"
                                                               var="CreationDate" scope="request"/>
                                                        <c:set value="${ChatAdminRequest.important_for_admin}"
                                                               var="Important" scope="request"/>


                                                        <c:if test="${requestScope.Token == directChatNotofy.chatSessionToken}">
                                                            <li class="item pr-2 active">
                                                        </c:if>
                                                        <c:if test="${requestScope.Token != directChatNotofy.chatSessionToken}">
                                                            <li class="item pr-2">
                                                        </c:if>
                                                        <a href="${pageContext.request.contextPath}/admin/directchat?id=${directChatNotofy.uid}&sessionid=${directChatNotofy.chatSessionToken}"
                                                           class="block-href get-session-item"
                                                           data-session="jdhgfjf876">

                                                            <div class="product-img ">
                                                                <c:choose>
                                                                    <c:when test="${Important != 0}">
                                                                        <input class="star select-star-important"
                                                                               data-sessionId="${directChatNotofy.chatSessionToken}"
                                                                               type="checkbox"
                                                                               title="bookmark page" checked>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <input class="star select-star-important"
                                                                               data-sessionId="${directChatNotofy.chatSessionToken}"
                                                                               type="checkbox"
                                                                               title="bookmark page">
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </div>
                                                            <div class="product-info">
                                                                <c:set value="${directChatNotofy.companyName}"
                                                                       var="company"/>
                                                                <c:choose>
                                                                    <c:when test="${company ==null}">
                                                                        <small>${directChatNotofy.firstName} ${directChatNotofy.lasetName}- </small>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <small>${directChatNotofy.companyName}</small>
                                                                    </c:otherwise>
                                                                </c:choose>

                                                                    ${directChatNotofy.chatSessionName}
                                                                <span class="float-right">${ChatAdminDirectString.stringDate}</span>
                                                                <span class="product-description"> ID ${directChatNotofy.chatSessionToken}</span>
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
                        <c:set value="${requestScope.Type}" var="type"/>
                        <c:choose>
                            <c:when test="${type != 0 }">
                                <div class="col-12 col-sm-6 col-lg-8">

                                    <div class="card direct-chat direct-chat-primary direct-custom">

                                        <form id="messForm">
                                            <div class="card-header">


                                                <h3 class="card-title">${requestScope.NameSessionFront}</h3>
                                                <c:set var="status" value="${requestScope.StatusFront}"/>
                                                <c:choose>
                                                    <c:when test="${status !=  0}">
                                                        <button type="button" data-toggle="modal" data-target="#modal-session"
                                                                class="btn btn-block bg-gradient-secondary position-absolute-chat-btn btn-sm">
                                                            Close
                                                        </button>
                                                    </c:when>
                                                    <c:otherwise>

                                                    </c:otherwise>
                                                </c:choose>

                                            </div>
                                            <!-- /.card-header -->

                                            <div class="card-body">
                                                <!-- Conversations are loaded here -->
                                                <div class="direct-chat-messages" id="direct-chat-messages">


                                                    <c:if test="${requestScope.SesionChatMessagingList!= null}">
                                                        <c:forEach items="${requestScope.SesionChatMessagingList}"
                                                                   var="Chat">
                                                            <c:set var="from" value="${Chat.chatFrom}" scope="request"/>
                                                            <c:set var="id" value="${requestScope.AdminsObjects.id}" scope="request"/>
                                                            <c:choose>
                                                                <c:when test="${id == Chat.chatFrom}">
                                                                    <div class="direct-chat-msg right">
                                                                        <div class="direct-chat-infos clearfix">
                                                                            <div class="direct-chat-text">${Chat.chatBody}</div>
                                                                            <span class="direct-chat-timestamp float-right">${Chat.chatString}</span>
                                                                        </div>
                                                                    </div>
                                                                </c:when>
                                                                <c:otherwise>

                                                                    <div class="direct-chat-msg">
                                                                        <div class="direct-chat-infos clearfix">
                                                                            <div class="direct-chat-text">${Chat.chatBody}</div>
                                                                            <span class="direct-chat-timestamp float-right">${Chat.chatString}</span>
                                                                        </div>
                                                                    </div>
                                                                </c:otherwise>
                                                            </c:choose>

                                                        </c:forEach>
                                                    </c:if>


                                                </div>
                                                <!--/.direct-chat-messages-->
                                            </div>
                                            <!-- /.card-body -->

                                            <c:set var="status" value="${requestScope.StatusFront}"/>
                                            <c:choose>
                                                <c:when test="${status != 0}">
                                                    <div class="card-footer d-flex">
                                                        <form action="#" method="post"
                                                              class="send-message-form">
                                                            <div class="select-file-place-chat">
                                                                <input type="file" id="message_file"/>
                                                                <label for="message_file"/><i
                                                                    class="fas fa-paperclip"></i></label>
                                                            </div>
                                                            <div class="input-group">
                                                                <input type="text" name="message" id="message"
                                                                       placeholder="Type Message ..."
                                                                       class="form-control">
                                                                <span class="input-group-append"></span>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </c:when>
                                            </c:choose>
                                            <!-- /.card-footer-->
                                        </form>
                                    </div>

                                </div>
                            </c:when>
                        </c:choose>

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

        <%
            //Id = Integer.parseInt(String.valueOf(request.getAttribute("UserId")));
            //SessId = String.valueOf(request.getAttribute("Sessionid"));
        %>
        <script>
            var roomIdFromFrontAdmin = 1;

            var selfId = 1;


        </script>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <div class="modal fade" id="modal-session">
        <div class="modal-dialog">
            <div class="modal-content bg-default">
                <form action="" method="POST">
                    <div class="modal-header">
                        <h4 class="modal-title">Close Conversation</h4>
                        <button id="close-conversation-modal-btn" type="button" class="close" data-dismiss="modal"
                                aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Would you like to close conversation session?</p>
                    </div>
                    <div class="modal-footer justify-content-between">
                        <button type="button" class="btn btn-secondary click-dismiss-del-modal" data-dismiss="modal">
                            Cancel
                        </button>
                        <button type="button" class="btn btn-danger close-conversation">Close Conversation</button>
                    </div>
                </form>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
</div>
<!-- ./wrapper -->
<script>
    var roomIdFromFrontAdmin = 1;
    var roomIdFromFrontUser = ${requestScope.UserIdChat};
    var selfId = 1;
    var sessionRoomFromFront = '${requestScope.Token}';

    var roomIdFromFrontAdmin = ${requestScope.AdminsObjects.id};
    var selfId = ${requestScope.AdminsObjects.id};
</script>
<jsp:include page="../../includes/Footer.jsp"/>
<script defer src="<%=request.getContextPath()%>/js/sessions.js"></script>

</body>
</html>

