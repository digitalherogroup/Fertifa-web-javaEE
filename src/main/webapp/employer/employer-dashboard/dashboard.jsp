<%--
  Created by IntelliJ IDEA.
  User: Shant
  Date: 1/14/2020
  Time: 12:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@page import="java.sql.Timestamp" %>
<%@page import="java.text.ParseException" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>
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
        if("POST".equalsIgnoreCase(request.getMethod())){
            response.sendRedirect(request.getContextPath() + "/employer/employer-dashboard");
        }

    %>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>


    <jsp:include page="${root}/includes/BundleInclude.jsp"/>

    <title>Dashboard</title>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="bg-light sidebar">
            <jsp:include page="${root}/includes/CompanySideBar.jsp"/>
        </div>
        <jsp:include page="${pageContext.request.contextPath}/includes/MobileSideBar.jsp"/>
        <main role="main" class="main-container">

            <div class="content">

                <c:if test="${requestScope.EmployerObject != null}">
                    <div class="row mb-5">
                        <div class="col-12">
                            <div class="dash-user-info">
                                <div class="loader-place">
                                </div>
                                <div class="dash-user-info__image">
                                    <c:set value="${requestScope.EmployerObject.userImage}" var="Image"/>
                                    <c:choose>
                                        <c:when test="${Image == null}">
                                            <img class="user-image-for-show"
                                                 src="<%=request.getContextPath()%>/WEB-INF/jsp/img/avatar-empty.svg" alt=""/>
                                            <div class="custom-file user-image-manage hidden-first">
                                                <input type="file"
                                                       data-file="<%=request.getContextPath()%>/img/avatar-empty.svg"
                                                       accept=".png,.jpg,.jpeg" id="user-image-manage"
                                                       class="custom-file-input" value="">
                                                <label class="custom-file-label" for="user-image-manage"><i
                                                        class="fas fa-plus"></i></label></div>
                                        </c:when>
                                        <c:otherwise>
                                            <!-- src/main/webapp/upload/book_1.jpg-->
                                            <div class="user__image">
                                                <img class="user-image-for-show"
                                                     src="http://second.fertifabenefits.com/${requestScope.EmployerObject.userImage}"
                                                     alt=""
                                                />
                                                <div class="custom-file user-image-manage hidden-first">
                                                    <input type="file" data-file="${requestScope.EmployerObject.userImage}"
                                                           accept=".png,.jpg,.jpeg" id="user-image-manage"
                                                           class="custom-file-input" value="">
                                                    <label class="custom-file-label" for="user-image-manage"><i
                                                            class="fas fa-plus"></i></label></div>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>

                                </div>

                                <div class="dash-user-info__content">
                                    <form action="" id="user-edit-form" class="user-edit-form" novalidate>
                                        <h1 id="edited_user_company"
                                            class="dash-user-info__content-company shown-first">${requestScope.EmployerObject.comapny}</h1>

                                        <input
                                                type="text"
                                                class="form-control hidden-first form-control-sm custom-rounded-input custom-editable-input"
                                                name="company_name"
                                                id="company_name"
                                                placeholder="com.fertifa.app.company Name"
                                                value="${requestScope.EmployerObject.comapny}"
                                        />
                                        <div class="user-main-info d-flex">
                                            <div class="user-main-info__item">
                                                <span class="dash-user-info__content-label">E-mail:</span>
                                                <p class="dash-user-info__content-value">${requestScope.EmployerObject.email}</p>
                                            </div>
                                            <div class="user-main-info__item">
                                                <span class="dash-user-info__content-label">Phone:</span>
                                                <p id="edited_user_phone"
                                                   class="dash-user-info__content-value shown-first">${requestScope.EmployerObject.phoneNumber}</p>
                                                <input
                                                        type="text"
                                                        class="form-control hidden-first form-control-sm custom-rounded-input  custom-editable-input"
                                                        name="user_phone"
                                                        id="user_phone"
                                                        placeholder="Phone Number"
                                                        value="${requestScope.EmployerObject.phoneNumber}"
                                                />
                                            </div>
                                            <div class="user-main-info__item">
                                                <span class="dash-user-info__content-label">Address:</span>
                                                <p id="edited_user_address"
                                                   class="dash-user-info__content-value shown-first">${requestScope.EmployerObject.address}</p>
                                                <input
                                                        type="text"
                                                        class="form-control hidden-first form-control-sm custom-rounded-input custom-editable-input"
                                                        name="user_address"
                                                        id="user_address"
                                                        placeholder="Address"
                                                        value="${requestScope.EmployerObject.address}"
                                                />
                                                <input type="hidden" value="${requestScope.EmployerObject.id}" name="id">
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="dash-user-info__edit">
                                    <button class="dash-user-info__edit-profile edit-profile-action">Edit Profile
                                    </button>
                                    <button class="dash-user-info__edit-profile save-company-profile-action">Save
                                    </button>
                                </div>


                            </div>
                        </div>
                    </div>
                </c:if>
                <div class="row">
                    <div class="col-6">
                        <%-- <c:set var="packageName" value="${packageName}"/>--%>

                        <div class="status-widget ${requestScope.EmployerObject.packagName.toLowerCase()}">

                            <div class="status-widget__content">
                                <div class="status-widget__content-img">
                                    <img src="${root}/WEB-INF/jsp/img/status-silver.svg" alt=""/>
                                </div>
                                <c:if test="${requestScope.EmployerObject != null}">
                                    <div class="right-part">
                                        <h4 class="status-widget__content-title">${requestScope.EmployerObject.packagName} status</h4>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <div class="table-outside">

                            <table class="table custom-table">
                                <thead>
                                <tr>
                                    <th scope="col">Option</th>
                                    <th scope="col">At clinic</th>
                                    <th scope="col">Bronze</th>
                                    <th scope="col">Silver</th>
                                    <th scope="col">Gold</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>Enrolment data</td>
                                    <td><i class="cross-sign"></i></td>
                                    <td><i class="check-sign"></i></td>
                                    <td><i class="check-sign"></i></td>
                                    <td><i class="check-sign"></i></td>
                                </tr>
                                <tr>
                                    <td>Employer feedback</td>
                                    <td><i class="cross-sign"></i></td>
                                    <td><i class="check-sign"></i></td>
                                    <td><i class="check-sign"></i></td>
                                    <td><i class="check-sign"></i></td>
                                </tr>
                                <tr>
                                    <td>Monthly enrolment tracker</td>
                                    <td><i class="cross-sign"></i></td>
                                    <td><i class="cross-sign"></i></td>
                                    <td><i class="check-sign"></i></td>
                                    <td><i class="check-sign"></i></td>
                                </tr>
                                <tr>
                                    <td>Claim processing</td>
                                    <td><i class="cross-sign"></i></td>
                                    <td><i class="cross-sign"></i></td>
                                    <td><i class="cross-sign"></i></td>
                                    <td><i class="check-sign"></i></td>
                                </tr>
                                <tr>
                                    <td>Trend & Analytics</td>
                                    <td><i class="cross-sign"></i></td>
                                    <td><i class="cross-sign"></i></td>
                                    <td><i class="cross-sign"></i></td>
                                    <td><i class="check-sign"></i></td>
                                </tr>
                                <tr>
                                    <td>Quarterly benchmarking report</td>
                                    <td><i class="cross-sign"></i></td>
                                    <td><i class="cross-sign"></i></td>
                                    <td><i class="cross-sign"></i></td>
                                    <td><i class="check-sign"></i></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td colspan="4">
                                        <span class="light-grey-strong">Monthly Cost Perfomance</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Summary — take up, total costs and savings</td>
                                    <td><i class="cross-sign"></i></td>
                                    <td><i class="cross-sign"></i></td>
                                    <td><i class="check-sign"></i></td>
                                    <td><i class="check-sign"></i></td>
                                </tr>
                                <tr>
                                    <td>Detailed breakdown — take up, total costs & savings</td>
                                    <td><i class="cross-sign"></i></td>
                                    <td><i class="cross-sign"></i></td>
                                    <td><i class="cross-sign"></i></td>
                                    <td><i class="check-sign"></i></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="more-journey-list">
                            <img src="../../img/warning-fill.svg" alt=""/>
                            <a href="" data-toggle="modal" data-target="#lourney-list"> Learn more</a>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="card custom-card">
                            <div class="card-body custom-card__body">
                                <h5 class="custom-card__title">Savings</h5>

                                <canvas id="sales-line" height="250" style="height: 300px;"></canvas>
                            </div>
                        </div>


                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
