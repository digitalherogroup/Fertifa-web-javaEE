<%--
  Created by IntelliJ IDEA.
  User: Shant
  Date: 1/31/2020
  Time: 1:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
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
                        <h1>Booking requests</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/admin-dashboard">Dashboard</a></li>
                            <li class="breadcrumb-item">Booking requests</li>
                        </ol>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="container-fluid">
                <div class="row">
                    <div class="col-12">
                        <!-- Default box -->
                        <div class="card">
                            <!-- /.card-header -->
                            <div class="card-body table-with-extra-filters">
                                <form action="" method="POST" id="all_users_form">
                                    <table id="users-list" class="table table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>Date sent</th>
                                            <th>Service name</th>
                                            <th>User Name</th>
                                            <th>company</th>
                                            <th>Cost</th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:if test="${requestScope.ServiceDateTimeArrayListFinal!=null}">
                                            <c:forEach items="${requestScope.ServiceDateTimeArrayListFinal}" var="Orders">
                                                <tr>
                                                    <c:set value="${Orders.date}" var="CreationDate" scope="request"/>

                                                    <td>
                                                        <ul class="bookings-times-format">${Orders.date}</ul>
                                                    </td>
                                                    <td>${Orders.serviceName}</td>
                                                    <td>${Orders.firstName} ${Orders.lastName}</td>
                                                    <td>${Orders.companyname}</td>

                                                    <td>
                                                    £ <fmt:formatNumber type="number" maxFractionDigits="3"
                                                                        value='${Orders.servicePrice}'/>
                                                    </td>

                                                    <td class="table-buttons-manage">
                                                        <c:set value="${Orders.status}" var="status"/>
                                                        <c:choose>
                                                            <c:when test="${status != 1}">
                                                                <button type="button"
                                                                        class="btn btn-block btn-outline-success btn-sm open-approve-modal"
                                                                        data-id="${Orders.order_id}">
                                                                    Approve
                                                                </button>
                                                                <button type="button"
                                                                        class="btn btn-block btn-outline-warning btn-sm open-edit-modal"
                                                                        data-id="${Orders.order_id}">
                                                                    Edit
                                                                </button>
                                                            </c:when>
                                                            <c:otherwise>

                                                            </c:otherwise>
                                                        </c:choose>

                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>

                                        </tbody>
                                    </table>
                                    <input type="hidden" name="action" class="action-field" value="">
                                </form>
                            </div>
                            <!-- /.card-body -->
                        </div>
                        <!-- /.card -->
                    </div>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <div class="modal fade" id="modal-edit">
        <div class="modal-dialog" style="max-width: 700px;">
            <div class="modal-content">
                <form action="" method="POST" id="book-request-form">
                    <div class="modal-header">
                        <h4 class="modal-title">Suggest other dates</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Would you like to suggest other dates?</p>
                        <div class="row">
                            <div class="col-lg-6">
                                <div id="appointement-calendar-place" class="service-calendar"></div>
                            </div>
                            <div class="col-lg-6">
                                <ul id="wizzard-append-filtered-dates" class="nav flex-column filtered-dates"></ul>
                            </div>
                        </div>
                        <input type="hidden" class="hidden-selected-user" value="">
                    </div>
                    <div class="modal-footer justify-content-between">
                        <button type="button" class="btn btn-secondary click-dismiss-edit-modal" data-dismiss="modal">
                            Cancel
                        </button>
                        <button type="submit" class="btn btn-warning">Edit</button>
                    </div>
                </form>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
    <div class="modal fade" id="modal-approve">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="" method="POST" id="book-approval-form">
                    <div class="modal-header">
                        <h4 class="modal-title">Approve</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span></button>
                    </div>
                    <div class="modal-body">
                        <p>Would you like to approve this order?</p>
                        <input type="hidden" class="hidden-selected-user" value="">
                    </div>
                    <div class="modal-footer justify-content-between">
                        <button type="button" class="btn btn-secondary click-dismiss-approve-modal"
                                data-dismiss="modal">Cancel
                        </button>
                        <button type="submit" class="btn btn-success">Approve</button>
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
    var confirm_book_request_ajax = '/admin/SendOrderDataAjaxResponse';
    var manage_book_request_ajax = '/admin/ManageBookRequestAjax';
    var roomIdFromFrontAdmin = ${requestScope.AdminsObject.id};
    var selfId = ${requestScope.AdminsObject.id};
    var roomIdFromFrontUser = 0;
    var sessionRoomFromFront = 'jok2f4fud_9';

</script>
<jsp:include page="../../includes/Footer.jsp"/>
<!-- page script -->


</body>
</html>

