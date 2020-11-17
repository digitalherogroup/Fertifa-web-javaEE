<%--
  Created by IntelliJ IDEA.
  User: rooter
  Date: 1/7/20
  Time: 11:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
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
    <jsp:include page="../includes/Header.jsp"/>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<!-- Site wrapper -->
<div class="wrapper">
    <!-- Navbar -->
    <jsp:include page="../includes/NavigationBar.jsp"/>

    <!-- Main Sidebar Container -->
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <!-- Brand Logo -->
        <jsp:include page="../includes/LogoAndBrand.jsp"/>

        <!-- Sidebar -->
        <div class="sidebar">
            <!-- Sidebar user (optional) -->
            <div class="user-panel mt-3 pb-3 mb-3 d-flex">
                <div class="image">
                    <c:if test="${requestScope.AdminFullList != null}">
                        <c:forEach items="${requestScope.AdminFullList}" var="admin">
                            <c:set value="${admin.firstName}" var="FirstNameToCut" scope="request"/>
                            <c:set value="${admin.lastName}" var="LastNameToCut" scope="request"/>
                            <%
                                String firstNameCutted = String.valueOf(request.getAttribute("FirstNameToCut"));
                                String SecondNameCutted = String.valueOf(request.getAttribute("LastNameToCut"));
                                String finalFirstName = firstNameCutted.substring(0,1);
                                String finalSecondName = SecondNameCutted.substring(0,1);
                            %>
                            <span class="img-size-30 mr-3 img-circle empty-avatar"><%=finalFirstName.toUpperCase()%><%=finalSecondName.toUpperCase()%></span>
                        </c:forEach>
                    </c:if>
                </div>
                <div class="info">
                    <%--<c:if test="${requestScope.AdminFullList != null}">
                        <c:forEach items="${requestScope.AdminFullList}" var="username">
                            <a href="#" class="d-block">${username.firstName} ${username.lastName}</a>
                        </c:forEach>
                    </c:if>--%>

                </div>
            </div>
            <jsp:include page="../includes/Sidebar.jsp"/>
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
                        <h5 class="main-user-subtitle">Alexander Pierce <span>/ From company</span></h5>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/admin-dashboard">Dashboard</a></li>
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
                                            <a class="nav-link active" id="custom-tabs-one-chat-tab" data-toggle="pill" href="#custom-tabs-one-chat" role="tab" aria-controls="custom-tabs-one-chat" aria-selected="true">Chat</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" id="custom-tabs-one-request-tab" data-toggle="pill" href="#custom-tabs-one-request" role="tab" aria-controls="custom-tabs-one-request" aria-selected="false">com.fertifa.app.request</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" id="custom-tabs-one-notification-tab" data-toggle="pill" href="#custom-tabs-one-notification" role="tab" aria-controls="custom-tabs-one-notification" aria-selected="false">Notification</a>
                                        </li>
                                    </ul>
                                    <button type="button" class="btn btn-block bg-gradient-secondary generate-new-session-chat btn-sm">
                                        <i class="fas fa-plus"></i>
                                    </button>
                                </div>
                                <div class="card-body tab-sessions-list">
                                    <div class="tab-content" id="custom-tabs-one-tabContent">
                                        <div class="tab-pane fade active show" id="custom-tabs-one-chat" role="tabpanel" aria-labelledby="custom-tabs-one-chat-tab">
                                            <ul class="products-list product-list-in-card pl-2 pr-2">
                                                <li class="item">
                                                    <a href="" class="block-href">
                                                        <div class="product-img">
                                                            <i class="fas fa-star"></i>
                                                        </div>
                                                        <div class="product-info">
                                                            Linking card
                                                            <span class="float-right">03:24</span>
                                                            <span class="product-description">
                                                        ID 12035202
                                                    </span>
                                                        </div>
                                                    </a>
                                                </li>
                                                <li class="item">
                                                    <a href="" class="block-href">
                                                        <div class="product-img">
                                                            <i class="fas fa-star"></i>
                                                        </div>
                                                        <div class="product-info">
                                                            Linking card
                                                            <span class="float-right">03:24</span>
                                                            <span class="product-description">
                                                        ID 12035202
                                                    </span>
                                                        </div>
                                                    </a>
                                                </li>
                                                <li class="item">
                                                    <a href="" class="block-href">
                                                        <div class="product-img">
                                                            <i class="fas fa-star"></i>
                                                        </div>
                                                        <div class="product-info">
                                                            Linking card
                                                            <span class="float-right">03:24</span>
                                                            <span class="product-description">
                                                        ID 12035202
                                                    </span>
                                                        </div>
                                                    </a>
                                                </li>
                                                <li class="item">
                                                    <a href="" class="block-href">
                                                        <div class="product-img">
                                                            <i class="fas fa-star"></i>
                                                        </div>
                                                        <div class="product-info">
                                                            Linking card
                                                            <span class="float-right">03:24</span>
                                                            <span class="product-description">
                                                        ID 12035202
                                                    </span>
                                                        </div>
                                                    </a>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="tab-pane fade tab-sessions-list" id="custom-tabs-one-request" role="tabpanel" aria-labelledby="custom-tabs-one-request-tab">
                                            <ul class="products-list product-list-in-card pl-2 pr-2">
                                                <li class="item">
                                                    <a href="" class="block-href">
                                                        <div class="product-img">
                                                            <i class="fas fa-star"></i>
                                                        </div>
                                                        <div class="product-info">
                                                            Linking card
                                                            <span class="float-right">03:24</span>
                                                            <span class="product-description">
                                                        ID 12035202
                                                    </span>
                                                        </div>
                                                    </a>
                                                </li>
                                                <li class="item">
                                                    <a href="" class="block-href">
                                                        <div class="product-img">
                                                            <i class="fas fa-star"></i>
                                                        </div>
                                                        <div class="product-info">
                                                            Linking card
                                                            <span class="float-right">03:24</span>
                                                            <span class="product-description">
                                                        ID 12035202
                                                    </span>
                                                        </div>
                                                    </a>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="tab-pane fade tab-sessions-list" id="custom-tabs-one-notification" role="tabpanel" aria-labelledby="custom-tabs-one-notification-tab">
                                            <ul class="products-list product-list-in-card pl-2 pr-2">
                                                <li class="item">
                                                    <a href="" class="block-href">
                                                        <div class="product-img">
                                                            <i class="fas fa-star"></i>
                                                        </div>
                                                        <div class="product-info">
                                                            Linking card
                                                            <span class="float-right">03:24</span>
                                                            <span class="product-description">
                                                        ID 12035202
                                                    </span>
                                                        </div>
                                                    </a>
                                                </li>
                                                <li class="item">
                                                    <a href="" class="block-href">
                                                        <div class="product-img">
                                                            <i class="fas fa-star"></i>
                                                        </div>
                                                        <div class="product-info">
                                                            Linking card
                                                            <span class="float-right">03:24</span>
                                                            <span class="product-description">
                                                        ID 12035202
                                                    </span>
                                                        </div>
                                                    </a>
                                                </li>
                                                <li class="item">
                                                    <a href="" class="block-href">
                                                        <div class="product-img">
                                                            <i class="fas fa-star"></i>
                                                        </div>
                                                        <div class="product-info">
                                                            Linking card
                                                            <span class="float-right">03:24</span>
                                                            <span class="product-description">
                                                        ID 12035202
                                                    </span>
                                                        </div>
                                                    </a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.card -->
                            </div>
                        </div>

                        <div class="col-12 col-sm-6 col-lg-8">
                            <div class="card direct-chat direct-chat-primary direct-custom">
                                <div class="card-header">
                                    <h3 class="card-title">Direct Chat</h3>
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
                                    <!-- Conversations are loaded here -->
                                    <div class="direct-chat-messages">
                                        <!-- Message. Default to the left -->
                                        <div class="direct-chat-msg">
                                            <div class="direct-chat-infos clearfix">
                                                <span class="direct-chat-name float-left">Alexander Pierce</span>
                                                <span class="direct-chat-timestamp float-right">23 Jan 2:00 pm</span>
                                            </div>
                                            <!-- /.direct-chat-infos -->
                                            <img class="direct-chat-img" src="<%=request.getContextPath()%>/WEB-INF/jsp/img/user1-128x128.jpg" alt="message user image">
                                            <!-- /.direct-chat-img -->
                                            <div class="direct-chat-text">
                                                Is this template really for free? That's unbelievable!
                                            </div>
                                            <!-- /.direct-chat-text -->
                                        </div>
                                        <!-- /.direct-chat-msg -->

                                        <!-- Message to the right -->
                                        <div class="direct-chat-msg right">
                                            <div class="direct-chat-infos clearfix">
                                                <span class="direct-chat-name float-right">Sarah Bullock</span>
                                                <span class="direct-chat-timestamp float-left">23 Jan 2:05 pm</span>
                                            </div>
                                            <!-- /.direct-chat-infos -->
                                            <img class="direct-chat-img" src="<%=request.getContextPath()%>/WEB-INF/jsp/img/user3-128x128.jpg" alt="message user image">
                                            <!-- /.direct-chat-img -->
                                            <div class="direct-chat-text">
                                                You better believe it!
                                            </div>
                                            <!-- /.direct-chat-text -->
                                        </div>
                                        <!-- /.direct-chat-msg -->

                                        <!-- Message. Default to the left -->
                                        <div class="direct-chat-msg">
                                            <div class="direct-chat-infos clearfix">
                                                <span class="direct-chat-name float-left">Alexander Pierce</span>
                                                <span class="direct-chat-timestamp float-right">23 Jan 5:37 pm</span>
                                            </div>
                                            <!-- /.direct-chat-infos -->
                                            <img class="direct-chat-img" src="<%=request.getContextPath()%>/WEB-INF/jsp/img/user1-128x128.jpg" alt="message user image">
                                            <!-- /.direct-chat-img -->
                                            <div class="direct-chat-text">
                                                Working with AdminLTE on a great new app! Wanna join?
                                            </div>
                                            <!-- /.direct-chat-text -->
                                        </div>
                                        <!-- /.direct-chat-msg -->

                                        <!-- Message to the right -->
                                        <div class="direct-chat-msg right">
                                            <div class="direct-chat-infos clearfix">
                                                <span class="direct-chat-name float-right">Sarah Bullock</span>
                                                <span class="direct-chat-timestamp float-left">23 Jan 6:10 pm</span>
                                            </div>
                                            <!-- /.direct-chat-infos -->
                                            <img class="direct-chat-img" src="<%=request.getContextPath()%>/WEB-INF/jsp/img/user3-128x128.jpg" alt="message user image">
                                            <!-- /.direct-chat-img -->
                                            <div class="direct-chat-text">
                                                I would love to.
                                            </div>
                                            <!-- /.direct-chat-text -->
                                        </div>
                                        <!-- /.direct-chat-msg -->

                                    </div>
                                    <!--/.direct-chat-messages-->

                                    <!-- Contacts are loaded here -->
                                    <div class="direct-chat-contacts">
                                        <ul class="contacts-list">
                                            <li>
                                                <a href="#">
                                                    <img class="contacts-list-img" src="<%=request.getContextPath()%>/?img/user1-128x128.jpg">

                                                    <div class="contacts-list-info">
                                    <span class="contacts-list-name">
                                        Count Dracula
                                        <small class="contacts-list-date float-right">2/28/2015</small>
                                    </span>
                                                        <span class="contacts-list-msg">How have you been? I was...</span>
                                                    </div>
                                                    <!-- /.contacts-list-info -->
                                                </a>
                                            </li>
                                            <!-- End Contact Item -->
                                            <li>
                                                <a href="#">
                                                    <img class="contacts-list-img" src="<%=request.getContextPath()%>/img/user7-128x128.jpg">

                                                    <div class="contacts-list-info">
                                    <span class="contacts-list-name">
                                        Sarah Doe
                                        <small class="contacts-list-date float-right">2/23/2015</small>
                                    </span>
                                                        <span class="contacts-list-msg">I will be waiting for...</span>
                                                    </div>
                                                    <!-- /.contacts-list-info -->
                                                </a>
                                            </li>
                                            <!-- End Contact Item -->
                                            <li>
                                                <a href="#">
                                                    <img class="contacts-list-img" src="<%=request.getContextPath()%>/WEB-INF/jsp/img/user3-128x128.jpg">

                                                    <div class="contacts-list-info">
                                    <span class="contacts-list-name">
                                        Nadia Jolie
                                        <small class="contacts-list-date float-right">2/20/2015</small>
                                    </span>
                                                        <span class="contacts-list-msg">I'll call you back at...</span>
                                                    </div>
                                                    <!-- /.contacts-list-info -->
                                                </a>
                                            </li>
                                            <!-- End Contact Item -->
                                            <li>
                                                <a href="#">
                                                    <img class="contacts-list-img" src="<%=request.getContextPath()%>/img/user5-128x128.jpg">

                                                    <div class="contacts-list-info">
                                    <span class="contacts-list-name">
                                        Nora S. Vans
                                        <small class="contacts-list-date float-right">2/10/2015</small>
                                    </span>
                                                        <span class="contacts-list-msg">Where is your new...</span>
                                                    </div>
                                                    <!-- /.contacts-list-info -->
                                                </a>
                                            </li>
                                            <!-- End Contact Item -->
                                            <li>
                                                <a href="#">
                                                    <img class="contacts-list-img" src="<%=request.getContextPath()%>/img/user6-128x128.jpg">

                                                    <div class="contacts-list-info">
                                    <span class="contacts-list-name">
                                        John K.
                                        <small class="contacts-list-date float-right">1/27/2015</small>
                                    </span>
                                                        <span class="contacts-list-msg">Can I take a look at...</span>
                                                    </div>
                                                    <!-- /.contacts-list-info -->
                                                </a>
                                            </li>
                                            <!-- End Contact Item -->
                                            <li>
                                                <a href="#">
                                                    <img class="contacts-list-img" src="<%=request.getContextPath()%>/WEB-INF/jsp/img/user8-128x128.jpg">

                                                    <div class="contacts-list-info">
                                    <span class="contacts-list-name">
                                        Kenneth M.
                                        <small class="contacts-list-date float-right">1/4/2015</small>
                                    </span>
                                                        <span class="contacts-list-msg">Never mind I found...</span>
                                                    </div>
                                                    <!-- /.contacts-list-info -->
                                                </a>
                                            </li>
                                            <!-- End Contact Item -->
                                        </ul>
                                        <!-- /.contacts-list -->
                                    </div>
                                    <!-- /.direct-chat-pane -->
                                </div>
                                <!-- /.card-body -->
                                <div class="card-footer">
                                    <form action="#" method="post" class="send-message-form">
                                        <div class="">
                                            <input type="file" id="file" />
                                            <label for="file" /><i class="fas fa-paperclip"></i></label>
                                        </div>
                                        <div class="input-group">
                                            <input type="text" name="message" placeholder="Type Message ..." class="form-control">
                                            <span class="input-group-append">
                                    <button type="button" class="btn btn-primary btn-custom-grey">Send</button>
                                    </span>
                                        </div>
                                        <div class="form-group message-notification">
                                            <div class="form-check">
                                                <input class="form-check-input" name="messageType" value="message" type="radio" checked>
                                                <label class="form-check-label">Message</label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" name="messageType" value="notification" type="radio">
                                                <label class="form-check-label">Notification</label>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <!-- /.card-footer-->
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

</div>
<!-- ./wrapper -->
<script>
    var roomIdFromFrontAdmin = ${requestScope.AdminId};
    var selfId = ${requestScope.AdminId};
</script>
<jsp:include page="../includes/Footer.jsp"/>
</body>
</html>

