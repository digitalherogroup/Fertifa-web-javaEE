<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: rooter
  Date: 12/26/19
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set value="${pageContext.request.contextPath}" var="root"/>
<!-- Sidebar Menu -->
<nav class="mt-2">
    <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
        <!-- Add icons to the links using the .nav-icon class
             with font-awesome or any other icon font library -->
        <li class="nav-item">
            <a href="${root}/admin/admin-dashboard" class="nav-link">
                <i class="nav-icon fas fa-th"></i>
                <p>
                    Dashboard
                </p>
            </a>
        </li>
        <li class="nav-item">
            <a href="https://chat.fertifabenefits.com/api/v1/${AdminsObject.id}" class="nav-link" target="_blank" >
                <i class="nav-icon fas fa-envelope "></i>
                <p>
                    Messages
                </p>
            </a>
        </li>
        <li class="nav-item has-treeview">
            <a href="#" class="nav-link">
                <i class="nav-icon fas fa-shopping-bag"></i>
                <p>
                    Products
                    <i class="fas fa-angle-left right"></i>
                </p>
            </a>
            <ul class="nav nav-treeview">
                <li class="nav-item">
                    <a href="${root}/admin/products/add" class="nav-link">
                        <i class="far fa-circle nav-icon"></i>
                        <p>Add Product</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="${root}/admin/products/all" class="nav-link">
                        <i class="far fa-circle nav-icon"></i>
                        <p>Products</p>
                    </a>
                </li>
            </ul>
        </li>

        <li class="nav-item has-treeview">
            <a href="javascript:" class="nav-link">
                <i class="nav-icon fas fa-user"></i>
                <p>
                    Customers
                    <i class="fas fa-angle-left right"></i>
                </p>
            </a>
            <ul class="nav nav-treeview">
                <li class="nav-item">
                    <a href="${root}/admin/customer/employer" class="nav-link">
                        <i class="far fa-circle nav-icon"></i>
                        <p>Employers</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="${root}/admin/customer/employee" class="nav-link">
                        <i class="far fa-circle nav-icon"></i>
                        <p>Employees</p>
                    </a>
                </li>
            </ul>
        </li>

        <li class="nav-item">
            <a href="${root}/admin/employer/invite" class="nav-link">
                <i class="nav-icon fas fa-envelope"></i>
                <p>
                    Invite Employer
                </p>
            </a>
        </li>

        <li class="nav-item">
            <a href="${root}/admin/employee/invite" class="nav-link">
                <i class="nav-icon fas fa-envelope"></i>
                <p>
                    Invite Employee
                </p>
            </a>
        </li>

        <li class="nav-item">
            <a href="${root}/admin/booking" class="nav-link">
                <i class="nav-icon fas fa-star"></i>
                <p>
                    Booking requests
                </p>
            </a>
        </li>

        <li class="nav-item">
            <a href="${root}/admin/sales" class="nav-link">
                <i class="nav-icon fas fa-shopping-cart"></i>
                <p>
                    sales
                </p>
            </a>
        </li>
        <li class="nav-item">
            <a href="${root}/admin/feedbacks" class="nav-link">
                <i class="nav-icon fas fa-star"></i>
                <p>
                    FeedBack
                </p>
            </a>
        </li>
        <li class="nav-item has-treeview">
            <a href="#" class="nav-link">
                <i class="nav-icon fas fa-handshake"></i>
                <p>
                    Partners
                    <i class="fas fa-angle-left right"></i>
                </p>
            </a>
            <ul class="nav nav-treeview">
                <li class="nav-item">
                    <a href="${root}/admin/partners/add" class="nav-link">
                        <i class="far fa-circle nav-icon"></i>
                        <p>Add partner</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="${root}/admin/partners" class="nav-link">
                        <i class="far fa-circle nav-icon"></i>
                        <p>partners</p>
                    </a>
                </li>
            </ul>
        </li>
        <li class="nav-item has-treeview">
            <a href="#" class="nav-link">
                <i class="nav-icon fas fa-newspaper"></i>
                <p>
                    News
                    <i class="fas fa-angle-left right"></i>
                </p>
            </a>
            <ul class="nav nav-treeview">
                <li class="nav-item">
                    <a href="${root}/admin/news/add" class="nav-link">
                        <i class="far fa-circle nav-icon"></i>
                        <p>Add news</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="${root}/admin/news" class="nav-link">
                        <i class="far fa-circle nav-icon"></i>
                        <p>Preview news</p>
                    </a>
                </li>
            </ul>
        </li>
        <li class="nav-item has-treeview">
            <a href="#" class="nav-link">
                <i class="nav-icon fas fa-question"></i>
                <p>
                    FAQ
                    <i class="fas fa-angle-left right"></i>
                </p>
            </a>
            <ul class="nav nav-treeview">
                <li class="nav-item">
                    <a href="${root}/admin/faqs/add" class="nav-link">
                        <i class="far fa-circle nav-icon"></i>
                        <p>Add FAQ</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="${root}/admin/faqs" class="nav-link">
                        <i class="far fa-circle nav-icon"></i>
                        <p>Preview FAQ</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="${root}/admin/category/add" class="nav-link">
                        <i class="far fa-circle nav-icon"></i>
                        <p>Add FAQ Category</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="${root}/admin/category" class="nav-link">
                        <i class="far fa-circle nav-icon"></i>
                        <p>FAQ Categories</p>
                    </a>
                </li>
            </ul>
        </li>
        <li class="nav-item has-treeview">
            <a href="#" class="nav-link">
                <i class="nav-icon fas fa-user-secret"></i>
                <p>
                    Users
                    <i class="fas fa-angle-left right"></i>
                </p>
            </a>
            <ul class="nav nav-treeview">
                <li class="nav-item">
                    <a href="${root}/admin/nurse/add" class="nav-link">
                        <i class="far fa-circle nav-icon"></i>
                        <p>Add Nurse</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="${root}/admin/nurse" class="nav-link">
                        <i class="far fa-circle nav-icon"></i>
                        <p>All Admins</p>
                    </a>
                </li>

            </ul>
        </li>

        <li class="nav-item">
            <a href="${root}/admin/logout" class="nav-link">
                <i class="far fa-circle nav-icon"></i>
                <p>Logout</p>
            </a>
        </li>
    </ul>
</nav>

<!-- /.sidebar-menu -->

