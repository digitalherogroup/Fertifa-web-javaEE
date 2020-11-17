<%--
  Created by IntelliJ IDEA.
  User: rooter
  Date: 1/3/20
  Time: 3:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
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
    <div class="content-wrapper" style="min-height: 365.5px;">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>Products</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a
                                    href="${pageContext.request.contextPath}/admin/admin-dashboard">Dashboard</a></li>
                            <li class="breadcrumb-item active">Products</li>
                        </ol>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="row justify-content-center">
                <c:if test="${products != null}">
                    <c:forEach items="${products}" var="product">
                        <div class="col-md-4 col-12">
                            <div class="card product-card">
                                <img class="card-img-top product-image" src="${product.imageLink}" alt="${product.title}">
                                <div class="card-body">
                                    <h4 class="card-title product-title">${product.title}</h4>
                                    <p class="card-text product-short-description">${product.shortDescription}</p>
                                    <div class="table-buttons-manage mt-5">
                                        <a href="${pageContext.request.contextPath}/admin/products/edit/${product.id}"
                                           class="btn btn-block btn-outline-warning btn-sm">Edit</a>
                                        <button type="button"
                                                class="btn btn-block btn-outline-danger btn-sm open-delete-modal"
                                                data-id="${product.id}">
                                            Delete
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
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
    <!--delete modal start-->
    <div class="modal fade" id="modal-delete">
        <div class="modal-dialog">
            <div class="modal-content bg-danger">
                <form action="${pageContext.request.contextPath}/admin/products/delete" method="post">
                    <div class="modal-header">
                        <h4 class="modal-title">Delete</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Would you like to delete the product?</p>
                        <input type="hidden" class="hidden-selected-user" name="id" value="">
                    </div>
                    <div class="modal-footer justify-content-between">
                        <button type="button" class="btn btn-outline-light" data-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-outline-light">Delete</button>
                    </div>
                </form>

            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!--delete modal end-->

    <!--unpublish modal start-->
    <div class="modal fade" id="modal-unpublish">
        <div class="modal-dialog">
            <div class="modal-content bg-success">
                <div class="modal-header">
                    <h4 class="modal-title">Unpublish</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p>Would you like to unpublish product?</p>
                </div>
                <div class="modal-footer justify-content-between">
                    <button type="button" class="btn btn-outline-light" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-outline-light">Unpublish</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!--unpublish modal end-->
    <!-- /.modal -->
</div>
<!-- ./wrapper -->

<!-- jQuery -->
<script>
    var roomIdFromFrontAdmin = ${requestScope.AdminsObject.id};
    var selfId = ${requestScope.AdminsObject.id};
    var roomIdFromFrontUser = 0;
    var sessionRoomFromFront = 'jok2f4fud_9';
</script>
<jsp:include page="../../includes/Footer.jsp"/>
<script>
    $(function () {
        $('#users-list').DataTable({
            "paging": true,
            "lengthChange": false,
            "searching": true,
            "ordering": true,
            "columns": [
                null,
                null,
                {"orderable": false, "width": "150px"},
            ],
            "info": true,
            "autoWidth": false,
        });
        $(document).on('click', '.open-delete-modal', function () {
            var data_id = $(this).attr('data-id');
            $('#modal-delete').find('.hidden-selected-user').val('');
            $('#modal-delete').find('.hidden-selected-user').val(data_id);
            $('#modal-delete').modal();

        });
    });
</script>
</body>
</html>
