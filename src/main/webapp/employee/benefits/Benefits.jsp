<%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 2/5/2020
  Time: 10:58 PM
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
            <div class="content">
                <h1 class="content-title">My Benefits</h1>
                <div class="row">
                    <div class="col-md-12">

                        <!--                            <div class="table-outside">-->
                        <!--                                    <table class="table custom-table mt-3">-->
                        <!--                                        <thead>-->
                        <!--                                            <tr>-->
                        <!--                                                <th scope="col">Employee journey list </th>-->
                        <!--                                                <th scope="col">At clinic</th>-->
                        <!--                                                <th scope="col">Bronze</th>-->
                        <!--                                                <th scope="col">Silver</th>-->
                        <!--                                                <th scope="col">Gold</th>-->
                        <!--                                            </tr>-->
                        <!--                                        </thead>-->
                        <!--                                        <tbody>-->
                        <!--                                            <tr>-->
                        <!--                                                <td>Free exploratory consultation</td>-->
                        <!--                                                <td><i class="cross-sign"></i></td>-->
                        <!--                                                <td><i class="check-sign"></i></td>-->
                        <!--                                                <td><i class="check-sign"></i></td>-->
                        <!--                                                <td><i class="check-sign"></i></td>-->
                        <!--                                            </tr>-->
                        <!--                                            <tr>-->
                        <!--                                                <td>Unlimited 24/7 messaging</td>-->
                        <!--                                                <td><i class="cross-sign"></i></td>-->
                        <!--                                                <td><i class="check-sign"></i></td>-->
                        <!--                                                <td><i class="check-sign"></i></td>-->
                        <!--                                                <td><i class="check-sign"></i></td>-->
                        <!--                                            </tr>-->
                        <!--                                            <tr>-->
                        <!--                                                <td>Personalised portal/app</td>-->
                        <!--                                                <td><i class="cross-sign"></i></td>-->
                        <!--                                                <td><i class="check-sign"></i></td>-->
                        <!--                                                <td><i class="check-sign"></i></td>-->
                        <!--                                                <td><i class="check-sign"></i></td>-->
                        <!--                                            </tr>-->
                        <!--                                            <tr>-->
                        <!--                                                <td>One-stop 1-to-1 supervision</td>-->
                        <!--                                                <td><i class="cross-sign"></i></td>-->
                        <!--                                                <td><i class="check-sign"></i></td>-->
                        <!--                                                <td><i class="check-sign"></i></td>-->
                        <!--                                                <td><i class="check-sign"></i></td>-->
                        <!--                                            </tr>-->
                        <!--                                            <tr>-->
                        <!--                                                <td>Hormone testing (£)</td>-->
                        <!--                                                <td>£ 300</td>-->
                        <!--                                                <td>£ 125</td>-->
                        <!--                                                <td>£ 125</td>-->
                        <!--                                                <td>£ 125</td>-->
                        <!--                                            </tr>-->
                        <!--                                            <tr>-->
                        <!--                                                <td>Savings (£)</td>-->
                        <!--                                                <td></td>-->
                        <!--                                                <td>£ 175</td>-->
                        <!--                                                <td>£ 175</td>-->
                        <!--                                                <td>£ 175</td>-->
                        <!--                                            </tr>-->
                        <!--                                            <tr>-->
                        <!--                                                <td>Doctor consultation (£)</td>-->
                        <!--                                                <td>£ 275</td>-->
                        <!--                                                <td>10% discount</td>-->
                        <!--                                                <td>15% discount</td>-->
                        <!--                                                <td>20% discount</td>-->
                        <!--                                            </tr>-->
                        <!--                                            <tr>-->
                        <!--                                                <td>Savings (£)</td>-->
                        <!--                                                <td></td>-->
                        <!--                                                <td>£ 28</td>-->
                        <!--                                                <td>£ 41</td>-->
                        <!--                                                <td>£ 55</td>-->
                        <!--                                            </tr>-->
                        <!--                                            <tr>-->
                        <!--                                                <td>Nurse consultation</td>-->
                        <!--                                                <td></td>-->
                        <!--                                                <td>10% discount</td>-->
                        <!--                                                <td>1 free session + 15% disc for future sessions</td>-->
                        <!--                                                <td>1 free session + 20% disc for future sessions</td>-->
                        <!--                                            </tr>-->
                        <!--                                            <tr>-->
                        <!--                                                <td>Savings (4 x 15 min sessions) (£)</td>-->
                        <!--                                                <td></td>-->
                        <!--                                                <td>£ 6</td>-->
                        <!--                                                <td>£ 22</td>-->
                        <!--                                                <td>£ 24</td>-->
                        <!--                                            </tr>-->
                        <!--                                            <tr>-->
                        <!--                                                <td>Wellbeing</td>-->
                        <!--                                                <td></td>-->
                        <!--                                                <td>10% discount</td>-->
                        <!--                                                <td>15% discount</td>-->
                        <!--                                                <td>1 free session + 20% discount for future sessions</td>-->
                        <!--                                            </tr>-->
                        <!--                                            <tr>-->
                        <!--                                                <td rowspan="4">Savings (5 sessions on average)</td>-->
                        <!--                                                <td>Nutrition</td>-->
                        <!--                                                <td>£ 55</td>-->
                        <!--                                                <td>£ 82</td>-->
                        <!--                                                <td>£ 230</td>-->
                        <!--                                            </tr>-->
                        <!--                                            <tr>-->
                        <!--                                                <td>Acupuncture</td>-->
                        <!--                                                <td>£ 32</td>-->
                        <!--                                                <td>£ 48</td>-->
                        <!--                                                <td>£ 64</td>-->
                        <!--                                            </tr>-->
                        <!--                                            <tr>-->
                        <!--                                                <td>Reflectology</td>-->
                        <!--                                                <td>£ 31</td>-->
                        <!--                                                <td>£ 46</td>-->
                        <!--                                                <td>£ 62</td>-->
                        <!--                                            </tr>-->
                        <!--                                            <tr>-->
                        <!--                                                <td>Counselling</td>-->
                        <!--                                                <td>£ 37</td>-->
                        <!--                                                <td>£ 55</td>-->
                        <!--                                                <td>£ 74</td>-->
                        <!--                                            </tr>-->
                        <!--                                            <tr>-->
                        <!--                                                <td>Treatment discount with partnered clinic</td>-->
                        <!--                                                <td>N/A</td>-->
                        <!--                                                <td>2%</td>-->
                        <!--                                                <td>3%</td>-->
                        <!--                                                <td>5%</td>-->
                        <!--                                            </tr>-->
                        <!--                                            <tr>-->
                        <!--                                                <td>Savings ( average 2 cycles) </td>-->
                        <!--                                                <td></td>-->
                        <!--                                                <td>£ 160</td>-->
                        <!--                                                <td>£ 240</td>-->
                        <!--                                                <td>£ 800</td>-->
                        <!--                                            </tr>-->
                        <!--                                            <tr>-->
                        <!--                                                <td><span class="total-info">Total savings the Fertifa way</span></td>-->
                        <!--                                                <td></td>-->
                        <!--                                                <td><span class="total-info">£ 524</span></td>-->
                        <!--                                                <td><span class="total-info">£ 708</span></td>-->
                        <!--                                                <td><span class="total-info">£ 1,484</span></td>-->
                        <!--                                            </tr>-->
                        <!--                                        </tbody>-->
                        <!--                                    </table>-->
                        <!--                                </div>-->
                        <img class="benefits-image w-100" src="${root}/img/benefits.png" alt="">
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>

<script>
    var roomIdFromFrontUser = ${requestScope.EmployeeObject.id};
    var selfId = ${requestScope.EmployeeObject.id};
</script>

<script>
    var selfId = ${requestScope.EmployeeObject.id};
    var roomIdFromFrontUser = ${requestScope.EmployeeObject.id};
    var sessionRoomFromFront = "jok2f4fud_9";
</script>
<jsp:include page="${root}/includes/BundleJsInclude.jsp"/>
<jsp:include page="${root}/includes/FrontFooter.jsp"/>
<%--<jsp:include page="../../includes/CookieManagerUser.jsp"/>--%>
</body>
</html>

