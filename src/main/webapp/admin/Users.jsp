<%--
  Created by IntelliJ IDEA.
  User: rooter
  Date: 12/26/19
  Time: 2:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
	<%
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader ("Expires", 0);
		if(session.getAttribute("adminEmail")==null){
			response.sendRedirect(request.getContextPath() + "/com/fertifa/app/adminSide");

		}
	%>
	<jsp:include page="../includes/Header.jsp"/>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<!-- Site wrapper -->
	<div class="wrapper">
		<!-- Navbar -->
		<jsp:include page="../includes/NavigationBar.jsp" />

		<!-- Main Sidebar Container -->
		<aside class="main-sidebar sidebar-dark-primary elevation-4">
			<!-- Brand Logo -->
			<jsp:include page="../includes/LogoAndBrand.jsp" />

			<!-- Sidebar -->
			<div class="sidebar">
				<!-- Sidebar user (optional) -->
				<div class="user-panel mt-3 pb-3 mb-3 d-flex">
					<div class="image">
						<c:if test="${requestScope.AdminFullList != null}">
							<c:forEach items="${requestScope.AdminFullList}" var="admin">
								<c:set value="${admin.firstName}" var="FirstNameToCut"
									scope="request" />
								<c:set value="${admin.lastName}" var="LastNameToCut"
									scope="request" />
								<%
                                String firstNameCutted = String.valueOf(request.getAttribute("FirstNameToCut"));
                                String SecondNameCutted = String.valueOf(request.getAttribute("LastNameToCut"));
                                String finalFirstName = firstNameCutted.substring(0,1);
                                String finalSecondName = SecondNameCutted.substring(0,1);
                            %>
								<span class="img-size-30 mr-3 img-circle empty-avatar"><%=finalFirstName.toUpperCase()%><%=finalSecondName.toUpperCase()%></span>
							</c:forEach>
						</c:if>
					</div>
					<div class="info">
						<c:if test="${requestScope.AdminFullList != null}">
							<c:forEach items="${requestScope.AdminFullList}" var="username">
								<a href="#" class="d-block">${username.firstName}
									${username.lastName}</a>
							</c:forEach>
						</c:if>
					</div>
				</div>

				<!-- Sidebar Menu -->
				<nav class="mt-2">
					<ul class="nav nav-pills nav-sidebar flex-column"
						data-widget="treeview" role="menu" data-accordion="false">
						<!-- Add icons to the links using the .nav-icon class
                         with font-awesome or any other icon font library -->
						<li class="nav-item"><a href="./dashboard.html"
							class="nav-link"> <i class="nav-icon fas fa-th"></i>
								<p>Dashboard</p>
						</a></li>
						<li class="nav-item"><a href="./users.html" class="nav-link">
								<i class="nav-icon fas fa-users"></i>
								<p>com.fertifa.app.users</p>
						</a></li>

						<li class="nav-item has-treeview"><a href="#"
							class="nav-link"> <i class="nav-icon fas fa-edit"></i>
								<p>
									Forms <i class="fas fa-angle-left right"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="../forms/general.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>General Elements</p>
								</a></li>
								<li class="nav-item"><a href="../forms/advanced.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>Advanced Elements</p>
								</a></li>
								<li class="nav-item"><a href="../forms/editors.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>Editors</p>
								</a></li>
							</ul></li>

					</ul>
				</nav>
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
							<h1>com.fertifa.app.users</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item"><a href="#">Layout</a></li>
								<li class="breadcrumb-item active">Fixed Layout</li>
							</ol>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">

				<div class="container-fluid">
					<div class="row">
						<div class="col-12">
							<!-- Default box -->
							<div class="card">
								<div class="card-header">
									<form action="" method="">
										<div class="row">
											<div class="col-md-3">
												<input type="email" class="form-control"
													id="exampleInputEmail1" placeholder="Username">
											</div>
											<div class="col-md-2">
												<select class="form-control">
													<option value="">All</option>
													<option value="1">Employer</option>
													<option value="2">Employer</option>
												</select>
											</div>
											<div class="col-md-3">
												<input type="email" class="form-control"
													id="exampleInputEmail1" placeholder="Date">
											</div>
											<div class="col-md-2">
												<button type="submit" class="btn btn-primary">Search</button>
											</div>
										</div>
									</form>
								</div>
								<!-- /.card-header -->
								<div class="card-body table-responsive p-0">
									<table class="table table-hover custom-table">
										<thead>
											<tr>
												<th>User</th>
												<th>Date</th>
												<th>Status</th>
												<th>Reason</th>
												<th></th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td><a href="">John Doe</a></td>
												<td>11-7-2014</td>
												<td><span class="tag tag-success">Approved</span></td>
												<td>Bacon ipsum dolor sit amet salami venison chicken
													flank fatback doner.</td>
												<td class="buttons-manage"><a href="Messaging"
													class="text-muted"> <i class="fas fa-comments"></i>
												</a> <a href="#" class="text-manage"> <i
														class="fas fa-pencil-alt"></i>
												</a> <a href="#" class="text-delete"> <i
														class="fas fa-times"></i>
												</a></td>
											</tr>
											<tr>
												<td><a href="">Alexander Pierce</a></td>
												<td>11-7-2014</td>
												<td><span class="tag tag-warning">Pending</span></td>
												<td>Bacon ipsum dolor sit amet salami venison chicken
													flank fatback doner.</td>
												<td class="buttons-manage"><a href="#"
													class="text-muted"> <i class="fas fa-comments"></i>
												</a> <a href="#" class="text-manage"> <i
														class="fas fa-pencil-alt"></i>
												</a> <a href="#" class="text-delete"> <i
														class="fas fa-times"></i>
												</a></td>
											</tr>
											<tr>
												<td><a href="">Bob Doe</a></td>
												<td>11-7-2014</td>
												<td><span class="tag tag-primary">Approved</span></td>
												<td>Bacon ipsum dolor sit amet salami venison chicken
													flank fatback doner.</td>
												<td class="buttons-manage"><a href="#"
													class="text-muted"> <i class="fas fa-comments"></i>
												</a> <a href="#" class="text-manage"> <i
														class="fas fa-pencil-alt"></i>
												</a> <a href="#" class="text-delete"> <i
														class="fas fa-times"></i>
												</a></td>
											</tr>
											<tr>
												<td><a href="">Mike Doe</a></td>
												<td>11-7-2014</td>
												<td><span class="tag tag-danger">Denied</span></td>
												<td>Bacon ipsum dolor sit amet salami venison chicken
													flank fatback doner.</td>
												<td class="buttons-manage"><a href="#"
													class="text-muted"> <i class="fas fa-comments"></i>
												</a> <a href="#" class="text-manage"> <i
														class="fas fa-pencil-alt"></i>
												</a> <a href="#" class="text-delete"> <i
														class="fas fa-times"></i>
												</a></td>
											</tr>
										</tbody>
									</table>
								</div>
								<!-- /.card-body -->
								<div class="card-footer clearfix">
									<ul class="pagination pagination-sm m-0 float-right">
										<li class="page-item"><a class="page-link" href="#">«</a></li>
										<li class="page-item"><a class="page-link" href="#">1</a></li>
										<li class="page-item"><a class="page-link" href="#">2</a></li>
										<li class="page-item"><a class="page-link" href="#">3</a></li>
										<li class="page-item"><a class="page-link" href="#">»</a></li>
									</ul>
								</div>
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
	</div>
	<!-- ./wrapper -->
	<script>
		var roomIdFromFrontAdmin = ${requestScope.AdminId};
		var selfId = ${requestScope.AdminId};
	</script>
	<jsp:include page="../includes/Footer.jsp" />
</body>
</html>

