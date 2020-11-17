<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 1/22/2020
  Time: 7:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
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

            <%!
                static String SessId;
            %>

            <c:set value='<%=request.getParameter("id")%>' var="Sessionid" scope="request"/>
            <%!
                static String typeChat;
                static String typeRequest;
                static String typeNotiy;

            %>
            <%
                typeChat = "";
                typeRequest = "";
                typeNotiy = "";
            %>
            <c:if test="${requestScope.ChatSessionListByToken != null}">
                <c:forEach items="${requestScope.ChatSessionListByToken}" var="SectionSelection">
                    <c:set value="${SectionSelection.chatSessionType}" var="type"/>
                    <c:choose>

                        <c:when test="${type == 1}">
                            <% typeNotiy = " active show ";%>
                        </c:when>
                        <c:when test="${type == 2}">
                            <% typeRequest = " active show ";%>
                        </c:when>
                        <c:when test="${type == 3}">
                            <% typeChat = " active show ";%>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </c:if>
            <div class="content">
                <h1 class="content-title">com.fertifa.app.messages</h1>
                <div class="row">
                    <div class="col-md-4">
                        <ul class="nav nav-pills mb-3 custom-tabs" id="messageTab" role="tablist">
                            <li class="nav-item custom-tabs__item">
                                <a
                                        class="nav-link custom-tabs__item--link <%=typeChat%>"
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
                                        class="nav-link custom-tabs__item--link <%=typeNotiy%>"
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
                                ><i class="fa fa-plus-circle"></i> NEW INQUIRY </a
                                >
                            </li>
                        </ul>
                        <div class="tab-content custom-tab-content" id="messageTabContent">
                            <div
                                    class="tab-pane custom-tab-content__tab fade <%=typeChat%>"
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
                                <div class="sessions-block scrollbar <%=typeNotiy%>">
                                    <ul class="sessions-list">
                                        <c:if test="${requestScope.ChatSessionListString != null}">
                                            <c:forEach items="${requestScope.ChatSessionListString}" var="ChatMenu">
                                                <li class="sessions-list__item">
                                                    <c:if test="${Sessionid == ChatMenu.chatSessionToken}">
                                                    <a href="${root}/employer/chat/conversation?id=${ChatMenu.chatSessionToken}"
                                                       class="active get-session-item"
                                                       data-session="${ChatMenu.chatSessionToken}">
                                                        </c:if>
                                                        <c:if test="${Sessionid != ChatMenu.chatSessionToken}">
                                                        <a href="${root}/employer/chat/conversation?id=${ChatMenu.chatSessionToken}"
                                                           class=" get-session-item"
                                                           data-session="${ChatMenu.chatSessionToken}">
                                                            </c:if>
                                                            <c:set var="important"
                                                                   value="${ChatMenu.important_for_user}"/>
                                                            <c:choose>
                                                                <c:when test="${important != 0}">
                                                                    <div class="sessions-list__star">
                                                                        <input class="star select-star-important"
                                                                               checked
                                                                               data-sessionId="${ChatMenu.chatSessionToken}"
                                                                               type="checkbox"
                                                                               title="bookmark page"/>
                                                                    </div>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <div class="sessions-list__star">
                                                                        <input class="star select-star-important"
                                                                               data-sessionId="${ChatMenu.chatSessionToken}"
                                                                               type="checkbox"
                                                                               title="bookmark page"/>
                                                                    </div>
                                                                </c:otherwise>
                                                            </c:choose>

                                                            <div class="sessions-list__center">
                                                                <h3 class="sessions-list__center-title">${ChatMenu.chatSessionName}</h3>
                                                                <span class="sessions-list__center-code">ID ${ChatMenu.chatSessionToken}</span>
                                                            </div>
                                                            <div class="sessions-list__right">

                                                                <span class="sessions-list__right-date">${ChatMenu.stringDate}</span>

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
                                    class="tab-pane custom-tab-content__tab fade <%=typeNotiy%>"
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
                                            <c:forEach items="${requestScope.ChatSessionListNotification}"
                                                       var="ChatMenu">
                                                <c:if test="${Sessionid == ChatMenu.chatSessionToken}">
                                                    <li class="sessions-list__item active">
                                                    <a href="${root}/employer/chat/conversation?id=${ChatMenu.chatSessionToken}" class="active get-session-item"
                                                    data-session="${ChatMenu.chatSessionToken}">
                                                </c:if>
                                                <c:if test="${Sessionid != ChatMenu.chatSessionToken}">
                                                    <li class="sessions-list__item">
                                                    <a href="${root}/employer/chat/conversation?id=${ChatMenu.chatSessionToken}" class=" get-session-item"
                                                    data-session="${ChatMenu.chatSessionToken}">
                                                </c:if>

                                                <c:set var="important" value="${ChatMenu.important_for_user}"/>
                                                <c:choose>
                                                    <c:when test="${important != 0}">
                                                        <div class="sessions-list__star">
                                                            <input class="star select-star-important" checked
                                                                   data-sessionId="${ChatMenu.chatSessionToken}"
                                                                   type="checkbox"
                                                                   title="bookmark page"/>
                                                        </div>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div class="sessions-list__star">
                                                            <input class="star select-star-important"
                                                                   data-sessionId="${ChatMenu.chatSessionToken}"
                                                                   type="checkbox"
                                                                   title="bookmark page"/>
                                                        </div>
                                                    </c:otherwise>
                                                </c:choose>

                                                <div class="sessions-list__center">
                                                    <h3 class="sessions-list__center-title">${ChatMenu.chatSessionName}</h3>
                                                    <span class="sessions-list__center-code">ID ${ChatMenu.chatSessionToken}</span>
                                                </div>
                                                <div class="sessions-list__right">

                                                    <span class="sessions-list__right-date">${ChatMenu.stringDate}</span>

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
                            <div class="card custom-card message-place <%=typeNotiy%>">
                                <form id="messForm">
                                    <div class="card-header custom-card__header">
                                        <c:forEach items="${requestScope.ChatSessionListByToken}" var="ChatMenu2">
                                            <div class="custom-card__header-chat">ID ${ChatMenu2.chatSessionToken}</div>


                                            <div class="custom-card__header-date">${ChatMenu2.stringDate}  </div>

                                            
                                        </c:forEach>
                                    </div>
                                    <div class="card-body">
                                        <div class="direct-chat-messages" id="direct-chat-messages">
                                            <c:if test="${requestScope.SesionChatMessagingList!= null}">
                                                <c:forEach items="${requestScope.SesionChatMessagingList}" var="Chat">
                                                    <c:set var="from" value="${Chat.chatFrom}" scope="request"/>
                                                    <c:set var="ChatId" value="${requestScope.ID}" scope="request"/>
                                                    <c:choose>
                                                        <c:when test="${ChatId == Chat.chatFrom}">
                                                            <div class="direct-chat-messages__item right">
                                                                <div class="direct-chat-messages__item-text">${Chat.chatBody}</div>
                                                                <span class="direct-chat-messages__item-date">${Chat.dateString}</span>
                                                            </div>
                                                        </c:when>
                                                        <c:otherwise>

                                                            <div class="direct-chat-messages__item">
                                                                <div class="direct-chat-messages__item-text">${Chat.chatBody}</div>
                                                                <span class="direct-chat-messages__item-date">${Chat.dateString}</span>
                                                            </div>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </c:forEach>
                                            </c:if>
                                        </div>
                                    </div>
                                    <c:set var="status" value="${requestScope.StatusFront}"/>
                                    <c:choose>
                                        <c:when test="${status != 0}">
                                            <div class="card-footer custom-card__footer mes">
                                                <div class="search-place">
                                                    <input
                                                            type="text"
                                                            class="form-control search-place__input"
                                                            id="message"
                                                            placeholder="Write a message..."
                                                    />
                                                        <%--<div class="search-place__selectfile">
                                                            <input type="file" id="file"/>
                                                            <label for="file"><i class="fas fa-paperclip"></i></label>
                                                        </div>--%>
                                                </div>
                                            </div>
                                        </c:when>
                                    </c:choose>
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
<%
    SessId = String.valueOf(request.getAttribute("Sessionid"));
%>
<script>
    var create_session_for_chat = "create_session_for_chat";
    var get_session_conversation = "get_session_conversation";
    var roomIdFromFrontUser = ${requestScope.ID};
    var selfId = ${requestScope.ID};
    var sessionRoomFromFront = "${requestScope.Token}";

    var requestDataEmmiter = JSON.parse(localStorage.getItem('requestData'));
    localStorage.removeItem('requestData');
    console.log('requestData', requestDataEmmiter)
</script>

<script src="${root}/js/bundle.js"></script>
<%--<jsp:include page="../../../includes/CookieManagerUser.jsp"/>--%>
</body>
</html>

