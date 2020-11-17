<%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 2/10/2020
  Time: 7:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html >
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
                <h1 class="content-title">Order Test</h1>
                <div class="row">
                    <div class="col-md-12">
                        <div class="bookings-list order-testing">
                            <c:if test="${requestScope.TestingOrdersList!= null}">
                                <c:forEach items="${requestScope.TestingOrdersList}" var="testingOrders">
                                    <a
                                            href=""
                                            data-id="${testingOrders.id}"

                                            class="bookings-list__item testing-item open-order-testing-modal"
                                            style="background-image: url('${testingOrders.imageLink}')"
                                    >
                                        <h3 class="bookings-list__item-title">${testingOrders.title}</h3>

                                    </a>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                </div>

                <jsp:include page="${root}/includes/Toaster.jsp"/>
            </div>
        </main>
    </div>
</div>

<div class="modal custom-modal modal-service fade" id="order-testing-invitation">
    <div class="modal-dialog custom-modal__dialog">
        <div class="modal-content bg-default">
            <form method="POST" id="order-testing-form">
                <div class="modal-header custom-modal__header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body mh-400 custom-modal__body">
                    <div class="modal-loader-place"></div>
                    <div class="main-content order-test-modal__content">
                        <form action="" method="post">
                            <h4 class="service-title">Testing Order 1</h4>
                            <div class="row">
                                <div class="col-lg-7">
                                    <div class="service-image">
                                        <img src="" alt=""/>
                                    </div>
                                </div>
                                <div class="col-lg-5 text-right">
                                    <div class="service-price text-left"></div>
                                    <div class="service-description text-left">
                                        <span class="ser-desc-title">Description:</span>
                                        <p></p>
                                    </div>
                                    <button
                                            id="employee-order-testing-btn"
                                            type="submit"
                                            class="btn btn-secondary position-relative mt-5 custom-rounded-btn light-green"
                                    >
                                        ORDER NOW
                                    </button>
                                </div>
                            </div>
                            <input type="hidden" id="service-modal-id" value=""/>
                        </form>
                    </div>
                </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<script>
    var send_order_testing_data_ajax = '/employee/SendOrderTestingDataAjax';
    var show_order_testing_ajax = "/employee/ShowOrderTestingAjax";
    var roomIdFromFrontUser = ${requestScope.EmployeeObject.id};
    var selfId = ${requestScope.EmployeeObject.id};
    var sessionRoomFromFront = "jok2f4fud_9";
</script>
<jsp:include page="${root}/includes/BundleJsInclude.jsp"/>
<jsp:include page="${root}/includes/FrontFooter.jsp"/>
<%--<jsp:include page="../../includes/CookieManagerUser.jsp"/>--%>
</body>
</html>
