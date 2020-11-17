<%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 1/21/2020
  Time: 3:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <c:set value="${pageContext.request.contextPath}" var="root"/>
    <%
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        if (session.getAttribute("employerEmail") == null) {
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
                                    <img src="<%=request.getContextPath()%>/img/avatar-empty.svg" alt=""/>
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
                <h1 class="content-title" style="letter-spacing: 1px;">Affiliate data</h1>
                <div class="row">
                    <div class="col-lg-7 col-md-12 mb-3">
                        <div class="affiliate-link-place">
                            <span class="affiliate-link-place__link">Link: </span>
                            <%!
                                static String link;
                                static String fullLink;
                                static String affiliateLink;
                            %>

                            <%
                                link = String.valueOf(request.getAttribute("SecretKey"));
                                fullLink = "https://second.fertifabenefits.com/registring/af/registeraf.jsp?link=";
                                affiliateLink = fullLink + link;
                            %>
                            <input
                                    type="text"
                                    class="affiliate-link-place__input custom-rounded-input"
                                    readonly
                                    id="affiliate-link-input"
                                    value="<%=affiliateLink%>"
                            />
                            <button class="custom-rounded-btn affiliate-link-place__button copy-affiliate-link purple">
                                COPY LINK
                            </button>
                        </div>
                        <div class="enrolment-general">
                            <span class="enrolment-general__desc">Clicked users : ${requestScope.CountClicks}</span>
                            <span class="enrolment-general__desc">Five seconds landing page : ${requestScope.CountPageClick}</span>
                            <span class="enrolment-general__desc">Registered Users : ${requestScope.CountRegisters}</span>
                        </div>
                    </div>
                    <div class="col-lg-5 col-md-12 mb-3">
                        <div class="card custom-card card-without-border">
                            <div class="card-body custom-card__body card-without-border__body">
                                <h4 class="domains-title">Whitelisted domains</h4>
                                <div class="response-message-place"></div>
                                <div class="domains-list">
                                    <c:if test="${requestScope.WhiteModelList != null}">
                                        <c:forEach var="whiteDomainList" items="${requestScope.WhiteModelList}">
                                            <c:if test="${whiteDomainList.status == 1}">
                                                <div class="domain-element">
                                                        ${whiteDomainList.whiteDomain}
                                                    <button type="button" class="close delete-affiliate-link"
                                                            data-id="${whiteDomainList.id}">
                                                        <i class="fas fa-times-circle"></i>
                                                    </button>
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </div>
                            </div>
                            <div class="card-footer custom-card__footer domains">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="add-new-domain">
                                            <input
                                                    type="text"
                                                    class="form-control custom-rounded-input"
                                                    id="domain-name"
                                                    placeholder="Add Domain"
                                            />
                                            <button class="add-domain-btn add-link-action" type="submit"></button>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <select
                                                class="selectpicker custom-selectpick choose-domain-show custom-rounded-dropdown"
                                                required
                                                data-display="static"
                                                data-dropup-auto="false"
                                        >
                                            <option value="">Select domain</option>
                                            <c:if test="${requestScope.WhiteModelList != null}">
                                                <c:forEach var="whiteDomainList" items="${requestScope.WhiteModelList}">
                                                    <c:if test="${whiteDomainList.status == 0}">
                                                        <option value="${whiteDomainList.id}">${whiteDomainList.whiteDomain}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- ends adding white domain -->
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-7 col-md-12 mb-3">
                        <div class="card custom-card card-without-border">
                            <div class="card-body custom-card__body card-without-border__body">
                                <div class="charts-loading-data-place"></div>
                                <div style="height: 350px">
                                    <c:if test="${requestScope.CountClicks == 0 && requestScope.CountRegisters == 0}">
                                        <h3 style="text-align: center;line-height: 100%">There is no data</h3>
                                    </c:if>
                                    <c:if test="${requestScope.CountClicks != 0 || requestScope.CountRegisters != 0}">
                                        <canvas class="enrolment-chart-style" id="affiliate-bar"></canvas>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-5 col-md-12 mb-3">
                        <div class="card custom-card card-without-border">
                            <div class="card-body custom-card__body card-without-border__body">
                                <div class="charts-loading-data-place"></div>
                                <div style="height: 350px">
                                    <c:if test="${requestScope.CountClicks == 0 && requestScope.CountRegisters == 0}">
                                        <h3 style="text-align: center;line-height: 100%">There is no data</h3>
                                    </c:if>
                                    <c:if test="${requestScope.CountClicks != 0 || requestScope.CountRegisters != 0}">
                                        <canvas class="enrolment-chart-style" id="affiliate-pie"></canvas>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </main>
    </div>
</div>
<jsp:include page="${root}/includes/NotificationBlog.jsp"/>

<script>
    var affiliate_labels = ['Clicked users', 'Registered Users', 'Five seconds landing page'];
    var affiliate_labels_pie =  ['Lost users','',''];
    var clicked_users = [${requestScope.CountClicksArray.get(0)}];
    var registered_users = [${requestScope.CountClicksArray.get(2)}];
    var page_views = [${requestScope.CountClicksArray.get(1)}];

    var lost_users_percent = [${requestScope.CountClicksArray_Percentage.get(2)}];
    var registered_users_percent = [${requestScope.CountClicksArray_Percentage.get(0)}];
    var clicked_users_percent = [${requestScope.CountClicksArray_Percentage.get(1)}];

    var selfId = ${requestScope.EmployerObject.id};
    var roomIdFromFrontUser = ${requestScope.EmployerObject.id};
    var sessionRoomFromFront = ${requestScope.EmployerObject.id};
    var get_white_domains_list = '';
    var save_white_domain = '/add-White-Domain?user_id=${requestScope.EmployerObject.id}';
    var remove_white_domain_from_list = "/remove-White-Domain?user_id=${requestScope.EmployerObject.id}";
    var add_white_domain_in_list = "/add-dropdown-White-Domain?user_id=${requestScope.EmployerObject.id}";
</script>
<jsp:include page="${root}/includes/BundleJsInclude.jsp"/>
<jsp:include page="${root}/includes/FooterZnglik.jsp"/>
<jsp:include page="${root}/includes/FrontFooter.jsp"/>
<script>
    $(document).ready(function () {
        $('.select2-selection > input.select2-search__field').on('keyup', function (e) {
            if (e.keyCode === 13) {
                console.log($(this).val())
            }
        })

        $('.save-links').on('click', function () {
            console.log($('#add-affiliate').val());
            var _data = {
                white_domain: $('#add-affiliate').val()
            };

            $.ajax({
                url: get_white_domains_list,
                type: 'POST',
                data: _data,
                dataType: 'text',
                success: function (data) {
                    var new_data = JSON.parse(data);

                    if (new_data.status == 1) {

                    } else {

                    }
                }
            });
        });
    })
</script>
<%--<jsp:include page="../../includes/CookieManagerUser.jsp"/>--%>
</body>
</html>

