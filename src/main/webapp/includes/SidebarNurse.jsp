<%--
  Created by IntelliJ IDEA.
  User: rooter
  Date: 12/26/19
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Sidebar Menu -->
<nav class="mt-2">
    <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
        <!-- Add icons to the links using the .nav-icon class
             with font-awesome or any other icon font library -->
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/admin/logout" class="nav-link">
                <i class="nav-icon fas fa-th"></i>
                <p>
                    Log out
                </p>
            </a>
        </li>


    </ul>
</nav>
<!-- /.sidebar-menu -->