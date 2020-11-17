<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="java.text.ParseException" %><%--
  Created by IntelliJ IDEA.
  User: Shant
  Date: 1/14/2020
  Time: 10:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html >
<head>
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <%
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader ("Expires", 0);
        if(session.getAttribute("employerEmail")==null){
            response.sendRedirect(request.getContextPath() + "/employer/employer-dashboard");
        }
    %>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>


    <jsp:include page="../../includes/BundleInclude.jsp"/>

       <title>Fertifa | Digital Platform</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="bg-light sidebar">
            <jsp:include page="../../includes/CompanySideBar.jsp"/>
        </div>
        <jsp:include page="../../includes/MobileSideBar.jsp"/>
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
                                        <img src="<%=request.getContextPath()%>/WEB-INF/jsp/img/avatar-empty.svg" alt=""/>
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
                <h1 class="content-title">Employer tracker</h1>
                <div class="row">
                    <div class="col-md-8">
                        <div class="employee-tracker-list">
                            <form action="${pageContext.request.contextPath}/employer/enrolmentdata" method="POST" id="all_users_form">

                                <table id="Tracker-list" class="table import-table table-hover">

                                    <thead>
                                    <tr>
                                        <td>
                                            <div class="form-check">
                                                <input type="checkbox" name="all" class="form-check-input"
                                                       id="check_all"/>
                                            </div>
                                        </td>
                                        <th scope="col">Work e-mail <img
                                                src="<%=request.getContextPath()%>/WEB-INF/jsp/img/sort-alpha.svg" alt=""/></th>
                                        <th scope="col">Date sent <img
                                                src="<%=request.getContextPath()%>/WEB-INF/jsp/img/filter-light.svg" alt=""/>
                                        </th>
                                        <th scope="col">Full name <img
                                                src="<%=request.getContextPath()%>/WEB-INF/jsp/img/sort-alpha.svg" alt=""/></th>
                                        <td scope="col" class="text-right pr-3 p-0">
                                            <div class="dropdown custom-rounded-dropdown fix-width">
                                                <c:if test="${requestScope.CountAllUsers != 0}">
                                                    <button disabled class="btn btn-secondary dropdown-toggle enable-disable" type="button"
                                                            data-toggle="dropdown">
                                                        Edit
                                                    </button>
                                                </c:if>


                                                <div class="dropdown-menu sml">
                                                    <button
                                                            type="submit"
                                                            class="btn btn-info dropdown-item send-invitattion-all"
                                                            href="#"
                                                    >
                                                        Send
                                                    </button>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:if test="${requestScope.EmployeeModelList != null}">
                                        <c:forEach items="${requestScope.EmployeeModelList}" var="TempUsers"
                                                   varStatus="TheCount">
                                            <tr data-id="${TheCount.count}">
                                                <th>
                                                    <div class="form-check">
                                                        <input type="checkbox" name="id[${TheCount.count}]"
                                                               value="${TempUsers.id}"
                                                               class="form-check-input single_checkox"
                                                               id="${TheCount.count}"/>
                                                    </div>
                                                </th>
                                                <td>
                                                        ${TempUsers.email}
                                                </td>

                                                <td>${TempUsers.getCreationDate}
                                                </td>
                                                <td>${TempUsers.firstName} ${TempUsers.lastName}</td>

                                                <td class="text-right pr-3">
                                                    <button type="button"
                                                            class="delete-from-tracker open-invitation-modal" data-toggle="tooltip" data-placement="top" title="Send Invitation"
                                                            data-id="${TempUsers.id}">
                                                        <img src="<%=request.getContextPath()%>/WEB-INF/jsp/img/resend.svg" alt=""/>
                                                    </button>
                                                    <button type="button" class="delete-from-tracker open-delete-modal" data-toggle="tooltip" data-placement="top" title="Delete"
                                                            data-id="${TempUsers.id}">
                                                        <img src="<%=request.getContextPath()%>/WEB-INF/jsp/img/trash.svg" alt=""/>
                                                    </button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                    </tbody>


                                </table>

                            </form>
                        </div>

                    </div>
                    <div class="col-md-4">
                        <div class="card custom-card employees-tracker-total">
                            <div class="card-body custom-card__body">
                                <ul class="employees-tracker-total__info">
                                    <li>Added in last month
                                        <span>:</span><span>+${requestScope.ThreeMonthsCount}</span></li>
                                    <li>Removed in last month
                                        <span>:</span><span>-${requestScope.CountUsersDeletedLastThreeMonths}</span></li>
                                    <li>Total employees <span>:</span><span>${requestScope.CountAllUsers}</span></li>
                                </ul>
                            </div>
                        </div>

                        <p class="text-center group-invitation-text">
                            <strong>Invite employee by e-mail or upload e-mail list</strong>
                        </p>
                        <div class="actions-place d-flex">
                            <button
                                    data-toggle="modal"
                                    data-target="#send-email-notification"
                                    class="btn btn-info custom-rounded-btn purple"
                            >
                                By E-mail
                            </button>
                            <button
                                    data-toggle="modal"
                                    data-target="#upload-file-notification"
                                    class="btn btn-info custom-rounded-btn purple-line"
                            >
                                com.fertifa.app.upload CSV file
                            </button>
                            <a href="#0" data-link="http://www.fertifabenefits.com/sample.csv" class="download-template-file">Download .csv template file</a>
                        </div>

                    </div>
                </div>
            </div>
        </main>
    </div>
