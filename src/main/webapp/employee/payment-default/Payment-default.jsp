<%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 2/1/2020
  Time: 4:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <% if (request.getHeader("User-Agent").contains("Mobile")) {
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
        if("POST".equals(request.getMethod())){
            response.sendRedirect(request.getContextPath() + "/employee/shoppingcart");
        }
    %>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>


    <jsp:include page="${root}/includes/BundleInclude.jsp"/>

    <title>Fertifa | Digital Platform</title>
</head>
<body>
<input type="hidden" id="refreshed" value="no">
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
                <h1 class="content-title">Billing Info</h1>
                <form action="${root}/employee/stripe-detail" method="post" class="billing-form needs-validation" novalidate>
                    <div class="row">
                        <div class="col-md-8">
                            <h2 class="billing-form__title">Credit Card Details</h2>
                            <div class="form-group form-element">
                                <input
                                        type="text"
                                        name="card_number"
                                        id="card_number"
                                        pattern="^\d{13,16}$"
                                        class="form-control custom-rounded-input form-element__input"
                                        placeholder="Card Number"
                                        readonly
                                        value="**** **** ****${requestScope.Card.last4Digits}"
                                />
                            </div>
                            <div class="form-group form-element mb-4">
                                <div class="row">
                                    <div class="col-md-4">
                                        <input
                                                type="text"
                                                name="expireMonths"
                                                id="expireMonths"
                                                class="form-control custom-rounded-input form-element__input"
                                                placeholder="Card Number"
                                                required
                                                readonly
                                                value="${requestScope.Card.month}"

                                        />
                                    </div>
                                    <div class="col-md-4">
                                        <input
                                                type="text"
                                                name="expireYears"
                                                id="expireYears"
                                                class="form-control custom-rounded-input form-element__input"
                                                placeholder="Card Number"
                                                required
                                                value="${requestScope.Card.year}"
                                                readonly
                                        />

                                    </div>
                                    <div class="col-md-4">
                                        <input
                                                type="text"
                                                name="cvv"
                                                id="cvv"
                                                pattern="^\d{3,4}$"
                                                class="form-control custom-rounded-input form-element__input"
                                                placeholder="CVV/CVV2"
                                                required
                                                value=""
                                        />
                                        <div class="invalid-feedback">Please fill.</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card custom-card">
                                <div class="card-body custom-card__body">
                                    <span>Details:</span>
                                    <p class="small">
                                        Free cancellation up to 10 days prior to delivery<br />
                                        High security payment process<br />
                                        Complete data privacy<br />
                                    </p>
                                    <div class="card-data-info">
                                        <span class="card-data-info__exp">Total: £ <%=request.getAttribute("Total")%></span>
                                    </div>
                                    <button type="submit" class="btn btn-secondary custom-rounded-btn w-100 mt-4 purple">
                                        CONFIRM
                                    </button>
                                    <input name="total" value='<%=request.getAttribute("Total")%>' type="hidden">
                                    <input name="cardId" value='${requestScope.Card.cardId}' type="hidden">
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </main>
    </div>
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
<jsp:include page="${root}/includes/FrontFooter.jsp"/>
</body>
</html>

