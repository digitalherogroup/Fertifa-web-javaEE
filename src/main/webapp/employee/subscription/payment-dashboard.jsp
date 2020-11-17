<%--
  Created by IntelliJ IDEA.
  User: Shant
  Date: 7/18/2020
  Time: 8:02 PM
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
            String url = "https://m.fertifabenefits.com/employee/employee-dashboard";
            response.sendRedirect(url);
        }
    %>
    <c:set var="root" value="${pageContext.request.contextPath}"/>

    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

    <jsp:include page="${root}/includes/BundleInclude.jsp"/>

    <title>Fertifa | Digital Platform</title>
</head>
<body>
<div
        class="modal show custom-modal modal-service fade background"
        id="billing-info"
        tabindex="-1"
        role="dialog"
>
    <div class="modal-dialog modal-lg custom-modal">
        <div class="modal-content custom-modal__content bg-default">

            <form
                    action="${root}/employee/subscription/add"
                    method="POST"
                    id="dashboard-billingInfo-form"
                    class="billing-form needs-validation"
                    novalidate
            >
                <div class="modal-body mh-400 custom-modal__body">
                    <div class="modal-loader-place"></div>
                    <div class="main-content">
                        <h3 class="custom-modal__sub-title mt-3">Billing Info</h3>
                        <div class="row">
                            <div class="col-12">
<%--                                <p class="text-billing-info justify-content-center">You have ${requestScope.EmployerDiscounts.totalDiscountAmount} offer for ${requestScope.EmployerDiscounts.monthsOfDiscount} month</p>--%>
<%--                                <input type="hidden" name="discountId" value="${requestScope.EmployerDiscounts.id}">--%>
<%--                                <input type="hidden" name="price"--%>
<%--                                       value="${requestScope.EmployerDiscounts.totalDiscountAmount}">--%>
                            </div>
                        </div>
                        <%
                            if (request.getSession().getAttribute("message") != null) {
                        %>
                        <div style="max-width: 420px; color: red">
                            <%=request.getSession().getAttribute("message")%>
                        </div>
                        <%
                            }
                            request.getSession().setAttribute("message",null);
                        %>
                        <div class="align-items-center mb-4">
                            <label for="" class="help-contact-form__label">Credit card details</label>
                            <div class="">
                                <input
                                        type="text"
                                        id="card-number"
                                        name="card_number"
                                        class="form-control custom-rounded-input"
                                        value=""
                                        placeholder="Card number"
                                        required=""
                                        pattern="^\d{13,16}$"
                                />
                                <div class="invalid-feedback">Please fill in card number.</div>
                            </div>
                            <div class="form-group form-element mb-4">
                                <div class="row">
                                    <div class="col-md-4 pt-3">
                                        <select
                                                class="selectpicker custom-selectpick w-100 custom-rounded-dropdown"
                                                name="expireMonths"
                                                required
                                        >
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
                                    <div class="col-md-4 pt-3">
                                        <select
                                                class="selectpicker custom-selectpick w-100 custom-rounded-dropdown"
                                                required
                                                name="expireYears"
                                        >
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
                                    <div class="col-md-4 pt-3">
                                        <input
                                                type="text"
                                                pattern="^\d{3,4}$"
                                                name="cvv"
                                                id="cvv"
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
                        <h2 class="billing-form__title">Billing Information</h2>
                        <div class="form-group form-element">
                            <input
                                    type="text"
                                    pattern="[A-Za-z]{1,40}"
                                    name="first_name"
                                    id="first_name"
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
                                    pattern="^\[A-Za-z]$"
                                    name="last_name"
                                    id="last_name"
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
                                    name="country"
                                    id="state"
                                    class="form-control custom-rounded-input form-element__input"
                                    placeholder="State"
                                    required
                                    value=""
                            />
                            <div class="invalid-feedback">Please fill in State.</div>
                        </div>
                        <div class="form-group form-element">
                            <input
                                    type="text"
                                    pattern="^[A-Z]{1,2}[0-9][A-Z0-9]? ?[0-9][A-Z]{2}$"
                                    name="post_code"
                                    id="post_code"
                                    class="form-control custom-rounded-input form-element__input"
                                    placeholder="Post Code"
                                    required
                                    value=""
                            />
                            <div class="invalid-feedback">Please fill in Post Code.</div>
                        </div>
                    </div>
                </div>
                <div class="form-row d-flex justify-content-center pb-5">
                    <button
                            id="save-action-btn"
                            type="submit"
                            class="btn btn-primary position-relative custom-rounded-btn light-green w-50"
                    >
                        Submit
                    </button>
                    <input type="hidden" name="ID" value="${requestScope.EmployeeObject.id}">
                </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<script>
    var selfId = ${requestScope.EmployeeObject.id};
    var roomIdFromFrontUser = ${requestScope.EmployeeObject.id};
    var sessionRoomFromFront = "jok2f4fud_9";
</script>
<jsp:include page="${root}/includes/BundleJsInclude.jsp"/>

<jsp:include page="${root}/includes/FooterZnglik.jsp"/>
</body>
</html>