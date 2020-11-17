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
                                        required
                                        value=""
                                />
                                <div class="invalid-feedback">Please fill in card number.</div>
                            </div>
                            <div class="form-group form-element mb-4">
                                <div class="row">
                                    <div class="col-md-4">
                                        <select class="selectpicker custom-selectpick w-100 custom-rounded-dropdown" name="expireMonths" required>
                                            <option value="">Month</option>
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <option value="5">5</option>
                                            <option value="6">6</option>
                                            <option value="7">7</option>
                                            <option value="8">8</option>
                                            <option value="9">9</option>
                                            <option value="10">10</option>
                                            <option value="11">11</option>
                                            <option value="12">12</option>
                                        </select>
                                        <div class="invalid-feedback">Please fill.</div>
                                    </div>
                                    <div class="col-md-4">
                                        <select class="selectpicker custom-selectpick w-100 custom-rounded-dropdown" name="expireYears" required>
                                            <option value="">Year</option>
                                            <option value="2020">2020</option>
                                            <option value="2021">2021</option>
                                            <option value="2022">2022</option>
                                            <option value="2023">2023</option>
                                            <option value="2024">2024</option>
                                            <option value="2025">2025</option>
                                            <option value="2026">2026</option>
                                            <option value="2027">2027</option>
                                            <option value="2028">2028</option>
                                            <option value="2029">2029</option>
                                            <option value="2030">2030</option>
                                        </select>
                                        <div class="invalid-feedback">Please fill.</div>
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

                            <h2 class="billing-form__title">Billing Information</h2>
                            <div class="form-group form-element">
                                <input
                                        type="text"
                                        name="first_name"
                                        id="first_name"
                                        pattern="[A-Za-z]{1,40}"
                                        class="form-control custom-rounded-input form-element__input"
                                        placeholder="First Name"
                                        required
                                        value=""
                                />
                                <div class="invalid-feedback">Please fill in first name.</div>
                            </div>
                            <div class="form-group form-element">
                                <input
                                        type="text"
                                        name="last_name"
                                        id="last_name"
                                        pattern="[A-Za-z]{1,40}"
                                        class="form-control custom-rounded-input form-element__input"
                                        placeholder="Last Name"
                                        required
                                        value=""
                                />
                                <div class="invalid-feedback">Please fill in last name.</div>
                            </div>
                            <div class="form-group form-element">
                                <input
                                        type="tel"
                                        pattern="^\d{2,}$"
                                        name="phone"
                                        id="phone"
                                        class="form-control custom-rounded-input form-element__input"
                                        placeholder="Phone"
                                        required
                                        value=""
                                />
                                <div class="invalid-feedback">Please fill in phone.</div>
                            </div>
                            <div class="form-group form-element">
                                <input
                                        type="email"
                                        name="email"
                                        id="email"
                                        class="form-control custom-rounded-input form-element__input"
                                        placeholder="Email"
                                        required
                                        value=""
                                />
                                <div class="invalid-feedback">Please fill in email.</div>
                            </div>
                            <div class="form-group form-element">
                                <input
                                        type="text"
                                        name="address"
                                        id="address"
                                        class="form-control custom-rounded-input form-element__input"
                                        placeholder="Address"
                                        required
                                        value=""
                                />
                                <div class="invalid-feedback">Please fill in Address.</div>
                            </div>
                            <div class="form-group form-element">
                                <input
                                        type="text"
                                        name="city"
                                        id="city"
                                        pattern="[A-Za-z]{1,40}"
                                        class="form-control custom-rounded-input form-element__input"
                                        placeholder="City"
                                        required
                                        value=""
                                />
                                <div class="invalid-feedback">Please fill in City.</div>
                            </div>
                            <div class="form-group form-element">
                                <input
                                        type="text"
                                        name="state"
                                        id="country"
                                        pattern="[A-Za-z]{1,40}"
                                        class="form-control custom-rounded-input form-element__input"
                                        placeholder="Country"
                                        required
                                        value=""
                                />
                                <div class="invalid-feedback">Please fill in Country name.</div>
                            </div>
                            <div class="form-group form-element">
                                <input
                                        type="text"
                                        name="post_code"
                                        id="post_code"
                                        class="form-control custom-rounded-input form-element__input"
                                        placeholder="Post Code"
                                        required
                                        pattern="^[A-Z]{1,2}[0-9][A-Z0-9]? ?[0-9][A-Z]{2}$"
                                        value=""
                                />
                                <div class="invalid-feedback">Please fill in Post Code.</div>
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

