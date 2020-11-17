<%--
  Created by IntelliJ IDEA.
  User: rooter
  Date: 12/28/19
  Time: 4:58 PM
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
                        <h1>Nurses</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/admin-dashboard">Dashboard</a></li>
                            <li class="breadcrumb-item active">Nurses</li>
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
                                <form action="MuliActionUsers" method="POST" id="all_users_form">
                                    <table id="nurses-list" class="table table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>User</th>
                                            <th>Image</th>
                                            <th>Status</th>
                                            <th>Actions</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:if test="${requestScope.NurseList != null}">
                                            <c:forEach items="${requestScope.NurseList}" var="Nurses"
                                                       varStatus="TheCount">
                                                <tr>
                                                    <td>
                                                        <a href="${pageContext.request.contextPath}/admin/nurse/detail?id=${Nurses.id}">${Nurses.firstName} ${Nurses.lastName}</a>
                                                    </td>

                                                    <c:set value="${Nurses.firstName}" var="nameToCut" scope="request"/>
                                                    <c:set value="${Nurses.lastName}" var="lastToCut" scope="request"/>
                                                    <%
                                                        String name = String.valueOf(request.getAttribute("nameToCut"));
                                                        String last = String.valueOf(request.getAttribute("lastToCut"));
                                                        String finalName = name.substring(0, 1);
                                                        String finallast = last.substring(0, 1);
                                                    %>
                                                    <td>
                                                        <span class="img-size-30 mr-3 img-circle empty-avatar"><%=finalName.toUpperCase()%><%=finallast.toUpperCase()%></span>
                                                    </td>

                                                    <td>
                                                        <c:set value="${Nurses.status}" var="status"/>
                                                        <c:choose>
                                                            <c:when test="${status == 1}">
                                                                <span class="badge badge-success">Active</span>
                                                            </c:when>
                                                            <c:when test="${status == 0}">
                                                                <span class="badge badge-secondary">Inactive</span>
                                                            </c:when>
                                                        </c:choose>
                                                    </td>

                                                    <td class="table-buttons-manage">
                                                            <%--<a href="#" class="btn btn-block btn-outline-info btn-sm">Message</a>--%>
                                                        <a href="${pageContext.request.contextPath}/admin/nurse/edit?id=${Nurses.id}"
                                                           class="btn btn-block btn-outline-warning btn-sm">Edit</a>
                                                        <button type="button"
                                                                class="btn btn-block btn-outline-danger btn-sm open-delete-modal"
                                                                data-id="${Nurses.id}">
                                                            Delete
                                                        </button>
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

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Control sidebar content goes here -->
    </aside>
    <!-- /.control-sidebar -->

    <div class="modal fade" id="modal-delete">
        <div class="modal-dialog">
            <div class="modal-content bg-danger">
                <form action="${pageContext.request.contextPath}/admin/nurse/Delete" method="POST">
                    <div class="modal-header">
                        <h4 class="modal-title">Delete</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Would you like to delete a nurse?</p>
                        <input type="hidden" class="hidden-selected-user" name="id" value="">
                    </div>
                    <div class="modal-footer justify-content-between">
                        <button type="button" class="btn btn-outline-light click-dismiss-del-modal"
                                data-dismiss="modal">Cancel
                        </button>
                        <button type="submit" class="btn btn-outline-light">Delete</button>
                    </div>
                </form>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->

    <!-- /.modal -->
</div>
<!-- ./wrapper -->
<script>
    var roomIdFromFrontAdmin = ${requestScope.AdminsObject.id};
    var selfId = ${requestScope.AdminsObject.id};
    var roomIdFromFrontUser = 0;
    var sessionRoomFromFront = 'jok2f4fud_9';
</script>
<jsp:include page="../../includes/Footer.jsp"/>
<!-- page script -->
<script>
    $(function () {
        var dataTable = $('#nurses-list').DataTable({
            "paging": true,
            "lengthChange": false,
            "searching": true,
            "ordering": true,
            "columns": [
                null,
                {"orderable": false, "width": "25px"},
                null,
                {"orderable": false, "width": "235px"},
            ],
            "info": true,
            "autoWidth": false,
        });

        $('#select-company-table').on('change', function () {
            dataTable.columns(4).search(this.value).draw();
        });

        $('#select-status-table').on('change', function () {
            dataTable.columns(3).search(this.value).draw();
        });

        $('.click-dismiss-del-modal').on('click', function () {
            $('#modal-delete').find('.hiden-selected-user').val('');
        });

        $(document).on('click', '.open-delete-modal', function () {
            var delete_data_id = $(this).attr('data-id');
            $('#modal-delete').find('.hidden-selected-user').val('');
            $('#modal-delete').find('.hidden-selected-user').val(delete_data_id);
            $('#modal-delete').modal();
        });
    });
</script>
</body>
</html>

