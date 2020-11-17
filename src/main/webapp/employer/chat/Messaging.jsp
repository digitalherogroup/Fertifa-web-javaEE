<%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 1/22/2020
  Time: 6:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
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

    <title>Dashboard</title>
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
                    <c:if test="${requestScope.EmployerObject != null}">
                            <div class="user__info">
                                <span class="user__company"></span>
                                <span class="user__name">${requestScope.EmployerObject.comapny} </span>
                            </div>
                            <c:choose>

                                <c:when test="${requestScope.EmployerObject.userImage == null}">
                        <div class="user__image">
                                    <img src="${root}/WEB-INF/jsp/img/avatar-empty.svg" alt=""/>
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
                <h1 class="content-title">messages</h1>
                <div class="row">
                    <div class="col-md-4">
                        <ul class="nav nav-pills mb-3 custom-tabs" id="messageTab" role="tablist">
                            <li class="nav-item custom-tabs__item">
                                <a
                                        class="nav-link custom-tabs__item--link active"
                                        id="chat-tab"
                                        data-selected="3"
                                        data-toggle="tab"
                                        href="#chat"
                                        role="tab"
                                        aria-controls="chat"
                                        aria-selected="true"
                                >Chat</a
                                >
                            </li>
                            <li class="nav-item custom-tabs__item">
                                <a
                                        class="nav-link custom-tabs__item--link"
                                        id="notification-tab"
                                        data-selected="1"
                                        data-toggle="tab"
                                        href="#notification"
                                        role="tab"
                                        aria-controls="notification"
                                        aria-selected="false"
                                >Notification</a
                                >
                            </li>
                            <li class="nav-item custom-tabs__item list">
                                <a href="" class="custom-tabs__item--session open-new-session-window"
                                ><i class="fa fa-plus-circle"></i> NEW INQUIRY</a
                                >
                            </li>
                        </ul>
                        <div class="tab-content custom-tab-content" id="messageTabContent">
                            <div
                                    class="tab-pane custom-tab-content__tab fade show active"
                                    id="chat"
                                    role="tabpanel"
                                    aria-labelledby="chat-tab"
                                    data-selected-content="3"
                            >
                                <div class="search-place">
                                    <button class="search-place__button"></button>
                                    <input
                                            type="text"
                                            class="form-control search-place__input"
                                            id="search_chat"
                                            placeholder="Search"
                                    />
                                </div>
                                <div class="sessions-block scrollbar">
                                    <ul class="sessions-list">
                                        <c:if test="${requestScope.ChatSessionListString != null}">
                                            <c:forEach items="${requestScope.ChatSessionListString}"
                                                       var="ChatAdmin">
                                                <li class="sessions-list__item">
                                                    <a href="${root}/employer/chat/conversation?id=${ChatAdmin.chatSessionToken}" class=" get-session-item"
                                                       data-session="${ChatAdmin.chatSessionToken}">
                                                        <c:set var="important" value="${ChatAdmin.important_for_user}"/>
                                                        <c:choose>
                                                            <c:when test="${important != 0}">
                                                                <div class="sessions-list__star">
                                                                    <input class="star select-star-important" data-sessionId="${ChatAdmin.chatSessionToken}" type="checkbox" title="bookmark page" checked/>
                                                                </div>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <div class="sessions-list__star">
                                                                    <input class="star select-star-important" data-sessionId="${ChatAdmin.chatSessionToken}" type="checkbox" title="bookmark page"/>
                                                                </div>
                                                            </c:otherwise>
                                                        </c:choose>

                                                        <div class="sessions-list__center">
                                                            <h3 class="sessions-list__center-title">${ChatAdmin.chatSessionName}</h3>
                                                            <span class="sessions-list__center-code">ID ${ChatAdmin.chatSessionToken}</span>
                                                        </div>
                                                        <div class="sessions-list__right">


                                                            <span class="sessions-list__right-date">${ChatAdmin.stringDate}</span>

                                                        </div>
                                                    </a>
                                                </li>
                                            </c:forEach>
                                        </c:if>
                                    </ul>
                                    <div class="force-overflow"></div>
                                </div>
                            </div>
                            <div
                                    class="tab-pane custom-tab-content__tab fade"
                                    id="notification"
                                    role="tabpanel"
                                    aria-labelledby="notification-tab"
                                    data-selected-content="1"
                            >
                                <div class="search-place">
                                    <button class="search-place__button"></button>
                                    <input
                                            type="text"
                                            class="form-control search-place__input"
                                            id="search_notification"
                                            placeholder="Search"
                                    />
                                </div>
                                <div class="sessions-block scrollbar">
                                    <ul class="sessions-list">
                                        <c:if test="${requestScope.ChatSessionListNotification != null}">
                                            <c:forEach items="${requestScope.ChatSessionListNotification}" var="Notify">


                                                <li class="sessions-list__item">
                                                    <a href="${root}/employer/chat/conversation?id=${Notify.chatSessionToken}" class=" get-session-item"
                                                       data-session="${Notify.chatSessionToken}">
                                                        <c:set var="important" value="${Notify.important_for_user}"/>
                                                        <c:choose>
                                                            <c:when test="${important != 0}">
                                                                <div class="sessions-list__star">
                                                                    <input class="star select-star-important" data-sessionId="${ChatAdmin.chatSessionToken}" type="checkbox" title="bookmark page" checked/>
                                                                </div>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <div class="sessions-list__star">
                                                                    <input class="star select-star-important" data-sessionId="${ChatAdmin.chatSessionToken}" type="checkbox" title="bookmark page"/>
                                                                </div>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <div class="sessions-list__center">
                                                            <h3 class="sessions-list__center-title">${Notify.chatSessionName}</h3>
                                                            <span class="sessions-list__center-code">ID ${Notify.chatSessionToken}</span>
                                                        </div>
                                                        <div class="sessions-list__right">

                                                            <span class="sessions-list__right-date">${Notify.stringDate}</span>

                                                        </div>
                                                    </a>
                                                </li>
                                            </c:forEach>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="message-place">
                            <div class="card custom-card message-place">
                                <form id="messForm">
                                    <div class="card-header custom-card__header">
                                        <h3 class="card-title">Direct Chat</h3>

                                    </div>
                                    <div class="card-body custom-card__body">
                                        <dir class="default-unselect-text">Please select an existing chat channel or create a new one</dir>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card custom-card session-box">
                    <div class="card-header custom-card__header">
                        <button class="custom-card__header-close close-session-box">
                            <i class="fas fa-times"></i>
                        </button>
                    </div>
                    <div class="card-body custom-card__body">
                        <div class="form-group">
                            <label for="sessionTitle" class="custom-card__body-label">Conversation Title</label>
                            <input type="text" class="form-control form-control-sm" id="sessionTitle"
                                   placeholder="Title..."/>
                        </div>
                    </div>
                    <div class="card-footer custom-card__footer session">
                        <button class="btn btn-info btn-sm custom-card__footer-start" id="createSession">Start Chat
                        </button>
                        <!-- start-conversation-session-->
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
<script>
    var create_session_for_chat = "create_session_for_chat";
    var get_session_conversation = "get_session_conversation";
    var roomIdFromFrontUser = ${requestScope.EmployerObject.id};
    var selfId = ${requestScope.EmployerObject.id};
    var sessionRoomFromFront = "jok2f4fud_9";
</script>


<script src="${root}/js/bundle.js"></script>
<jsp:include page="${root}/includes/FrontFooter.jsp"/>
<%--<jsp:include page="../../includes/CookieManagerUser.jsp"/>--%>
</body>
</html>