</div>

<jsp:include page="../../includes/NotificationBlog.jsp"/>

<div class="modal fade" id="send-email-notification">
    <div class="modal-dialog custom-modal">
        <div class="modal-content custom-modal__content bg-default">
            <form action="${pageContext.request.contextPath}/employer/SendForJustOne" method="post">
                <div class="modal-header custom-modal__header">
                    <h4 class="modal-title custom-modal__title">Invite employee by e-mail</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body custom-modal__body">
                    <div class="row justify-content-center">
                        <div class="col-md-10">
                            <div class="form-group">
                                <input type="text" class="custom-rounded-input" placeholder="First name" name="Firstname"
                                       required/>
                            </div>
                            <div class="form-group">
                                <input type="text" class="custom-rounded-input" placeholder="Last name" name="Lastname"
                                       required/>
                            </div>
                            <div class="form-group">
                                <input type="email" class="custom-rounded-input" placeholder="Email" name="Email"
                                       required/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer custom-modal__footer justify-content-center">
                    <button
                            type="button"
                            class="btn btn-secondary custom-rounded-btn grey-line click-dismiss-del-modal"
                            data-dismiss="modal"
                    >
                        Cancel
                    </button>
                    <button type="submit" class="btn btn-secondary custom-rounded-btn light-green">Send</button>
                </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
</div>

<div class="modal fade" id="upload-file-notification">
    <div class="modal-dialog custom-modal">
        <div class="modal-content custom-modal__content bg-default">
            <form action="${pageContext.request.contextPath}/employer/upload" method="post"
                  enctype="multipart/form-data">
                <div class="modal-header custom-modal__header">
                    <h4 class="modal-title custom-modal__title">com.fertifa.app.upload e-mail list</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body custom-modal__body">
                    <div class="row justify-content-center">
                        <div class="col-md-10">
                            <div class="custom-file custom-rounded-file">
                                <input type="file" class="custom-file-input" name="Image" id="validatedCustomFile"
                                       required/>
                                <label class="custom-file-label" for="validatedCustomFile">com.fertifa.app.upload CSV
                                    file</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer custom-modal__footer justify-content-center">
                    <button
                            type="button"
                            class="btn btn-secondary custom-rounded-btn grey-line click-dismiss-del-modal"
                            data-dismiss="modal"
                    >
                        Cancel
                    </button>
                    <button type="submit" class="btn btn-secondary custom-rounded-btn light-green">Send</button>
                </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<div class="modal fade" id="modal-delete">
    <div class="modal-dialog custom-modal">

        <div class="modal-content custom-modal__content bg-default">
            <form action="${pageContext.request.contextPath}/employer/delete" method="post">
                <div class="modal-header custom-modal__header">
                    <h4 class="modal-title custom-modal__title">Delete</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body custom-modal__body text-center">
                    <p>Would you like to delete a user?</p>
                    <input type="hidden" class="hidden-selected-user" value="" name="idDelete"/>
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
<!-- /.modal -->
<div class="modal fade" id="modal-invitation">
    <div class="modal-dialog custom-modal">
        <div class="modal-content custom-modal__content bg-default">
            <form method="POST" action="${pageContext.request.contextPath}/employer/sendingone">
                <div class="modal-header custom-modal__header">
                    <h4 class="modal-title custom-modal__title">Send Invitattion</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body custom-modal__body text-center">
                    <p>Would you like to send invitattion to user?</p>
                    <input type="hidden" class="hidden-selected-user" value="" name="id"/>
                </div>
                <div class="modal-footer custom-modal__footer justify-content-center">
                    <button
                            type="button"
                            class="btn btn-secondary custom-rounded-btn grey-line click-dismiss-invitation-modal"
                            data-dismiss="modal"
                    >
                        Close
                    </button>
                    <button type="submit"
                            class="btn btn-success custom-rounded-btn light-green ">
                        Send
                    </button>
                </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
</div>
<!-- /.modal -->
<script>
    // invitation link for ajax request
    var send_invitation_by_ajax = "/EnrollmentDataCompany";
    var roomIdFromFrontUser = ${requestScope.EmployerObject.id};
    var selfId = ${requestScope.EmployerObject.id};
    var sessionRoomFromFront = "jok2f4fud_9";
    var checkSuccess = '${sessionScope.session_success_message}';
    var checkError = '${sessionScope.session_error_message}';
</script>
<jsp:include page="${root}/includes/BundleJsInclude.jsp"/>
<%--<jsp:include page="../../includes/CookieManagerUser.jsp"/>--%>
<jsp:include page="${root}/includes/FooterZnglik.jsp"/>
<jsp:include page="${root}/includes/FrontFooter.jsp"/>

</body>
</html>
