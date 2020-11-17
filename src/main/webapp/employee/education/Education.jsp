<%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 1/28/2020
  Time: 5:28 AM
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
                <h1 class="content-title">Education</h1>
                <div class="row">
                    <div class="col-md-12">
                        <div class="button-group filters-button-group mb-4">
                            <button class="btn custom-rounded-btn grey-line is-checked" data-filter="*">Show
                                all
                            </button>
                            <c:forEach var="categories" items="${requestScope.NewCategoryList}">
                                <c:set var="nameToShow" value="${categories.newsCategory}" scope="request"/>
                                <%
                                    String nameCategory = String.valueOf(request.getAttribute("nameToShow"));
                                %>
                                <%!
                                    private String nameCategoryReplaces(String name) {
                                        String replacingName = "";
                                        if (!name.isEmpty()) {
                                            replacingName = name.replace(" ", "-");
                                            replacingName = name.replace("&", "");
                                        }
                                        return replacingName;
                                    }
                                %>
                                <button class="btn custom-rounded-btn grey-line button"
                                        data-filter=.<%=nameCategoryReplaces(nameCategory)%>>${categories.newsCategory}
                                </button>
                            </c:forEach>
                        </div>
                        <div class="education-list grid-filter">
                            <c:if test="${requestScope.NewsModelList != null}">
                                <c:forEach items="${requestScope.NewsModelList}" var="Edu">
                                    <c:set var="EduOne" value="${Edu.categoryOne}" scope="request"/>
                                    <c:set var="EduTwo" value="${Edu.categoryTwo}" scope="request"/>
                                    <%
                                        String nameCategoryOne = String.valueOf(request.getAttribute("EduOne"));
                                        String nameCategoryOTwo = String.valueOf(request.getAttribute("EduTwo"));
                                    %>
                                    <div class="education-list__item grid-filter-element <%=nameCategoryReplaces(nameCategoryOne)%> <%=nameCategoryReplaces(nameCategoryOTwo)%>"
                                         data-category='<%=nameCategoryReplaces(nameCategoryOne)%>'>
                                        <div class="education-list__item-inner">
                                            <a href="${root}/employee/education/educations?id=${Edu.id}">
                                                <div class="education-list__image">
                                                    <div class="education-list__image-date">${Edu.getCreationDate}</div>
                                                    <img src="${root}/${Edu.thumbnailUrl}" alt=""/>
                                                </div>
                                                <div class="education-list__content">
                                                    <h3 class="education-list__content-title">${Edu.title}</h3>
                                                    <p class="education-list__content-description">
                                                            ${Edu.shortDescription}
                                                    </p>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
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
</body>
</html>

