<%@ page import="com.fertifa.app.adminSide.CookiesManager.AdminCookiManager" %><%--
  Created by IntelliJ IDEA.
  User: Asus / BeautyIt +374 98 22 98 98
  Date: 12/24/2019
  Time: 11:40 AM

--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%--<jsp:forward page="redirectAdmin.jsp"/>--%>
<head>
    <c:set var="root" value='<%=request.getContextPath()%>'/>
    <c:set var="servletRoot" value='<%=request.getServletPath()%>'/>
    <%--
     <%
            if(AdminCookiManager.isUserLogged(request,response)){
                response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard");
            }
        %>
    --%>

    <jsp:include page="../../includes/Header.jsp"/>
</head>
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <img src="<%=request.getContextPath()%>/img/main_fertifa.png"
             alt="AdminLTE Logo"/>
    </div>

    <!-- /.login-logo -->
    <div class="card">
        <div class="card-body login-card-body">
            <p class="login-box-msg">Sign in to start your session</p>
            <%
                if (request.getSession().getAttribute("adminMessage") != null) {
            %>
            <span class="alert alert-danger"><%=request.getSession().getAttribute("adminMessage")%></span>
            <%
                }
                request.getSession().setAttribute("adminMessage",null);
            %>
            <form action="${root}/admin/admin-dashboard" method="post">
                <div class="input-group mb-3">
                    <input type="email" class="form-control" placeholder="Email" name="login" required>
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-envelope"></span>
                        </div>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <input type="password" class="form-control" placeholder="Password" name="login" required>
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-lock"></span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-8">
                        <div class="icheck-primary">
                            <%-- <input type="checkbox" id="remember">
                             <label for="remember">
                                 Remember Me
                             </label>--%>
                        </div>
                    </div>
                    <!-- /.col -->

                    <div class="col-4">
                        <button type="submit" class="btn btn-primary btn-block">Sign In</button>
                    </div>
                    <!-- /.col -->
                </div>
            </form>

            <div class="social-auth-links text-center mb-3">

            </div>

        </div>
        <!-- /.login-card-body -->
    </div>
</div>
<!-- /.login-box -->
<script>
    var selfId = 0;
    var roomIdFromFrontUser = 0;
    var sessionRoomFromFront = "__";
</script>
<jsp:include page="../../includes/Footer.jsp"/>

</body>
</html>

