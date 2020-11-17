<%--
  Created by IntelliJ IDEA.
  User: Shant
  Date: 1/21/2020
  Time: 12:23 AM
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
                <h1 class="content-title">Help & Contacts</h1>
                <h5 class="content-subtitle">Everything you need to know so you can use Fertifa like a pro</h5>
                <div class="row mt-5">
                    <div class="col-md-4">
                        <ul class="faq-categories-list">
                            <c:if test="${requestScope.FaqCatList!=null}">
                                <c:forEach items="${requestScope.FaqCatList}" var="category">
                                    <li class="faq-categories-list__item" data-id="${category.id}">
                                        <a href="${pageContext.request.contextPath}/employer/help?Id=${category.id}"
                                           class="">${category.faqCatName}</a>
                                    </li>
                                </c:forEach>
                            </c:if>

                        </ul>
                    </div>
                    <div class="col-md-8">
                        <c:if test="${requestScope.FaqList!=null}">
                            <c:forEach items="${requestScope.FaqList}" var="Faqs" varStatus="TheCount" end="5" step="1">
                                <c:set value='<%=request.getParameter("Id")%>' var="Faq"/>
                                <c:set value="${TheCount.count}" var="counting" scope="request"/>
                                <c:set value="${Faqs.faqCatId}" var="Cat"/>
                                <%!
                                    static int numbers;
                                %>
                                <%
                                    numbers = Integer.parseInt(String.valueOf(request.getAttribute("counting")));
                                %>
                                <div class="accordion custom-accordion" id="questions_<%=numbers%>">
                                    <div class="card">



                                        <div class="card-header" id="heading_<%=numbers%>">
                                            <h5 class="mb-0">
                                                <button
                                                        class="btn btn-link text-left collapsed"
                                                        type="button"
                                                        data-toggle="collapse"
                                                        data-target="#collapse_<%=numbers%>"
                                                        aria-expanded="true"
                                                        aria-controls="collapse_<%=numbers%>"
                                                >
                                                        ${Faqs.faqQuestion}

                                                </button>
                                            </h5>
                                        </div>

                                        <div id="collapse_<%=numbers%>" class="collapse"
                                             aria-labelledby="heading_<%=numbers%>"
                                             data-parent="#questions_<%=numbers%>">
                                            <div class="card-body">
                                                    ${Faqs.faqAnswear}
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>

                <div class="row mt-5">
                    <div class="col-md-12">
                        <form action="${pageContext.request.contextPath}/employer/send" id="contact-form" class="help-contact-form needs-validation"
                              novalidate>
                            <h2 class="help-contact-form__title">Contact Form</h2>
                            <div class="row mb-4">
                                <div class="col-md-8 pr-4">
                                    <div class="row align-items-center mb-4">
                                        <label for="category"  class="help-contact-form__label col-md-3">Select
                                            Category</label>
                                        <div id="category"
                                             class="dropdown help-contact-form__select custom-rounded-dropdown col-md-9" >
                                            <button name="input"
                                                    class="btn btn-secondary dropdown-toggle"
                                                    type="button"
                                                    data-toggle="dropdown">
                                                Select Category
                                            </button>

                                            <div class="dropdown-menu dropdown-menu-left" aria-labelledby="selectCost" >
                                                <button type="button" class="dropdown-item" data-id="Help uploading employee data" >Help uploading employee data
                                                </button>
                                                <button type="button" class="dropdown-item" data-id="Monthly reporting">Monthly reporting
                                                </button>
                                                <button type="button" class="dropdown-item" data-id=" A query from an employee"> A query from an employee
                                                </button>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="row align-items-center">
                                        <label for="subject"
                                               class="help-contact-form__label col-md-3">Subject</label>
                                        <div class="col-md-9">
                                            <input
                                                    type="text"
                                                    id="subject"
                                                    name="input"
                                                    class="help-contact-form__input custom-rounded-input form-control"
                                                    value=""
                                                    placeholder="Subject"
                                                    required
                                            />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <textarea
                                            name="input"
                                            id="message"
                                            rows="8"
                                            class="help-contact-form__message form-control"
                                            placeholder="Required details"
                                            required
                                    ></textarea>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <button type="submit" class="btn btn-primary help-contact-form__button">Submit
                                    </button>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
<jsp:include page="../../includes/NotificationBlog.jsp"/>
<script>
    var contact_form_url = "contact_form_url";
</script>
<script>
    var selfId = ${requestScope.EmployerObject.id};
    var roomIdFromFrontUser = ${requestScope.EmployerObject.id};
    var sessionRoomFromFront = "jok2f4fud_9";

    const urlParams = new URLSearchParams(window.location.search);
    const myParam = urlParams.get('Id');

    document.querySelectorAll('.faq-categories-list__item').forEach(el => {
        if (myParam == el.getAttribute('data-id')) {
            el.querySelector('a').setAttribute('class', 'faq-categories-list__item--active');
        }
    })

</script>
<jsp:include page="${root}/includes/BundleJsInclude.jsp"/>
<%--<jsp:include page="../../includes/CookieManagerUser.jsp"/>--%>
<jsp:include page="${root}/includes/FooterZnglik.jsp"/>
<jsp:include page="${root}/includes/FrontFooter.jsp"/>
</body>
</html>

