<%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 1/28/2020
  Time: 3:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <%
        if (request.getHeader("User-Agent").contains("Mobile")) {
            String url = "https://m.second.fertifabenefits.com/employee/employee-dashboard";
            response.sendRedirect(url);
        }
    %>
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <%
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader ("Expires", 0);
        if(session.getAttribute("employeeEmail")==null){
            response.sendRedirect(request.getContextPath() + "/employee/employee-dashboard");
        }
    %>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>


    <jsp:include page="${root}/includes/BundleInclude.jsp"/>

    <title>Fertifa | Digital Platform</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="bg-light sidebar">
            <jsp:include page="${root}/includes/UsersSideBar.jsp"/>
        </div>
        <jsp:include page="${root}/includes/MobileSideBar.jsp"/>
        <main role="main" class="main-container">
            <div class="header">
                <div class="user">
                    <c:if test="${requestScope.EmployeeObject != null}">

                        <div class="user__info">

                            <span class="user__company">${requestScope.EmployeeObject.comapny}</span>
                            <span class="user__name">${requestScope.EmployeeObject.firstName} ${requestScope.EmployeeObject.lastName}</span>

                        </div>
                        <div class="user__image">
                            <c:set value="${requestScope.EmployeeObject.userImage}" var="Image"/>
                            <c:choose>
                                <c:when test="${Image != null}">
                                    <img src="${root}/${requestScope.EmployeeObject.userImage}" alt=""/>
                                </c:when>
                                <c:otherwise>
                                    <div class="user__image">
                                        <img src="${root}/img/avatar-empty.svg" alt=""/>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </c:if>

                </div>
            </div>
            <div class="content">
                <h1 class="content-title">My orders</h1>
                <div class="row">
                    <div class="col-md-12">
                        <div class="employee-tracker-list orders-list">
                            <table id="orders-list" class="table import-table table-hover">
                                <thead>

                                <tr>
                                    <th>Date sent <img src="../../img/sort-alpha.svg" alt=""/></th>
                                    <th>Service Name <img src="../../img/filter-light.svg" alt=""/></th>
                                    <th>Appointment Dates <img src="../../img/sort-alpha.svg" alt=""/></th>
                                    <%--  <th>Company <img src="../../img/sort-alpha.svg" alt=""/></th>--%>
                                    <th>Price <img src="../../img/filter-light.svg" alt=""/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${requestScope.OrdersList != null}">
                                    <c:forEach items="${requestScope.OrdersList}" var="orders">


                                        <td>${orders.dateCreated}</td>
                                        <td>${orders.serviceName}</td>
                                        <td>${orders.date}</td>
                                        <%--<td></td>--%>
                                        <td>£ <fmt:formatNumber type="number" maxFractionDigits="3"
                                                                value='${orders.servicePrice}'/></td>

                                        </tr>
                                    </c:forEach>
                                </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

        </main>
    </div>

    <jsp:include page="${root}/includes/NotificationBlogUser.jsp"/>
</div>

<div class="modal custom-modal fade" id="appointement-invitation">
    <div class="modal-dialog ustom-modal__dialog">
        <div class="modal-content bg-default">
            <form method="POST">
                <div class="modal-header ustom-modal__header">
                    <h4 class="modal-title">Send Invitattion</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body ustom-modal__body"></div>
                <div class="modal-footer ustom-modal__footer"></div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<script>
    var selfId = ${requestScope.EmployeeObject.id};
    var roomIdFromFrontUser = ${requestScope.EmployeeObject.id};
    var sessionRoomFromFront = "jok2f4fud_9";
</script>
<jsp:include page="${root}/includes/BundleJsInclude.jsp"/>
<jsp:include page="${root}/includes/FrontFooter.jsp"/>
<%--<jsp:include page="../../includes/CookieManagerUser.jsp"/>--%>
</body>
</html>
