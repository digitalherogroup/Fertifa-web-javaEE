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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<!DOCTYPE html>
<html>
<head>
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <%
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader ("Expires", 0);
        if(session.getAttribute("userEmail")==null){
            response.sendRedirect(request.getContextPath() + "/employeeloginpage.jsp");

        }
    %>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>


    <jsp:include page="includes/BundleInclude.jsp"/>

    <title>Fertifa | Digital Platform</title>

</head>
<body>
<%
    HttpServletResponse httpResponse = response;
    httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0
    httpResponse.setDateHeader("Expires", 0); // Proxies.
%>
<div class="container-fluid">
    <div class="row">
            <div class="bg-light sidebar">
                <jsp:include page="includes/UsersSideBar.jsp"/>

            </div>
        <jsp:include page="includes/MobileSideBar.jsp"/>
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
                <h1 class="content-title">Billing Info</h1>
                <form action="AddingUserStripeDetail" method="post" class="billing-form needs-validation" novalidate>
                    <div class="row">
                        <div class="col-md-8">
                            <h2 class="billing-form__title">Billing Information</h2>
                            <div class="form-group form-element">
                                <input
                                        type="text"
                                        name="first_name"
                                        id="first_name"
                                        class="form-control custom-rounded-input form-element__input"
                                        placeholder="First Name"
                                        value=""
                                />
                                <div class="invalid-feedback">Please fill in first name.</div>
                            </div>
                            <div class="form-group form-element">
                                <input
                                        type="text"
                                        name="last_name"
                                        id="last_name"
                                        class="form-control custom-rounded-input form-element__input"
                                        placeholder="Last Name"
                                        value=""
                                />
                                <div class="invalid-feedback">Please fill in last name.</div>
                            </div>
                            <div class="form-group form-element">
                                <input
                                        type="text"
                                        name="phone"
                                        id="phone"
                                        class="form-control custom-rounded-input form-element__input"
                                        placeholder="Phone"
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

                                        value=""
                                />
                                <div class="invalid-feedback">Please fill in City.</div>
                            </div>
                            <div class="form-group form-element">
                                <input
                                        type="text"
                                        name="state"
                                        id="state"
                                        class="form-control custom-rounded-input form-element__input"
                                        placeholder="State"

                                        value=""
                                />
                                <div class="invalid-feedback">Please fill in State.</div>
                            </div>
                            <div class="form-group form-element">
                                <input
                                        type="text"
                                        name="post_code"
                                        id="post_code"
                                        class="form-control custom-rounded-input form-element__input"
                                        placeholder="Post Code"

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
<%--                                        <span class="card-data-info__card mb-2">Visa•••• 1234</span>--%>
<%--                                        <span class="card-data-info__exp">Expires 12/22</span>--%>
                                        <span class="card-data-info__exp">Total:
                                            <fmt:formatNumber type="number" maxFractionDigits="3"
                                                          value='${requestScope.Total}'/>
                                       </span>
                                    </div>
                                    <button type="submit" class="btn btn-secondary custom-rounded-btn w-100 mt-4 purple">
                                        CONFIRM
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </main>
    </div>
</div>

<div class="modal fade" id="cart-modal-delete">
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
                    <input type="hidden" class="hidden-selected-item" value="" />
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
</script>

<script>
    var selfId = ${requestScope.ID};
    var roomIdFromFrontUser = ${requestScope.ID};
    var sessionRoomFromFront = "jok2f4fud_9";
</script>
<%--<jsp:include page="includes/BundleJsInclude.jsp"/>--%>
<jsp:include page="includes/CookieManagerUser.jsp"/>
<jsp:include page="${root}/includes/FrontFooter.jsp"/>

</body>
</html>

