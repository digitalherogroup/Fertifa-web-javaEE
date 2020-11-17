<%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 2/4/2020
  Time: 9:14 AM
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
    <c:set value="${pageContext.request.contextPath}" var="root"/>
    <%
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        if (session.getAttribute("employeeEmail") == null) {
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
                <h1 class="content-title">My journey</h1>
                <div class="row">
                    <div class="col-md-12">
                        <ul class="timeline">

                            <c:if test="${requestScope.JourneyList != null}">


                                <c:forEach items="${requestScope.JourneyList}" var="journey" varStatus="TheCount">
                                    <c:set var="length" value="${requestScope.HealthSize}" scope="request"/>
                                    <c:set var="oddOrEvenElement" value="${TheCount.index}" scope="request"/>
                                    <c:set var="journeySize" value="${requestScope.journeyListSize}" scope="request"/>
                                    <%
                                        int healthHistorySize = Integer.parseInt(String.valueOf(request.getAttribute("length")));
                                        int count = Integer.parseInt(String.valueOf(request.getAttribute("oddOrEvenElement")));
                                        int sizeJourney = Integer.parseInt(String.valueOf(request.getAttribute("journeySize")));
                                        String elementClass = "";

                                        if (healthHistorySize == 0) {
                                            if(sizeJourney % 2 == 0) {
                                                if (count != 0 && count % 2 != 0) {
                                                    elementClass = "timeline__event--flipped";
                                                } else {
                                                    elementClass = "";
                                                }
                                            }else{
                                                if (count != 0 && count % 2 != 0) {
                                                    elementClass = "";
                                                } else {
                                                    elementClass = "timeline__event--flipped";
                                                }
                                            }

                                        } else {
                                            if(sizeJourney % 2 == 0) {
                                                if (count != 0 && count % 2 != 0) {
                                                    elementClass = "";
                                                } else {
                                                    elementClass = "timeline__event--flipped";
                                                }
                                            }else{
                                                if (count != 0 && count % 2 != 0) {
                                                    elementClass = "timeline__event--flipped";
                                                } else {
                                                    elementClass = "";
                                                }
                                            }
                                        }

                                    %>


                                    <li class="timeline__event <%=elementClass%>">
                                        <div class="timeline__marker"><span></span></div>

                                        <div class="timeline__content">

                                            <div class="timeline__title">${journey.serviceName}</div>
                                            <c:set var="tringdate" value="${journey.order_dates}"
                                                   scope="request"/>
                                            <%!
                                                static String strt;
                                                static int checkt;
                                            %>
                                            <%
                                                strt = String.valueOf(request.getAttribute("tringdate"));
                                                checkt = 0;
                                                if (strt.contains(".")) {
                                                    checkt = 0;
                                                } else {
                                                    checkt = 1;
                                                }
                                            %>
                                            <c:if test="<%=checkt == 0%>">
                                                <div class="timeline__date">${journey.getOrderDates}</div>
                                            </c:if>
                                            <c:if test="<%=checkt == 1%>">
                                                <div class="timeline__date">${journey.order_dates}</div>
                                            </c:if>

                                        </div>
                                    </li>
                                </c:forEach>
                            </c:if>

                            <c:if test="${requestScope.CreateTimeHealthHistory !=null}">

                                <li class="timeline__event timeline__event--flipped">
                                    <div class="timeline__marker"><span></span></div>
                                    <div class="timeline__content">
                                        <c:set var="getDateCreated" scope="request" value="${requestScope.CreateTimeHealthHistory}"/>
                                        <%
                                            String userRegistrationDate="";
                                            String getCreationDate = String.valueOf(request.getAttribute("getDateCreated"));
                                            String [] reationDateArra = getCreationDate.split("-");
                                            userRegistrationDate = reationDateArra[2];
                                        %>
                                        <div class="timeline__title">Enquiry form has been created</div>

                                        <div class="timeline__date"> 20<%=userRegistrationDate%> - Started my fertility journey</div>

                                    </div>
                                </li>
                            </c:if>


                            <c:if test="${requestScope.UserRegistrationDate  !=null}">
                                <li class="timeline__event  ">
                                    <div class="timeline__marker"><span></span></div>
                                    <div class="timeline__content">

                                        <div class="timeline__title">Joined Fertifa</div>
                                        <div class="timeline__date">${requestScope.UserRegistrationDate}</div>

                                    </div>
                                </li>
                            </c:if>

                        </ul>
                    </div>
                </div>
            </div>
        </main>
    </div>
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
    var roomIdFromFrontUser =${requestScope.EmployeeObject.id};
    var sessionRoomFromFront = "jok2f4fud_9";
</script>

<jsp:include page="${root}/includes/BundleJsInclude.jsp"/>
<jsp:include page="${root}/includes/FrontFooter.jsp"/>
<%--<jsp:include page="${root}/includes/CookieManagerUser.jsp"/>--%>
</body>
</html>
