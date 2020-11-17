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
    <title>MyAccount</title>
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
                <h1 class="content-title">Add payment method</h1>
                <form action="${root}/employee/myaccount/addnewcard/update" method="post"
                      class="billing-form needs-validation" novalidate>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="create-payment-block">
                                <div class="row">
                                    <div class="col-md-6">

                                        <div class="form-group form-element mb-4">
                                            <input
                                                    type="text"
                                                    pattern="^\d{13,16}$"
                                                    name="card_number"
                                                    id="card_number"
                                                    class="form-control custom-rounded-input form-element__input"
                                                    placeholder=""
                                                    disabled
                                                    value="${requestScope.Card.last4Digits}"
                                            />
                                            <input type="hidden" name="card_id" value="${requestScope.Card.cardId}">
                                            <div class="invalid-feedback">Please fill in card number.</div>
                                        </div>
                                        <div class="form-group form-element mb-4">
                                            <div class="row">
                                                <div class="col-md-4">
                                                    <select
                                                            class="selectpicker custom-selectpick w-100 custom-rounded-dropdown mb-md-0 mb-4"
                                                            required
                                                            name="expireMonths"
                                                    >
                                                        <option value="${requestScope.Card.month}">${requestScope.Card.month}</option>
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
                                                    <select
                                                            class="selectpicker custom-selectpick w-100 custom-rounded-dropdown mb-md-0 mb-4"
                                                            required
                                                            name="expireYears"

                                                    >
                                                        <option value="${requestScope.Card.year}">${requestScope.Card.year}</option>
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
                                                            pattern="^\d{3,4}$"
                                                            name="cvv"
                                                            id="cvv"
                                                            class="form-control custom-rounded-input form-element__input"
                                                            placeholder="CVV"
                                                            required
                                                            value=""
                                                    />
                                                    <div class="invalid-feedback">Please fill.</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group form-element mb-4">
                                            <input
                                                    type="text"
                                                    pattern="[a-zA-Z0-9\s]+"
                                                    name="card_holder_name"
                                                    id="card_holder_name"
                                                    class="form-control custom-rounded-input form-element__input"
                                                    placeholder="Card holder name"
                                                    required
                                                    value="${requestScope.Card.name}"
                                            />
                                            <div class="invalid-feedback">Please fill place.</div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">

                                        <div class="form-group form-element mb-4">
                                            <div class="row">
                                                <%-- <div class="col-md-6 mb-md-0 mb-4">
                                                     <select
                                                             name="country"
                                                             class="selectpicker countrypicker custom-selectpick w-100 custom-rounded-dropdown"
                                                             data-flag="true"
                                                             data-default="DE"
                                                             required
                                                     >
                                                     </select>
                                                     <div class="invalid-feedback">Please fill.</div>
                                                 </div>--%>
                                                <div class="col-md-12">
                                                    <input
                                                            type="text"
                                                            pattern="[A-Za-z]{1,50}"
                                                            name="city"
                                                            id="city"
                                                            class="form-control custom-rounded-input form-element__input"
                                                            placeholder=""
                                                            required
                                                            value="${requestScope.StripeUser.city}"
                                                    />
                                                    <div class="invalid-feedback">Please fill place.</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group form-element mb-4">
                                            <div class="row">
                                                <%--<div class="col-md-6">
                                                    <select
                                                            name="country"
                                                            class="selectpicker custom-selectpick w-100 custom-rounded-dropdown mb-md-0 mb-4"
                                                            required
                                                    >
                                                        <option value="">Area</option>
                                                        <option value="100">100</option>
                                                        <option value="200">200</option>
                                                        <option value="300">300</option>
                                                    </select>
                                                    <div class="invalid-feedback">Please fill.</div>
                                                </div>--%>
                                                 <div class="col-md-12">
                                                     <input
                                                             type="text"
                                                             pattern="^[A-Z]{1,2}[0-9][A-Z0-9]? ?[0-9][A-Z]{2}$"
                                                             id="index"
                                                             class="form-control custom-rounded-input form-element__input"
                                                             placeholder=""
                                                             required
                                                             value="${requestScope.StripeUser.zipCode}"
                                                     />
                                                     <div class="invalid-feedback">Please fill place.</div>
                                                 </div>
                                            </div>
                                        </div>
                                        <div class="form-group form-element mb-4">
                                            <input
                                                    type="text"
                                                    name="address"
                                                    id="address"
                                                    class="form-control custom-rounded-input form-element__input"
                                                    placeholder=""
                                                    required
                                                    value="${requestScope.StripeUser.address}"
                                            />
                                            <div class="invalid-feedback">Please fill in Address.</div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 mb-md-0 mb-4">
                                        <div class="payment-method-terms">
                                            By continuing, you accept the
                                            <a href="">Google Payments privacy notice and Terms of use</a>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <button
                                                        type="submit"
                                                        class="btn btn-secondary custom-rounded-btn w-100 light-green mb-md-0 mb-4"
                                                >
                                                    SAVE
                                                </button>
                                            </div>
                                            <div class="col-md-6">
                                               <%-- <button
                                                        type="button"
                                                        id="clear-payment-form"
                                                        class="btn btn-secondary custom-rounded-btn w-100 grey-line"
                                                >
                                                    CANCEL
                                                </button>--%>
                                            </div>
                                        </div>
                                    </div>
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
    var selfId = ${requestScope.EmployeeObject.id};
    var roomIdFromFrontUser = ${requestScope.EmployeeObject.id};
    var sessionRoomFromFront = "jok2f4fud_9";
</script>
<script src="${root}/js/index.bundle.js"></script>

<script>
    // $(document).ready(function () {
    //     //$('.order-testing-toast').toast('show');
    // });
</script>
<div
        class="order-testing-toast custom-toast fide hide toast"
        role="alert"
        aria-live="assertive"
        aria-atomic="true"
        data-delay="5000"
>
    <div class="toast-header">
        <button type="button" class="ml-2 mb-1 ml-auto close" data-dismiss="toast" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <div class="toast-body"></div>
</div>
<jsp:include page="${root}/includes/FooterZnglik.jsp"/>
</body>
</html>
