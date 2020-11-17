<%--
  Created by IntelliJ IDEA.
  User: Shant
  Date: 1/14/2020
  Time: 10:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html >
<head>
    <c:set value="${pageContext.request.contextPath}" var="root"/>
    <%
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader ("Expires", 0);
        if(session.getAttribute("employerEmail")==null){
            response.sendRedirect(request.getContextPath() +  "/employer/employer-dashboard");

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
            <jsp:include page="${root}/includes/CompanySideBar.jsp"/>
        </div>
        <jsp:include page="${root}/includes/MobileSideBar.jsp"/>
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
                <h1 class="content-title">Employer feedback</h1>
                <div class="row">
                    <div class="col-md-6">
                        <div class="feedback-result">
                            <c:set var="Rating" value="${requestScope.feedbackrating}" scope="request"/>
                            <%!
                                static float starsShine;
                                static float starsNoShine;
                                static float countStars;
                            %>
                            <%
                                String floatString = String.valueOf(request.getAttribute("Rating"));
                                if (floatString.toLowerCase().equals("nan")) {
                                    floatString = "0";
                                }
                                System.out.println(floatString);
                                starsShine = Float.parseFloat(floatString);
                                System.out.println("starsShine " + starsShine);
                                starsNoShine = 0;
                                countStars = 0;
                            %>

                            <%!
                                float countStars(float Stars) {
                                    float looper = 5 - Stars;
                                    if (looper % 2.0 == 0.0) {
                                        looper = 0;
                                    } else {
                                        looper = 0.5f;
                                    }
                                    return looper;

                                }
                            %>
                            <div class="feedback-result__stars">
                                <% float looper = 0;
                                    countStars = 0;
                                    if (starsShine % 10 == 0.0) {
                                        looper = starsShine - 1;
                                    } else {
                                        looper = (starsShine + 1);
                                    }
                                %>
                                <% for (int i = 1; i <= starsShine; i++) {%>
                                <span><i class="fas fa-star feedback-result__stars-rated"></i></span>
                                <%}%>

                                <% if (starsShine % 1 != 0) {%>
                                <span><i class="fas fa-star-half-alt feedback-result__stars-rated"></i></span>
                                <%}%>
                                <%
                                    float incuzumes = starsShine % 2;
                                    int emptyStar = 0;
                                    emptyStar = (int) (5 - starsShine);
                                %>
                                <% for (int i = 1; i <= emptyStar; i++) {%>
                                <span><i class="far fa-star feedback-result__stars-rated"></i></span>
                                <%}%>

                            </div>


                            <%--   <span><i class="fas fa-star feedback-result__stars-rated"></i></span>
                               <span><i class="fas fa-star feedback-result__stars-rated"></i></span>
                               <span><i class="fas fa-star feedback-result__stars-rated"></i></span>
                               <span><i class="fas fa-star-half-alt feedback-result__stars-rated"></i></span>
                               <span><i class="far fa-star feedback-result__stars-rated"></i></span>
                           </div>--%>

                            <div class="feedback-result__value"><%=floatString%>
                            </div>
                            <div class="feedback-result__description">Based on ${requestScope.AllStars} user reviews
                            </div>
                        </div>
                        <div class="separate-list">
                            <div class="feedback-separate">
                                <div class="feedback-separate__stars">
                                    <c:forEach begin="0" end="4" step="1">
                                        <span><i class="fas fa-star feedback-result__stars-unrated"></i></span>
                                    </c:forEach>
                                </div>
                                <div class="feedback-separate__line"></div>
                                <div class="feedback-separate__value">${requestScope.FiveStars}</div>
                            </div>
                            <div class="feedback-separate">
                                <div class="feedback-separate__stars">
                                    <c:forEach begin="0" end="3" step="1">
                                        <span><i class="fas fa-star feedback-result__stars-unrated"></i></span>
                                    </c:forEach>
                                </div>
                                <div class="feedback-separate__line"></div>
                                <div class="feedback-separate__value">${requestScope.FourStars}</div>
                            </div>
                            <div class="feedback-separate">
                                <div class="feedback-separate__stars">
                                    <c:forEach begin="0" end="2" step="1">
                                        <span><i class="fas fa-star feedback-result__stars-unrated"></i></span>
                                    </c:forEach>
                                </div>
                                <div class="feedback-separate__line"></div>
                                <div class="feedback-separate__value">${requestScope.ThreeStars}</div>
                            </div>
                            <div class="feedback-separate">
                                <div class="feedback-separate__stars">
                                    <c:forEach begin="0" end="1" step="1">
                                        <span><i class="fas fa-star feedback-result__stars-unrated"></i></span>
                                    </c:forEach>
                                </div>
                                <div class="feedback-separate__line"></div>
                                <div class="feedback-separate__value">${requestScope.TwoStars}</div>
                            </div>
                            <div class="feedback-separate">
                                <div class="feedback-separate__stars">
                                    <span><i class="fas fa-star feedback-result__stars-unrated"></i></span>
                                </div>
                                <div class="feedback-separate__line"></div>
                                <div class="feedback-separate__value">${requestScope.Stars}</div>
                            </div>
                        </div>
                        <div class="summary">
                            <h2 class="summary__title">Summary</h2>
                            <ul class="summary__list">
                                <li>New reviews in last 3 months <span
                                        class="summary__list--deep">:</span><span>+${requestScope.CountLastThreeMonths}</span>
                                </li>
                                <%--<li>
                                    Dynamics of reviews for the last 3 months <span class="summary__list--deep">:</span
                                ><span>-4</span>
                                </li>
                                <li>Unique user reviews <span class="summary__list--deep">:</span><span>20</span></li>--%>
                                <li>Total reviews <span
                                        class="summary__list--deep">:</span><span>${requestScope.AllStars}</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="survey-feedbacks">
                            <h2 class="survey-feedbacks__title">Survey feedback</h2>

                            <div class="surveys">
                                <c:if test="${requestScope.ServayFeedbackList != null}">
                                    <c:forEach items="${requestScope.ServayFeedbackList}" var="Feedback"
                                               varStatus="TheCount"
                                               end="8" step="1">
                                        <div class="surveys__single">
                                            <span class="surveys__single--number">${TheCount.count}</span>
                                                ${Feedback.question}

                                            <div class="d-flex ml-3">
                                                <span class="pr-2">1</span> <span class="surveys__single--good"> (${Feedback.yes}%)</span>
                                                <span class="pr-2">3</span> <span class="surveys__single--bad"> (${Feedback.no}%)</span>
                                                <span class="pr-2">5</span> <span class="surveys__single--normal"> (${Feedback.maybe}%)</span>
                                            </div>
                                        </div>

                                    </c:forEach>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12"></div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="feedbacks">
                            <div class="title-filter">
                                <h2 class="title-filter__title">Survey feedback</h2>
                                <div class="title-filter__filters">

                                    <div class="star-filter" role="optgroup">
                                        <i data-rating="1" tabindex="0" id="rating-1" role="radio"
                                           class="fas fa-star rating-star feedback-result__stars-unrated"></i>
                                        <i data-rating="2" tabindex="0" id="rating-2" role="radio"
                                           class="fas fa-star rating-star feedback-result__stars-unrated"></i>
                                        <i data-rating="3" tabindex="0" id="rating-3" role="radio"
                                           class="fas fa-star rating-star feedback-result__stars-unrated"></i>
                                        <i data-rating="4" tabindex="0" id="rating-4" role="radio"
                                           class="fas fa-star rating-star feedback-result__stars-unrated"></i>
                                        <i data-rating="5" tabindex="0" id="rating-5" role="radio"
                                           class="fas fa-star rating-star feedback-result__stars-unrated"></i>
                                    </div>
                                    <div class="date-filter chart-date-range" id="daterange-feedback">
                                        <span class="date-place-view">DATE</span>
                                        <img src="../../img/filter.svg" alt="com.fertifa.app.filter"/>
                                        <input type="hidden" id="dateFrom" value=""/>
                                        <input type="hidden" id="dateTo" value=""/>
                                    </div>
                                    <input type="hidden" id="ratingValue" value="">
                                </div>
                            </div>
                            <div class="feedbacks-blocks">
                                <c:if test="${requestScope.FeedbackList != null}">
                                    <c:forEach items="${requestScope.FeedbackList}" var="Feedback">
                                        <c:set var="RatingSecond" value="${Feedback.feedbackrating}" scope="request"/>
                                        <%!
                                            static float starsShineSecond;
                                            static float starsNoShineSecond;
                                            static float countStarsSecond;
                                        %>
                                        <%
                                            String floatStringSecond = String.valueOf(request.getAttribute("RatingSecond"));
                                            System.out.println(floatStringSecond);
                                            starsShineSecond = Float.parseFloat(floatStringSecond);
                                            System.out.println("starsShine " + starsShineSecond);
                                            starsNoShineSecond = 0;
                                            countStarsSecond = 0;
                                        %>
                                        <%!
                                            float countStarsSecond(float Stars) {
                                                float counter = 5 - Stars;
                                                if (counter % 2.0 == 0.0) {
                                                    counter = 0;
                                                } else {
                                                    counter = 0.5f;
                                                }
                                                return counter;

                                            }
                                        %>

                                        <div class="feedbacks-blocks__item grid-item">
                                            <h5>${Feedback.feddbackSubject}</h5>

                                            <div class="feedback-result">
                                                <div class="feedback-result__stars">
                                                    <% float counter = 0;
                                                        if (starsShineSecond != 0) {
                                                            if (starsShineSecond % 2 == 0.0) {
                                                                counter = (int) starsNoShineSecond - 1;
                                                            } else {
                                                                counter = (int) (starsShineSecond + 1);
                                                            }
                                                        }
                                                    %>
                                                    <% if (counter == 0) {
                                                    %>
                                                    <span><i
                                                            class="far fa-star feedback-result__stars-rated"></i></span>
                                                    <%} else {%>
                                                    <%
                                                        for (float i = 0; i < starsShineSecond; i++) {
                                                    %>
                                                    <span><i
                                                            class="fas fa-star feedback-result__stars-rated"></i></span>
                                                    <%}%>
                                                        <%--  <% if (countStarsSecond(starsShineSecond) == 0.5f) {
                                                          %>
                                                          <span><i
                                                                  class="fas fa-star-half-alt feedback-result__stars-rated"></i></span>
                                                          <%} else {%>
                                                          <%}%>--%>
                                                    <%}%>

                                                </div>
                                                <div class="feedback-result__value">${Feedback.feedbackrating}</div>

                                                <div class="feedback-result__date">${Feedback.getCreationDate}
                                                </div>
                                            </div>
                                            <p>${Feedback.feedbackText}</p>
                                        </div>
                                    </c:forEach>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>

</div>
<jsp:include page="${root}/includes/NotificationBlog.jsp"/>

<script>
    var feedback_data_ajax = "${root}/FeedBackDataAjax?id=${requestScope.EmployerObject.id}";
</script>

<script>
    var selfId = ${requestScope.EmployerObject.id};
    var roomIdFromFrontUser = ${requestScope.EmployerObject.id};
    var sessionRoomFromFront = "jok2f4fud_9";
</script>

<jsp:include page="${root}/includes/BundleJsInclude.jsp"/>
<jsp:include page="${root}/includes/FrontFooter.jsp"/>
<%--<jsp:include page="../../includes/CookieManagerUser.jsp"/>--%>
</body>
</html>

