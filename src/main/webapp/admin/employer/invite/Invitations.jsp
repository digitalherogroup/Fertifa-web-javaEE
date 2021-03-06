<%--
  Created by IntelliJ IDEA.
  User: rooter
  Date: 12/26/19
  Time: 3:57 PM
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
    <jsp:include page="${pageContext.request.contextPath}/includes/Header.jsp"/>
</head>

<body class="hold-transition sidebar-mini layout-fixed">
<script>

    function DisableMenu(){

        if(document.getElementById("typename").value==="0" || document.getElementById("typename").value==="1" ){
            document.getElementById("packagename").disabled = true;
        } else {
            document.getElementById("packagename").disabled = false;
        }

    }
</script>
<!-- Site wrapper -->
<div class="wrapper">
    <!-- Navbar -->
    <jsp:include page="${pageContext.request.contextPath}/includes/NavigationBar.jsp"/>

    <!-- Main Sidebar Container -->
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <!-- Brand Logo -->
        <jsp:include page="${pageContext.request.contextPath}/includes/LogoAndBrand.jsp"/>

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
            <jsp:include page="${pageContext.request.contextPath}/includes/Sidebar.jsp"/>
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
                        <h1>Employer Invitation</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/admin-dashboard">Dashboard</a></li>
                            <li class="breadcrumb-item">Employer Invitation</li>
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
                            <h3 class="card-title">Invitation Form</h3>
                        </div>
                        <!-- /.card-header -->
                        <!-- form start -->
                        <form class="form-horizontal" action="${pageContext.request.contextPath}/admin/employer/inviter" method="post">
                            <div class="card-body">
                                <div class="form-group row">
                                    <label for="inputEmail3" class="col-sm-2 col-form-label">Email</label>
                                    <div class="col-sm-10">
                                        <input type="email" name="email" class="form-control" id="inputEmail3"
                                               placeholder="Email"
                                               required/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="company" class="col-sm-2 col-form-label">company</label>
                                    <div class="col-sm-10">
                                        <input type="text" name="company" class="form-control" id="company"
                                               placeholder="company"
                                               required/>
                                    </div>
                                </div>

                                    <!--Types -->
                                <div class="form-group row">
                                    <label for="typename" class="col-sm-2 col-form-label">Type
                                        Name</label>
                                    <div class="col-sm-10">

                                        <select class="form-control"  id="typename" name="typename" required onchange="DisableMenu()">
                                            <option value="0" selected>Select Type</option>
                                            <option value="1">Affiliate</option>
                                            <option value="2">Employer</option>

                                        </select>
                                    </div>
                                </div>

                                <!--packages -->
                                <div class="form-group row">
                                    <label for="packagename" class="col-sm-2 col-form-label">Package
                                        Name</label>
                                    <div class="col-sm-10">

                                        <select class="form-control"  id="packagename" name="packagename" required>
                                            <option value="0" selected>Select Package</option>
                                                    <option value="1">Silver</option>
                                                    <option value="2">Gold</option>

                                        </select>
                                    </div>
                                </div>
                            </div>
                            <!-- /.card-body -->
                            <div class="card-footer">
                                <button type="submit" class="btn btn-primary float-right custom-btn">Send invitation
                                </button>
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
<!-- ./wrapper -->
<script>
    var roomIdFromFrontAdmin = ${requestScope.AdminsObject.id};
    var selfId = ${requestScope.AdminsObject.id};
    var roomIdFromFrontUser = 0;
    var sessionRoomFromFront = 'jok2f4fud_9';

</script>

<jsp:include page="${pageContext.request.contextPath}/includes/Footer.jsp"/>
</body>
</html>

