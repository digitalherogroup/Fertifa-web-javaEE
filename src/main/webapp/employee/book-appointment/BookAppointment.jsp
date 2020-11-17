<%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 1/28/2020
  Time: 2:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <h1 class="content-title">Book An Appointment</h1>
                <div class="row">
                    <div class="col-md-12">
                        <ul class="nav nav-pills mb-4 mt-0 custom-tabs" id="consultationTab" role="tablist">
                            <li class="nav-item custom-tabs__item mr-4">
                                <a
                                        class="nav-link custom-tabs__item--link active"
                                        id="consultation-tab"
                                        data-selected="3"
                                        data-toggle="tab"
                                        href="#consultation"
                                        role="tab"
                                        aria-controls="consultation"
                                        aria-selected="true"
                                >CONSULTATION</a
                                >
                            </li>
                            <li class="nav-item custom-tabs__item">
                                <a
                                        class="nav-link custom-tabs__item--link"
                                        id="clinic-fertility-tab"
                                        data-selected="2"
                                        data-toggle="tab"
                                        href="#clinic-fertility"
                                        role="tab"
                                        aria-controls="clinic-fertility"
                                        aria-selected="false"
                                >CLINIC FERTILITY TREATMENT BOOKING</a
                                >
                            </li>
                        </ul>
                        <div class="tab-content custom-tab-content" id="consultationContent">
                            <div
                                    class="tab-pane custom-tab-content__tab fade show active"
                                    id="consultation"
                                    role="tabpanel"
                                    aria-labelledby="consultation-tab"
                            >

                                <div class="bookings-list ">
                                    <c:if test="${requestScope.ServiceList!= null}">
                                        <c:forEach items="${requestScope.ServiceList}" var="service">
                                            <a data-id="${service.id}" data-type="1" href=""
                                               class="bookings-list__item open-appointement-modal"
                                               style="background-image: url('${service.imageLink}')">
                                                <h3 class="bookings-list__item-title">${service.title}</h3>
                                            </a>
                                        </c:forEach>
                                    </c:if>
                                </div>
                            </div>
                            <div
                                    class="tab-pane custom-tab-content__tab fade"
                                    id="clinic-fertility"
                                    role="tabpanel"
                                    aria-labelledby="clinic-fertility-tab"
                            >
                                <div class="bookings-list treatment">
                                    <c:if test="${requestScope.TreatmentList!= null}">
                                        <c:forEach items="${requestScope.TreatmentList}" var="service">
                                            <a data-id="${service.id}" data-type="2" href=""
                                               class="bookings-list__item open-appointement-modal" >
                                                <h3 class="bookings-list__item-title">${service.title}</h3>
                                            </a>
                                        </c:forEach>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>

<div class="modal custom-modal modal-service fade" id="appointement-invitation">
    <div class="modal-dialog custom-modal__dialog">
        <div class="modal-content bg-default">
            <form method="POST" id="book-request-form">
                <div class="modal-header custom-modal__header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body custom-modal__body">
                    <div class="modal-loader-place"></div>
                    <div class="main-content">
                        <div class="error-message-place"></div>
                        <div class="tab-content custom-tab-content wizzard-setup-content" id="productContent">
                            <div class="tab-pane custom-tab-content__tab fade show active" id="product" role="tabpanel"
                                 aria-labelledby="product-tab">
                                <h4 class="service-title"></h4>
                                <div class="row">
                                    <div class="col-lg-7">
                                        <div class="service-image">
                                            <img src="" alt=""/>
                                        </div>
                                        <div class="service-description">
                                            <span class="ser-desc-title">Description:</span>
                                            <p>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="col-lg-5">
                                        <div class="service-price">£ 240,00</div>
                                        <span class="ser-desc-title">Prefered dates:</span>
                                        <div id="appointement-calendar-place" class="service-calendar"></div>
                                        <button type="button" id="next-modal-tab" disabled class="btn btn-secondary custom-rounded-btn wiz-next-step-action light-green">NEXT STEP</button>
                                    </div>
                                </div>
                                <input type="hidden" name="id" id="service-modal-id" value=""/>
                            </div>
                            <div class="tab-pane custom-tab-content__tab" id="date" role="tabpanel"
                                 aria-labelledby="date-tab">
                                <div class="row">
                                    <div class="col-lg-12 d-flex flex-column mh-400">
                                        <div id="wizzard-append-filtered-dates" class="filtered-dates">
                                        </div>
                                        <div class="d-flex justify-content-end mt-auto">
                                            <button type="button" id="previus-modal-tab"
                                                    class="btn btn-secondary custom-rounded-btn mr-3 position-relative wiz-next-step-action light-green">
                                                BACK
                                            </button>
                                            <button
                                                    id="employee-book-appointement-btn"
                                                    type="submit"
                                                    class="btn btn-secondary custom-rounded-btn position-relative purple">
                                                Enquire
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
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
    var send_order_data_ajax = '/employee/SendOrderDataAjax';
    var show_service_data_ajax = '/employee/ShowServiceDataAjax';
</script>
<jsp:include page="${root}/includes/BundleJsInclude.jsp"/>
<jsp:include page="${root}/includes/FrontFooter.jsp"/>
<%--<jsp:include page="../../includes/CookieManagerUser.jsp"/>--%>
</body>
</html>

