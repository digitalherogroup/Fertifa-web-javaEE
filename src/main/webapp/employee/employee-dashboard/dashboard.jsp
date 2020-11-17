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
<%
    if (request.getHeader("User-Agent").contains("Mobile")) {
        String url = "https://m.second.fertifabenefits.com/employee/employee-dashboard";
        response.sendRedirect(url);
    }
%>
<!DOCTYPE html>
<html >
<head>
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <%
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader ("Expires", 0);
        if(session.getAttribute("employeeEmail")==null){
            response.sendRedirect(request.getContextPath() + "/employee/employee-dashboard");
        }
       if("POST".equalsIgnoreCase(request.getMethod())){
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
            <div class="content">
                <div class="row mb-5">
                    <div class="col-12">

                        <div class="dash-user-info">
                            <div class="loader-place"></div>
                            <div class="dash-user-info__image">
                                <c:if test="${requestScope.EmployeeObject != null}">

                                    <c:set value="${requestScope.EmployeeObject.userImage}" var="Image"/>
                                    <c:choose>
                                        <c:when test="${Image != null}">

                                            <img class="user-image-for-show" src="${root}/${requestScope.EmployeeObject.userImage}"
                                                 alt=""/>
                                            <div class="custom-file user-image-manage hidden-first">
                                                <input type="file" data-file="${requestScope.EmployeeObject.userImage}"
                                                       accept=".png,.jpg,.jpeg" id="user-image-manage"
                                                       class="custom-file-input" value="">
                                                <label class="custom-file-label" for="user-image-manage"><i
                                                        class="fas fa-plus"></i></label>
                                            </div>
                                        </c:when>

                                        <c:when test="${Image == null}">
                                            <img class="user-image-for-show"
                                                 src="<%=request.getContextPath()%>/img/avatar-empty.svg" alt=""/>
                                            <div class="custom-file user-image-manage hidden-first">
                                                <input type="file"
                                                       data-file="<%=request.getContextPath()%>/img/avatar-empty.svg"
                                                       accept=".png,.jpg,.jpeg" id="user-image-manage"
                                                       class="custom-file-input" value="">
                                                <label class="custom-file-label" for="user-image-manage"><i
                                                        class="fas fa-plus"></i></label>
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </c:if>
                            </div>
                            <div class="dash-user-info__content">
                                <form action="" id="user-edit-form" class="user-edit-form" novalidate>
                                    <c:if test="${requestScope.EmployeeObject != null}">
                                        <h1 id="edited_user_company"
                                            class="dash-user-info__content-company shown-first">${requestScope.EmployeeObject.firstName} ${requestScope.EmployeeObject.lastName} </h1>
                                        <input
                                                type="text"
                                                class="form-control hidden-first form-control-sm custom-rounded-input custom-editable-input"
                                                name="first_name"
                                                id="first_name"
                                                placeholder="First Name"
                                                value="${requestScope.EmployeeObject.firstName}"
                                        />
                                        <input
                                                type="text"
                                                class="form-control hidden-first form-control-sm custom-rounded-input custom-editable-input"
                                                name="last_name"
                                                id="last_name"
                                                placeholder="Last Name"
                                                value="${requestScope.EmployeeObject.lastName}"
                                        />
                                        <div class="user-main-info employee d-flex">
                                            <div class="user-main-info__item">
                                                <span class="dash-user-info__content-label">E-mail:</span>
                                                <p class="dash-user-info__content-value">${requestScope.EmployeeObject.email}</p>
                                            </div>
                                            <div class="user-main-info__item">
                                                <span class="dash-user-info__content-label">Phone:</span>
                                                <p id="edited_user_phone"
                                                   class="dash-user-info__content-value shown-first">
                                                        ${requestScope.EmployeeObject.phoneNumber}
                                                </p>
                                                <input
                                                        type="text"
                                                        class="form-control hidden-first form-control-sm custom-rounded-input custom-editable-input"
                                                        name="user_phone"
                                                        id="user_phone"
                                                        placeholder="Phone Number"
                                                        value="${requestScope.EmployeeObject.phoneNumber}"

                                                />
                                                <span class="invalid-phone-number">Please enter a valid phone number (excluding any letters or symbols).</span>
                                            </div>
                                            <div class="user-main-info__item">
                                                <span class="dash-user-info__content-label">Address:</span>
                                                <p id="edited_user_address"
                                                   class="dash-user-info__content-value shown-first">
                                                        ${requestScope.EmployeeObject.address}
                                                </p>
                                                <input
                                                        type="text"
                                                        class="form-control hidden-first form-control-sm custom-rounded-input custom-editable-input"
                                                        name="user_address"
                                                        id="user_address"
                                                        placeholder="Address"
                                                        value="${requestScope.EmployeeObject.address}"
                                                />
                                            </div>
                                            <div class="user-main-info__item">
                                                    <%-- <span class="dash-user-info__content-label">Card:</span>
                                                     <p id="edited_user_card" class="dash-user-info__content-value">
                                                         &lt;%&ndash;Visa **** 5678 Expires: 12/22&ndash;%&gt;
                                                     </p>--%>
                                            </div>
                                        </div>

                                    </c:if>
                                </form>
                            </div>
                            <div class="dash-user-info__edit">
                               <%-- <button class="dash-user-info__edit-profile edit-profile-action">Edit profile</button>
                                <button class="dash-user-info__edit-profile save-user-profile-action">Save</button>--%>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row mb-5">
                    <div class="col-lg-6 col-md-12">
                        <div class="card custom-card label-card purple mb-4">
                            <a href="${root}/employee/book-appointment" class="btn custom-rounded-btn purple">BOOK AN APPOINTMENT</a>
                        </div>
                        <div class="card custom-card label-card purple mb-4" style="background:linear-gradient(90deg,rgba(108,71,222,.9) -15.13%,rgba(155,141,242,.9) 89.56%)">
                            <span style="padding: 20px 25px;font-size: 18px;color: #fff;font-weight: 600">Want to ask something now?</span>
                            <a href="${root}/employee/chat" class="btn custom-rounded-btn purple">MESSAGE</a>
                        </div>
                        <div class="card custom-card">
                            <div class="card-body custom-card__body">
                                <h3 class="custom-card-title">Notifications</h3>
                                <ul class="news-recent-list card-list">
                                    <li class="news-recent-list__item">
                                        <c:if test="${requestScope.ChatSessionList!= null}">
                                            <c:forEach items="${requestScope.ChatSessionList}" var="Chats" step="1"
                                                       end="4">
                                                <a href="${root}/employee/chat/conversation?id=${Chats.chatSessionToken}">
                                                    <c:set value="${Chats.chatCreationDate}" var="CreationDate"
                                                           scope="request"/>


                                                    <span class="  news-recent-list__item-date">${Chats.stringDate}</span>
                                                    <div class="news-recent-list__item-title">${Chats.chatSessionName}
                                                    </div>
                                                </a>
                                            </c:forEach>
                                        </c:if>

                                    </li>

                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-12">
                        <div class="card custom-card label-card green mb-4">
                            <a href="${root}/employee/order-testing" class="btn custom-rounded-btn green">ORDER TESTS</a>
                        </div>
                        <div class="card custom-card label-card green mb-4" style="background: linear-gradient(90deg,rgba(96,153,108,.8) -16.19%,rgba(141,200,153,.8) 88.22%)">
                            <span style="padding: 20px 25px;font-size: 18px;color: #fff;font-weight: 600">Checkout the full list of benefits</span>
                            <a href="${root}/employee/benefits" class="btn custom-rounded-btn green">BENEFITS</a>
                        </div>
                        <div class="card custom-card">
                            <div class="card-body custom-card__body">
                                <h3 class="custom-card-title">Education</h3>
                                <ul class="news-recent-list card-list">
                                    <li class="news-recent-list__item">
                                        <c:if test="${requestScope.NewsModelList!= null}">
                                            <c:forEach items="${requestScope.NewsModelList}" var="News" step="1"
                                                       end="4">
                                                <a href="${root}/employee/education/educations?id=${News.id}">
                                                    <c:set value="${News.creation_Date}" var="CreationDate"
                                                           scope="request"/>
                                                    <%
                                                        String dateBeforeString = String.valueOf(request.getAttribute("CreationDate"));

                                                    %>
                                                    <%!
                                                        public static String convertDateWithRegex(String date, String pattern) {
                                                            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                                                            return sdf.format(new Date(convertStringToTimestamp(date).getTime()));
                                                        }

                                                        private static Timestamp convertStringToTimestamp(String dateString) {

                                                            SimpleDateFormat dateFormat = null;
                                                            if (dateString.contains(".")) {
                                                                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                                            }
                                                            if (dateString.contains(",")) {
                                                                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                                            }
                                                            Timestamp timestamp = null;
                                                            Date parsedDate;
                                                            try {
                                                                parsedDate = dateFormat.parse(dateString);
                                                                timestamp = new java.sql.Timestamp(parsedDate.getTime());

                                                            } catch (ParseException e) {
                                                                e.printStackTrace();
                                                            }
                                                            return timestamp;
                                                        }
                                                    %>
                                                    <span class="
                                                   news-recent-list__item-date"><%=convertDateWithRegex(dateBeforeString, "dd-MM-yyyy")%></span>
                                                    <div class="news-recent-list__item-title">
                                                            ${News.title}
                                                    </div>
                                                </a>
                                            </c:forEach>
                                        </c:if>
                                    </li>

                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <c:if test="${requestScope.HealthHistory  == 0}">
                    <div id="enquiry-form-block"
                         class="alert alert-secondary alert-dismissible d-flex align-items-center mb-3 fade show light-grey"
                    >
                        <p>
                            To get started, please take 10 minutes to fill in our Health Questionnaire. We'll then be in touch to arrange a scoping consultation with one of our advisors to talk through next steps.
                        </p>

                        <a href="" data-toggle="modal" data-target="#enquiry-modal"
                           class="btn btn-primary custom-rounded-btn line">Health Questionnaire</a>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <i class="fas fa-times-circle"></i>
                        </button>

                    </div>
                </c:if>

            </div>
        </main>
    </div>

    <jsp:include page="${root}/includes/NotificationBlogUser.jsp"/>
</div>
<!-- Modal -->



<div class="modal custom-modal modal-service fade" id="enquiry-modal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg custom-modal">
        <div class="modal-content custom-modal__content bg-default">
            <form method="POST" id="enquiry-form" class="enquiry-form-list">
                <div class="message-place"></div>
                <div class="modal-header custom-modal__header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                </div>
                <div class="modal-body mh-400 custom-modal__body">
                    <div class="modal-loader-place"></div>
                    <div class="main-content">

                        <h3 class="custom-modal__sub-title mt-3">Personal Details</h3>
                        <div class="form-row">
                            <label for="inputEmail3" class="col-sm-2 col-form-label">Last name:</label>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control custom-rounded-input" name="yourLastName"
                                       id="yourLastName" placeholder="Your Last name" required>
                            </div>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="partnerLastName"
                                       id="partnerLastName" placeholder="Partner Last name">
                            </div>
                        </div>
                        <div class="form-row">
                            <label for="inputEmail3" class="col-sm-2 col-form-label">First name(s):</label>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="yourFirstName"
                                       id="yourFirstName" placeholder="Your First name" required>
                            </div>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="partnerFirstName"
                                       id="partnerFirstName" placeholder="Partner First name">
                            </div>
                        </div>
                        <div class="form-row">
                            <label for="inputEmail3" class="col-sm-2 col-form-label">Date of birth</label>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="yourDateOfBirth"
                                       id="yourDateOfBirth" placeholder="Your Date Of Birth" required>
                            </div>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="partnerDateOfBirth"
                                       id="partnerDateOfBirth" placeholder="Partner Date Of Birth">
                            </div>
                        </div>
                        <div class="form-row">
                            <label for="inputEmail3" class="col-sm-2 col-form-label">Gender</label>
                            <div class="form-group col-md-5">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="yourGender" id="yourMale"
                                           value="Male" required>
                                    <label class="form-check-label" for="yourMale">Male</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="yourGender"
                                           id="yourFemale"
                                           value="Female" required>
                                    <label class="form-check-label" for="yourFemale">Female</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="yourGender" id="yourOther"
                                           value="Other" required>
                                    <label class="form-check-label" for="yourOther">Other</label>
                                </div>
                                <input type="radio" name="yourGender" value="" checked style="display:none;">
                            </div>
                            <div class="form-group col-md-5">
                                <div class="for m-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="partnerGender"
                                           id="genderMale"
                                           value="Male">
                                    <label class="form-check-label" for="genderMale">Male</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="partnerGender"
                                           id="genderFemale"
                                           value="Female">
                                    <label class="form-check-label" for="genderFemale">Female</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="partnerGender" id="partnerOther"
                                           value="Other" required>
                                    <label class="form-check-label" for="partnerOther">Other</label>
                                </div>
                                <input type="radio" name="partnerGender" value="" checked style="display:none;">
                            </div>
                        </div>
                        <div class="form-row">
                            <label for="inputEmail3" class="col-sm-2 col-form-label">Marital status</label>
                            <div class="form-group col-md-5">
                                <select class="selectpicker custom-selectpick custom-rounded-dropdown mr-0"
                                        name="yourMartialStatus" id="yourMartialStatus">
                                    <option value="">Choose an item</option>
                                    <option value="Married">Married</option>
                                    <option value="Widowed">Widowed</option>
                                    <option value="Separated">Separated</option>
                                    <option value="Divorced">Divorced</option>
                                    <option value="Single">Single</option>
                                </select>
                            </div>
                            <div class="form-group col-md-5">
                                <select class="selectpicker custom-selectpick custom-rounded-dropdown mr-0"
                                        name="partnerMartialStatus" id="partnerMartialStatus">
                                    <option value="">Choose an item</option>
                                    <option value="Married">Married</option>
                                    <option value="Widowed">Widowed</option>
                                    <option value="Separated">Separated</option>
                                    <option value="Divorced">Divorced</option>
                                    <option value="Single">Single</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-row">
                            <label for="inputEmail3" class="col-sm-2 col-form-label">Home address</label>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="yourHomeAddress"
                                       id="yourHomeAddress" placeholder="Your Home Address">
                            </div>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="partnerHomeAddress"
                                       id="partnerHomeAddress" placeholder="Partner Home Address">
                            </div>
                        </div>
                        <div class="form-row">
                            <label for="inputEmail3" class="col-sm-2 col-form-label">Postcode</label>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control custom-rounded-input" name="yourPostcode"
                                       id="yourPostcode" placeholder="Your Postcode">
                            </div>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="partnerPostcode"
                                       id="partnerPostcode" placeholder="Partner Postcode">
                            </div>
                        </div>
                        <div class="form-row">
                            <label for="inputEmail3" class="col-sm-2 col-form-label">Mobile phone number</label>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="yourMobilePhone"
                                       id="yourMobilePhone" placeholder="Your phone number">
                            </div>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="partnerMobilePhone"
                                       id="partnerMobilePhone" placeholder="Partner phone number">
                            </div>
                        </div>
                        <div class="form-row">
                            <label for="inputEmail3" class="col-sm-2 col-form-label">Email</label>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control custom-rounded-input" name="yourEmail"
                                       id="yourEmail" placeholder="Your email">
                            </div>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control custom-rounded-input" name="partnerEmail"
                                       id="partnerEmail" placeholder="Partner email">
                            </div>
                        </div>
                        <div class="form-row">
                            <label for="inputEmail3" class="col-sm-2 col-form-label">Nationality</label>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="yourNationality"
                                       id="yourNationality" placeholder="Your Nationality">
                            </div>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="partnerNationality"
                                       id="partnerNationality" placeholder="Partner Nationality">
                            </div>
                        </div>
                        <div class="form-row">
                            <label for="inputEmail3" class="col-sm-2 col-form-label">Employer name</label>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="yourEmployerName"
                                       id="yourEmployerName" placeholder="Your Employer name">
                            </div>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="partnerEmployerName"
                                       id="partnerEmployerName" placeholder="Partner Employer name">
                            </div>
                        </div>
                        <div class="form-row">
                            <label class="col-sm-2 col-form-label">Occupation</label>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="yourOccupation"
                                       id="yourOccupation" placeholder="Your Occupation">
                            </div>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="partnerOccupation"
                                       id="partnerOccupation" placeholder="Partner Occupation">
                            </div>
                        </div>
                        <h3 class="custom-modal__sub-title mt-3">Next of Kin</h3>
                        <div class="form-row">
                            <label for="nextKinName" class="col-sm-4 col-form-label">Next of kin name</label>
                            <div class="form-group col-md-8">
                                <input type="text" class="form-control custom-rounded-input" name="nextKinName"
                                       id="nextKinName" placeholder="Next of kin name">
                            </div>
                        </div>
                        <div class="form-row">
                            <label for="relationship" class="col-sm-4 col-form-label">Relationship to
                                you:</label>
                            <div class="form-group col-md-8">
                                <input type="text" class="form-control custom-rounded-input" name="relationship"
                                       id="relationship" placeholder="Relationship to you">
                            </div>
                        </div>
                        <div class="form-row">
                            <label for="contactNumber" class="col-sm-4 col-form-label">Contact number</label>
                            <div class="form-group col-md-8">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="contactNumber"
                                       id="contactNumber" placeholder="Contact number">
                            </div>
                        </div>

                        <h3 class="custom-modal__sub-title mt-3">General Practitioner Details</h3>
                        <div class="form-row">
                            <label for="gpName" class="col-sm-4 col-form-label">GP name</label>
                            <div class="form-group col-md-8">
                                <input type="text" class="form-control custom-rounded-input" name="gpName"
                                       id="gpName"
                                       placeholder="GP name">
                            </div>
                        </div>
                        <div class="form-row">
                            <label for="gpAddress" class="col-sm-4 col-form-label">GP address</label>
                            <div class="form-group col-md-8">
                                <input type="text" class="form-control custom-rounded-input" name="gpAddress"
                                       id="gpAddress" placeholder="GP address">
                            </div>
                        </div>
                        <div class="form-row">
                            <label for="gpContactNumber" class="col-sm-4 col-form-label">GP contact
                                number</label>
                            <div class="form-group col-md-8">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="gpContactNumber"
                                       id="gpContactNumber" placeholder="GP contact number">
                            </div>
                        </div>
                        <div class="form-row">
                            <label for="gpEmailAddress" class="col-sm-4 col-form-label">GP email address</label>
                            <div class="form-group col-md-8">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="gpEmailAddress"
                                       id="gpEmailAddress" placeholder="GP email address">
                            </div>
                        </div>

                        <h3 class="custom-modal__sub-title mt-3">Lifestyle Details</h3>
                        <div class="form-row">
                            <label class="col-sm-2 col-form-label">Smoker</label>
                            <div class="form-group col-md-5">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="yourSmoker" id="yourYes"
                                           value="Yes">
                                    <label class="form-check-label" for="yourYes">Yes</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="yourSmoker" id="yourNo"
                                           value="No">
                                    <label class="form-check-label" for="yourNo">No</label>
                                </div>
                                <input type="radio" name="yourSmoker" value="" checked style="display:none;">
                            </div>
                            <div class="form-group col-md-5">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="partnerSmoker"
                                           id="partnerYes"
                                           value="Yes">
                                    <label class="form-check-label" for="partnerYes">Yes</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="partnerSmoker" id="part
nerNo" value="No">
                                    <label class="form-check-label" for="partnerNo">No</label>
                                </div>
                                <input type="radio" name="partnerSmoker" value="" checked style="display:none;">
                            </div>
                        </div>
                        <div class="form-row">
                            <label class="col-sm-2 col-form-label">Alcohol (units per week)</label>
                            <div class="form-group col-md-5">
                                <div class="row">
                                    <div class="col-md-8">
                                        <input type="text" class="form-control custom-rounded-input"
                                               name="yourAlcohol"
                                               id="yourAlcohol" placeholder="For you">
                                    </div>
                                    <div class="col-md-4">per week</div>
                                </div>
                            </div>
                            <div class="form-group col-md-5">
                                <div class="row">
                                    <div class="col-md-8">
                                        <input type="text" class="form-control custom-rounded-input"
                                               name="partnerAlcohol" id="partnerAlcohol"
                                               placeholder="For Partner">
                                    </div>
                                    <div class="col-md-4">per week</div>
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <label class="col-sm-2 col-form-label">Allergies</label>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="yourAllergies"
                                       id="yourAllergies" placeholder="For You">
                            </div>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="partnerAllergies"
                                       id="partnerAllergies" placeholder="For Partner">
                            </div>
                        </div>

                        <h3 class="custom-modal__sub-title mt-3">Medical History</h3>
                        <div class="form-row">
                            <label class="col-sm-2 col-form-label">Details of previous operations or serious
                                illnesses</label>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="yourIllnesses"
                                       id="yourIllnesses" placeholder="For You">
                            </div>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="partnerIllnesses"
                                       id="partnerIllnesses" placeholder="For Partner">
                            </div>
                        </div>
                        <div class="form-row">
                            <label class="col-sm-2 col-form-label">Family health history: (Please state familial
                                relationship)</label>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="yourFamilyHealtHistory" id="yourFamilyHealtHistory"
                                       placeholder="Your">
                            </div>
                            <div class="form-group col-md-5">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="partnerFamilyHealtHistory" id="partnerFamilyHealtHistory"
                                       placeholder="Partner">
                            </div>
                        </div>

                        <h3 class="custom-modal__sub-title mt-3">Fertility History</h3>
                        <div class="form-row">
                            <label class="col-sm-2 col-form-label">How long have you been trying to
                                conceive?</label>
                            <div class="form-group col-md-5">
                                <div class="row">
                                    <div class="col-md-7">
                                        <input type="text" class="form-control custom-rounded-input"
                                               name="yourConceive"
                                               id="yourConceive" placeholder="For you">
                                    </div>
                                    <div class="col-md-5"><small>years/months</small></div>
                                    <div class="col-md-12"><small>(delete as applicable)</small></div>
                                </div>
                            </div>
                            <div class="form-group col-md-5">
                                <div class="row">
                                    <div class="col-md-7">
                                        <input type="text" class="form-control custom-rounded-input"
                                               name="partnerConceive" id="partnerConceive"
                                               placeholder="For partner">
                                    </div>
                                    <div class="col-md-5"><small>years/months</small></div>
                                    <div class="col-md-12"><small>(delete as applicable)</small></div>
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <label class="col-sm-2 col-form-label">Have you had fertility treatment
                                previously?</label>
                            <div class="form-group col-md-5">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="youTreatmentPreviously"
                                           id="youTreatmentPreviouslyYes" value="Yes">
                                    <label class="form-check-label" for="youTreatmentPreviouslyYes">Yes</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="youTreatmentPreviously"
                                           id="youTreatmentPreviouslyNo" value="No">
                                    <label class="form-check-label" for="youTreatmentPreviouslyNo">No</label>
                                </div>
                                <input type="radio" name="youTreatmentPreviously" value="" checked
                                       style="display:none;">
                            </div>
                            <div class="form-group col-md-5">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio"
                                           name="partnerTreatmentPreviously"
                                           id="partnerTreatmentPreviouslyYes" value="Yes">
                                    <label class="form-check-label"
                                           for="partnerTreatmentPreviouslyYes">Yes</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio"
                                           name="partnerTreatmentPreviously"
                                           id="partnerTreatmentPreviouslyNo" value="No">
                                    <label class="form-check-label"
                                           for="partnerTreatmentPreviouslyNo">No</label>
                                </div>
                                <input type="radio" name="partnerTreatmentPreviously" value="" checked
                                       style="display:none;">
                            </div>
                        </div>
                        <div class="form-row">
                            <label class="col-sm-4 col-form-label">Have you had fertility
                                assessments/investigations?</label>
                            <div class="form-group col-md-4">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="yourAssessments"
                                           id="yourAssessmentsYes" value="Yes">
                                    <label class="form-check-label" for="yourAssessmentsYes">Yes</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="yourAssessments"
                                           id="yourAssessmentsNo" value="No">
                                    <label class="form-check-label" for="yourAssessmentsNo">No</label>
                                </div>
                                <input type="radio" name="yourAssessments" value="" checked style="display:none;">
                                <div><small>(If Yes, please submit the reports)</small></div>
                            </div>
                            <div class="form-group col-md-4">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="partnerAssessments"
                                           id="partnerAssessmentsYes" value="Yes">
                                    <label class="form-check-label" for="partnerAssessmentsYes">Yes</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="partnerAssessments"
                                           id="partnerAssessmentsNo" value="No">
                                    <label class="form-check-label" for="partnerAssessmentsNo">No</label>
                                </div>
                                <input type="radio" name="partnerAssessments" value="" checked style="display:none;">
                                <div><small>(If Yes, please submit the reports)</small></div>
                            </div>
                        </div>
                        <div class="form-row">
                            <label class="col-sm-4 col-form-label">Have you/your partner ever been pregnant
                                before?</label>
                            <div class="form-group col-md-4">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="youPregnantBefore"
                                           id="youPregnantBeforeYes" value="Yes">
                                    <label class="form-check-label" for="youPregnantBeforeYes">Yes</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="youPregnantBefore"
                                           id="youPregnantBeforeNo" value="No">
                                    <label class="form-check-label" for="youPregnantBeforeNo">No</label>
                                </div>
                                <input type="radio" name="youPregnantBefore" value="" checked style="display:none;">
                                <div><small>(If No, go to section 7)</small></div>
                            </div>
                            <div class="form-group col-md-4">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="partnerPregnantBefore"
                                           id="partnerPregnantBeforeYes" value="Yes">
                                    <label class="form-check-label" for="partnerPregnantBeforeYes">Yes</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="partnerPregnantBefore"
                                           id="partnerPregnantBeforeNo" value="No">
                                    <label class="form-check-label" for="partnerPregnantBeforeNo">No</label>
                                </div>
                                <input type="radio" name="partnerPregnantBefore" value="" checked style="display:none;">
                            </div>
                        </div>
                        <div class="form-row">
                            <label class="col-sm-2 col-form-label">If so, was conception natural or assisted?
                                <small>(Please
                                    detail number of pregnancies)</small></label>
                            <div class="form-group col-md-5">
                                <div class="row mb-2">
                                    <div class="col-md-6"><small>Natural conception</small></div>
                                    <div class="col-md-6">
                                        <input type="text" class="form-control custom-rounded-input"
                                               name="yourNaturalConnection" id="yourNaturalConnection"
                                               placeholder="For you">
                                    </div>
                                </div>
                                <div class="row mb-2">
                                    <div class="col-md-6"><small>Assisted conception</small></div>
                                    <div class="col-md-6">
                                        <input type="text" class="form-control custom-rounded-input"
                                               name="yourAssistedConnection" id="yourAssistedConnection"
                                               placeholder="For you">
                                    </div>
                                </div>

                            </div>
                            <div class="form-group col-md-5">
                                <div class="row mb-2">
                                    <div class="col-md-6"><small>Natural conception</small></div>
                                    <div class="col-md-6">
                                        <input type="text" class="form-control custom-rounded-input"
                                               name="partnerNaturalConnection" id="partnerNaturalConnection"
                                               placeholder="For Partner">
                                    </div>
                                </div>
                                <div class="row mb-2">
                                    <div class="col-md-6"><small>Assisted conception</small></div>
                                    <div class="col-md-6">
                                        <input type="text" class="form-control custom-rounded-input"
                                               name="partnerAssistedConnection" id="partnerAssistedConnection"
                                               placeholder="For Partner">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <label class="col-sm-2 col-form-label">What was the outcome? <small>(Please detail
                                number)</small></label>
                            <div class="form-group col-md-5">
                                <div class="row mb-2">
                                    <div class="col-md-6"><small>Full-term delivery</small></div>
                                    <div class="col-md-6">
                                        <input type="text" class="form-control custom-rounded-input"
                                               name="yourFullDelivery" id="yourFullDelivery" placeholder="You">
                                    </div>
                                </div>
                                <div class="row mb-2">
                                    <div class="col-md-6"><small>Pre-term delivery</small></div>
                                    <div class="col-md-6">
                                        <input type="text" class="form-control custom-rounded-input"
                                               name="yourPreConnection" id="yourPreConnection"
                                               placeholder="You">
                                    </div>
                                </div>
                                <div class="row mb-2">
                                    <div class="col-md-6"><small>Miscarriage</small></div>
                                    <div class="col-md-6">
                                        <input type="text" class="form-control custom-rounded-input"
                                               name="yourMiscarriage" id="yourMiscarriage" placeholder="You">
                                    </div>
                                </div>
                                <div class="row mb-2">
                                    <div class="col-md-6"><small>Ectopic</small></div>
                                    <div class="col-md-6">
                                        <input type="text" class="form-control custom-rounded-input"
                                               name="yourEctopic"
                                               id="yourEctopic" placeholder="You">
                                    </div>
                                </div>
                                <div class="row mb-2">
                                    <div class="col-md-6"><small>Termination</small></div>
                                    <div class="col-md-6">
                                        <input type="text" class="form-control custom-rounded-input"
                                               name="yourTermination" id="yourTermination" placeholder="You">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-5">
                                <div class="row mb-2">
                                    <div class="col-md-6"><small>Full-term delivery</small></div>
                                    <div class="col-md-6">
                                        <input type="text" class="form-control custom-rounded-input"
                                               name="partnerFullDelivery" id="partnerFullDelivery"
                                               placeholder="Partner">
                                    </div>
                                </div>
                                <div class="row mb-2">
                                    <div class="col-md-6"><small>Pre-term delivery</small></div>
                                    <div class="col-md-6">
                                        <input type="text" class="form-control custom-rounded-input"
                                               name="partnerPreDelivery" id="partnerPreDelivery"
                                               placeholder="Partner">
                                    </div>
                                </div>
                                <div class="row mb-2">
                                    <div class="col-md-6"><small>Miscarriage</small></div>
                                    <div class="col-md-6">
                                        <input type="text" class="form-control custom-rounded-input"
                                               name="partnerMiscarriage" id="partnerMiscarriage"
                                               placeholder="Partner">
                                    </div>
                                </div>
                                <div class="row mb-2">
                                    <div class="col-md-6"><small>Ectopic</small></div>
                                    <div class="col-md-6">
                                        <input type="text" class="form-control custom-rounded-input"
                                               name="partnerEctopic" id="partnerEctopic" placeholder="Partner">
                                    </div>
                                </div>
                                <div class="row mb-2">
                                    <div class="col-md-6"><small>Termination</small></div>
                                    <div class="col-md-6">
                                        <input type="text" class="form-control custom-rounded-input"
                                               name="partnerTermination" id="partnerTermination"
                                               placeholder="Partner">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <h3 class="custom-modal__sub-title mt-3">Fertility History</h3>
                        <div class="form-row">
                            <label class="col-sm-4 col-form-label">I am interested in</label>
                            <div class="form-group col-md-8">
                                <select class="selectpicker custom-selectpick custom-rounded-dropdown mr-0"
                                        name="interestedIn" id="interestedIn">
                                    <option value="">Choose an item</option>
                                    <option value="Fertility assessment">Fertility assessment</option>
                                    <option value="Fertility treatment">Fertility treatment</option>
                                    <option value="Fertility wellbeing support">Fertility wellbeing support
                                    </option>
                                    <option value="Egg/sperm preservation">Egg/sperm preservation</option>
                                    <option value="Egg/sperm donor">Egg/sperm donor</option>
                                    <option value="Surrogacy">Surrogacy</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-row">
                            <label class="col-sm-4 col-form-label">Current fertility treatment <small>(if
                                applicable)</small></label>
                            <div class="form-group col-md-8">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="currentFertilityTreatment" id="currentFertilityTreatment"
                                       placeholder="Current fertility treatment">
                            </div>
                        </div>
                        <div class="form-row">
                            <label class="col-sm-4 col-form-label">Hospital / Clinic name</label>
                            <div class="for
m-group col-md-8">
                                <input type="text" class="form-control custom-rounded-input"
                                       name="hospiotalName"
                                       id="hospiotalName" placeholder="Hospital / Clinic name">
                            </div>
                        </div>
                        <div class="form-row d-flex justify-content-end">
                            <button type="submit"
                                    class="btn btn-primary position-relative custom-rounded-btn light-green">
                                Submit
                            </button>
                        </div>
                    </div>
                </div>

            </form>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<%--    </c:when>
</c:choose>--%>

<script>
    var send_enquiry_data_ajax = '/employee/SendEnquiryDataAjax';
</script>


<script>
    var selfId = ${requestScope.EmployeeObject.id};
    var roomIdFromFrontUser = ${requestScope.EmployeeObject.id};
    var sessionRoomFromFront = ${requestScope.EmployeeObject.id};
    var update_user_ajax = '<%=request.getContextPath()%>/UpdateUsersC?id=${requestScope.EmployeeObject.id}';
    /*  var update_user_ajax = 'UpdateUsersC';*/
    localStorage.removeItem('formData')
</script>
<jsp:include page="${root}/includes/BundleJsInclude.jsp"/>
<jsp:include page="${root}/includes/FrontFooter.jsp"/>
<%--<jsp:include page="../../includes/CookieManagerUser.jsp"/>--%>
<div class="order-testing-toast custom-toast fide hide toast" role="alert" aria-live="assertive" aria-atomic="true"
     data-delay="5000">
    <div class="toast-header">
        <button type="button" class="ml-2 mb-1 ml-auto close" data-dismiss="toast" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <div class="toast-body"></div>
</div>
</body>
</html>
