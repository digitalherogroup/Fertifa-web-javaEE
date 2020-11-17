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
    <c:set value="${pageContext.request.contextPath}" var="root"/>
    <%
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader ("Expires", 0);
        if(session.getAttribute("adminEmail")==null){
            response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard");

        }
    %>
    <jsp:include page="${root}/includes/Header.jsp"/>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<!-- Site wrapper -->
<div class="wrapper">
    <!-- Navbar -->
    <jsp:include page="${root}/includes/NavigationBar.jsp"/>

    <!-- Main Sidebar Container -->
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <!-- Brand Logo -->
        <jsp:include page="${root}/includes/LogoAndBrand.jsp"/>
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
                        <h1>Employees</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="${root}/admin/admin-dashboard">Dashboard</a></li>
                            <li class="breadcrumb-item active">Employees</li>
                        </ol>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="container-fluid">
                <div class="loading-place"></div>
                <div class="row">
                    <div class="col-12">
                        <!-- Default box -->
                        <div class="card">
                            <!-- /.card-header -->
                            <div class="card-body table-with-extra-filters">
                                <div class="table-static-actions">
                                    <select class="form-control select-company-table" id="select-company-table">
                                        <option value="" selected>Employer</option>
                                        <c:if test="${requestScope.CompanyList != null}">
                                            <c:forEach items="${requestScope.CompanyList}" var="Company">
                                                <option value="${Company.comapny}">${Company.comapny}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                    <select class="form-control select-status-table" id="select-status-table">
                                        <option value="">Status</option>
                                        <option value="Active">Active</option>
                                        <option value="Inactive">Inactive</option>
                                        <option value="Pending">Pending</option>
                                        <option value="Preparing Deletion">Preparing deletion</option>
                                    </select>

                                    <button class="btn btn-danger enable-disable delete-all-items" disabled>Delete all
                                    </button>
                                    <button class="btn btn-info enable-disable send-invitattion-all" disabled>Send
                                        Invitation
                                    </button>
                                </div>
                                <form action="${root}/admin/customer/employee/multi-invitation" method="POST" id="all_users_form">
                                    <table id="users-list-emp" class="table table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <td>
                                                <div class="form-check">
                                                    <input type="checkbox" name="all" class="form-check-input"
                                                           id="check_all">
                                                </div>
                                            </td>
                                            <th>Image</th>
                                            <th>User</th>
                                            <th>Status</th>
                                            <th>com.fertifa.app.company</th>
                                            <th>Actions</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:if test="${requestScope.UsersList != null}">
                                            <c:forEach items="${requestScope.UsersList}" var="Users" varStatus="TheCount">
                                                <tr>
                                                    <th>
                                                        <div class="form-check">
                                                            <input name="id[${TheCount.count}]" value="${Users.id}" type="checkbox"
                                                                   class="form-check-input single_checkox"
                                                                   id="${Users.id}">
                                                        </div>
                                                    </th>
                                                    <c:set value="${Users.firstName}" var="nameToCut" scope="request"/>
                                                    <c:set value="${Users.lastName}" var="lastToCut" scope="request"/>
                                                    <%
                                                        String finalName = "";
                                                        String finallast = "";
                                                        String name = String.valueOf(request.getAttribute("nameToCut"));
                                                        String last = String.valueOf(request.getAttribute("lastToCut"));
                                                        if(!name.equals("")){
                                                            finalName = name.substring(0, 1);
                                                        }

                                                        if(!last.equals("")){
                                                            finallast = last.substring(0, 1);
                                                        }
                                                    %>
                                                    <td>
                                                        <span class="img-size-30 mr-3 img-circle empty-avatar"><%=finalName.toUpperCase()%><%=finallast.toUpperCase()%></span>
                                                    </td>
                                                    <td><a href="${root}/admin/profile/detail?id=${Users.id}">${Users.firstName} ${Users.lastName}</a></td>
                                                    <td>
                                                        <c:set value="${Users.status}" var="status"/>
                                                        <c:choose>
                                                            <c:when test="${status == 1}">
                                                                <span class="badge badge-success">Active</span>
                                                            </c:when>
                                                            <c:when test="${status == 2}">
                                                                <span class="badge badge-danger">Inactive</span>
                                                            </c:when>
                                                            <c:when test="${status == 3}">

                                                                <span class="badge badge-deletion open-delete-modal" data-id="${Users.id}">Preparing Deletion</span>
                                                            </c:when>
                                                            <c:when test="${status == 0}">
                                                                <span class="badge badge-secondary">Pending</span>
                                                                <button type="button"
                                                                        class="btn btn-info open-invitation-modal invitattion-resend-button"
                                                                        data-id="${Users.id}">Resend
                                                                </button>
                                                            </c:when>
                                                        </c:choose>
                                                    </td>
                                                    <td>${Users.comapny}
                                                    </td>
                                                    <td class="table-buttons-manage">
                                                        <c:choose>
                                                            <c:when test="${status == 0}">
                                                                <button type="button"
                                                                        class="btn btn-block btn-outline-danger btn-sm open-delete-modal"
                                                                        data-id="${Users.id}">
                                                                    Delete
                                                                </button>
                                                            </c:when>
                                                            <c:when test="${status == 2}">
                                                                <a href="${root}/admin/customer/employee/edit?id=${Users.id}"
                                                                   class="btn btn-block btn-outline-warning btn-sm">Edit</a>
                                                                <button type="button"
                                                                        class="btn btn-block btn-outline-danger btn-sm open-delete-modal"
                                                                        data-id="${Users.id}">
                                                                    Delete
                                                                </button>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a href="${root}/admin/customer/chat?id=${Users.id}" class="btn btn-block btn-outline-info btn-sm">Message</a>
                                                                <a href="${root}/admin/customer/employee/edit?id=${Users.id}"
                                                                   class="btn btn-block btn-outline-warning btn-sm">Edit</a>
                                                                <button type="button"
                                                                        class="btn btn-block btn-outline-danger btn-sm open-delete-modal"
                                                                        data-id="${Users.id}">
                                                                    Delete
                                                                </button>
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

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Control sidebar content goes here -->
    </aside>
    <!-- /.control-sidebar -->

    <div class="modal fade" id="modal-delete">
        <div class="modal-dialog">
            <div class="modal-content bg-danger">
                <form action="${root}/admin/customer/employee/delete" method="POST">
                    <div class="modal-header">
                        <h4 class="modal-title">Delete</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Would you like to delete a user?</p>
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
    <div class="modal fade" id="modal-invitation">
        <div class="modal-dialog">
            <div class="modal-content bg-secondary">
                <form action="${root}/admin/customer/employee/resend" id="resend-invitation-form" method="POST">
                    <div class="modal-header">
                        <h4 class="modal-title">Send Invitattion</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span></button>
                    </div>
                    <div class="modal-body">
                        <p>Would you like to send notifikation to user?</p>
                        <input type="hidden" class="hidden-selected-user" name="id" value="">
                    </div>
                    <div class="modal-footer justify-content-between">
                        <button type="button" class="btn btn-outline-light click-dismiss-invitation-modal"
                                data-dismiss="modal">Close
                        </button>
                        <button type="button" class="btn btn-outline-light resend-single-invitation">Send Invitattion</button>
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
<jsp:include page="../../../includes/Footer.jsp"/>
<!-- page script -->
<script>
    $(function () {
        var dataTable = $('#users-list-emp').DataTable({
            "paging": true,
            "lengthChange": false,
            "searching": true,
            "ordering": true,
            "columns": [
                {"orderable": false, "width": "25px"},
                {"orderable": false, "width": "25px"},
                null,
                {"width": "115px"},
                null,
                {"orderable": false, "width": "235px"},
            ],
            "info": true,
            "autoWidth": false,
        });

        $('#select-company-table').on('change', function () {
            dataTable.columns(4).search(this.value).draw();
            disableAllCheckboxes()
        });

        $('#select-status-table').on('change', function () {
            dataTable.columns(3).search(this.value).draw();
            disableAllCheckboxes()
        });

        dataTable.on('search.dt', function () {
            // delete checkboxes
            disableAllCheckboxes();
        });

        $('#users-list-emp').on( 'page.dt', function () {
            disableAllCheckboxes();
        });

        $("#check_all").change(function () {  //"select all" change
            var status = this.checked; // "select all" checked status
            $('input.single_checkox').each(function () { //iterate all listed checkbox items
                this.checked = status; //change ".checkbox" checked status
            });
            enableDisableButtons();
        });

        $(document).on('change', 'input.single_checkox', function () { //".checkbox" change
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

        $('.resend-single-invitation').on('click', function () {
            $('.loading-place').html('<div class="request-ring"><div></div><div></div><div></div><div></div></div>');
            $('form#resend-invitation-form').submit();
        });

        $('.click-dismiss-del-modal').on('click', function () {
            $('#modal-delete').find('.hiden-selected-user').val('');
        });

        $('.click-dismiss-invitation-modal').on('click', function () {
            $('#modal-invitation').find('.hiden-selected-user').val('');
        });

        $(document).on('click', '.open-delete-modal', function () {
            var delete_data_id = $(this).attr('data-id');
            $('#modal-delete').find('.hidden-selected-user').val('');
            $('#modal-delete').find('.hidden-selected-user').val(delete_data_id);
            $('#modal-delete').modal();
        });

        $('.open-invitation-modal').on('click', function () {
            var invitation_data_id = $(this).attr('data-id');
            $('#modal-invitation').find('.hidden-selected-user').val('');
            $('#modal-invitation').find('.hidden-selected-user').val(invitation_data_id);
            $('#modal-invitation').modal();
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
        $('input.single_checkox').each(function(){ //iterate all listed checkbox items
            this.checked = false; //change ".checkbox" checked status
        });
        $("#check_all")[0].checked = false;
        enableDisableButtons();
    }
</script>
</body>
</html>

