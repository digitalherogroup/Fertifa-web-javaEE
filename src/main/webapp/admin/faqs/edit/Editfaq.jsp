<%--
  Created by IntelliJ IDEA.
  User: rooter
  Date: 12/26/19
  Time: 9:08 PM
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
    <jsp:include page="../../../includes/Header.jsp"/>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<!-- Site wrapper -->
<div class="wrapper">
    <!-- Navbar -->
    <jsp:include page="../../../includes/NavigationBar.jsp"/>

    <!-- Main Sidebar Container -->
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <!-- Brand Logo -->
        <jsp:include page="../../../includes/LogoAndBrand.jsp"/>
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
                        <h1>FAQ</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/admin-dashboard">Dashboard</a></li>
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/faqs">Faq</a></li>
                            <li class="breadcrumb-item active">Edit Faq</li>
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
                        <%
                            if (request.getAttribute("errormessage") != null) {
                        %>
                        <span class="alert alert-danger"><%=request.getAttribute("errormessage")%></span>
                        <%
                            }
                        %>
                        <%
                            if (request.getAttribute("worningmessage") != null) {
                        %>
                        <span class="alert alert-warning"><%=request.getAttribute("worningmessage")%></span>
                        <%
                            }
                        %>
                        <%
                            if (request.getAttribute("message") != null) {
                        %>
                        <span class="alert alert-success"><%=request.getAttribute("message")%></span>
                        <%
                            }
                        %>
                        <div class="card-header">
                            <h3 class="card-title">Create FAQ</h3>
                        </div>
                        <!-- /.card-header -->
                        <!-- form start -->

                        <form class="form-horizontal" action="${pageContext.request.contextPath}/admin/faqs/update" method="post">
                            <c:if test="${requestScope.FaqsById != null}">
                                <c:forEach items="${requestScope.FaqsById}" var="EditingFaq">
                                    <div class="card-body">
                                        <div class="form-group row">
                                            <label  class="col-sm-2 col-form-label">Category</label>
                                            <div class="col-sm-10">
                                                <c:out value="${requestScope.FaqCategory}"/>
                                            </div>
                                        </div>


                                        <div class="form-group row">
                                            <label for="question" class="col-sm-2 col-form-label">Question</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" name="question" id="question"
                                                       placeholder="Question" value="${EditingFaq.faqQuestion}" required>

                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="answer" class="col-sm-2 col-form-label">Answer</label>
                                            <div class="col-sm-10">
                                        <textarea class="form-control" name="answer"  id="answer" placeholder="Answer"
                                                  required>${EditingFaq.faqAnswear}</textarea>
                                            </div>
                                        </div>


                                    </div>
                                    <!-- /.card-body -->
                                    <div class="card-footer">
                                        <button type="submit" class="btn btn-primary custom-btn float-right">Update</button>
                                        <input type="hidden" name="id" value="${EditingFaq.id}">
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
<script>
    var roomIdFromFrontAdmin = ${requestScope.AdminsObject.id};
    var selfId = ${requestScope.AdminsObject.id};
    var roomIdFromFrontUser = 0;
    var sessionRoomFromFront = 'jok2f4fud_9';
</script>
<!-- ./wrapper -->
<jsp:include page="../../../includes/Footer.jsp"/>
</body>
</html>
