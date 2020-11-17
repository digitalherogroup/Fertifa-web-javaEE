<%--
  Created by IntelliJ IDEA.
  User: Shant
  Date: 1/20/2020
  Time: 11:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                                    <img src="${root}/WEB-INF/jsp/img/avatar-empty.svg" alt=""/>
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
                <h1 class="content-title">Fertifa partners</h1>
                <div class="row">
                    <div class="col-md-12">
                        <div class="partners-filter">
                            <%--<span class="partners-filter__desc">Overall number of partners: ${requestScope.PartnersList.size()}</span>--%>

                        </div>
                    </div>
                </div>
                <div class="row partners-list dynamic-data-place">
                    <c:if test="${requestScope.PartnersList!= null}">
                        <c:forEach items="${requestScope.PartnersList}" var="Partner">
                            <div class="col-md-3 col-sm-6 partners-list__item">

                                <div class="front">
                                    <div class="partners-list__item--logo">
                                        <img src="http://second.fertifabenefits.com/${Partner.logoLink}" alt=""/>
                                    </div>
                                    <div class="partners-list__item--title">${Partner.partnerName}</div>
                                </div>
                                <div class="back">
                                    <p class="partners-list__item--desc" style="line-height: 14px">
                                            ${Partner.partnerDiscription}
                                    </p>
                                          <a href="${Partner.domain}" class="partners-list__item--link" target="_blank">${Partner.domain}</a>
                                </div>
                            </div>
                     </c:forEach>
                    </c:if>
                </div>
            </div>
        </main>
    </div>
</div>
<jsp:include page="${root}/includes/NotificationBlog.jsp"/>
<script>
    var selfId = ${requestScope.EmployerObject.id};
    var roomIdFromFrontUser =${requestScope.EmployerObject.id};
    var sessionRoomFromFront = "jok2f4fud_9";
</script>
<jsp:include page="${root}/includes/BundleJsInclude.jsp"/>
<jsp:include page="${root}/includes/FrontFooter.jsp"/>
<%--<jsp:include page="../../includes/CookieManagerUser.jsp"/>--%>
</body>
</html>

