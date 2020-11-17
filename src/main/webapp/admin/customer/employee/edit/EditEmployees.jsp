<%--
  Created by IntelliJ IDEA.
  User: Shant
  Date: 1/6/2020
  Time: 6:33 PM
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

        } if("POST".equals(request.getMethod())){
        response.sendRedirect(request.getContextPath() + "/admin/customer/employee/edit");
    }
    %>
    <jsp:include page="../../../../includes/Header.jsp"/>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<!-- Site wrapper -->
<div class="wrapper">
    <!-- Navbar -->
    <jsp:include page="../../../../includes/NavigationBar.jsp"/>

    <!-- Main Sidebar Container -->
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <!-- Brand Logo -->
        <jsp:include page="../../../../includes/LogoAndBrand.jsp"/>
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
            <jsp:include page="../../../../includes/Sidebar.jsp"/>
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
                        <h1>User Manage</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/admin-dashboard">Dashboard</a></li>
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/customer/employee">Employees</a></li>
                            <li class="breadcrumb-item active">User Manage</li>
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
                            <h3 class="card-title">Manage Form</h3>
                        </div>
                        <!-- /.card-header -->
                        <!-- form start -->
                        <form class="form-horizontal" action="${pageContext.request.contextPath}/admin/customer/employee/update" id="company-manage" method="post">
                            <c:if test="${requestScope.UsersList != null}">
                                <c:forEach items="${requestScope.UsersList}" var="User">
                                    <div class="card-body">
                                        <div class="form-group row">
                                            <div class="col-sm-12">
                                                    <%-- <img src="./img/0.jpg" alt="User Avatar" class="img-size-50 mr-3 img-circle">--%>
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label for="firstname" class="col-sm-3 col-form-label">Firstname</label>
                                            <div class="col-sm-9">
                                                <input type="text" class="form-control" name="firstname" id="firstname"
                                                       placeholder="Firstname" value="${User.firstName} " required>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="lastname" class="col-sm-3 col-form-label">Lastname</label>
                                            <div class="col-sm-9">
                                                <input type="text" class="form-control" name="lastname" id="lastname"
                                                       placeholder="Lastname" value="${User.lastName}" required>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="email" class="col-sm-3 col-form-label">Email</label>
                                            <div class="col-sm-9">
                                                <input type="email" class="form-control" disabled id="email"
                                                       placeholder="Email" value="${User.email}" required>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="company" class="col-sm-3 col-form-label">company</label>
                                            <div class="col-sm-9">
                                                <select class="form-control" id="company" name="company" required>
                                                    <c:if test="${requestScope.CompanyList != null}">
                                                        <option value="${User.comapny}"
                                                                selected>${User.comapny}</option>
                                                        <c:forEach items="${requestScope.CompanyList}"
                                                                   var="companyList">
                                                            <option value="${companyList.comapny}">${companyList.comapny}</option>
                                                        </c:forEach>
                                                    </c:if>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="" class="col-sm-3 col-form-label">Status</label>
                                            <div class="col-sm-9">
                                                <c:set value="${User.status}" var="status"/>
                                                <c:choose>
                                                    <c:when test="${status == '0'}">
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio" name="status"
                                                                   value="1">
                                                            <label class="form-check-label">Active</label>
                                                        </div>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio" name="status"
                                                                   value="2">
                                                            <label class="form-check-label">Inactive</label>
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${status == '1'}">
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio" name="status"
                                                                   checked="" value="1">
                                                            <label class="form-check-label">Active</label>
                                                        </div>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio" name="status"
                                                                   value="2">
                                                            <label class="form-check-label">Inactive</label>
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${status == '2'}">
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio" name="status"
                                                                   value="1">
                                                            <label class="form-check-label">Active</label>
                                                        </div>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio" name="status"
                                                                   checked="" value="2">
                                                            <label class="form-check-label">Inactive</label>
                                                        </div>
                                                    </c:when>
                                                </c:choose>

                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="address" class="col-sm-3 col-form-label">Address</label>
                                            <div class="col-sm-9">
                                                <input type="text" class="form-control" name="address" id="address"
                                                       placeholder="Address" value="${User.address}" required>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="phone" class="col-sm-3 col-form-label">Phone</label>
                                            <div class="col-sm-9">
                                                <input type="text" class="form-control" name="phone" id="phone"
                                                       placeholder="Phone" value="${User.phoneNumber}" required>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="" class="col-sm-3 col-form-label">Gender</label>
                                            <div class="col-sm-9">
                                                <c:set value="${User.gender}" var="gender"/>
                                                <c:choose>
                                                    <c:when test="${gender == 1}">
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio" name="gender"
                                                                   value="2" >
                                                            <label class="form-check-label">Female</label>
                                                        </div>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio" name="gender"
                                                                   value="1" checked="">
                                                            <label class="form-check-label">Male</label>
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${gender == 2}">
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio" name="gender"
                                                                   value="2" checked="">
                                                            <label class="form-check-label">Female</label>
                                                        </div>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio" name="gender"
                                                                   value="1" >
                                                            <label class="form-check-label">Male</label>
                                                        </div>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio" name="gender"
                                                                   value="1">
                                                            <label class="form-check-label">Female</label>
                                                        </div>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio" name="gender"
                                                                   value="2" checked="">
                                                            <label class="form-check-label">Male</label>
                                                        </div>
                                                    </c:otherwise>
                                                </c:choose>

                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="phone" class="col-sm-3 col-form-label">Age</label>
                                            <div class="col-sm-9">
                                                <input type="text" class="form-control" name="age" id="age"
                                                       placeholder="age" value="${User.age}" required>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.card-body -->
                                    <div class="card-footer">
                                        <button type="submit" class="btn btn-primary float-right custom-btn">Update
                                        </button>
                                        <input type="hidden" value="${User.id}" name="id">
                                    </div>
                                </c:forEach>
                            </c:if>
                            <!-- /.card-footer -->
                        </form>
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
<jsp:include page="../../../../includes/Footer.jsp"/>
</body>
</html>

