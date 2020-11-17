<%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 2/11/2020
  Time: 11:11 AM
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

            <c:if test="${requestScope.HealthHistory  == 0}">

                <div class="content">
                    <h1 class="content-title">
                        My Health History
                    </h1>

                    <div id="enquiry-form-block"
                         class="alert alert-secondary alert-dismissible d-flex align-items-center mb-3 fade show light-grey"
                    >
                        <p>
                            To get started, please take 10 minutes to fill in our Health Questionnaire. We'll then be in touch to arrange a scoping consultation with one of our advisors to talk through next steps.
                        </p>

                        <a href="" data-toggle="modal" data-target="#enquiry-modal-insert"
                           class="btn btn-primary custom-rounded-btn line">Health Questionnaire</a>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <i class="fas fa-times-circle"></i>
                        </button>

                    </div>

                    <div class="modal custom-modal modal-service fade" id="enquiry-modal-insert" tabindex="-1" role="dialog">
                        <div class="modal-dialog modal-lg custom-modal">
                            <div class="modal-content custom-modal__content bg-default">
                                <form method="POST" id="enquiry-form-insert" class="enquiry-form-list">
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
                                                <label for="inputEmail3" class="col-sm-2 col-form-label" >Marital status</label>
                                                <div class="form-group col-md-5">
                                                    <select class="selectpicker custom-selectpick custom-rounded-dropdown mr-0"
                                                            name="yourMartialStatus" id="yourMartialStatus" required>
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
                                                            name="partnerMartialStatus" id="partnerMartialStatus" required>
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
                                                            <%--  <div class="col-md-12"><small>(delete as applicable)</small></div>--%>
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
                                                            <%--  <div class="col-md-12"><small>(delete as applicable)</small></div>--%>
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
                                                    <div><%--<small>(If No, go to section 7)</small>--%></div>
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
                                                        id="click-to-refresh"
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
                </div>
            </c:if>
            <c:if test="${requestScope.HealthHistory  == 1}">
                <div class="content">
                    <h1 class="content-title">
                        My Health History
                        <a href=""
                           data-toggle="modal"
                           data-target="#enquiry-modal"
                           class="btn btn-primary ml-auto edit-health-details custom-rounded-btn grey-line"
                        >Edit Details</a>
                    </h1>

                    <c:if test="${requestScope.HealthHistoriesList.size() !=0}">
                        <c:forEach items="${requestScope.HealthHistoriesList}" var="History">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card custom-card mb-4">
                                        <div class="card-header custom-card__header">
                                            <h2 class="healt-history-subtitle">Personal Details</h2>
                                        </div>
                                        <div class="card-body custom-card__body">
                                            <table class="table healt-history-table custom-table mt-0">
                                                <thead>
                                                <tr>
                                                    <th scope="col"></th>
                                                    <th scope="col">You</th>
                                                    <th scope="col">Your Partner (if applicable)</th>
                                                </tr>
                                                </thead>
                                                <tbody>

                                                <tr>
                                                    <td>Last name(s)</td>
                                                    <td>${History.yourLastName}</td>
                                                    <td>${History.partnerLastName}</td>
                                                </tr>
                                                <tr>
                                                    <td>First name(s)</td>
                                                    <td>${History.yourFirstName}</td>
                                                    <td>${History.partnerFirstName}</td>
                                                </tr>
                                                <tr>
                                                    <td>Date of birth</td>
                                                    <td>${History.yourDateOfBirth}</td>
                                                    <td>${History.partnerDateOfBirth}</td>
                                                </tr>
                                                <tr>
                                                    <td>Gender</td>
                                                    <c:if test="${History.yourGender != null}">
                                                        <td>${History.yourGender}</td>
                                                    </c:if>
                                                    <c:if test="${History.partnerGender != null}">
                                                        <td>${History.partnerGender}</td>
                                                    </c:if>

                                                </tr>
                                                <tr>
                                                    <td>Marital status</td>
                                                    <td>${History.yourMartialStatus}</td>
                                                    <td>${History.partnerMartialStatus}</td>
                                                </tr>
                                                <tr>
                                                    <td>Home address</td>
                                                    <td>${History.yourHomeAddress}</td>
                                                    <td>${History.partnerHomeAddress}</td>
                                                </tr>
                                                <tr>
                                                    <td>Postcode</td>
                                                    <td>${History.yourPostcode}</td>
                                                    <td>${History.partnerPostcode}</td>
                                                </tr>
                                                <tr>
                                                    <td>Mobile phone number</td>
                                                    <td>${History.yourMobilePhone}</td>
                                                    <td>${History.partnerMobilePhone}</td>
                                                </tr>
                                                <tr>
                                                    <td>Email address</td>
                                                    <td>${History.yourEmail}</td>
                                                    <td>${History.partnerEmail}</td>
                                                </tr>
                                                <tr>
                                                    <td>Nationality</td>
                                                    <td>${History.yourNationality}</td>
                                                    <td>${History.partnerNationality}</td>
                                                </tr>
                                                <tr>
                                                    <td>Employer name</td>
                                                    <td>${History.yourEmployerName}</td>
                                                    <td>${History.partnerEmployerName}</td>
                                                </tr>
                                                <tr>
                                                    <td>Occupation</td>
                                                    <td>${History.yourOccupation}</td>
                                                    <td>${History.partnerOccupation}</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="card custom-card mb-4">
                                        <div class="card-header custom-card__header">
                                            <h2 class="healt-history-subtitle">Next of Kin</h2>
                                        </div>
                                        <div class="card-body custom-card__body">
                                            <table class="table healt-history-table with-border custom-table mt-0">
                                                <tbody>
                                                <tr>
                                                    <td>Next of kin name</td>
                                                    <td>${History.nextKinName}</td>
                                                </tr>
                                                <tr>
                                                    <td>Relationship to you</td>
                                                    <td>${History.relationship}</td>
                                                </tr>
                                                <tr>
                                                    <td>Contact number</td>
                                                    <td>${History.contactNumber}</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="card custom-card mb-4">
                                        <div class="card-header custom-card__header">
                                            <h2 class="healt-history-subtitle">General Practitioner Details</h2>
                                        </div>
                                        <div class="card-body custom-card__body">
                                            <table class="table healt-history-table with-border custom-table mt-0">
                                                <tbody>
                                                <tr>
                                                    <td>GP name</td>
                                                    <td>${History.gpName}</td>
                                                </tr>
                                                <tr>
                                                    <td>GP address</td>
                                                    <td>${History.gpAddress}</td>
                                                </tr>
                                                <tr>
                                                    <td>GP contact number</td>
                                                    <td>${History.gpContactNumber}</td>
                                                </tr>
                                                <tr>
                                                    <td>GP email address</td>
                                                    <td>${History.gpEmailAddress}</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="card custom-card mb-4">
                                        <div class="card-header custom-card__header">
                                            <h2 class="healt-history-subtitle">Lifestyle Details</h2>
                                        </div>
                                        <div class="card-body custom-card__body">
                                            <table class="table healt-history-table custom-table mt-0">
                                                <thead>
                                                <tr>
                                                    <th scope="col"></th>
                                                    <th scope="col">You</th>
                                                    <th scope="col">Your Partner (if applicable)</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td>Smoker</td>
                                                    <c:if test="${History.yourSmoker != null}">
                                                        <td>${History.yourSmoker}</td>
                                                    </c:if>
                                                    <c:if test="${History.partnerSmoker != null}">
                                                        <td>${History.partnerSmoker}</td>
                                                    </c:if>

                                                </tr>
                                                <tr>
                                                    <td>Alcohol (units per week)</td>
                                                    <td>${History.yourAlcohol}</td>
                                                    <td>${History.partnerAlcohol}</td>
                                                </tr>
                                                <tr>
                                                    <td>Allergies</td>
                                                    <td>${History.yourAllergies}</td>
                                                    <td>${History.partnerAllergies}</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="card custom-card mb-4">
                                        <div class="card-header custom-card__header">
                                            <h2 class="healt-history-subtitle">Medical History</h2>
                                        </div>
                                        <div class="card-body custom-card__body">
                                            <table class="table healt-history-table custom-table mt-0">
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
                                                    <td>${History.yourIllnesses}</td>
                                                    <td>${History.partnerIllnesses}</td>
                                                </tr>
                                                <tr>
                                                    <td>Family health history: (Please state familial
                                                        relationship)
                                                    </td>
                                                    <td>${History.yourFamilyHealtHistory}</td>
                                                    <td>${History.partnerFamilyHealtHistory}</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="card custom-card mb-4">
                                        <div class="card-header custom-card__header">
                                            <h2 class="healt-history-subtitle">Fertility History</h2>
                                        </div>
                                        <div class="card-body custom-card__body">
                                            <table class="table healt-history-table custom-table mt-0">
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
                                                    <td>${History.yourConceive}</td>
                                                    <td>${History.partnerConceive}</td>
                                                </tr>
                                                <tr>
                                                    <td>Have you had fertility treatment previously?</td>
                                                    <c:if test="${History.youTreatmentPreviously != null}">
                                                        <td>${History.youTreatmentPreviously}</td>
                                                    </c:if>
                                                    <c:if test="${History.partnerTreatmentPreviously != null}">
                                                        <td>${History.partnerTreatmentPreviously}</td>
                                                    </c:if>

                                                </tr>
                                                <tr>
                                                    <td>Have you had fertility assessments/investigations?</td>
                                                    <c:if test="${History.yourAssessments != null}">
                                                        <td>${History.yourAssessments}</td>
                                                    </c:if>
                                                    <c:if test="${History.partnerAssessments != null}">
                                                        <td>${History.partnerAssessments}</td>
                                                    </c:if>
                                                </tr>
                                                <tr>
                                                    <td>Have you/your partner ever been pregnant before?</td>
                                                    <c:if test="${History.youPregnantBefore != null}">
                                                        <td>${History.youPregnantBefore}</td>
                                                    </c:if>
                                                    <c:if test="${History.partnerPregnantBefore != null}">
                                                        <td>${History.partnerPregnantBefore}</td>
                                                    </c:if>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        If so, was conception natural or assisted?
                                                        <small>(Please detail number of pregnancies)</small>
                                                    </td>
                                                    <c:if test="${History.yourNaturalConnection != null}">
                                                        <td>
                                                            <div>${History.yourNaturalConnection}</div>
                                                            <div>${History.yourAssistedConnection}</div>
                                                        </td>
                                                    </c:if>
                                                    <c:if test="${History.partnerNaturalConnection != null}">
                                                        <td>
                                                            <div>${History.partnerNaturalConnection}</div>
                                                            <div>${History.partnerAssistedConnection}</div>
                                                        </td>
                                                    </c:if>
                                                </tr>
                                                <tr>
                                                    <td>What was the outcome? <small>(Please detail
                                                        number)</small></td>

                                                    <td>
                                                        <div>${History.yourFullDelivery}</div>
                                                        <div>${History.yourPreConnection}</div>
                                                        <div>${History.yourMiscarriage}</div>
                                                        <div>${History.yourEctopic}</div>
                                                        <div>${History.yourTermination}</div>
                                                    </td>


                                                    <td>
                                                        <div>${History.partnerFullDelivery}</div>
                                                        <div>${History.partnerPreDelivery}</div>
                                                        <div>${History.partnerMiscarriage}</div>
                                                        <div>${History.partnerEctopic}</div>
                                                        <div>${History.partnerTermination}</div>
                                                    </td>

                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="card custom-card mb-4">
                                        <div class="card-header custom-card__header">
                                            <h2 class="healt-history-subtitle">Enquiry Details</h2>
                                        </div>
                                        <div class="card-body custom-card__body">
                                            <table class="table healt-history-table with-border custom-table mt-0">
                                                <tbody>
                                                <tr>
                                                    <td>I am interested in</td>
                                                    <td>${History.interestedIn}</td>
                                                </tr>
                                                <tr>
                                                    <td>Current fertility treatment <small>(if applicable)</small>
                                                    </td>
                                                    <td>${History.currentFertilityTreatment}</td>
                                                </tr>
                                                <tr>
                                                    <td>Hospital / Clinic name</td>
                                                    <td>${History.hospiotalName}</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </c:if>
        </main>
    </div>

    <div class="modal custom-modal modal-service fade" id="enquiry-modal" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-lg custom-modal">
            <div class="modal-content custom-modal__content bg-default">
                <form method="POST" id="enquiry-form" class="enquiry-form-list">
                    <div class="message-place"></div>
                    <div class="modal-header custom-modal__header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <c:if test="${requestScope.HealthHistoriesList!= null}">
                        <c:forEach items="${requestScope.HealthHistoriesList}" var="HistoryUpdate" end="1" begin="0">

                            <div class="modal-body mh-400 custom-modal__body">
                                <div class="modal-loader-place"></div>
                                <div class="main-content">
                                    <h3 class="custom-modal__sub-title mt-3">Personal Details</h3>
                                    <div class="form-row">
                                        <label for="inputEmail3" class="col-sm-2 col-form-label">Last name:</label>
                                        <div class="form-group col-md-5">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="yourLastName"
                                                    id="yourLastName"
                                                    placeholder="Your Last name"
                                                    value="${HistoryUpdate.yourLastName}"
                                            />
                                        </div>
                                        <div class="form-group col-md-5">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="partnerLastName"
                                                    id="partnerLastName"
                                                    placeholder="Partner Last name"
                                                    value="${HistoryUpdate.partnerLastName}"
                                            />
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <label for="inputEmail3" class="col-sm-2 col-form-label">First name(s):</label>
                                        <div class="form-group col-md-5">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="yourFirstName"
                                                    id="yourFirstName"
                                                    placeholder="Your First name"
                                                    value="${HistoryUpdate.yourFirstName}"
                                            />
                                        </div>
                                        <div class="form-group col-md-5">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="partnerFirstName"
                                                    id="partnerFirstName"
                                                    placeholder="Partner First name"
                                                    value="${HistoryUpdate.partnerFirstName}"
                                            />
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <label for="inputEmail3" class="col-sm-2 col-form-label">Date of birth</label>
                                        <div class="form-group col-md-5">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="yourDateOfBirth"
                                                    id="yourDateOfBirth"
                                                    placeholder="Your Date Of Birth"
                                                    value="${HistoryUpdate.yourDateOfBirth}"
                                            />
                                        </div>
                                        <div class="form-group col-md-5">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="partnerDateOfBirth"
                                                    id="partnerDateOfBirth"
                                                    placeholder="Partner Date Of Birth"
                                                    value="${HistoryUpdate.partnerDateOfBirth}"
                                            />
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <label for="inputEmail3" class="col-sm-2 col-form-label">Gender</label>
                                        <div class="form-group col-md-5">
                                            <div class="form-check form-check-inline">
                                                <input
                                                        class="form-check-input"
                                                        type="radio"
                                                        name="yourGender"
                                                        id="yourMale"
                                                        value="Male"
                                                        <c:if test="${HistoryUpdate.yourGender == 'Male'}">checked</c:if>

                                                />
                                                <label class="form-check-label" for="yourMale">Male</label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                                <input
                                                        class="form-check-input"
                                                        type="radio"
                                                        name="yourGender"
                                                        id="yourFemale"
                                                        value="Female"
                                                        <c:if test="${HistoryUpdate.yourGender == 'Female'}">checked</c:if>
                                                />
                                                <label class="form-check-label" for="yourFemale">Female</label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                                <input
                                                        class="form-check-input"
                                                        type="radio"
                                                        name="yourGender"
                                                        id="yourOther"
                                                        value="Other"
                                                        <c:if test="${HistoryUpdate.yourGender == 'Other'}">checked</c:if>
                                                />
                                                <label class="form-check-label" for="yourOther">Other</label>
                                            </div>


                                        </div>

                                        <div class="form-group col-md-5">

                                            <div class="form-check form-check-inline">
                                                <input
                                                        class="form-check-input"
                                                        type="radio"
                                                        name="partnerGender"
                                                        id="partnerMale"
                                                        value="Male"
                                                        <c:if test="${HistoryUpdate.partnerGender == 'Male'}">checked</c:if>


                                                />
                                                <label class="form-check-label" for="partnerMale">Male</label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                                <input
                                                        class="form-check-input"
                                                        type="radio"
                                                        name="partnerGender"
                                                        id="partnerFemale"
                                                        value="Female"
                                                        <c:if test="${HistoryUpdate.partnerGender == 'Female'}">checked</c:if>

                                                />
                                                <label class="form-check-label"
                                                       for="partnerFemale">Female</label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                                <input
                                                        class="form-check-input"
                                                        type="radio"
                                                        name="partnerGender"
                                                        id="partnerOther"
                                                        value="Other"
                                                        <c:if test="${HistoryUpdate.partnerGender == 'Other'}">checked</c:if>
                                                />
                                                <label class="form-check-label"
                                                       for="partnerFemale">Other</label>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="form-row">
                                        <label for="inputEmail3" class="col-sm-2 col-form-label">Marital status</label>
                                        <div class="form-group col-md-5">
                                            <select
                                                    class="selectpicker custom-selectpick custom-rounded-dropdown mr-0"
                                                    name="yourMartialStatus"
                                                    id="yourMartialStatus"
                                            >
                                                <option value="">Select Marital status
                                                </option>
                                                <option <c:if
                                                        test="${HistoryUpdate.yourMartialStatus == 'Married'}"> selected </c:if>
                                                        value="Married">Married
                                                </option>
                                                <option <c:if
                                                        test="${HistoryUpdate.yourMartialStatus == 'Widowed'}"> selected </c:if>
                                                        value="Widowed">Widowed
                                                </option>
                                                <option  <c:if
                                                        test="${HistoryUpdate.yourMartialStatus == 'Separated'}"> selected </c:if>
                                                        value="Separated">Separated
                                                </option>
                                                <option  <c:if
                                                        test="${HistoryUpdate.yourMartialStatus == 'Divorced'}"> selected </c:if>
                                                        value="Divorced">Divorced
                                                </option>
                                                <option  <c:if
                                                        test="${HistoryUpdate.yourMartialStatus == 'Single'}"> selected </c:if>
                                                        value="Single">Single
                                                </option>
                                            </select>
                                        </div>
                                        <div class="form-group col-md-5">
                                            <select
                                                    class="selectpicker custom-selectpick custom-rounded-dropdown mr-0"
                                                    name="partnerMartialStatus"
                                                    id="partnerMartialStatus"
                                            >
                                                <option value="">Select Marital status
                                                </option>
                                                <option <c:if
                                                        test="${HistoryUpdate.partnerMartialStatus == 'Married'}"> selected </c:if>
                                                        value="Married">Married
                                                </option>
                                                <option <c:if
                                                        test="${HistoryUpdate.partnerMartialStatus == 'Widowed'}"> selected </c:if>
                                                        value="Widowed">Widowed
                                                </option>
                                                <option  <c:if
                                                        test="${HistoryUpdate.partnerMartialStatus == 'Separated'}"> selected </c:if>
                                                        value="Separated">Separated
                                                </option>
                                                <option  <c:if
                                                        test="${HistoryUpdate.partnerMartialStatus == 'Divorced'}"> selected </c:if>
                                                        value="Divorced">Divorced
                                                </option>
                                                <option  <c:if
                                                        test="${HistoryUpdate.partnerMartialStatus == 'Single'}"> selected </c:if>
                                                        value="Single">Single
                                                </option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <label for="inputEmail3" class="col-sm-2 col-form-label">Home address</label>
                                        <div class="form-group col-md-5">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="yourHomeAddress"
                                                    id="yourHomeAddress"
                                                    placeholder="Your Home Address"
                                                    value="${HistoryUpdate.yourHomeAddress}"
                                            />
                                        </div>
                                        <div class="form-group col-md-5">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="partnerHomeAddress"
                                                    id="partnerHomeAddress"
                                                    placeholder="Partner Home Address"
                                                    value="${HistoryUpdate.partnerHomeAddress}"
                                            />
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <label for="inputEmail3" class="col-sm-2 col-form-label">Postcode</label>
                                        <div class="form-group col-md-5">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="yourPostcode"
                                                    id="yourPostcode"
                                                    placeholder="Your Postcode"
                                                    value="${HistoryUpdate.yourPostcode}"
                                            />
                                        </div>
                                        <div class="form-group col-md-5">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="partnerPostcode"
                                                    id="partnerPostcode"
                                                    placeholder="Partner Postcode"
                                                    value="${HistoryUpdate.partnerPostcode}"
                                            />
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <label for="inputEmail3" class="col-sm-2 col-form-label">Mobile phone
                                            number</label>
                                        <div class="form-group col-md-5">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="yourMobilePhone"
                                                    id="yourMobilePhone"
                                                    placeholder="Your phone number"
                                                    value="${HistoryUpdate.yourMobilePhone}"
                                            />
                                        </div>
                                        <div class="form-group col-md-5">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="partnerMobilePhone"
                                                    id="partnerMobilePhone"
                                                    placeholder="Partner phone number"
                                                    value="${HistoryUpdate.partnerMobilePhone}"
                                            />
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <label for="inputEmail3" class="col-sm-2 col-form-label">Email</label>
                                        <div class="form-group col-md-5">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="yourEmail"
                                                    id="yourEmail"
                                                    placeholder="Your email"
                                                    value="${HistoryUpdate.yourEmail}"
                                            />
                                        </div>
                                        <div class="form-group col-md-5">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="partnerEmail"
                                                    id="partnerEmail"
                                                    placeholder="Partner email"
                                                    value="${HistoryUpdate.partnerEmail}"
                                            />
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <label for="inputEmail3" class="col-sm-2 col-form-label">Nationality</label>
                                        <div class="form-group col-md-5">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="yourNationality"
                                                    id="yourNationality"
                                                    placeholder="Your Nationality"
                                                    value="${HistoryUpdate.yourNationality}"
                                            />
                                        </div>
                                        <div class="form-group col-md-5">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="partnerNationality"
                                                    id="partnerNationality"
                                                    placeholder="Partner Nationality"
                                                    value="${HistoryUpdate.partnerNationality}"
                                            />
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <label for="inputEmail3" class="col-sm-2 col-form-label">Employer name</label>
                                        <div class="form-group col-md-5">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="yourEmployerName"
                                                    id="yourEmployerName"
                                                    placeholder="Your Employer name"
                                                    value="${HistoryUpdate.yourEmployerName}"
                                            />
                                        </div>
                                        <div class="form-group col-md-5">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="partnerEmployerName"
                                                    id="partnerEmployerName"
                                                    placeholder="Partner Employer name"
                                                    value="${HistoryUpdate.partnerEmployerName}"
                                            />
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <label class="col-sm-2 col-form-label">Occupation</label>
                                        <div class="form-group col-md-5">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="yourOccupation"
                                                    id="yourOccupation"
                                                    placeholder="Your Occupation"
                                                    value="${HistoryUpdate.yourOccupation}"
                                            />
                                        </div>
                                        <div class="form-group col-md-5">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="partnerOccupation"
                                                    id="partnerOccupation"
                                                    placeholder="Partner Occupation"
                                                    value="${HistoryUpdate.partnerOccupation}"
                                            />
                                        </div>
                                    </div>
                                    <h3 class="custom-modal__sub-title mt-3">Next of Kin</h3>
                                    <div class="form-row">
                                        <label for="nextKinName" class="col-sm-4 col-form-label">Next of kin
                                            name</label>
                                        <div class="form-group col-md-8">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="nextKinName"
                                                    id="nextKinName"
                                                    placeholder="Next of kin name"
                                                    value="${HistoryUpdate.nextKinName}"
                                            />
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <label for="relationship" class="col-sm-4 col-form-label">Relationship to
                                            you:</label>
                                        <div class="form-group col-md-8">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="relationship"
                                                    id="relationship"
                                                    placeholder="Relationship to you"
                                                    value="${HistoryUpdate.relationship}"
                                            />
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <label for="contactNumber" class="col-sm-4 col-form-label">Contact
                                            number</label>
                                        <div class="form-group col-md-8">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="contactNumber"
                                                    id="contactNumber"
                                                    placeholder="Contact number"
                                                    value="${HistoryUpdate.contactNumber}"
                                            />
                                        </div>
                                    </div>

                                    <h3 class="custom-modal__sub-title mt-3">General Practitioner Details</h3>
                                    <div class="form-row">
                                        <label for="gpName" class="col-sm-4 col-form-label">GP name</label>
                                        <div class="form-group col-md-8">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="gpName"
                                                    id="gpName"
                                                    placeholder="GP name"
                                                    value="${HistoryUpdate.gpName}"
                                            />
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <label for="gpAddress" class="col-sm-4 col-form-label">GP address</label>
                                        <div class="form-group col-md-8">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="gpAddress"
                                                    id="gpAddress"
                                                    placeholder="GP address"
                                                    value="${HistoryUpdate.gpAddress}"
                                            />
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <label for="gpContactNumber" class="col-sm-4 col-form-label">GP contact
                                            number</label>
                                        <div class="form-group col-md-8">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="gpContactNumber"
                                                    id="gpContactNumber"
                                                    placeholder="GP contact number"
                                                    value="${HistoryUpdate.gpContactNumber}"
                                            />
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <label for="gpEmailAddress" class="col-sm-4 col-form-label">GP email
                                            address</label>
                                        <div class="form-group col-md-8">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="gpEmailAddress"
                                                    id="gpEmailAddress"
                                                    placeholder="GP email address"
                                                    value="${HistoryUpdate.gpEmailAddress}"
                                            />
                                        </div>
                                    </div>

                                    <h3 class="custom-modal__sub-title mt-3">Lifestyle Details</h3>
                                    <div class="form-row">
                                        <label class="col-sm-2 col-form-label">Smoker</label>
                                        <div class="form-group col-md-5">
                                            <c:set value="${HistoryUpdate.yourSmoker}" var="smokeYour"/>
                                            <c:choose>
                                                <c:when test="${smokeYour == 'Yes' }">
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" name="yourSmoker"
                                                               id="yourYes" checked
                                                               value="Yes"/>
                                                        <label class="form-check-label" for="yourYes">Yes</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" name="yourSmoker"
                                                               id="yourNo"
                                                               value="No"/>
                                                        <label class="form-check-label" for="yourNo">No</label>
                                                    </div>
                                                </c:when>
                                                <c:when test="${smokeYour == 'No' }">
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" name="yourSmoker"
                                                               id="yourYes"
                                                               value="Yes"/>
                                                        <label class="form-check-label" for="yourYes">Yes</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" name="yourSmoker"
                                                               id="yourNo" checked
                                                               value="No"/>
                                                        <label class="form-check-label" for="yourNo">No</label>
                                                    </div>
                                                </c:when>
                                                <c:when test="${smokeYour == null || smokeYour == ''}">
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" name="yourSmoker"
                                                               id="yourYes"
                                                               value="Yes"/>
                                                        <label class="form-check-label" for="yourYes">Yes</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" name="yourSmoker"
                                                               id="yourNo"
                                                               value="No"/>
                                                        <label class="form-check-label" for="yourNo">No</label>
                                                    </div>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                        <div class="form-group col-md-5">
                                            <c:set value="${HistoryUpdate.partnerSmoker}" var="partnerSmoker"/>
                                            <c:choose>
                                                <c:when test="${partnerSmoker == 'Yes' || partnerSmoker == null}">
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio"
                                                               name="partnerSmoker"
                                                               id="partnerYes" checked
                                                               value="Yes"/>
                                                        <label class="form-check-label" for="yourYes">Yes</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio"
                                                               name="partnerSmoker"
                                                               id="partnerNo"
                                                               value="No"/>
                                                        <label class="form-check-label" for="yourNo">No</label>
                                                    </div>
                                                </c:when>
                                                <c:when test="${partnerSmoker == 'No' }">
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio"
                                                               name="partnerSmoker"
                                                               id="partnerYes"
                                                               value="Yes"/>
                                                        <label class="form-check-label" for="yourYes">Yes</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio"
                                                               name="partnerSmoker"
                                                               id="partnerNo" checked
                                                               value="No"/>
                                                        <label class="form-check-label" for="yourNo">No</label>
                                                    </div>
                                                </c:when>
                                                <c:when test="${partnerSmoker == null || partnerSmoker == ''}">
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio"
                                                               name="partnerSmoker"
                                                               id="partnerYes"
                                                               value="Yes"/>
                                                        <label class="form-check-label" for="yourYes">Yes</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio"
                                                               name="partnerSmoker"
                                                               id="partnerNo"
                                                               value="No"/>
                                                        <label class="form-check-label" for="yourNo">No</label>
                                                    </div>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <label class="col-sm-2 col-form-label">Alcohol (units per week)</label>
                                        <div class="form-group col-md-5">
                                            <div class="row">
                                                <div class="col-md-8">
                                                    <input
                                                            type="text"
                                                            class="form-control custom-rounded-input"
                                                            name="yourAlcohol"
                                                            id="yourAlcohol"
                                                            placeholder="For you"
                                                            value="${HistoryUpdate.yourAlcohol}"
                                                    />
                                                </div>
                                                <div class="col-md-4">per week</div>
                                            </div>
                                        </div>
                                        <div class="form-group col-md-5">
                                            <div class="row">
                                                <div class="col-md-8">
                                                    <input
                                                            type="text"
                                                            class="form-control custom-rounded-input"
                                                            name="partnerAlcohol"
                                                            id="partnerAlcohol"
                                                            placeholder="For Partner"
                                                            value="${HistoryUpdate.partnerAlcohol}"
                                                    />
                                                </div>
                                                <div class="col-md-4">per week</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <label class="col-sm-2 col-form-label">Allergies</label>
                                        <div class="form-group col-md-5">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="yourAllergies"
                                                    id="yourAllergies"
                                                    placeholder="For You"
                                                    value="${HistoryUpdate.yourAllergies}"
                                            />
                                        </div>
                                        <div class="form-group col-md-5">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="partnerAllergies"
                                                    id="partnerAllergies"
                                                    placeholder="For Partner"
                                                    value="${HistoryUpdate.partnerAllergies}"
                                            />
                                        </div>
                                    </div>

                                    <h3 class="custom-modal__sub-title mt-3">Medical History</h3>
                                    <div class="form-row">
                                        <label class="col-sm-2 col-form-label">Details of previous operations or serious
                                            illnesses</label>
                                        <div class="form-group col-md-5">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="yourIllnesses"
                                                    id="yourIllnesses"
                                                    placeholder="For You"
                                                    value="${HistoryUpdate.yourIllnesses}"
                                            />
                                        </div>
                                        <div class="form-group col-md-5">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="partnerIllnesses"
                                                    id="partnerIllnesses"
                                                    placeholder="For Partner"
                                                    value="${HistoryUpdate.partnerIllnesses}"
                                            />
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <label class="col-sm-2 col-form-label"
                                        >Family health history: (Please state familial relationship)</label
                                        >
                                        <div class="form-group col-md-5">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="yourFamilyHealtHistory"
                                                    id="yourFamilyHealtHistory"
                                                    placeholder="Your"
                                                    value="${HistoryUpdate.yourFamilyHealtHistory}"
                                            />
                                        </div>
                                        <div class="form-group col-md-5">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="partnerFamilyHealtHistory"
                                                    id="partnerFamilyHealtHistory"
                                                    placeholder="Partner"
                                                    value="${HistoryUpdate.partnerFamilyHealtHistory}"
                                            />
                                        </div>
                                    </div>

                                    <h3 class="custom-modal__sub-title mt-3">Fertility History</h3>
                                    <div class="form-row">
                                        <label class="col-sm-2 col-form-label">How long have you been trying to
                                            conceive?</label>
                                        <div class="form-group col-md-5">

                                            <div class="row">
                                                <div class="col-md-7">
                                                    <input
                                                            type="text"
                                                            class="form-control custom-rounded-input"
                                                            name="yourConceive"
                                                            id="yourConceive"
                                                            placeholder="For you"
                                                            value="${HistoryUpdate.yourConceive}"
                                                    />
                                                </div>
                                                <div class="col-md-5"><small>years/months</small></div>
                                                    <%--<div class="col-md-12"><small>(delete as applicable)</small></div>--%>
                                            </div>
                                        </div>
                                        <div class="form-group col-md-5">
                                            <div class="row">
                                                <div class="col-md-7">
                                                    <input
                                                            type="text"
                                                            class="form-control custom-rounded-input"
                                                            name="partnerConceive"
                                                            id="partnerConceive"
                                                            placeholder="For partner"
                                                            value="${HistoryUpdate.partnerConceive}"
                                                    />
                                                </div>
                                                <div class="col-md-5"><small>years/months</small></div>
                                                    <%--<div class="col-md-12"><small>(delete as applicable)</small></div>--%>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <label class="col-sm-2 col-form-label">Have you had fertility treatment
                                            previously?</label>
                                        <div class="form-group col-md-5">
                                            <c:set value="${HistoryUpdate.youTreatmentPreviously}" var="treatment"/>
                                            <c:choose>
                                                <c:when test="${treatment == 'Yes'}">
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="youTreatmentPreviously"
                                                                id="youTreatmentPreviouslyYes"
                                                                value="Yes" checked
                                                        />
                                                        <label class="form-check-label"
                                                               for="youTreatmentPreviouslyYes">Yes</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="youTreatmentPreviously"
                                                                id="youTreatmentPreviouslyNo"
                                                                value="No"
                                                        />
                                                        <label class="form-check-label"
                                                               for="youTreatmentPreviouslyNo">No</label>
                                                    </div>
                                                </c:when>
                                                <c:when test="${treatment == 'No'}">
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="youTreatmentPreviously"
                                                                id="youTreatmentPreviouslyYes"
                                                                value="Yes"
                                                        />
                                                        <label class="form-check-label"
                                                               for="youTreatmentPreviouslyYes">Yes</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="youTreatmentPreviously"
                                                                id="youTreatmentPreviouslyNo"
                                                                value="No" checked
                                                        />
                                                        <label class="form-check-label"
                                                               for="youTreatmentPreviouslyNo">No</label>
                                                    </div>
                                                </c:when>
                                                <c:when test="${treatment == null || treatment == ''}">
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="youTreatmentPreviously"
                                                                id="youTreatmentPreviouslyYes"
                                                                value="Yes"
                                                        />
                                                        <label class="form-check-label"
                                                               for="youTreatmentPreviouslyYes">Yes</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="youTreatmentPreviously"
                                                                id="youTreatmentPreviouslyNo"
                                                                value="No"
                                                        />
                                                        <label class="form-check-label"
                                                               for="youTreatmentPreviouslyNo">No</label>
                                                    </div>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                        <div class="form-group col-md-5">

                                            <c:set value="${HistoryUpdate.partnerTreatmentPreviously}"
                                                   var="partnerTreat"/>
                                            <c:choose>
                                                <c:when test="${partnerTreat == 'Yes'}">
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="partnerTreatmentPreviously"
                                                                id="partnerTreatmentPreviouslyYes"
                                                                value="Yes" checked
                                                        />
                                                        <label class="form-check-label"
                                                               for="partnerTreatmentPreviouslyYes">Yes</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="partnerTreatmentPreviously"
                                                                id="partnerTreatmentPreviouslyNo"
                                                                value="No"
                                                        />
                                                        <label class="form-check-label"
                                                               for="partnerTreatmentPreviouslyNo">No</label>
                                                    </div>
                                                </c:when>
                                                <c:when test="${partnerTreat == 'No'}">
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="partnerTreatmentPreviously"
                                                                id="partnerTreatmentPreviouslyYes"
                                                                value="Yes"
                                                        />
                                                        <label class="form-check-label"
                                                               for="partnerTreatmentPreviouslyYes">Yes</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="partnerTreatmentPreviously"
                                                                id="partnerTreatmentPreviouslyNo"
                                                                value="No" checked
                                                        />
                                                        <label class="form-check-label"
                                                               for="partnerTreatmentPreviouslyNo">No</label>
                                                    </div>
                                                </c:when>
                                                <c:when test="${partnerTreat == null  || partnerTreatv == ''}">
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="partnerTreatmentPreviously"
                                                                id="partnerTreatmentPreviouslyYes"
                                                                value="Yes"
                                                        />
                                                        <label class="form-check-label"
                                                               for="partnerTreatmentPreviouslyYes">Yes</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="partnerTreatmentPreviously"
                                                                id="partnerTreatmentPreviouslyNo"
                                                                value="No"
                                                        />
                                                        <label class="form-check-label"
                                                               for="partnerTreatmentPreviouslyNo">No</label>
                                                    </div>

                                                </c:when>
                                            </c:choose>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <label class="col-sm-4 col-form-label">Have you had fertility
                                            assessments/investigations?</label>
                                        <div class="form-group col-md-4">
                                            <c:set var="Fertility" value="${HistoryUpdate.yourAssessments}"/>
                                            <c:choose>
                                                <c:when test="${Fertility == 'Yes'}">
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="yourAssessments"
                                                                id="yourAssessmentsYes"
                                                                value="Yes" checked
                                                        />
                                                        <label class="form-check-label"
                                                               for="yourAssessmentsYes">Yes</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="yourAssessments"
                                                                id="yourAssessmentsNo"
                                                                value="No"
                                                        />
                                                        <label class="form-check-label"
                                                               for="yourAssessmentsNo">No</label>
                                                    </div>
                                                    <div><small>(If Yes, please submit the reports)</small></div>
                                                </c:when>
                                                <c:when test="${Fertility == 'No'}">
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="yourAssessments"
                                                                id="yourAssessmentsYes"
                                                                value="Yes"
                                                        />
                                                        <label class="form-check-label"
                                                               for="yourAssessmentsYes">Yes</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="yourAssessments"
                                                                id="yourAssessmentsNo"
                                                                value="No" checked
                                                        />
                                                        <label class="form-check-label"
                                                               for="yourAssessmentsNo">No</label>
                                                    </div>
                                                    <div><small>(If Yes, please submit the reports)</small></div>
                                                </c:when>
                                                <c:when test="${Fertility == null  || Fertility == ''}">
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="yourAssessments"
                                                                id="yourAssessmentsYes"
                                                                value="Yes"
                                                        />
                                                        <label class="form-check-label"
                                                               for="yourAssessmentsYes">Yes</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="yourAssessments"
                                                                id="yourAssessmentsNo"
                                                                value="No"
                                                        />
                                                        <label class="form-check-label"
                                                               for="yourAssessmentsNo">No</label>
                                                    </div>
                                                    <div><small>(If Yes, please submit the reports)</small></div>
                                                </c:when>
                                            </c:choose>

                                        </div>
                                        <div class="form-group col-md-4">
                                            <c:set value="${HistoryUpdate.partnerAssessments}" var="partnerAss"/>
                                            <c:choose>
                                                <c:when test="${partnerAss == 'Yes'}">
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="partnerAssessments"
                                                                id="partnerAssessmentsYes"
                                                                value="Yes" checked
                                                        />
                                                        <label class="form-check-label"
                                                               for="partnerAssessmentsYes">Yes</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="partnerAssessments"
                                                                id="partnerAssessmentsNo"
                                                                value="No"
                                                        />
                                                        <label class="form-check-label"
                                                               for="partnerAssessmentsNo">No</label>
                                                    </div>
                                                    <div><small>(If Yes, please submit the reports)</small></div>
                                                </c:when>
                                                <c:when test="${partnerAss == 'No'}">
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="partnerAssessments"
                                                                id="partnerAssessmentsYes"
                                                                value="Yes"
                                                        />
                                                        <label class="form-check-label"
                                                               for="partnerAssessmentsYes">Yes</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="partnerAssessments"
                                                                id="partnerAssessmentsNo"
                                                                value="No" checked
                                                        />
                                                        <label class="form-check-label"
                                                               for="partnerAssessmentsNo">No</label>
                                                    </div>
                                                    <div><small>(If Yes, please submit the reports)</small></div>
                                                </c:when>
                                                <c:when test="${partnerAss == null || partnerAss == ''}">
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="partnerAssessments"
                                                                id="partnerAssessmentsYes"
                                                                value="Yes"
                                                        />
                                                        <label class="form-check-label"
                                                               for="partnerAssessmentsYes">Yes</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="partnerAssessments"
                                                                id="partnerAssessmentsNo"
                                                                value="No"
                                                        />
                                                        <label class="form-check-label"
                                                               for="partnerAssessmentsNo">No</label>
                                                    </div>
                                                    <div><small>(If Yes, please submit the reports)</small></div>
                                                </c:when>
                                            </c:choose>

                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <label class="col-sm-4 col-form-label">Have you/your partner ever been pregnant
                                            before?</label>
                                        <div class="form-group col-md-4">
                                            <c:set var="partner" value="${HistoryUpdate.youPregnantBefore}"/>
                                            <c:choose>
                                                <c:when test="${partner == 'Yes'}">
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="youPregnantBefore"
                                                                id="youPregnantBeforeYes"
                                                                value="Yes" checked
                                                        />
                                                        <label class="form-check-label"
                                                               for="youPregnantBeforeYes">Yes</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="youPregnantBefore"
                                                                id="youPregnantBeforeNo"
                                                                value="No"
                                                        />
                                                        <label class="form-check-label"
                                                               for="youPregnantBeforeNo">No</label>
                                                    </div>
                                                    <div><%--<small>(If No, go to section 7)</small>--%></div>
                                                </c:when>
                                                <c:when test="${partner == 'No'}">
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="youPregnantBefore"
                                                                id="youPregnantBeforeYes"
                                                                value="Yes"
                                                        />
                                                        <label class="form-check-label"
                                                               for="youPregnantBeforeYes">Yes</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="youPregnantBefore"
                                                                id="youPregnantBeforeNo"
                                                                value="No" checked
                                                        />
                                                        <label class="form-check-label"
                                                               for="youPregnantBeforeNo">No</label>
                                                    </div>
                                                    <div><%--<small>(If No, go to section 7)</small>--%></div>
                                                </c:when>
                                                <c:when test="${partner == null || partner == ''}">
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="youPregnantBefore"
                                                                id="youPregnantBeforeYes"
                                                                value="Yes"
                                                        />
                                                        <label class="form-check-label"
                                                               for="youPregnantBeforeYes">Yes</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="youPregnantBefore"
                                                                id="youPregnantBeforeNo"
                                                                value="No"
                                                        />
                                                        <label class="form-check-label"
                                                               for="youPregnantBeforeNo">No</label>
                                                    </div>
                                                    <div><%--<small>(If No, go to section 7)</small>--%></div>
                                                </c:when>
                                            </c:choose>

                                        </div>
                                        <div class="form-group col-md-4">
                                            <c:set var="partners" value="${HistoryUpdate.partnerPregnantBefore}"/>
                                            <c:choose>
                                                <c:when test="${partners == 'Yes'}">
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="partnerPregnantBefore"
                                                                id="youPregnantBeforeYes"
                                                                value="Yes" checked
                                                        />
                                                        <label class="form-check-label"
                                                               for="youPregnantBeforeYes">Yes</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="partnerPregnantBefore"
                                                                id="youPregnantBeforeNo"
                                                                value="No"
                                                        />
                                                        <label class="form-check-label"
                                                               for="youPregnantBeforeNo">No</label>
                                                    </div>
                                                    <div><%--<small>(If No, go to section 7)</small>--%></div>
                                                </c:when>
                                                <c:when test="${partners == 'No'}">
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="partnerPregnantBefore"
                                                                id="youPregnantBeforeYes"
                                                                value="Yes"
                                                        />
                                                        <label class="form-check-label"
                                                               for="youPregnantBeforeYes">Yes</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="partnerPregnantBefore"
                                                                id="youPregnantBeforeNo"
                                                                value="No" checked
                                                        />
                                                        <label class="form-check-label"
                                                               for="youPregnantBeforeNo">No</label>
                                                    </div>
                                                    <div><%--<small>(If No, go to section 7)</small>--%></div>
                                                </c:when>
                                                <c:when test="${partners == null || partners == ''}">
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="partnerPregnantBefore"
                                                                id="youPregnantBeforeYes"
                                                                value="Yes"
                                                        />
                                                        <label class="form-check-label"
                                                               for="youPregnantBeforeYes">Yes</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input
                                                                class="form-check-input"
                                                                type="radio"
                                                                name="partnerPregnantBefore"
                                                                id="youPregnantBeforeNo"
                                                                value="No"
                                                        />
                                                        <label class="form-check-label"
                                                               for="youPregnantBeforeNo">No</label>
                                                    </div>
                                                    <div><%--<small>(If No, go to section 7)</small>--%></div>
                                                </c:when>
                                            </c:choose>

                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <label class="col-sm-2 col-form-label"
                                        >If so, was conception natural or assisted?
                                            <small>(Please detail number of pregnancies)</small></label
                                        >
                                        <div class="form-group col-md-5">
                                            <div class="row mb-2">
                                                <div class="col-md-6"><small>Natural conception</small></div>
                                                <div class="col-md-6">
                                                    <input
                                                            type="text"
                                                            class="form-control custom-rounded-input"
                                                            name="yourNaturalConnection"
                                                            id="yourNaturalConnection"
                                                            placeholder="For you"
                                                            value="${HistoryUpdate.yourNaturalConnection}"
                                                    />
                                                </div>
                                            </div>
                                            <div class="row mb-2">
                                                <div class="col-md-6"><small>Assisted conception</small></div>
                                                <div class="col-md-6">
                                                    <input
                                                            type="text"
                                                            class="form-control custom-rounded-input"
                                                            name="yourAssistedConnection"
                                                            id="yourAssistedConnection"
                                                            placeholder="For you"
                                                            value="${HistoryUpdate.yourAssistedConnection}"
                                                    />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group col-md-5">
                                            <div class="row mb-2">
                                                <div class="col-md-6"><small>Natural conception</small></div>
                                                <div class="col-md-6">
                                                    <input
                                                            type="text"
                                                            class="form-control custom-rounded-input"
                                                            name="partnerNaturalConnection"
                                                            id="partnerNaturalConnection"
                                                            placeholder="For Partner"
                                                            value="${HistoryUpdate.partnerNaturalConnection}"
                                                    />
                                                </div>
                                            </div>
                                            <div class="row mb-2">
                                                <div class="col-md-6"><small>Assisted conception</small></div>
                                                <div class="col-md-6">
                                                    <input
                                                            type="text"
                                                            class="form-control custom-rounded-input"
                                                            name="partnerAssistedConnection"
                                                            id="partnerAssistedConnection"
                                                            placeholder="For Partner"
                                                            value="${HistoryUpdate.partnerAssistedConnection}"
                                                    />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <label class="col-sm-2 col-form-label"
                                        >What was the outcome? <small>(Please detail number)</small></label
                                        >
                                        <div class="form-group col-md-5">
                                            <div class="row mb-2">
                                                <div class="col-md-6"><small>Full-term delivery</small></div>
                                                <div class="col-md-6">
                                                    <input
                                                            type="text"
                                                            class="form-control custom-rounded-input"
                                                            name="yourFullDelivery"
                                                            id="yourFullDelivery"
                                                            placeholder="You"
                                                            value="${HistoryUpdate.yourFullDelivery}"
                                                    />
                                                </div>
                                            </div>
                                            <div class="row mb-2">
                                                <div class="col-md-6"><small>Pre-term delivery</small></div>
                                                <div class="col-md-6">
                                                    <input
                                                            type="text"
                                                            class="form-control custom-rounded-input"
                                                            name="yourPreConnection"
                                                            id="yourPreConnection"
                                                            placeholder="You"
                                                            value="${HistoryUpdate.yourPreConnection}"
                                                    />
                                                </div>
                                            </div>
                                            <div class="row mb-2">
                                                <div class="col-md-6"><small>Miscarriage</small></div>
                                                <div class="col-md-6">
                                                    <input
                                                            type="text"
                                                            class="form-control custom-rounded-input"
                                                            name="yourMiscarriage"
                                                            id="yourMiscarriage"
                                                            placeholder="You"
                                                            value="${HistoryUpdate.yourMiscarriage}"
                                                    />
                                                </div>
                                            </div>
                                            <div class="row mb-2">
                                                <div class="col-md-6"><small>Ectopic</small></div>
                                                <div class="col-md-6">
                                                    <input
                                                            type="text"
                                                            class="form-control custom-rounded-input"
                                                            name="yourEctopic"
                                                            id="yourEctopic"
                                                            placeholder="You"
                                                            value="${HistoryUpdate.yourEctopic}"
                                                    />
                                                </div>
                                            </div>
                                            <div class="row mb-2">
                                                <div class="col-md-6"><small>Termination</small></div>
                                                <div class="col-md-6">
                                                    <input
                                                            type="text"
                                                            class="form-control custom-rounded-input"
                                                            name="yourTermination"
                                                            id="yourTermination"
                                                            placeholder="You"
                                                            value="${HistoryUpdate.yourTermination}"
                                                    />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group col-md-5">
                                            <div class="row mb-2">
                                                <div class="col-md-6"><small>Full-term delivery</small></div>
                                                <div class="col-md-6">
                                                    <input
                                                            type="text"
                                                            class="form-control custom-rounded-input"
                                                            name="partnerFullDelivery"
                                                            id="partnerFullDelivery"
                                                            placeholder="Partner"
                                                            value="${HistoryUpdate.partnerFullDelivery}"
                                                    />
                                                </div>
                                            </div>
                                            <div class="row mb-2">
                                                <div class="col-md-6"><small>Pre-term delivery</small></div>
                                                <div class="col-md-6">
                                                    <input
                                                            type="text"
                                                            class="form-control custom-rounded-input"
                                                            name="partnerPreDelivery"
                                                            id="partnerPreDelivery"
                                                            placeholder="Partner"
                                                            value="${HistoryUpdate.partnerPreDelivery}"
                                                    />
                                                </div>
                                            </div>
                                            <div class="row mb-2">
                                                <div class="col-md-6"><small>Miscarriage</small></div>
                                                <div class="col-md-6">
                                                    <input
                                                            type="text"
                                                            class="form-control custom-rounded-input"
                                                            name="partnerMiscarriage"
                                                            id="partnerMiscarriage"
                                                            placeholder="Partner"
                                                            value="${HistoryUpdate.partnerMiscarriage}"
                                                    />
                                                </div>
                                            </div>
                                            <div class="row mb-2">
                                                <div class="col-md-6"><small>Ectopic</small></div>
                                                <div class="col-md-6">
                                                    <input
                                                            type="text"
                                                            class="form-control custom-rounded-input"
                                                            name="partnerEctopic"
                                                            id="partnerEctopic"
                                                            placeholder="Partner"
                                                            value="${HistoryUpdate.partnerEctopic}"
                                                    />
                                                </div>
                                            </div>
                                            <div class="row mb-2">
                                                <div class="col-md-6"><small>Termination</small></div>
                                                <div class="col-md-6">
                                                    <input
                                                            type="text"
                                                            class="form-control custom-rounded-input"
                                                            name="partnerTermination"
                                                            id="partnerTermination"
                                                            placeholder="Partner"
                                                            value="${HistoryUpdate.partnerTermination}"
                                                    />
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <h3 class="custom-modal__sub-title mt-3">Fertility History</h3>
                                    <div class="form-row">
                                        <label class="col-sm-4 col-form-label">I am interested in</label>
                                        <div class="form-group col-md-8">
                                            <select
                                                    class="selectpicker custom-selectpick custom-rounded-dropdown mr-0"
                                                    name="interestedIn"
                                                    id="interestedIn"
                                            >
                                                <option value="${HistoryUpdate.interestedIn}"
                                                        selected> ${HistoryUpdate.interestedIn}</option>
                                                <option value="Fertility assessment">Fertility assessment</option>
                                                <option value="Fertility treatment">Fertility treatment</option>
                                                <option value="Fertility wellbeing support">Fertility wellbeing
                                                    support
                                                </option>
                                                <option value="Egg/sperm preservation">Egg/sperm preservation</option>
                                                <option value="Egg/sperm donor">Egg/sperm donor</option>
                                                <option value="Surrogacy">Surrogacy</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <label class="col-sm-4 col-form-label"
                                        >Current fertility treatment <small>(if applicable)</small></label
                                        >
                                        <div class="form-group col-md-8">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="currentFertilityTreatment"
                                                    id="currentFertilityTreatment"
                                                    placeholder="Current fertility treatment"
                                                    value="${HistoryUpdate.currentFertilityTreatment}"
                                            />
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <label class="col-sm-4 col-form-label">Hospital / Clinic name</label>
                                        <div class="form-group col-md-8">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    name="hospiotalName"
                                                    id="hospiotalName"
                                                    placeholder="Hospital / Clinic name"
                                                    value="${HistoryUpdate.hospiotalName}"
                                            />
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
                        </c:forEach>
                    </c:if>
                </form>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>

