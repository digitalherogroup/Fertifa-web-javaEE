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
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/admin-dashboard">Dashboard</a></li>
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/products/all">Products</a></li>
                            <li class="breadcrumb-item active">Add Product</li>
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
                            <h3 class="card-title">Edit Product</h3>
                        </div>
                        <!-- /.card-header -->
                        <!-- form start -->
                        <form:form
                                class="form-horizontal"
                                action="/admin/products/update"
                                method="post"
                                enctype="multipart/form-data"
                                ModelAttribute="products">
                            <c:if test="${productObject != null}">
                            <div class="card-body">
                                <div class="form-group row">

                                            <div class="col-sm-12 news-edit-image">
                                                <img src="${productObject.imageLink}" alt="User Avatar"  >
                                                <input type="hidden" value="${productObject.imageLink}" name="imageLink">
                                            </div>

                                    <label for="imageLink" class="col-sm-2 col-form-label">Product Image</label>
                                    <div class="col-sm-10">
                                        <div class="input-group">
                                            <div class="custom-file">
                                                <input type="file" class="custom-file-input" accept=".png,.jpg,.jpeg" name="file" id="imageLink">
                                                <label class="custom-file-label" for="imageLink">Choose Image</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="title" class="col-sm-2 col-form-label">Product Title</label>
                                    <div class="col-sm-10">
                                        <input type="text" maxlength="150" class="form-control" name="title" id="title" value="${productObject.title}"placeholder="Product Title" required="">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="shortDescription" class="col-sm-2 col-form-label">Short Description</label>
                                    <div class="col-sm-10">
                                        <input maxlength="150" class="form-control" name="shortDescription"
                                                  id="shortDescription" placeholder="Short Description"
                                                  value="${productObject.shortDescription}" required=""/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="description" class="col-sm-2 col-form-label">Description</label>
                                    <div class="col-sm-10">
                                        <input maxlength="1200" class="form-control" name="description" id="description"
                                                  value="${productObject.description}" placeholder="Description" required=""/>
                                        </input>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="category" class="col-sm-2 col-form-label">Category</label>
                                    <div class="col-sm-10">
                                        <select class="form-control" name="categories" id="category" required>
                                            <option value="${productObject.categories.get(0).id}">${productObject.categories.get(0).categoryName}</option>
                                        <c:if test="${categories != null}">
                                                <c:forEach items="${categories}" var="category">
                                                    <option value="${category.id}">${category.categoryName}</option>
                                                </c:forEach>
                                            </select>

                                        </c:if>
                                    </div>
                                </div>
<%--                                <div class="form-group row">--%>
<%--                                    <label for="paymentType" class="col-sm-2 col-form-label">Payment Type</label>--%>
<%--                                    <div class="col-sm-10">--%>
<%--                                        <select class="form-control" name="paymentType" id="paymentType" required>--%>
<%--                                            <option value="#">Select Payment Type</option>--%>
<%--                                            <option value="">Hour</option>--%>
<%--                                            <option value="">Fixed</option>--%>
<%--                                        </select>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">Status</label>
                                    <div class="col-sm-10">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="published" id="published"
                                                   <c:if test="${productObject.published == 0}">checked=""</c:if> value="0">
                                            <label for="published" class="form-check-label">Publish</label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio"
                                                   <c:if test="${productObject.published == 1}">checked=""</c:if> name="published" id="unpublished" value="1">
                                            <label for="unpublished" class="form-check-label">Unpublish</label>
                                        </div>
                                    </div>


                                </div>
                                <div class="form-group row">
                                    <label for="price" class="col-sm-2 col-form-label">Price</label>
                                    <div class="col-sm-10">
                                        <input type="number" class="form-control" name="price" value="${productObject.price}"
                                               id="price" placeholder="Price" required="" step="any">
                                    </div>
                                </div>
                            </div>
                            <!-- /.card-body -->
                            <div class="card-footer">
                                <button type="submit" class="btn btn-primary custom-btn float-right">Update</button>
                                <input type="hidden" name="id" value="${productObject.id}"/>
                            </div>
                            </c:if>
                            <!-- /.card-footer -->
                        </form:form>
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
    <div id="sidebar-overlay"></div></div>
<!-- ./wrapper -->

<!-- jQuery -->
<script>
    var roomIdFromFrontAdmin = ${requestScope.AdminsObject.id};
    var selfId = ${requestScope.AdminsObject.id};
    var roomIdFromFrontUser = 0;
    var sessionRoomFromFront = 'jok2f4fud_9';
</script>
<jsp:include page="../../includes/Footer.jsp"/>
</body>
</html>
