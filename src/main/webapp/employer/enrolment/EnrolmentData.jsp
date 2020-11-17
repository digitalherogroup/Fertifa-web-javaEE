<%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 1/21/2020
  Time: 3:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html >
<head>
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <%
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader ("Expires", 0);
        if(session.getAttribute("employerEmail")==null){
            response.sendRedirect(request.getContextPath() + "/employer/employer-dashboard");
        }
    %>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>


    <jsp:include page="../../includes/BundleInclude.jsp"/>

       <title>Fertifa | Digital Platform</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="bg-light sidebar">
            <jsp:include page="../../includes/CompanySideBar.jsp"/>
        </div>
        <jsp:include page="../../includes/MobileSideBar.jsp"/>
        <main role="main" class="main-container">
            <div class="header">
                <div class="user">
                    <c:if test="${requestScope.EmployerObject!= null}">

                            <div class="user__info">
                                <span class="user__company"></span>
                                <span class="user__name">${requestScope.EmployerObject.comapny} </span>
                            </div>
                            <c:choose>
                                <c:when test="${requestScope.EmployerObject.userImage == null}">
                                    <div class="user__image">
                                        <img src="<%=request.getContextPath()%>/WEB-INF/jsp/img/avatar-empty.svg" alt=""/>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="user__image">
                                        <img src="${root}/${requestScope.EmployerObject.userImage}"
                                             alt=""
                                        />
                                    </div>
                                </c:otherwise>
                            </c:choose>
                    </c:if>

                </div>
            </div>

            <div class="content">
                <h1 class="content-title">Enrolment data</h1>
                <div class="row">
                    <div class="col-md-12 mb-3">
                        <div class="enrolment-general">
                            <span class="enrolment-general__desc">Total users registered: ${requestScope.CountingUsers}</span>
                            <span class="enrolment-general__desc">New users in past month: ${requestScope.CountingSixMonthUsers}</span>
                            <span class="enrolment-general__desc">Active users in past month: ${requestScope.CountingYearUsers}</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="card custom-card card-without-border">
                            <div class="card-header custom-card__header card-without-border__header">
                                <div class="date-filter chart-date-range" id="daterange-enrolment">
                                    <img src="../../img/calendar-line.svg" alt=""/>
                                    <span class="date-place-view">Select Date</span>
                                    <input type="hidden" id="dateFrom" value=""/>
                                    <input type="hidden" id="dateTo" value=""/>
                                </div>
                                <ul class="nav nav-pills mt-0 chart-nav" id="chart-tabs" role="tablist">
                                    <li class="nav-item chart-nav__item">
                                        <a
                                                class="nav-link chart-nav__item--link active"
                                                id="bar-chart-tab"
                                                data-toggle="pill"
                                                href="#bar-chart"
                                                role="tab"
                                                aria-controls="bar-chart"
                                                aria-selected="true"
                                        ></a>
                                    </li>
                                    <li class="nav-item chart-nav__item">
                                        <a
                                                class="nav-link chart-nav__item--link"
                                                id="line-chart-tab"
                                                data-toggle="pill"
                                                href="#line-chart"
                                                role="tab"
                                                aria-controls="line-chart"
                                                aria-selected="false"
                                        ></a>
                                    </li>
                                </ul>
                            </div>
                            <div class="card-body custom-card__body card-without-border__body">
                                <div class="charts-loading-data-place"></div>
                                <div class="tab-content" id="pills-tabContent">
                                    <div
                                            class="tab-pane fade show active"
                                            id="bar-chart"
                                            role="tabpanel"
                                            aria-labelledby="bar-chart-tab"
                                    >
                                        <div style="height: 370px">

                                            <canvas class="enrolment-chart-style" id="enrolment-bar"></canvas>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade" id="line-chart" role="tabpanel"
                                         aria-labelledby="line-chart-tab">
                                        <div style="height: 370px">
                                            <canvas class="enrolment-chart-style" id="enrolment-line"></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
<jsp:include page="../../includes/NotificationBlog.jsp"/>

<script>
    var show_enrollment_data_ajax = "<%=request.getContextPath()%>/ShowEnrollmentDataAjax";
    var enrollment_days_data = ${requestScope.MyArrayDates};
    var enrollment_users_data = ${requestScope.MyArrayCount};
    var selfId = ${requestScope.EmployerObject.id};
    var roomIdFromFrontUser = ${requestScope.EmployerObject.id};
    var sessionRoomFromFront = ${requestScope.EmployerObject.id};
</script>
<jsp:include page="${root}/includes/BundleJsInclude.jsp"/>
<jsp:include page="${root}/includes/FrontFooter.jsp"/>
<%--<jsp:include page="../../includes/CookieManagerUser.jsp"/>--%>
</body>
</html>

