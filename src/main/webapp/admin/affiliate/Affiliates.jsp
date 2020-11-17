<%--
  Created by IntelliJ IDEA.
  User: rooter
  Date: 12/28/19
  Time: 4:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@page import="java.text.DateFormat" %>
<%@page import="java.util.*" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Calendar" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.ParseException" %>
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
<!-- Site wrapper -->
<div class="wrapper">
    <!-- Navbar -->
    <jsp:include page="${pageContext.request.contextPath}/includes/NavigationBar.jsp"/>

    <!-- Main Sidebar Container -->
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <!-- Brand Logo -->
        <jsp:include page="${pageContext.request.contextPath}/includes/LogoAndBrand.jsp"/>
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
                        <h1>Employer</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/admin-dashboard">Dashboard</a></li>
                            <li class="breadcrumb-item active">Employer</li>
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
                        <div class="card table-with-extra-filters">
                            <!-- /.card-header -->
                            <div class="card-body table-with-extra-filters">
                                <div class="table-static-actions">
                                    <select class="form-control select-status-table" id="select-status-table">
                                        <option value="">Status</option>
                                        <option value="Active">Active</option>
                                        <option value="Inactive">Inactive</option>
                                        <option value="Pending">Pending</option>
                                    </select>
                                    <button class="btn btn-danger enable-disable delete-all-items" disabled>Delete all
                                    </button>
                                   <%-- <button class="btn btn-info enable-disable send-invitattion-all" disabled>Send
                                        Invitation
                                    </button>--%>
                                </div>
                                <form action="MuliActionCompany" method="POST" id="all_users_form">
                                    <table id="users-list-ems" class="table table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <td>
                                                <div class="form-check">
                                                    <input type="checkbox" name="all" class="form-check-input"
                                                           id="check_all">
                                                </div>
                                            </td>
                                            <th>Affiliate</th>
                                            <th>Invitation date</th>
                                            <th>Timer</th>
                                            <th>Status</th>
                                            <th>Actions</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:if test="${requestScope.Affiliates != null}">
                                        <c:forEach items="${requestScope.Affiliates}" var="affiliates"
                                                   varStatus="TheCount">
                                        <tr>
                                            <th>

                                                <div class="form-check">
                                                    <input name="id[${TheCount.count}]" value="${affiliates.id}"
                                                           type="checkbox" class="form-check-input single_checkox"
                                                           id="${affiliates.id}">
                                                </div>
                                            </th>
                                            <c:set value="${affiliates.getAffiliateOnline(affiliates.id)}" var="onlineStatus" />
                                            <td><a href="Detail?id=${affiliates.id}">${affiliates.firstName} ${affiliates.lastName} </a> -
                                                <c:if test="${onlineStatus == false}"> <span class="badge badge-secondary ml-2">Offline</span></c:if>
                                                <c:if test="${onlineStatus == true}"> <span class="badge badge-success ml-2">Online</span></c:if>
                                            </td>


                                            <td><span
                                                    class="rounded-time">${affiliates.getCreationDate}</span>
                                            </td>


                                            <c:set value='${affiliates.getCountCreationDate}' var="daysAlert"/>
                                            <c:choose>
                                                <c:when test="${daysAlert >= 90}">
                                                    <td class="alert-danger">
                                                            ${affiliates.getCountCreationDate}  days Passed
                                                    </td>
                                                </c:when>
                                                <c:when test="${daysAlert <= 60 && daysAlert >= 31}">
                                                    <td class="alert-warning">
                                                            ${affiliates.getCountCreationDate}  days Passed
                                                    </td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td>
                                                            ${affiliates.getCountCreationDate}  days Passed
                                                    </td>
                                                </c:otherwise>
                                            </c:choose>

                                            <td>
                                                <c:set value="${affiliates.status}" var="status"/>
                                                <c:choose>
                                                    <c:when test="${status == 1}">
                                                        <span class="badge badge-success">Active</span>
                                                    </c:when>
                                                    <c:when test="${status == 2}">
                                                        <span class="badge badge-danger">Inactive</span>
                                                    </c:when>
                                                    <c:when test="${status == 0}">
                                                        <span class="badge badge-secondary">Pending</span>
                                                      <%--  <button type="button"
                                                                class="btn btn-info open-invitation-modal invitattion-resend-button"
                                                                data-id="${affiliates.id}">
                                                            Resend
                                                        </button>--%>
                                                    </c:when>
                                                </c:choose>
                                            </td>
                                            <td class="table-buttons-manage">
                                                <c:choose>
                                                    <c:when test="${status == 0}">
                                                        <button type="button"
                                                                class="btn btn-block btn-outline-danger btn-sm open-delete-modal"
                                                                data-id="${affiliates.id}">
                                                            Delete
                                                        </button>
                                                    </c:when>
                                                    <c:when test="${status == 2}">
                                                        <a href="EditEmployee?id=${affiliates.id}"
                                                           class="btn btn-block btn-outline-warning btn-sm">Edit</a>
                                                        <button type="button"
                                                                class="btn btn-block btn-outline-danger btn-sm open-delete-modal"
                                                                data-id="${affiliates.id}">
                                                            Delete
                                                        </button>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a href="MessagingAdmin?id=${affiliates.id}"
                                                           class="btn btn-block btn-outline-info btn-sm">Message</a>
                                                        <a href="EditEmployee?id=${affiliates.id}"
                                                           class="btn btn-block btn-outline-warning btn-sm">Edit</a>
                                                        <button type="button"
                                                                class="btn btn-block btn-outline-danger btn-sm open-delete-modal"
                                                                data-id="${affiliates.id}">
                                                            Delete
                                                        </button>
                                                    </c:otherwise>
                                                </c:choose>

                                            </td>
                                        </tr>

                                        </c:forEach>
                                        </c:if>
                                        </tfoot>
                                    </table>
                                    <input type="hidden" name="requestType" value="" id="requestType"/>
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
                <form action="DeleteEmployee" method="POST">
                    <div class="modal-header">
                        <h4 class="modal-title">Delete</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Would you like to delete user?</p>
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
    <div class="modal fade" id="modal-resend">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="ResendInvitationEmployee" method="POST">
                    <div class="modal-header">
                        <h4 class="modal-title">Resend</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Would you like to resend invitation?</p>
                        <input type="hidden" class="hidden-selected-user" name="id" value="">
                    </div>
                    <div class="modal-footer justify-content-between">
                        <button type="button" class="btn btn-default click-dismiss-resend-modal"
                                data-dismiss="modal">Cancel
                        </button>
                        <button type="submit" class="btn btn-success">Resend</button>
                    </div>
                </form>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
