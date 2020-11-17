<%--
  Created by IntelliJ IDEA.
  User: Shant
  Date: 1/31/2020
  Time: 11:19 PM
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

                <h1 class="content-title">Checkout</h1>
                <div class="row">
                    <div class="col-md-8">
                        <div class="checkout-list">
                            <div class="checkout-list__header">
                                <div class="checkout-list__row-item checkout-list__service">Service / product</div>
                                <div class="checkout-list__row-item checkout-list__details">Details</div>
                                <div class="checkout-list__row-item checkout-list__address">Address</div>
                                <div class="checkout-list__row-item checkout-list__cost">Cost</div>
                                <div class="checkout-list__row-item checkout-list__del"></div>
                            </div>
                            <%!
                                static int count;
                            %>
                            <%
                                count = Integer.parseInt(String.valueOf(request.getAttribute("count")));
                            %>
                            <c:if test="${requestScope.ShoppingCartList!=null}">
                            <c:forEach items="${requestScope.ShoppingCartList}" var="cart" varStatus="TheCount">
                            <div class="checkout-list__item">
                                <c:set value="${TheCount.count}" var="count" scope="request"/>

                                <div class="checkout-list__row-item checkout-list__service">${cart.serviceName}</div>

                                    <%--  <div class="checkout-list__row-item checkout-list__details">${cart.getOrderDate}</div>--%>
                                <c:set var="tringdate" value="${cart.order_date}" scope="request"/>
                                <%!
                                    static String str;
                                    static int check;
                                %>
                                <%
                                    str = String.valueOf(request.getAttribute("tringdate"));
                                    check = 0;
                                    if (str.contains(".")) {
                                        check = 0;
                                    } else {
                                        check = 1;
                                    }
                                %>
                                <c:if test="<%=check == 0 %>">
                                <div class="checkout-list__row-item checkout-list__details">${cart.getOrderDate}
                                    </c:if>
                                    <c:if test="<%=check == 1%>">
                                    <div class="checkout-list__row-item checkout-list__details">${cart.order_date}
                                        </c:if>

                                    </div>

                                    <div class="checkout-list__row-item checkout-list__address">

                                    </div>
                                    <div class="checkout-list__row-item checkout-list__cost">
                                        £ <fmt:formatNumber type="number" maxFractionDigits="3"
                                                            value=' ${cart.price}'/>
                                    </div>
                                    <c:set value="${cart.price}" var="price" scope="request"/>
                                    <div class="checkout-list__row-item checkout-list__del">
                                        <a href="" data-id="${cart.id}" class="open-cart-delete-modal"><i class="fas fa-times"></i></a>
                                    </div>
                                </div>


                                </c:forEach>
                                </c:if>
                                <div class="checkout-list__item">
                                    <div class="checkout-list__row-item checkout-list__total-text ml-auto">Total:
                                        £ <fmt:formatNumber type="number" maxFractionDigits="3"
                                                            value='<%=request.getAttribute("Total")%>'/>
                                    </div>

                                    <input type="hidden" value='<%=request.getAttribute("Total")%>' name="total"/>
                                </div>

                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="card custom-card mb-3">
                                <div class="card-body custom-card__body">
                                    <span>Details:</span>
                                    <p class="small">
                                        Free cancellation up to 10 days prior to delivery<br/>
                                        High security payment process<br/>
                                        Complete data privacy<br/>
                                    </p>
                                </div>
                            </div>
                            <form action="${root}/employee/payment-default" method="GET">
                                <!-- choose another card from the list of your cards -->
                                <div class="card custom-card mb-3">
                                    <div class="card-body custom-card__body">
                                        <span>Details:</span>
                                        <div class="card-data-info">
                                            <c:if test="${requestScope.DefaultCard != null}">
                                                <button type="button" class="dropdown-item"
                                                        data-id="Accounts and billing">**** ****
                                                    **** ${requestScope.DefaultCard.last4Digits}
                                                    - ${requestScope.DefaultCard.month}/${requestScope.DefaultCard.year}
                                                </button>
                                                <input type="hidden" name="payment-card"
                                                       value="${requestScope.DefaultCard.cardId}">
                                                <input type="hidden" value='<%=request.getAttribute("Total")%>'
                                                       name="total"/>

                                                <c:if test="${requestScope.ShoppingCartList!=null}">
                                                    <c:forEach items="${requestScope.ShoppingCartList}" var="shop">
                                                        <input type="hidden" value='${shop.serviceName}' name="description"/>
                                                <input type="hidden" name="priceId"  value="${shop.priceId}"/>
                                                <input type="hidden" name="productId" value="${shop.productId}"/>
                                                    </c:forEach>
                                                </c:if>
                                            </c:if>
                                        </div>
                                        <button class="btn btn-secondary custom-rounded-btn w-100 mt-4 purple"  <% if (count == 0) {%>
                                                disabled <%}%>>
                                            PAY USING DEFAULT CARD
                                        </button>
                                    </div>
                                </div>
                            </form>

                            <form action="${root}/employee/payment-default" method="GET">
                                <!-- choose Default card to pay -->
                                <c:if test="${requestScope.Cards != null }">
                                    <c:if test="${requestScope.Cards.size() != 0}">
                                    <div class="card  custom-card mb-3">
                                        <div class="card-body custom-card__body">
                                            <span>Select another card:</span>
                                            <div class="row align-items-center mb-4">
                                                <div class="form-group w-100 d-flex align-items-center form-element">
                                                    <div id="category"
                                                         class="dropdown w-100 help-contact-form__select custom-rounded-dropdown col-md-12">
                                                        <select class="selectpicker cart-select-payment-method w-100 custom-selectpick custom-rounded-dropdown mr-0"
                                                                name="payment-card">

                                                            <option value="" name="payment" selected>Payment
                                                                Cards
                                                            </option>
                                                            <c:forEach var="cards" items="${requestScope.Cards}">
                                                                <option name="payment-card" value="${cards.cardId}">**** ****
                                                                    **** ${cards.last4Digits}
                                                                    - ${cards.month}/${cards.year}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <input type="hidden" value='<%=request.getAttribute("Total")%>' name="total"/>

                                            <c:if test="${requestScope.ShoppingCartList!=null}">
                                                <c:forEach items="${requestScope.ShoppingCartList}" var="shop">
                                                    <input type="hidden" value='${shop.serviceName}' name="description"/>
                                                    <input type="hidden" name="priceId"  value="${shop.priceId}"/>
                                                    <input type="hidden" name="productId" value="${shop.productId}"/>
                                                </c:forEach>
                                            </c:if>
                                            <button disabled class="btn pay-using-another-card btn-secondary custom-rounded-btn w-100 mt-4 purple" <% if (count == 0) {%>
                                                    disabled <%}%>>
                                                PAY USING ANOTHER CARD
                                            </button>

                                        </div>
                                    </div>
                                    </c:if>
                                </c:if>
                            </form>
                            <!-- new Card Section -->
                            <form action="${root}/employee/payment-info" method="GET">
                                <div class="card custom-card mb-3">
                                    <div class="card-body custom-card__body">
                                        <span>Details:</span>
                                        <div class="card-data-info">
                                        </div>
                                        <button class="btn btn-secondary custom-rounded-btn w-100 mt-4 purple"  <% if (count == 0) {%>
                                                disabled <%}%>>
                                            PAY WITH A NEW CARD
                                        </button>
                                        <input type="hidden" value='<%=request.getAttribute("Total")%>' name="total"/>
                                        <c:if test="${requestScope.ShoppingCartList!=null}">
                                            <c:forEach items="${requestScope.ShoppingCartList}" var="shop">
                                                <input type="hidden" value='${shop.serviceName}' name="description"/>
                                                <input type="hidden" name="priceId"  value="${shop.priceId}"/>
                                                <input type="hidden" name="productId" value="${shop.productId}"/>
                                            </c:forEach>
                                        </c:if>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </main>
    </div>

    <jsp:include page="${root}/includes/NotificationBlogUser.jsp"/>