</div>

<script>
    var send_enquiry_data_ajax = '/employee/SendEnquiryDataAjax';
</script>

<script>
    var selfId = ${requestScope.EmployeeObject.id};
    var roomIdFromFrontUser =  ${requestScope.EmployeeObject.id};
    var sessionRoomFromFront = "jok2f4fud_9";

</script>
<jsp:include page="${root}/includes/BundleJsInclude.jsp"/>
<jsp:include page="${root}/includes/FrontFooter.jsp"/>
<%--<jsp:include page="includes/CookieManagerUser.jsp"/>--%>
<script>
    $(document).on('submit', '#enquiry-form-insert', function (e) {
        e.preventDefault();

        $('#enquiry-form .message-place').html('');

        const formData = $('#enquiry-form-insert').serialize();

        if ($(':input[required]') == '') {
            $('#enquiry-form .message-place').html('<div className="alert alert-danger">Please fill all required places</div>')
            return false;
        }

        $.ajax({
            url: send_enquiry_data_ajax,
            type: 'POST',
            data: formData,
            dataType: 'text',
            beforeSend: function () {
                $('#appointement-invitation').find('.modal-loader-place').html('<div class="lds-ring"><div></div><div></div><div></div><div></div></div>');
            },
            success: function (data) {
                const newData = JSON.parse(data);

                $('#enquiry-form .message-place').html('');

                $('#appointement-invitation').find('.modal-loader-place').html('');

                if (newData.status === 'success') {
                    $('#enquiry-modal').modal('hide');
                    $('.order-testing-toast').addClass('success').find('.toast-body').html(newData.message);
                    $('#enquiry-form-block').remove();
                    $('.order-testing-toast').toast('show');

                    location.reload();

                    //send request to admin
                } else {
                    $('#enquiry-form .message-place').html('<div className="alert alert-danger">Please fill all required places</div>')
                }
            },
            error: function (data) {
                $('#appointement-invitation').find('.modal-loader-place').html('');
            }
        });

    });
    if ( document.getElementById('click-to-refresh')) {
        document.getElementById('click-to-refresh').addEventListener('click', function () {
            console.log('before close')
            $("#enquiry-modal-insert").on("hidden.bs.modal", function () {
                console.log('after close')
                setTimeout(function () {
                    location.reload()
                }, 1000)
            });
        })
    }
</script>
</body>
</html>

