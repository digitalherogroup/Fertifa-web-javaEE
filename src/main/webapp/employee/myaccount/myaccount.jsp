<%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 2/5/2020
  Time: 10:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

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
                <div class="main-title-part">
                    <h1 class="content-title mobile-title">My Account</h1>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <ul class="nav nav-pills mb-5 mt-0 custom-tabs" id="consultationTab" role="tablist">
                            <li class="nav-item custom-tabs__item mr-4">
                                <a
                                        class="nav-link custom-tabs__item--link active"
                                        id="payment-methods-tab"
                                        data-selected="1"
                                        data-toggle="tab"
                                        href="#payment-methods"
                                        role="tab"
                                        aria-controls="payment-methods"
                                        aria-selected="true"
                                >PAYMENT METHODS</a
                                >
                            </li>
                            <li class="nav-item custom-tabs__item">
                                <a
                                        class="nav-link custom-tabs__item--link"
                                        id="transaction-history-tab"
                                        data-selected="2"
                                        data-toggle="tab"
                                        href="#transaction-history"
                                        role="tab"
                                        aria-controls="transaction-history"
                                        aria-selected="false"
                                >TRANSACTION HISTORY</a
                                >
                            </li>
                            <li class="nav-item custom-tabs__item">
                                <a
                                        class="nav-link custom-tabs__item--link"
                                        id="account-settings-tab"
                                        data-selected="3"
                                        data-toggle="tab"
                                        href="#account-settings"
                                        role="tab"
                                        aria-controls="account-settings"
                                        aria-selected="false"
                                >ACCOUNT SETTINGS</a
                                >
                            </li>
                        </ul>
                        <div class="tab-content custom-tab-content" id="consultationContent">
                            <div
                                    class="tab-pane custom-tab-content__tab fade show active"
                                    id="payment-methods"
                                    role="tabpanel"
                                    aria-labelledby="payment-methods-tab"
                            >
                                <div class="credit-catrs">
                                    <c:if test="${requestScope.Cards != null}">
                                        <c:forEach items="${requestScope.Cards}" var="cardList">
                                            <a
                                                    class="credit-catrs__item <c:if test="${cardList.defaultCard == 1}">active-card </c:if>
                                                    <c:if test="${cardList.defaultCard != 1}">change-card-default</c:if>"
                                                    data-id="${cardList.cardId}"
                                            >

                                                <div class="credit-catrs__cart-type">
                                                    <div class="cart">

                                                        <img
                                                                <c:if test="${cardList.cardType.toLowerCase() == 'visa'}">src="${root}/img/visa-cart.svg"</c:if>
                                                                <c:if test="${cardList.cardType.toLowerCase() == 'master'}">src="${root}/img/master-cart.svg"</c:if>
                                                                <c:if test="${cardList.cardType.toLowerCase() == 'american'}">src="${root}/img/american-cart.svg"</c:if>
                                                                alt=""/>
                                                    </div>
                                                </div>
                                                <div class="credit-catrs__content">
                                                    <div class="cart-info">
                                                        <span class="cart-name">Card: </span>
                                                        <span>Visa **** ${cardList.last4Digits}</span>
                                                        <span>Expires: ${cardList.month}/${cardList.year}</span>
                                                    </div>
                                                    <c:if test="${requestScope.cardCount > 0}">
                                                        <c:if test="${cardList.defaultCard != 1}">
                                                            <button
                                                                    class="credit-catrs__delete open-payment-delete-modal"
                                                                    data-id="${cardList.cardId}"
                                                            ></button>
                                                        </c:if>
                                                    </c:if>
                                                </div>
                                            </a>
                                        </c:forEach>
                                    </c:if>


                                    <div class="credit-catrs__item add-cart">
                                        <a
                                                href="${root}/employee/myaccount/addnewcard"
                                                class="btn btn-info custom-rounded-btn add-payment-method grey-line"
                                        >ADD PAYMENT METHOD</a
                                        >
                                    </div>
                                </div>
                            </div>
                            <div
                                    class="tab-pane custom-tab-content__tab fade"
                                    id="transaction-history"
                                    role="tabpanel"
                                    aria-labelledby="transaction-history-tab"
                            >
                                <table
                                        id="transactions-list"
                                        class="table import-table table-hover transactions-list-table"
                                >
                                    <thead>
                                    <tr>
                                        <th>Date sent <img src="../../img/sort-alpha.svg" alt=""/></th>
                                        <th>Service Name <img src="../../img/filter-light.svg" alt=""/></th>
                                        <%-- <th>Appointment Dates <img src="../../img/sort-alpha.svg" alt=""/></th>--%>
                                        <%--  <th>Company <img src="../../img/sort-alpha.svg" alt=""/></th>--%>
                                        <th>Price <img src="../../img/filter-light.svg" alt=""/></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${requestScope.OrdersList != null}">
                                        <c:forEach items="${requestScope.OrdersList}" var="orders">

                                            <tr>
                                                <td>${orders.dateCreated}</td>
                                                <td>${orders.serviceName}</td>
                                                    <%-- <td>${orders.date}</td>--%>
                                                <td>£ <fmt:formatNumber type="number" maxFractionDigits="3"
                                                                        value=' ${orders.servicePrice}'/></td>
                                            </tr>

                                        </c:forEach>
                                    </c:if>
                                    </tbody>
                                </table>
                            </div>
                            <div
                                    class="tab-pane custom-tab-content__tab fade"
                                    id="account-settings"
                                    role="tabpanel"
                                    aria-labelledby="account-settings-tab"
                            >
                                <div class="row mb-5">
                                    <div class="col-12">
                                        <div class="dash-user-info">
                                            <div class="loader-place"></div>
                                            <div class="dash-user-info__image">
                                                <c:if test="${requestScope.EmployeeObject != null}">

                                                    <c:set value="${requestScope.EmployeeObject.userImage}"
                                                           var="Image"/>
                                                    <c:choose>
                                                        <c:when test="${Image != null}">

                                                            <img class="user-image-for-show"
                                                                 src="${root}/${requestScope.EmployeeObject.userImage}"
                                                                 alt=""/>
                                                            <div class="custom-file user-image-manage hidden-first">
                                                                <input type="file"
                                                                       data-file="${requestScope.EmployeeObject.userImage}"
                                                                       accept=".png,.jpg,.jpeg" id="user-image-manage"
                                                                       class="custom-file-input" value="">
                                                                <label class="custom-file-label"
                                                                       for="user-image-manage"><i
                                                                        class="fas fa-plus"></i></label>
                                                            </div>
                                                        </c:when>

                                                        <c:when test="${Image == null}">
                                                            <img class="user-image-for-show"
                                                                 src="<%=request.getContextPath()%>/img/avatar-empty.svg"
                                                                 alt=""/>
                                                            <div class="custom-file user-image-manage hidden-first">
                                                                <input type="file"
                                                                       data-file="${root}/img/avatar-empty.svg"
                                                                       accept=".png,.jpg,.jpeg" id="user-image-manage"
                                                                       class="custom-file-input" value="">
                                                                <label class="custom-file-label"
                                                                       for="user-image-manage"><i
                                                                        class="fas fa-plus"></i></label>
                                                            </div>
                                                        </c:when>
                                                    </c:choose>
                                                </c:if>
                                            </div>
                                            <div class="dash-user-info__content">
                                                <form action="" id="user-edit-form" class="user-edit-form" novalidate>
                                                    <c:if test="${requestScope.EmployeeObject != null}">
                                                        <h1 id="edited_user_company"
                                                            class="dash-user-info__content-company shown-first">${requestScope.EmployeeObject.firstName} ${requestScope.EmployeeObject.lastName} </h1>
                                                        <input
                                                                type="text"
                                                                class="form-control hidden-first form-control-sm custom-rounded-input custom-editable-input"
                                                                name="first_name"
                                                                id="first_name"
                                                                placeholder="First Name"
                                                                value="${requestScope.EmployeeObject.firstName}"
                                                        />
                                                        <input
                                                                type="text"
                                                                class="form-control hidden-first form-control-sm custom-rounded-input custom-editable-input"
                                                                name="last_name"
                                                                id="last_name"
                                                                placeholder="Last Name"
                                                                value="${requestScope.EmployeeObject.lastName}"
                                                        />
                                                        <div class="user-main-info employee d-flex">
                                                            <div class="user-main-info__item">
                                                                <span class="dash-user-info__content-label">E-mail:</span>
                                                                <p class="dash-user-info__content-value">${requestScope.EmployeeObject.email}</p>
                                                            </div>
                                                            <div class="user-main-info__item">
                                                                <span class="dash-user-info__content-label">Phone:</span>
                                                                <p id="edited_user_phone"
                                                                   class="dash-user-info__content-value shown-first">
                                                                        ${requestScope.EmployeeObject.phoneNumber}
                                                                </p>
                                                                <input
                                                                        type="text"
                                                                        class="form-control hidden-first form-control-sm custom-rounded-input custom-editable-input"
                                                                        name="user_phone"
                                                                        id="user_phone"
                                                                        placeholder="Phone Number"
                                                                        value="${requestScope.EmployeeObject.phoneNumber}"

                                                                />
                                                                <span class="invalid-phone-number">Please enter a valid phone number (excluding any letters or symbols).</span>
                                                            </div>
                                                            <div class="user-main-info__item">
                                                                <span class="dash-user-info__content-label">Address:</span>
                                                                <p id="edited_user_address"
                                                                   class="dash-user-info__content-value shown-first">
                                                                        ${requestScope.EmployeeObject.address}
                                                                </p>
                                                                <input
                                                                        type="text"
                                                                        class="form-control hidden-first form-control-sm custom-rounded-input custom-editable-input"
                                                                        name="user_address"
                                                                        id="user_address"
                                                                        placeholder="Address"
                                                                        value="${requestScope.EmployeeObject.address}"
                                                                />
                                                            </div>
                                                            <div class="user-main-info__item">
                                                                    <%-- <span class="dash-user-info__content-label">Card:</span>
                                                                     <p id="edited_user_card" class="dash-user-info__content-value">
                                                                         &lt;%&ndash;Visa **** 5678 Expires: 12/22&ndash;%&gt;
                                                                     </p>--%>
                                                            </div>
                                                        </div>

                                                    </c:if>
                                                </form>
                                            </div>
                                            <div class="dash-user-info__edit">
                                                <button class="dash-user-info__edit-profile edit-profile-action">Edit
                                                    profile
                                                </button>
                                                <button class="dash-user-info__edit-profile save-user-profile-action">
                                                    Save
                                                </button>
                                            </div>
                                        </div>
                                    </div>


                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>

