<%--
  Created by IntelliJ IDEA.
  User: rooter
  Date: 1/8/20
  Time: 6:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>

    <c:set value="${pageContext.request.contextPath}" var="root"/>
    <%
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        if (session.getAttribute("adminEmail") == null) {
            response.sendRedirect(request.getContextPath() + "/admin/admin-dashboard");

        }
    %>
    <jsp:include page="${root}/includes/Header.jsp"/>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<!-- Site wrapper -->
<div class="wrapper">
    <!-- Navbar -->
    <jsp:include page="${root}/includes/NavigationBar.jsp"/>

    <!-- Main Sidebar Container -->
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <!-- Brand Logo -->
        <jsp:include page="${root}/includes/LogoAndBrand.jsp"/>
        <!-- Sidebar Menu -->
        <!-- Sidebar -->
        <div class="sidebar">
            <!-- Sidebar user (optional) -->
            <div class="user-panel mt-3 pb-3 mb-3 d-flex">
                <div class="image">
                    <c:if test="${requestScope.AdminsObject != null}">

                        <c:set value="${requestScope.AdminsObject.firstName}" var="FirstNameToCut" scope="request"/>
                        <c:set value="${requestScope.AdminsObject.lastName}" var="LastNameToCut" scope="request"/>
                        <%
                            String firstNameCutted = String.valueOf(request.getAttribute("FirstNameToCut"));
                            String SecondNameCutted = String.valueOf(request.getAttribute("LastNameToCut"));
                            String finalFirstName = firstNameCutted.substring(0, 1);
                            String finalSecondName = SecondNameCutted.substring(0, 1);
                        %>
                        <span class="img-size-30 mr-3 img-circle empty-avatar"><%=finalFirstName.toUpperCase()%><%=finalSecondName.toUpperCase()%></span>

                    </c:if>
                </div>
                <div class="info">
                    <c:if test="${requestScope.AdminsObject != null}">
                        <a href="#"
                           class="d-block">${requestScope.AdminsObject.firstName} ${requestScope.AdminsObject.lastName}</a>
                    </c:if>

                </div>
            </div>
            <jsp:include page="${root}/includes/Sidebar.jsp"/>
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
                        <h1>Profile</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <%--<li class="breadcrumb-item"><a href="#">Dashboard</a></li>--%>
                        </ol>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="container-fluid">
                <div class="row justify-content-center">
                    <div class="col-md-8">
                        <!-- Profile Image -->
                        <div class="card card-primary card-outline custom-card">
                            <div class="card-body box-profile">
                                <c:if test="${requestScope.UsersObject!=null}">
                                    <div class="text-center">
                                        <c:set value="${requestScope.UsersObject.userImage}" var="image"/>
                                        <c:choose>
                                            <c:when test="${image != null}">
                                                <img class="profile-user-img img-fluid img-circle user-detail-img"
                                                     src="${root}/${requestScope.UsersObject.userImage}">
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="isCompany" value="${requestScope.UsersObject.firstName}"/>
                                                <c:choose>
                                                    <c:when test="${isCompany == null}">
                                                        <c:set value="${requestScope.UsersObject.firstName}"
                                                               var="FirstNameToCut"
                                                               scope="request"/>
                                                        <c:set value="${requestScope.UsersObject.lastName}"
                                                               var="LastNameToCut"
                                                               scope="request"/>
                                                        <%
                                                            String firstNameCutted = String.valueOf(request.getAttribute("FirstNameToCut"));
                                                            String SecondNameCutted = String.valueOf(request.getAttribute("LastNameToCut"));
                                                            String finalFirstName = firstNameCutted.substring(0, 1);
                                                            String finalSecondName = SecondNameCutted.substring(0, 1);
                                                        %>
                                                        <span class="profile-user-img img-fluid img-circle empty-avatar"><%=finalFirstName.toUpperCase()%><%=finalSecondName.toUpperCase()%></span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:set value="${requestScope.UsersObject.comapny}"
                                                               var="FirstNameToCut"
                                                               scope="request"/>
                                                        <%
                                                            String firstNameCutted = String.valueOf(request.getAttribute("FirstNameToCut"));
                                                            String finalFirstName = firstNameCutted.substring(0, 1);
                                                        %>
                                                        <span class="profile-user-img img-fluid img-circle empty-avatar"><%=finalFirstName.toUpperCase()%></span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <%-- <h3 class="profile-username text-center">${requestScope.UsersObject.firstName} ${requestScope.UsersObject.lastName}</h3>--%>
                                    <p class="text-muted text-center">${requestScope.UsersObject.comapny}</p>
                                </c:if>
                            </div>
                            <!-- /.card-body -->
                        </div>
                        <!-- /.card -->
                        <!-- /.card -->
                    </div>

                    <div class="col-md-8">
                        <!-- About Me Box -->


                        <div class="card card-primary custom-card">
                            <c:if test="${requestScope.UsersObject !=null}">
                                <div class="card-header">
                                    <h3 class="card-title">About</h3>
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
                                    <strong>Email</strong>
                                    <p class="text-muted"> ${requestScope.UsersObject.email}</p>
                                    <hr>
                                    <strong>Status</strong>
                                    <c:set value="${requestScope.UsersObject.status}" var="status"/>
                                    <c:choose>
                                        <c:when test="${status == 1}">
                                            <p class="text-muted"><span class="badge bg-success">Active</span></p>
                                        </c:when>
                                        <c:when test="${status == 2}">
                                            <p class="text-muted"><span class="badge bg-danger">Inactive</span></p>
                                        </c:when>
                                        <c:when test="${status == 0}">
                                            <p class="text-muted"><span class="badge bg-secondary">Pending</span>
                                            </p>
                                        </c:when>
                                        <c:when test="${status == 10}">
                                            <p class="text-muted"><span class="badge bg-danger">Deleted</span></p>
                                        </c:when>
                                    </c:choose>
                                </div>
                                <!-- /.card-body -->
                            </c:if>
                        </div>
                        <!-- start employee details and affiliate info start -->
                        <c:if test="${requestScope.UsersObject.companyId == 0}">
                            <div class="card card-primary card-outline custom-card">
                                <div  class="card-body box-profile">
                                    <small>Link: https://second.fertifabenefits.com/registring/af/registeraf.jsp?link=${requestScope.Affiliate.secretKey}</small>
                                </div>

                                <div class="card-body box-profile">
                                    <canvas id="affiliate-pie" height="270"
                                            style="display: block;"
                                            class="chartjs-render-monitor"></canvas>

                                </div>
                            </div>
                        </c:if>
                        <!-- start employee details and affiliate info end -->
                        <c:if test="${requestScope.UsersObject.companyId !=0}">
                            <div class="accordion" id="healtHistory">
                                <div class="card">
                                    <div class="card-header" id="headingOne">
                                        <h2 class="mb-0">
                                            <button class="btn btn-link" type="button" data-toggle="collapse"
                                                    data-target="#collapseOne" aria-expanded="true"
                                                    aria-controls="collapseOne">
                                                User Health History
                                            </button>
                                        </h2>
                                    </div>

                                    <div id="collapseOne" class="collapse" aria-labelledby="headingOne"
                                         data-parent="#healtHistory">
                                        <div class="card-body">
                                            <h5 class="healt-history-subtitle">Personal Details</h5>
                                            <c:if test="${requestScope.HealthHistryIdList != null}">
                                                <c:forEach items="${requestScope.HealthHistryIdList}" var="history">

                                                    <table class="table healt-history-table custom-table mt-0 mb-4">
                                                        <thead>
                                                        <tr>
                                                            <th scope="col"></th>
                                                            <th scope="col">You</th>
                                                            <th scope="col">Your Partner (if applicable)</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr>
                                                            <td>Last name</td>
                                                            <td>${history.yourLastName}</td>
                                                            <td>${history.partnerLastName}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>First name(s)</td>
                                                            <td>${history.yourFirstName}</td>
                                                            <td>${history.partnerFirstName}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Date of birth</td>
                                                            <td>${history.yourDateOfBirth}</td>
                                                            <td>${history.partnerDateOfBirth}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Gender</td>
                                                            <td>${history.yourGender}</td>
                                                            <td>${history.partnerGender}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Marital status</td>
                                                            <td>${history.yourMartialStatus}</td>
                                                            <td>${history.partnerMartialStatus}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Home address</td>
                                                            <td>${history.yourHomeAddress}</td>
                                                            <td>${history.partnerHomeAddress}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Postcode</td>
                                                            <td>${history.yourPostcode}</td>
                                                            <td>${history.partnerPostcode}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Mobile phone number</td>
                                                            <td>${history.yourMobilePhone}</td>
                                                            <td>${history.partnerMobilePhone}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Email address</td>
                                                            <td>${history.yourEmail}</td>
                                                            <td>${history.partnerEmail}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Nationality</td>
                                                            <td>${history.yourNationality}</td>
                                                            <td>${history.partnerNationality}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Employer name</td>
                                                            <td>${history.yourEmployerName}</td>
                                                            <td>${history.partnerEmployerName}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Occupation</td>
                                                            <td>${history.yourOccupation}</td>
                                                            <td>${history.partnerOccupation}</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                    <h5 class="healt-history-subtitle">Next of Kin</h5>

                                                    <table class="table healt-history-table custom-table mt-0 mb-4">
                                                        <tbody>
                                                        <tr>
                                                            <td>Next of kin name</td>
                                                            <td>${history.nextKinName}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Relationship to you</td>
                                                            <td>${history.relationship}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Contact number</td>
                                                            <td>${history.contactNumber}</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>

                                                    <h5 class="healt-history-subtitle">General Practitioner Details</h5>
                                                    <table class="table healt-history-table custom-table mt-0 mb-4">
                                                        <tbody>
                                                            <%-- General Practitioner Details--%>
                                                        <tr>
                                                            <td>GP name</td>
                                                            <td>${history.gpName}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>GP address</td>
                                                            <td>${history.gpAddress}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>GP contact number</td>
                                                            <td>${history.gpContactNumber}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>GP email address</td>
                                                            <td>${history.gpEmailAddress}</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>

                                                    <h5 class="healt-history-subtitle">Lifestyle Details</h5>
                                                    <table class="table healt-history-table custom-table mt-0 mb-4">
                                                        <tbody>
                                                        <tr>
                                                            <td>Smoker</td>
                                                            <td>${history.yourSmoker}</td>
                                                            <td>${history.partnerSmoker}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Alcohol (units per week)</td>
                                                            <td>${history.yourAlcohol}</td>
                                                            <td>${history.partnerAlcohol}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Allergies</td>
                                                            <td>${history.yourAllergies}</td>
                                                            <td>${history.partnerAllergies}</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>

                                                    <h5 class="healt-history-subtitle">Medical History</h5>
                                                    <table class="table healt-history-table custom-table mt-0 mb-4">
                                                        <thead>
                                                        <tr>
                                                            <th scope="col"></th>
                                                            <th scope="col">You</th>
                                                            <th scope="col">Your Partner (if applicable)</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr>
                                                            <td>Details of previous operations or serious illnesses</td>
                                                            <td>${history.yourIllnesses}</td>
                                                            <td>${history.partnerIllnesses}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Family health history: (Please state familial
                                                                relationship)
                                                            </td>
                                                            <td>${history.yourFamilyHealtHistory}</td>
                                                            <td>${history.partnerFamilyHealtHistory}</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>

                                                    <h5 class="healt-history-subtitle">Fertility History</h5>
                                                    <table class="table healt-history-table custom-table mt-0 mb-4">
                                                        <thead>
                                                        <tr>
                                                            <th scope="col"></th>
                                                            <th scope="col">You</th>
                                                            <th scope="col">Your Partner (if applicable)</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr>
                                                            <td>How long have you been trying to conceive?</td>
                                                            <td>${history.yourConceive}</td>
                                                            <td>${history.partnerConceive}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Have you had fertility treatment previously?</td>
                                                            <c:if test="${history.youTreatmentPreviously != null}">
                                                                <td>${history.youTreatmentPreviously}</td>
                                                            </c:if>
                                                            <c:if test="${history.partnerTreatmentPreviously != null}">
                                                                <td>${history.partnerTreatmentPreviously}</td>
                                                            </c:if>

                                                        </tr>
                                                        <tr>
                                                            <td>Have you had fertility assessments/investigations?</td>
                                                            <c:if test="${history.yourAssessments != null}">
                                                                <td>${history.yourAssessments}</td>
                                                            </c:if>
                                                            <c:if test="${history.partnerAssessments != null}">
                                                                <td>${history.partnerAssessments}</td>
                                                            </c:if>
                                                        </tr>
                                                        <tr>
                                                            <td>Have you/your partner ever been pregnant before?</td>
                                                            <c:if test="${history.youPregnantBefore != null}">
                                                                <td>${history.youPregnantBefore}</td>
                                                            </c:if>
                                                            <c:if test="${history.partnerPregnantBefore != null}">
                                                                <td>${history.partnerPregnantBefore}</td>
                                                            </c:if>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                If so, was conception natural or assisted?
                                                                <small>(Please detail number of pregnancies)</small>
                                                            </td>
                                                            <c:if test="${history.yourNaturalConnection != null}">
                                                                <td>
                                                                    <div>${history.yourNaturalConnection}</div>
                                                                    <div>${history.yourAssistedConnection}</div>
                                                                </td>
                                                            </c:if>
                                                            <c:if test="${history.partnerNaturalConnection != null}">
                                                                <td>
                                                                    <div>${history.partnerNaturalConnection}</div>
                                                                    <div>${history.partnerAssistedConnection}</div>
                                                                </td>
                                                            </c:if>
                                                        </tr>
                                                        <tr>
                                                            <td>What was the outcome? <small>(Please detail
                                                                number)</small></td>

                                                            <td>
                                                                <div>${history.yourFullDelivery}</div>
                                                                <div>${history.yourPreConnection}</div>
                                                                <div>${history.yourMiscarriage}</div>
                                                                <div>${history.yourEctopic}</div>
                                                                <div>${history.yourTermination}</div>
                                                            </td>


                                                            <td>
                                                                <div>${history.partnerFullDelivery}</div>
                                                                <div>${history.partnerPreDelivery}</div>
                                                                <div>${history.partnerMiscarriage}</div>
                                                                <div>${history.partnerEctopic}</div>
                                                                <div>${history.partnerTermination}</div>
                                                            </td>

                                                        </tr>
                                                        </tbody>
                                                    </table>

                                                    <h5 class="healt-history-subtitle">Enquiry Details</h5>
                                                    <table class="table healt-history-table custom-table mt-0 mb-4">
                                                        <tbody>
                                                        <tr>
                                                            <td>I am interested in</td>
                                                            <td>${history.interestedIn}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Current fertility treatment <small>(if
                                                                applicable)</small>
                                                            </td>
                                                            <td>${history.currentFertilityTreatment}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Hospital / Clinic name</td>
                                                            <td>${history.hospiotalName}</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>

                                                </c:forEach>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
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
    var roomIdFromFrontAdmin = ${requestScope.AdminsObject.id};
    var roomIdFromFrontUser = 0;
    var selfId = ${requestScope.AdminsObject.id};
    var sessionRoomFromFront = 'jok2f4fud_9';
    var affiliate_labels = ['Clicked users', 'Five seconds landing page', 'Registered Users'];
    var clicked_users = [${requestScope.CountClicksArray.get(0)}];
    var registered_users = [${requestScope.CountClicksArray.get(1)}];
    var page_views = [${requestScope.CountClicksArray.get(2)}];
</script>
<!-- ./wrapper -->
<jsp:include page="${root}/includes/Footer.jsp"/>
<script src="${root}/js/affiliate.js"></script>
</body>
</html>

