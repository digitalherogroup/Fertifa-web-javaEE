<%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 1/28/2020
  Time: 6:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
    if (request.getHeader("User-Agent").contains("Mobile")) {
        String url = "https://m.second.fertifabenefits.com/employee/employee-dashboard";
        response.sendRedirect(url);
    }
%>
<!DOCTYPE html>
<html>
<head>
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

                <h1 class="content-title">${requestScope.NewsResponse.title}</h1>

                <div class="row">
                    <div class="col-lg-8 col-md-12 col-sm-12">
                        <div class="news-container">
                            <c:if test="${requestScope.NewsResponse != null}">
                                <div class="news-container__image">
                                    <img src="${root}/${requestScope.NewsResponse.thumbnailURL}" alt=""/>
                                </div>
                                <h2 class="news-container__title">
                                        ${requestScope.NewsResponse.shortDescription}
                                </h2>
                                <div class="news-container__content">
                                        ${requestScope.NewsResponse.description}
                                </div>
                            </c:if>

                        </div>
                    </div>
                    <div class="col-lg-4 col-md-12 col-sm-12">
                        <div class="news-recent">
                            <h4 class="news-recent__title">Recent Articles</h4>
                            <ul class="news-recent-list">
                                <c:if test="${requestScope.FullNewsModelList != null}">
                                    <c:forEach items="${requestScope.FullNewsModelList}" var="OtherNews" step="1"
                                               end="8">
                                        <li class="news-recent-list__item">


                                            <a href="${root}/employee/education/educations?id=${OtherNews.id}">
                                                <span class="news-recent-list__item-date">${OtherNews.getCreationDate}</span>
                                                <div class="news-recent-list__item-title">${OtherNews.title}</div>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </c:if>

                            </ul>
                        </div>
                    </div>
                </div>
                <div class="row mt-5 mb-3">
                    <div class="col-md-12 d-flex justify-content-between">
                        <c:set value="${requestScope.perId}" var="per"/>
                        <c:choose>
                            <c:when test="${per == 0}">
                                <span class="prev-link" disabled=""><i
                                        class="fas fa-angle-left"></i> Previous Articles</span>
                            </c:when>
                            <c:otherwise>
                                <a href="${root}/employee/education/educations?id=${requestScope.perId}"
                                   class="prev-link"><i
                                        class="fas fa-angle-left"></i> Previous Articles</a>
                            </c:otherwise>
                        </c:choose>
                        <c:set value="${requestScope.NextId}" var="nex"/>
                        <c:choose>
                            <c:when test="${nex == 0}">
                                <span class="prev-link">Next Articles <i
                                        class="fas fa-angle-right"></i></span>
                            </c:when>
                            <c:otherwise>
                                <a href="${root}/employee/education/educations?id=${requestScope.NextId}"
                                   class="prev-link">Next Articles <i
                                        class="fas fa-angle-right"></i></a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </main>
    </div>

    <jsp:include page="${root}/includes/NotificationBlogUser.jsp"/>
</div>

<script>
    var selfId = ${requestScope.EmployeeObject.id};
    var roomIdFromFrontUser = ${requestScope.EmployeeObject.id};
    var sessionRoomFromFront = "jok2f4fud_9";
</script>
<jsp:include page="${root}/includes/BundleJsInclude.jsp"/>
<jsp:include page="${root}/includes/FrontFooter.jsp"/>
<%--<jsp:include page="../../../includes/CookieManagerUser.jsp"/>--%>
</body>
</html>