<div class="modal fade" id="payment-modal-delete">
    <div class="modal-dialog custom-modal">
        <div class="modal-content custom-modal__content bg-default">
            <form action="${root}/employee/myaccount/delete-card" method="post">
                <div class="modal-header custom-modal__header">
                    <h4 class="modal-title custom-modal__title">Delete</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body custom-modal__body text-center">
                    <p>Would you like to delete payment method?</p>
                    <input type="hidden" class="hidden-selected-item" name="card_id" value=""/>
                </div>
                <div class="modal-footer custom-modal__footer justify-content-center">
                    <button
                            type="button"
                            class="btn btn-secondary custom-rounded-btn grey-line click-dismiss-del-modal"
                            data-dismiss="modal"
                    >
                        Cancel
                    </button>
                    <button type="submit" class="btn btn-danger custom-rounded-btn light-red">Delete</button>
                </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
</div>
<div class="modal fade" id="change-default-card">
    <div class="modal-dialog custom-modal">
        <div class="modal-content custom-modal__content bg-default">
            <form action="${root}/employee/myaccount/addnewcard/update" method="post">
                <div class="modal-header custom-modal__header">
                    <h4 class="modal-title custom-modal__title">Change to default</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body custom-modal__body text-center">
                    <p>Would you like to change the default card?</p>
                    <input type="hidden" class="hidden-selected-item" name="card_id" value=""/>
                </div>
                <div class="modal-footer custom-modal__footer justify-content-center">
                    <button
                            type="button"
                            class="btn btn-secondary custom-rounded-btn grey-line click-dismiss-del-modal"
                            data-dismiss="modal"
                    >
                        Cancel
                    </button>
                    <button type="submit" class="btn btn-secondary custom-rounded-btn change-card-btn">
                        Change
                    </button>
                </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

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

<script>
    var selfId =${requestScope.EmployeeObject.id};
    var roomIdFromFrontUser = ${requestScope.EmployeeObject.id};
    var sessionRoomFromFront = "jok2f4fud_9";
    var update_user_ajax = '<%=request.getContextPath()%>/UpdateUsersC?id=${requestScope.EmployeeObject.id}';
    /*  var update_user_ajax = 'UpdateUsersC';*/
    localStorage.removeItem('formData')
</script>
<jsp:include page="${root}/includes/BundleJsInclude.jsp"/>
<jsp:include page="${root}/includes/FrontFooter.jsp"/>
<jsp:include page="${root}/includes/FooterZnglik.jsp"/>
</body>
</html>
