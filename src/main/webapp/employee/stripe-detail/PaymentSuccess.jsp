<%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 2/2/2020
  Time: 11:53 PM
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
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        if (session.getAttribute("employeeEmail") == null) {
            response.sendRedirect(request.getContextPath() + "/employee/employee-dashboard");
        }
        if (request.getSession().getAttribute("back") == null) {
            request.getSession().setAttribute("back", "done");
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
                    <c:if test="${requestScope.UsersList != null}">
                        <c:forEach items="${requestScope.UsersList}" var="userDetail">
                            <div class="user__info">

                                <span class="user__company">${userDetail.comapny}</span>
                                <span class="user__name">${userDetail.firstName} ${userDetail.lastName}</span>

                            </div>
                            <div class="user__image">
                                <c:set value="${userDetail.userImage}" var="Image"/>
                                <c:choose>
                                    <c:when test="${Image != null}">
                                        <img src="${userDetail.userImage}" alt=""/>
                                    </c:when>
                                    <c:otherwise>
                                        <img src="<%=request.getContextPath()%>/img/avatar-empty.svg" alt=""/>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </c:forEach>
                    </c:if>

                </div>
            </div>
            <div class="content">
                <h1 class="content-title">Order Confirm</h1>
                <form action="" method="post" class="billing-form needs-validation" novalidate>
                    <div class="row">
                        <div class="col-md-12">
                            <%
                                if (request.getSession().getAttribute("message") != null) {
                            %>
                            <div>
                                <%=request.getSession().getAttribute("message")%>
                            </div>
                            <%
                                    request.getSession().setAttribute("message", null);
                                }

                            %>
                        </div>
                    </div>
                </form>
            </div>
        </main>
    </div>
</div>

<div class=" modal fade
                            " id="cart-modal-delete">
    <div class="modal-dialog custom-modal">
        <div class="modal-content custom-modal__content bg-default">
            <form action="" method="post">
                <div class="modal-header custom-modal__header">
                    <h4 class="modal-title custom-modal__title">Delete</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body custom-modal__body text-center">
                    <p>Would you like to delete a product from cart?</p>
                    <input type="hidden" class="hidden-selected-item" value=""/>
                </div>
                <div class="modal-footer custom-modal__footer justify-content-center">
                    <button
                            type="button"
                            class="btn btn-secondary custom-rounded-btn grey-line click-dismiss-del-modal"
                            data-dismiss="modal"
                    >
                        Cancel
                    </button>
                    <button type="submit" class="btn btn-danger custom-rounded-btn light-red">
                        Delete
                    </button>
                </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<script>
    var show_service_data_ajax = "show_service_data_ajax";
</script>

<script>
    var selfId = ${requestScope.EmployeeObject.id};
    var roomIdFromFrontUser = ${requestScope.EmployeeObject.id};
    var sessionRoomFromFront = "jok2f4fud_9";
</script>
<script>
    let url = 'https://chat.fertifabenefits.com/api/v1/'+<%=request.getSession().getAttribute("userId")%>
    setTimeout(function(){ window.location.href = url; }, 2000);
</script>
<jsp:include page="${root}/includes/BundleJsInclude.jsp"/>
<jsp:include page="${root}/includes/FrontFooter.jsp"/>
<%--<jsp:include page="includes/CookieManagerUser.jsp"/>--%>
</body>
</html>
