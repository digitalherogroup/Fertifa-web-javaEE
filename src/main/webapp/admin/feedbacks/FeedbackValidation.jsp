<%--
  Created by IntelliJ IDEA.
  User: rooter
  Date: 1/7/20
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
                        <h1>Feedbacks</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/admin-dashboard">Dashboard</a></li>
                            <li class="breadcrumb-item">Feedbacks</li>
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
                            <div class="card-body">

                                <table id="feedback-list" class="table table-bordered table-hover">
                                    <thead>

                                    <tr>
                                        <th>Rating</th>
                                        <th>Feedback</th>
                                        <th>Status</th>
                                        <th>Actions</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${requestScope.FeedBacksList!=null}">
                                        <c:forEach items="${requestScope.FeedBacksList}" var="feedBacks">
                                            <c:set var="Rating" value="${feedBacks.feedbackrating}" scope="request"/>
                                            <%!
                                                static float starsShine;
                                                static float starsNoShine;
                                                static float countStars;
                                            %>
                                            <%
                                                String floatString = String.valueOf(request.getAttribute("Rating"));
                                                System.out.println(floatString);
                                                starsShine = Float.parseFloat(floatString);
                                                System.out.println("starsShine " + starsShine);
                                                starsNoShine = 0;
                                                countStars = 0;
                                            %>

                                            <tr>
                                                <td>
                                                    <div class="feedback-rating">
                                                        <% for (float i = 0; i < starsShine; i++) {
                                                        %>
                                                        <span><i class="fas fa-star rated"></i></span>

                                                        <%}%>
                                                            <%--  <% if (countStars(starsShine) == 0.5f) {
                                                              %>
                                                              <span><i class="fas fa-star-half-alt"></i></span>
                                                              <%}%>--%>

                                                    </div>
                                                    <span class="d-none"><%=starsShine%></span>
                                                </td>
                                                <td>${feedBacks.feedbackText}
                                                </td>
                                                <td>
                                                    <c:set value="${feedBacks.feedbackStatus}" var="status"/>
                                                    <c:choose>
                                                        <c:when test="${status == 0}">
                                                            <span class="badge badge-secondary">Pending</span>
                                                        </c:when>
                                                        <c:when test="${status == 1}">
                                                            <span class="badge badge-success">Approved</span>
                                                        </c:when>

                                                        <c:when test="${status == 2}">
                                                            <span class="badge badge-danger">Rejected</span>
                                                        </c:when>

                                                    </c:choose>

                                                </td>
                                                <c:choose>
                                                    <c:when test="${status == 2 || status == 1}">
                                                        <td class="table-buttons-manage">
                                                        </td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td class="table-buttons-manage">
                                                            <button type="button"
                                                                    class="btn btn-block btn-outline-warning btn-sm open-action-modal"
                                                                    data-id="${feedBacks.id}">
                                                                Action
                                                            </button>
                                                        </td>
                                                    </c:otherwise>

                                                </c:choose>

                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                    </tbody>
                                </table>

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

    <div class="modal fade" id="modal-action">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="${pageContext.request.contextPath}/admin/feedbacks/update" method="POST">
                    <div class="modal-header">
                        <h4 class="modal-title">Confirm Action</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Do you want to Confirm or Reject feedback?</p>
                        <input name="id" type="hidden" class="hidden-selected-item" value="">
                    </div>
                    <div class="modal-footer justify-content-right">
                        <button type="button" class="btn btn-default click-dismiss-action-modal float-left"
                                data-dismiss="modal">Cancel
                        </button>
                        <button type="submit" name="action" class="btn btn-success" value="confirm">Confirm</button>
                        <button type="submit" name="action" class="btn btn-danger" value="reject">Reject</button>
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
    var sessionRoomFromFront = "jok2f4fud_9";
    var roomIdFromFrontUser = 0;
</script>
<jsp:include page="../../includes/Footer.jsp"/>
<script>
    $(function () {
        $('#feedback-list').DataTable({
            "paging": true,
            "lengthChange": false,
            "searching": false,
            "ordering": true,
            "columns": [
                {"width": "110px"},
                null,
                null,
                {"orderable": false, "width": "100px"},
            ],
            "info": true,
            "autoWidth": false,
        });

        $(document).on('click', '.open-action-modal', function () {
            var action_data_id = $(this).attr('data-id');
            $('#modal-action').find('.hidden-selected-item').val('');
            $('#modal-action').find('.hidden-selected-item').val(action_data_id);
            $('#modal-action').modal();
        });

        $('.click-dismiss-action-modal').on('click', function () {
            $('#modal-action').find('.hidden-selected-item').val('');
        });
    });
</script>

</body>
</html>

