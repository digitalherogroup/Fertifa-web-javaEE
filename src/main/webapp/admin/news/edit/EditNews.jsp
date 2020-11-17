<%--
  Created by IntelliJ IDEA.
  User: Asus / BeautyIt +374 98 22 98 98
  Date: 12/29/2019
  Time: 12:34 AM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
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

            <!-- /.sidebar-menu -->
            <jsp:include page="../../../includes/Sidebar.jsp"/>
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
                        <h1>news</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/admin-dashboard">Dashboard</a></li>
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/news">News</a></li>
                            <li class="breadcrumb-item active">Edit news</li>
                        </ol>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="row justify-content-center">
                <div class="col-md-12">
                    <div class="card card-primary custom-card">
                        <div class="card-header">
                            <h3 class="card-title">Create news</h3>
                        </div>
                        <!-- /.card-header -->
                        <!-- form start -->
                        <form class="form-horizontal" action="${pageContext.request.contextPath}/admin/news/edit/update" method="post" enctype="multipart/form-data">

                            <c:if test="${requestScope.NewsList!= null}">
                                <c:forEach items="${requestScope.NewsList}" var="news">
                                    <div class="card-body">
                                        <div class="form-group row">
                                            <c:set value="${news.thumbnailUrl}" var="isImage"/>
                                            <c:choose>
                                                <c:when test="${isImage != null}">
                                                    <div class="col-sm-12 news-edit-image">
                                                        <img src="<%=request.getContextPath()%>/${news.thumbnailUrl}" alt="User Avatar"  >
                                                    </div>
                                                </c:when>
                                            </c:choose>

                                        </div>
                                        <div class="form-group row">
                                            <label for="Image" class="col-sm-2 col-form-label" >Image</label>
                                            <div class="col-sm-10">
                                                <div class="input-group">
                                                    <div class="custom-file">
                                                        <input type="file" class="custom-file-input" name="Image"
                                                               id="image"  accept=".png,.jpg,.jpeg">
                                                        <label class="custom-file-label" for="image">Choose
                                                            image</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="title" class="col-sm-2 col-form-label">Title</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" name="updatetitle" id="title"
                                                       placeholder="Title" value="${news.title}" required>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label for="description" class="col-sm-2 col-form-label" >Description</label>
                                            <div class="col-sm-10">
                                                <textarea maxlength="300" class="form-control" name="Updatedescription" id="description"
                                                          placeholder="Description"  required>${news.shortDescription}</textarea>
                                            </div>
                                        </div>

                                        <div class="form-group row" style="margin-top:30px">
                                            <div class="col-sm-12">
                                                <textarea name="content" id="txtEditor" style="width: 100%; height: 400px;">${news.description}
                                    </textarea>

                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.card-body -->
                                    <div class="card-footer">
                                        <button type="submit" class="btn btn-primary custom-btn float-right">Update
                                        </button>
                                        <input value="${news.id}" name="id" type="hidden">
                                    </div>
                                    <!-- /.card-footer -->

                                </c:forEach>
                            </c:if>
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
    var selfId =  ${requestScope.AdminsObject.id};
</script>
<jsp:include page="../../../includes/Footer.jsp"/>

<script>
    (function () {
        $('#txtEditor').summernote({
            height: 300,
            toolbar: [
                // [groupName, [list of button]]
                ['style', ['bold', 'italic', 'underline', 'clear']],
                ['font', ['strikethrough', 'superscript', 'subscript']],
                ['fontsize', ['fontsize']],
                ['color', ['color']],
                ['para', ['ul', 'ol', 'paragraph']],
                ['height', ['height']]
            ]
        });
        bsCustomFileInput.init();
    })()
</script>

</body>
</html>

