<%--
  Created by IntelliJ IDEA.
  User: rooter
  Date: 1/8/20
  Time: 6:26 PM
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
        <!-- Sidebar Menu -->
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
                        <h1>Profile</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/admin-dashboard">Dashboard</a></li>
                            <li class="breadcrumb-item">Profile</li>
                        </ol>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="container-fluid">
                <div class="row justify-content-center">
                    <div class="col-md-8">

                        <!-- Profile Image -->
                        <div class="card card-primary card-outline custom-card">
                            <div class="card-body box-profile">
                                <c:if test="${requestScope.AdminFullList!=null}">
                                    <c:forEach items="${requestScope.AdminFullList}" var="User">

                                        <div class="text-center">
                                            <%--<c:set value="${User.}" var="image"/>
                                            <c:choose>
                                                <c:when test="${image != null}">
                                                    <img class="profile-user-img img-fluid img-circle user-detail-img"
                                                         src="${User.userImage}" alt="User profile picture">
                                                </c:when>
                                                <c:otherwise>
                                                    <c:set value="${User.firstName}" var="FirstNameToCut"
                                                           scope="request"/>
                                                    <c:set value="${User.lastName}" var="LastNameToCut"
                                                           scope="request"/>
                                                    <%
                                                        String firstNameCutted = String.valueOf(request.getAttribute("FirstNameToCut"));
                                                        String SecondNameCutted = String.valueOf(request.getAttribute("LastNameToCut"));
                                                        String finalFirstName = firstNameCutted.substring(0, 1);
                                                        String finalSecondName = SecondNameCutted.substring(0, 1);
                                                    %>
                                                    <span class="profile-user-img img-fluid img-circle empty-avatar"><%=finalFirstName.toUpperCase()%><%=finalSecondName.toUpperCase()%></span>
                                                </c:otherwise>
                                            </c:choose>--%>

                                        </div>

                                        <h3 class="profile-username text-center">${User.firstName} ${User.lastName}</h3>

                                       <%-- <p class="text-muted text-center">${User.comapny}</p>--%>

                                    </c:forEach>
                                </c:if>
                            </div>
                            <!-- /.card-body -->
                        </div>
                        <!-- /.card -->


                        <!-- /.card -->
                    </div>
                    <div class="col-md-8">
                        <!-- About Me Box -->
                        <div class="card card-primary custom-card">
                            <c:if test="${requestScope.AdminFullList!=null}">
                                <c:forEach items="${requestScope.AdminFullList}" var="User">
                                    <div class="card-header">
                                        <h3 class="card-title">About</h3>
                                    </div>
                                    <!-- /.card-header -->
                                    <div class="card-body">
                                        <strong>Email</strong>

                                        <p class="text-muted"> ${User.email}</p>

                                        <hr>

                                        <strong>Status</strong>
                                        <c:set value="${User.status}" var="status"/>
                                        <c:choose>
                                            <c:when test="${status == 1}">
                                                <p class="text-muted"><span class="badge bg-success">Active</span></p>
                                            </c:when>
                                            <c:when test="${status == 2}">
                                                <p class="text-muted"><span class="badge bg-danger">Inactive</span></p>
                                            </c:when>
                                            <c:when test="${status == 0}">
                                                <p class="text-muted"><span class="badge bg-secondary">Pending</span>
                                                </p>
                                            </c:when>
                                        </c:choose>


                                    </div>
                                    <!-- /.card-body -->
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Control sidebar content goes here -->
    </aside>
    <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->
<script>
    var roomIdFromFrontAdmin = ${requestScope.AdminsObject.id};
    var selfId = ${requestScope.AdminsObject.id};
</script>
<!-- ./wrapper -->
<jsp:include page="../../../includes/Footer.jsp"/>
</body>
</html>