<jsp:include page="${root}/includes/NotificationBlog.jsp"/>

<!-- Modal -->
<div
        class="modal fade"
        id="lourney-list"
        tabindex="-1"
        role="dialog"
        aria-labelledby="lourneyListLabel"
        aria-hidden="true"
>
    <div class="modal-dialog employee-journey-modal" role="document">
        <div class="modal-content employee-journey-modal__content">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <div class="modal-body employee-journey-modal__body">
                <div class="table-outside">
                    <table class="table custom-table">
                        <thead>
                        <tr>
                            <th scope="col" width="32%">Employer journey list</th>
                            <th scope="col" width="17%">At clinic</th>
                            <th scope="col" width="17%">Bronze</th>
                            <th scope="col" width="17%">Silver</th>
                            <th scope="col" width="17%">Gold</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Free exploratory consultation</td>
                            <td><i class="cross-sign"></i></td>
                            <td><i class="check-sign"></i></td>
                            <td><i class="check-sign"></i></td>
                            <td><i class="check-sign"></i></td>
                        </tr>
                        <tr>
                            <td>Unlimited 24/7 messaging</td>
                            <td><i class="cross-sign"></i></td>
                            <td><i class="check-sign"></i></td>
                            <td><i class="check-sign"></i></td>
                            <td><i class="check-sign"></i></td>
                        </tr>
                        <tr>
                            <td>Personalised portal/app</td>
                            <td><i class="cross-sign"></i></td>
                            <td><i class="check-sign"></i></td>
                            <td><i class="check-sign"></i></td>
                            <td><i class="check-sign"></i></td>
                        </tr>
                        <tr>
                            <td>One-stop 1-to-1 supervision</td>
                            <td><i class="cross-sign"></i></td>
                            <td><i class="check-sign"></i></td>
                            <td><i class="check-sign"></i></td>
                            <td><i class="check-sign"></i></td>
                        </tr>
                        <tr>
                            <td>Hormone testing (£)</td>
                            <td>£ 300</td>
                            <td>£ 130</td>
                            <td>£ 130</td>
                            <td>£ 130</td>
                        </tr>
                        <tr>
                            <td>Savings (£)</td>
                            <td></td>
                            <td>£ 175</td>
                            <td>£ 175</td>
                            <td>£ 175</td>
                        </tr>
                        <tr>
                            <td>Doctor consultation (£)</td>
                            <td>£ 275</td>
                            <td>10% discount</td>
                            <td>15% discount</td>
                            <td>20% discount</td>
                        </tr>
                        <tr>
                            <td>Savings (£)</td>
                            <td></td>
                            <td>£ 28</td>
                            <td>£ 41</td>
                            <td>£ 55</td>
                        </tr>
                        <tr>
                            <td>Nurse consultation</td>
                            <td></td>
                            <td>10% discount</td>
                            <td>1 free session + 15% disc for future sessions</td>
                            <td>1 free session + 20% disc for future sessions</td>
                        </tr>
                        <tr>
                            <td>Savings (4 x 15 min sessions) (£)</td>
                            <td></td>
                            <td>£ 6</td>
                            <td>£ 22</td>
                            <td>£ 24</td>
                        </tr>
                        <tr>
                            <td>Wellbeing</td>
                            <td></td>
                            <td>10% discount</td>
                            <td>15% discount</td>
                            <td>1 free session + 20% discount for future sessions</td>
                        </tr>
                        <tr>
                            <td rowspan="4">Savings (5 sessions on average)</td>
                            <td>Nutrition</td>
                            <td>£ 55</td>
                            <td>£ 82</td>
                            <td>£ 230</td>
                        </tr>
                        <tr>
                            <td>Acupuncture</td>
                            <td>£ 32</td>
                            <td>£ 48</td>
                            <td>£ 64</td>
                        </tr>
                        <tr>
                            <td>Reflectology</td>
                            <td>£ 31</td>
                            <td>£ 46</td>
                            <td>£ 62</td>
                        </tr>
                        <tr>
                            <td>Counselling</td>
                            <td>£ 37</td>
                            <td>£ 55</td>
                            <td>£ 74</td>
                        </tr>
                        <tr>
                            <td>Treatment discount with partnered clinic</td>
                            <td>N/A</td>
                            <td>2%</td>
                            <td>3%</td>
                            <td>5%</td>
                        </tr>
                        <tr>
                            <td>Savings ( average 2 cycles)</td>
                            <td></td>
                            <td>£ 160</td>
                            <td>£ 240</td>
                            <td>£ 800</td>
                        </tr>
                        <tr>
                            <td><span class="total-info">Total savings the Fertifa way</span></td>
                            <td></td>
                            <td><span class="total-info">£ 524</span></td>
                            <td><span class="total-info">£ 708</span></td>
                            <td><span class="total-info">£ 1,484</span></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var selfId = ${requestScope.EmployerObject.id};
    var roomIdFromFrontUser = ${requestScope.EmployerObject.id};
    var sessionRoomFromFront = ${requestScope.EmployerObject.id};
    var update_company_ajax = '<%=request.getContextPath()%>/UpdateCompanyC?id=${requestScope.EmployerObject.id}';

</script>
<jsp:include page="${root}/includes/BundleJsInclude.jsp"/>
<jsp:include page="${root}/includes/FrontFooter.jsp"/>
<%--<jsp:include page="../../includes/CookieManagerUser.jsp"/>--%>
</body>
</html>



