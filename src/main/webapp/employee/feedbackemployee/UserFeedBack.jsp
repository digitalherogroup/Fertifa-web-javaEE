<%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 1/29/2020
  Time: 3:01 AM
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
    <c:set value="${pageContext.request.contextPath}" var="root"/>
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
                <h1 class="content-title">Submit feedback</h1>
                <h5 class="content-subtitle">Let us know your thoughts and help us improve our services.</h5>
                <div class="row">
                    <div class="col-md-12">
                        <div class="card custom-card">
                            <div class="card-body custom-card__body">
                                <div class="row">
                                    <div class="col-md-6">
                                        <form action="${root}/employee/submitfeedback" method="post"
                                              class="h-100 d-flex flex-column">
                                            <ul class="submit-feedback-list">

                                                <c:if test="${requestScope.FeedBackQuestionList!= null}">
                                                    <c:if test="${requestScope.SizeFeedback !=0 }">
                                                        <c:forEach items="${requestScope.FeedBackQuestionList}"
                                                                   var="feedBackQuestions" varStatus="TheCount" step="1"
                                                                   end="8">

                                                            <li class="submit-feedback-list__item">
                                                                <h3 class="submit-feedback-list__title">${TheCount.count}) ${feedBackQuestions.feedBackQuestion}</h3>
                                                                <div class="submit-feedback-list__checks-group pl-3">

                                                                    <div class="form-check form-check-inline">

                                                                        <input
                                                                                class="form-check-input"
                                                                                type="radio"
                                                                                name="likeApplication-${feedBackQuestions.id}"
                                                                                id="likeApplicationYes-${feedBackQuestions.id}"
                                                                                value="1"
                                                                                checked
                                                                        />
                                                                        <label class="form-check-label  font-13"
                                                                               for="likeApplicationYes-${feedBackQuestions.id}"
                                                                        >Disagree</label
                                                                        >
                                                                    </div>
                                                                    <div class="form-check form-check-inline">
                                                                        <input
                                                                                class="form-check-input"
                                                                                type="radio"
                                                                                name="likeApplication-${feedBackQuestions.id}"
                                                                                id="likeApplicationNo-${feedBackQuestions.id}"
                                                                                value="2"
                                                                        />
                                                                        <label class="form-check-label  font-13"
                                                                               for="likeApplicationNo-${feedBackQuestions.id}"
                                                                        >Neither agree or disagree</label
                                                                        >
                                                                    </div>
                                                                    <div class="form-check form-check-inline">
                                                                        <input
                                                                                class="form-check-input"
                                                                                type="radio"
                                                                                name="likeApplication-${feedBackQuestions.id}"
                                                                                id="likeApplicationMaybe-${feedBackQuestions.id}"
                                                                                value="3"
                                                                        />
                                                                        <label class="form-check-label  font-13"
                                                                               for="likeApplicationMaybe-${feedBackQuestions.id}"
                                                                        >Agree</label
                                                                        >
                                                                    </div>


                                                                </div>
                                                            </li>
                                                        </c:forEach>
                                                    </c:if>
                                                </c:if>
                                            </ul>

                                            <c:if test="${requestScope.FeedBackQuestionList!= null}">
                                                <c:forEach items="${requestScope.FeedBackQuestionList}"
                                                           var="feedBackTran">
                                                    <c:set var="feedback" value="${feedBackTran.id}"/>
                                                </c:forEach>
                                            </c:if>

                                            <c:choose>
                                                <c:when test="${feedback != null}">
                                                    <button type="submit "
                                                            class="btn btn-secondary mt-auto custom-rounded-btn light-green">
                                                        Submit
                                                    </button>
                                                </c:when>
                                                <c:otherwise>

                                                </c:otherwise>
                                            </c:choose>


                                        </form>
                                    </div>
                                    <div class="col-md-6 submit-feedback-form">
                                        <form action="${root}/employee/employee-feed-send-question" method="post">
                                            <div class="d-flex mb-4">
                                                <span class="rate-title pr-3">Rate: </span>
                                                <div class="feedback-result__stars star-filter-submit"
                                                     role="optgroup">
                                                    <i
                                                            data-rating="1"
                                                            tabindex="0"
                                                            id="rating-1"
                                                            role="radio"
                                                            class="fas fa-star rating-star-sb selected feedback-result__stars-unrated"
                                                    ></i>
                                                    <i
                                                            data-rating="2"
                                                            tabindex="0"
                                                            id="rating-2"
                                                            role="radio"
                                                            class="fas fa-star rating-star-sb feedback-result__stars-unrated"
                                                    ></i>
                                                    <i
                                                            data-rating="3"
                                                            tabindex="0"
                                                            id="rating-3"
                                                            role="radio"
                                                            class="fas fa-star rating-star-sb feedback-result__stars-unrated"
                                                    ></i>
                                                    <i
                                                            data-rating="4"
                                                            tabindex="0"
                                                            id="rating-4"
                                                            role="radio"
                                                            class="fas fa-star rating-star-sb feedback-result__stars-unrated"
                                                    ></i>
                                                    <i
                                                            data-rating="5"
                                                            tabindex="0"
                                                            id="rating-5"
                                                            role="radio"
                                                            class="fas fa-star rating-star-sb feedback-result__stars-unrated"
                                                    ></i>
                                                </div>
                                                <input type="hidden" id="ratingValue" name="ratingValue" value="1"/>
                                            </div>
                                            <div class="d-flex flex-column">
                                                <input
                                                        type="text"
                                                        name="subject"
                                                        class="custom-rounded-input mb-3"
                                                        placeholder="Subject"
                                                        value=""
                                                        required
                                                />
                                                <textarea
                                                        name="details"
                                                        class="custom-rounded-input mb-5"
                                                        placeholder="Required details"
                                                        required
                                                ></textarea>
                                                <button type="submit"
                                                        class="btn btn-secondary custom-rounded-btn light-green">
                                                    Submit
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <jsp:include page="${root}/includes/Toaster.jsp"/>
            </div>

        </main>
    </div>

    <jsp:include page="${root}/includes/NotificationBlogUser.jsp"/>
</div>

<script>
    var show_service_data_ajax = "show_service_data_ajax";
</script>

<script>
    var selfId = ${requestScope.EmployeeObject.id};
    var roomIdFromFrontUser = ${requestScope.EmployeeObject.id};
    var sessionRoomFromFront = "jok2f4fud_9";

</script>

<jsp:include page="${root}/includes/BundleJsInclude.jsp"/>
<%--<jsp:include page="../../includes/CookieManagerUser.jsp"/>--%>
<jsp:include page="${root}/includes/FooterZnglik.jsp"/>
<jsp:include page="${root}/includes/FrontFooter.jsp"/>
</body>


