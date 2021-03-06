<%--
  Created by IntelliJ IDEA.
  User: rooter
  Date: 1/7/20
  Time: 11:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                String finalFirstName = firstNameCutted.substring(0,1);
                                String finalSecondName = SecondNameCutted.substring(0,1);
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
                        <h1>Create Nurse</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/admin-dashboard">Dashboard</a></li>
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/nurse">Nurses</a></li>
                            <li class="breadcrumb-item active">Create Nurse</li>
                        </ol>
                    </div>

                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card card-primary custom-card">
                        <div class="card-header">
                            <h3 class="card-title">Create Nurse</h3>
                        </div>
                        <!-- /.card-header -->
                        <!-- form start -->
                        <form class="form-horizontal" action="${pageContext.request.contextPath}/admin/nurse/create" method="post">
                            <div class="card-body">

                                <div class="form-group row">
                                    <label for="name" class="col-sm-3 col-form-label">First Name</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" name="firstname" id="name"
                                               placeholder="First name" required>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="lastname" class="col-sm-3 col-form-label">Last Name</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" name="lastName" id="lastname"
                                               placeholder="Last name" required>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="password" class="col-sm-3 col-form-label">Password</label>
                                    <div class="col-sm-9">
                                        <input type="password" class="form-control" name="password" id="password"
                                               placeholder="Password" required>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="email" class="col-sm-3 col-form-label">Email</label>
                                    <div class="col-sm-9">
                                        <input type="email" class="form-control" id="email" name="email"
                                               placeholder="Email"
                                               required>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-3 col-form-label">Status</label>

                                    <div class="col-sm-9">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="status" value="1">
                                            <label class="form-check-label">Active</label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="status" checked=""
                                                   value="0">
                                            <label class="form-check-label">Inactive</label>
                                        </div>
                                    </div>


                                </div>

                                <div class="form-group row">
                                    <label for="address" class="col-sm-3 col-form-label">Address</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" name="address" id="address"
                                               placeholder="Address" required>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="phone" class="col-sm-3 col-form-label">Phone</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" name="phone" id="phone"
                                               placeholder="Phone" required>
                                    </div>
                                </div>

                            </div>
                            <!-- /.card-body -->
                            <div class="card-footer">
                                <button type="submit" class="btn btn-primary float-right custom-btn">Create</button>
                            </div>
                            <!-- /.card-footer -->

                        </form>
                    </div>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

</div>
<script>
    var roomIdFromFrontAdmin = ${requestScope.AdminsObject.id};
    var selfId = ${requestScope.AdminsObject.id};
    var roomIdFromFrontUser = 0;
    var sessionRoomFromFront = 'jok2f4fud_9';
</script>
<!-- ./wrapper -->
<jsp:include page="../../../includes/Footer.jsp"/>
</body>
</html>

