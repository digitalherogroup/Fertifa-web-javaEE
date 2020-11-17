<%@ page import="com.fertifa.app.adminSide.CookiesManager.AdminCookiManager" %><%--
  Created by IntelliJ IDEA.
  User: rooter
  Date: 1/7/20
  Time: 6:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <%
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        if (session.getAttribute("adminEmail") == null) {
            response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard");
        }
        if (!AdminCookiManager.isUserLogged(request, response)) {
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

                        <a href="#"
                           class="d-block">${requestScope.AdminsObject.firstName} ${requestScope.AdminsObject.lastName}</a>

                    </c:if>

                </div>
            </div>
            <jsp:include page="../../includes/Sidebar.jsp"/>
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
                        <h1>com.fertifa.app.sales</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a
                                    href="${pageContext.request.contextPath}/admin/admin-dashboard">Dashboard</a></li>
                            <li class="breadcrumb-item">com.fertifa.app.sales</li>
                        </ol>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="container-fluid">

                <div class="row">
                    <!-- /.col-md-6 -->
                    <div class="col-lg-12">
                        <div class="card sales-card-data">
                            <div class="card-header border-0">
                                <div class="d-flex justify-content-between">
                                    <h3 class="card-title">
                                        <span>Total savings: £12,000</span>
                                        <span>Total savings this month: £340</span>
                                        <span>Total subscription cost: £40,000</span>
                                    </h3>
                                    <div class="filter-section">
                                        <select class="form-control select-category" id="select-category">
                                            <option value="">Category</option>
                                            <c:if test="${requestScope.ServicesList!=null}">
                                                <c:forEach items="${requestScope.ServicesList}" var="servies"
                                                           varStatus="TheCount">
                                                    <option value="${servies.id}">${servies.title}</option>
                                                </c:forEach>
                                            </c:if>

                                        </select>
                                        <select class="form-control select-company" id="select-company">
                                            <option value="">com.fertifa.app.company</option>
                                            <c:if test="${requestScope.UsersCompanyList!=null}">
                                                <c:forEach items="${requestScope.UsersCompanyList}" var="compamny"
                                                           varStatus="TheCount">
                                                    <option value="${compamny.companyId}">${compamny.comapny}</option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="d-flex">
                                    <p class="d-flex flex-column">
                                        <%-- <span class="text-bold text-md">25.12.19 – 31.12.19</span>--%>
                                    </p>
                                    <div class="ml-auto d-flex flex-column text-right">
                                        <div id="daterange-select" class="form-control sel-daterange">Select Date <i
                                                class="fa fa-caret-down"></i></div>
                                        <input type="hidden" id="dateFrom" name="dateFrom" value="">
                                        <input type="hidden" id="dateTo" name="dateTo" value="">
                                    </div>

                                </div>
                                <!-- /.d-flex -->

                                <div class="position-relative mb-4">
                                    <div class="chartjs-size-monitor">
                                        <div class="chartjs-size-monitor-expand">
                                            <div class=""></div>
                                        </div>
                                        <div class="chartjs-size-monitor-shrink">
                                            <div class=""></div>
                                        </div>
                                    </div>
                                    <canvas id="sales-chart" height="200"
                                            style="display: block; width: 731px; height: 200px;" width="731"
                                            class="chartjs-render-monitor"></canvas>
                                </div>

                                <div class="d-flex flex-row justify-content-end">
                                    <a href="<%=request.getContextPath()%>/GetCVS?start=${requestScope.Todays}&End=${requestScope.Seven}&ServiceId${requestScope.ServiceId}&CompanyId=${requestScope.CompanyId}"
                                       class="btn btn-info">Export CSV</a>
                                </div>
                            </div>
                        </div>
                        <!-- /.card -->
                    </div>
                    <!-- /.col-md-6 -->
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

</div>
<!-- ./wrapper -->
<script>
    var roomIdFromFrontAdmin = ${requestScope.AdminsObject.id};
    var selfId = ${requestScope.AdminsObject.id};
    var show_more_sales_info_ajax = 'ShowMoreSalesInfoAjax';
    var enrollment_days_data = ${requestScope.MyArrayDates};
    var enrollment_price_data = ${requestScope.MyArrayCount};
    var roomIdFromFrontUser = 0;
    var sessionRoomFromFront = 'jok2f4fud_9';
</script>
<jsp:include page="../../includes/Footer.jsp"/>

<script src="<%=request.getContextPath()%>/js/sales.js"></script>
</body>
</html>