</div>
<!-- ./wrapper -->
<script>
    var roomIdFromFrontAdmin = ${requestScope.AdminsObject.id};
    var selfId = ${requestScope.AdminsObject.id};
    var roomIdFromFrontUser = 0;
    var sessionRoomFromFront = 'jok2f4fud_9';
</script>
<jsp:include page="${pageContext.request.contextPath}/includes/Footer.jsp"/>
<!-- page script -->
<script>
    $(function () {
        var dataTable = $('#users-list-ems').DataTable({
            "paging": true,
            "lengthChange": false,
            "searching": true,
            "ordering": true,
            "columns": [
                {"orderable": false, "width": "25px"},
                null,
                null,
                null,
                null,
                {"orderable": false, "width": "235px"},
            ],
            "info": true,
            "autoWidth": false,
        });

        $('#select-status-table').on('change', function () {
            dataTable.columns(4).search(this.value).draw();
            disableAllCheckboxes()
        });

        $('#users-list-ems').on('page.dt', function () {
            disableAllCheckboxes();
        });

        dataTable.on('search.dt', function () {
            // delete checkboxes
            disableAllCheckboxes();
        });

        $("#check_all").change(function () {  //"select all" change
            var status = this.checked; // "select all" checked status
            $('input.single_checkox').each(function () { //iterate all listed checkbox items
                this.checked = status; //change ".checkbox" checked status
            });
            enableDisableButtons();
        });

        $(document).on('change', 'input.single_checkox', function () {//".checkbox" change
            //uncheck "select all", if one of the listed checkbox item is unchecked
            if (this.checked == false) { //if this item is unchecked
                $("#check_all")[0].checked = false; //change "select all" checked status to false
            }

            //check "select all" if all checkbox items are checked
            if ($('input.single_checkox:checked').length == $('input.single_checkox').length) {
                $("#check_all")[0].checked = true; //change "select all" checked status to true
            }

            enableDisableButtons();
        });

        $('.delete-all-items').on('click', function () {
            $('#all_users_form').find('.action-field').val('delete');
            $('#requestType').val('delete');
            $('form#all_users_form').submit();
        });

        $('.send-invitattion-all').on('click', function () {
            $('#all_users_form').find('.action-field').val('invitation');
            $('#requestType').val('invitation');
            $('form#all_users_form').submit();
        });

        $('.click-dismiss-del-modal').on('click', function () {
            $('#modal-delete').find('.hiden-selected-user').val('');
        });

        $(document).on('click', '.open-delete-modal', function () {
            var data_id = $(this).attr('data-id');
            $('#modal-delete').find('.hidden-selected-user').val('');
            $('#modal-delete').find('.hidden-selected-user').val(data_id);
            $('#modal-delete').modal();
        });

        $('.invitattion-resend-button').on('click', function () {
            var data_id = $(this).attr('data-id');
            $('#modal-resend').find('.hidden-selected-user').val('');
            $('#modal-resend').find('.hidden-selected-user').val(data_id);
            $('#modal-resend').modal();
        });


        $('.click-dismiss-resend-modal').on('click', function () {
            $('#modal-resend').find('.hiden-selected-user').val('');
        });
    });

    function enableDisableButtons() {
        if ($('input.single_checkox:checked').length !== 0) {
            $('.enable-disable').prop('disabled', false);
        } else {
            $('.enable-disable').prop('disabled', true);
            $('#all_users_form').find('.action-field').val('');
        }
    }

    function disableAllCheckboxes() {
        // delete checkboxes
        $('input.single_checkox').each(function () { //iterate all listed checkbox items
            this.checked = false; //change ".checkbox" checked status
        });
        $("#check_all")[0].checked = false;
        enableDisableButtons();
    }
</script>
</body>
</html>