</div>

<div class="modal fade" id="cart-modal-delete">
    <div class="modal-dialog custom-modal">
        <div class="modal-content custom-modal__content bg-default">
            <form action="" method="post" id="cart-modal-delete-form">
                <div class="modal-header custom-modal__header">
                    <h4 class="modal-title custom-modal__title">Delete</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body custom-modal__body text-center">
                    <p>Would you like to delete a product from cart?</p>
                    <input type="hidden" name="orderid" class="hidden-selected-item" value=""/>
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
    <!-- /.modal-dialog -->
</div>

<script>
    var show_service_data_ajax = "show_service_data_ajax";
    var delete_item_from_shoppingcart = '/employee/deletecarddetail';
</script>

<script>
    var selfId = ${requestScope.EmployeeObject.id};
    var roomIdFromFrontUser = ${requestScope.EmployeeObject.id};
    var sessionRoomFromFront = "jok2f4fud_9";
</script>
<jsp:include page="${root}/includes/BundleJsInclude.jsp"/>
<jsp:include page="${root}/includes/FrontFooter.jsp"/>
<%--<jsp:include page="../../includes/CookieManagerUser.jsp"/>--%>
<script>
    $(document).on('submit', '#cart-modal-delete-form', function (e) {
        e.preventDefault();

        const data_id = $('#cart-modal-delete-form').find('.hidden-selected-item').val();

        const _data = {
            data_id: data_id
        };

        console.log('data_id', data_id);

        $.ajax({
            url: delete_item_from_shoppingcart,
            type: 'POST',
            data: _data,
            dataType: 'text',
            beforeSend: function () {
                $('#cart-modal-delete-form').find('.btn-danger').prop('disabled', true)
            },
            success: function (data) {
                const newData = JSON.parse(data);

                if (newData.status === 'success') {
                    $('#cart-modal-delete').modal('hide');
                    location.reload()
                }

                $('#cart-modal-delete-form').find('.btn-danger').prop('disabled', false)
            },
            error: function (data) {

            }
        });
    });
</script>
</body>
</html>
