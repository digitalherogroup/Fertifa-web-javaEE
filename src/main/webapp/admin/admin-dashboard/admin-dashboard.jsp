<%--
  Created by IntelliJ IDEA.
  User: rooter
  Date: 12/25/19
  Time: 6:50 PM
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
        if("POST".equalsIgnoreCase(request.getMethod())){
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

            <!-- /.sidebar-menu -->
            <jsp:include page="../../includes/Sidebar.jsp"/>
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
                        <h1>Dashboard</h1>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="container-fluid">
                <div class="users-filter-place">
                    <select class="form-control select-company" id="select-company">
                        <option value="">company</option>
                        <c:if test="${requestScope.CompanyList != null}">
                            <c:forEach items="${requestScope.CompanyList}" var="ComanyLists">
                                <option value="${ComanyLists.id}">${ComanyLists.comapny}</option>
                            </c:forEach>

                        </c:if>
                    </select>
                    <div id="daterange-select" class="form-control sel-daterange">Select Date <i
                            class="fa fa-caret-down"></i></div>
                    <input type="hidden" id="dateFrom" value="">
                    <input type="hidden" id="dateTo" value="">
                </div>
                <div class="dynamic-data-place">
                    <div class="loading-place"></div>
                    <div class="row">
                        <div class="col-lg-12 col-12">
                            <div class="dash-users-subtitle">
                                <h2 class="widget-subtitle">Employee</h2>
                                <div class="info-box mb-3 bg-info">
                                    <a href="${pageContext.request.contextPath}/admin/customer/employee" target="_blank">
                                        <div class="info-box-content">
                                            <span class="info-box-text">Total</span>

                                            <span class="info-box-number user-total-place">${requestScope.allUsers}</span>
                                        </div>
                                    </a>
                                    <!-- /.info-box-content -->
                                </div>
                            </div>
                        </div>
                        <p>

                        </p>

                        <div class="col-lg-7">
                            <div class="row">
                                <div class="col-lg-4 col-6">
                                    <!-- small box -->
                                    <div class="small-box bg-info">
                                        <div class="inner">
                                            <h3 class="user-pending-place">${requestScope.pendingUsers}</h3>
                                            <p>Pending</p>
                                        </div>
                                        <div class="icon">
                                            <i class="fas fa-user"></i>
                                        </div>
                                    </div>
                                </div>
                                <!-- ./col -->
                                <div class="col-lg-4 col-6">
                                    <!-- small box -->
                                    <div class="small-box bg-info">
                                        <div class="inner">
                                            <h3 class="user-active-place">${requestScope.activeUsers}</h3>
                                            <p>Active</p>
                                        </div>
                                        <div class="icon">
                                            <i class="fas fa-user"></i>
                                        </div>
                                    </div>
                                </div>
                                <!-- ./col -->
                                <div class="col-lg-4 col-6">
                                    <!-- small box -->
                                    <div class="small-box bg-info">
                                        <div class="inner">
                                            <h3 class="user-inactive-place">${requestScope.inActiveUsers}</h3>
                                            <p>Inactive</p>
                                        </div>
                                        <div class="icon">
                                            <i class="fas fa-user"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-5">
                            <div class="row">
                                <!-- ./col -->
                                <div class="col-lg-6 col-6">
                                    <!-- small box -->
                                    <div class="small-box bg-info">
                                        <div class="inner">
                                            <h3 class="user-requests-place">${requestScope.Requestsing}</h3>
                                            <p>Requests</p>
                                        </div>
                                        <div class="icon">
                                            <i class="fas fa-file"></i>
                                        </div>
                                    </div>
                                </div>
                                <!-- ./col -->
                                <div class="col-lg-6 col-6">
                                    <!-- small box -->
                                    <div class="small-box bg-info">
                                        <div class="inner">
                                            <h3 class="user-ratings-place">${requestScope.Ratings}</h3>
                                            <p>Feedback</p>
                                        </div>
                                        <div class="icon">
                                            <i class="fas fa-comment-alt"></i>
                                        </div>
                                    </div>
                                </div>
                                <!-- ./col -->
                            </div>
                        </div>
                        <!-- ./col -->
                    </div>

                    <hr>

                    <div class="row">
                        <div class="col-lg-12 col-12">
                            <div class="dash-users-subtitle">
                                <h2 class="widget-subtitle">Employers</h2>
                                <div class="info-box mb-3 bg-warning">
                                    <a href="${pageContext.request.contextPath}/admin/customer/employer" target="_blank">
                                        <div class="info-box-content">
                                            <span class="info-box-text">Total</span>
                                            <span class="info-box-number company-total-place">${requestScope.allCompanies}</span>
                                        </div>
                                    </a>
                                    <!-- /.info-box-content -->
                                </div>
                            </div>
                        </div>
                        <!-- ./col -->
                        <div class="col-lg-7">
                            <div class="row">
                                <div class="col-lg-4 col-6">
                                    <!-- small box -->
                                    <div class="small-box bg-warning">
                                        <div class="inner">
                                            <h3 class="company-pending-place">${requestScope.pendingCompanies}</h3>
                                            <p>Pending</p>
                                        </div>
                                        <div class="icon">
                                            <i class="fas fa-user"></i>
                                        </div>
                                    </div>
                                </div>
                                <!-- ./col -->
                                <div class="col-lg-4 col-6">
                                    <!-- small box -->
                                    <div class="small-box bg-warning">
                                        <div class="inner">
                                            <h3 class="company-active-place">${requestScope.activeCompanies}</h3>
                                            <p>Active</p>
                                        </div>
                                        <div class="icon">
                                            <i class="fas fa-user"></i>
                                        </div>
                                    </div>
                                </div>
                                <!-- ./col -->
                                <div class="col-lg-4 col-6">
                                    <!-- small box -->
                                    <div class="small-box bg-warning">
                                        <div class="inner">
                                            <h3 class="company-inactive-place">${requestScope.inActiveComapnies}</h3>
                                            <p>Inactive</p>
                                        </div>
                                        <div class="icon">
                                            <i class="fas fa-user"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-5">
                            <div class="row">
                                <!-- ./col -->
                                <div class="col-lg-6 col-6">
                                    <!-- small box -->
                                    <div class="small-box bg-warning">
                                        <div class="inner">
                                            <h3>0</h3>
                                            <p>Requests</p>
                                        </div>
                                        <div class="icon">
                                            <i class="fas fa-file"></i>
                                        </div>
                                    </div>
                                </div>
                                <!-- ./col -->
                                <div class="col-lg-6 col-6">
                                    <!-- small box -->
                                    <div class="small-box bg-warning">
                                        <div class="inner">
                                            <h3>0</h3>
                                            <p>Feedback</p>
                                        </div>
                                        <div class="icon">
                                            <i class="fas fa-comment-alt"></i>
                                        </div>
                                    </div>
                                </div>
                                <!-- ./col -->
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-lg-12 col-12 users-chart-place">
                            <div class="card">
                                <div class="card-body">
                                    <canvas id="users-chart" height="260" style="height: 260px; width: 100%;"
                                            class="chartjs-render-monitor"></canvas>
                                </div>
                            </div>
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
    var roomIdFromFrontUser = 0;
    var selfId = ${requestScope.AdminsObject.id};
    var sessionRoomFromFront = 'jok2f4fud_9';
    var users_days_data = ${requestScope.myArrayDates};
    var employers_quantity_data = ${requestScope.myArrayCountCom};
    var employees_quantity_data = ${requestScope.myArrayCount};

</script>
<jsp:include page="../../includes/Footer.jsp"/>

<script>
    var requestUrl = "${pageContext.request.contextPath}/DashboardStatics";
    var requestUrl = "${pageContext.request.contextPath}/admin/admin-dashboard/ajax";
    console.log(requestUrl)
</script>
<script src="../../js/dashboard.js"></script>

</body>
</html>

