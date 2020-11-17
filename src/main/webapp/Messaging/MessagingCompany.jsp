<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 1/22/2020
  Time: 6:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCT>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

    <jsp:include page="../includes/BundleInclude.jsp"/>

       <title>Fertifa | Digital Platform</title>
</head>
<body><%
    HttpServletResponse httpResponse = response;
    httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0
    httpResponse.setDateHeader("Expires", 0); // Proxies.
%>
<div class="container-fluid">
    <div class="row">
        <div class="bg-light sidebar">
            <jsp:include page="../includes/CompanySideBar.jsp"/>
        </div>
        <jsp:include page="../includes/MobileSideBar.jsp"/>
        <main role="main" class="main-container">
            <!-- <div class="header">
                  <div class="user">
                      <div class="user__info">
                          <span class="user__company">Tesla Motors</span>
                          <span class="user__name">Elon Musk</span>
                      </div>
                      <div class="user__image">
                          <img src="https://www.asiliaafrica.com/wp-content/uploads/2015/12/Rubondo-Region-Itineraries-Image-200X200.jpg" alt="">
                      </div>
                  </div>
              </div> -->

            <div class="content">
                <h1 class="content-title">com.fertifa.app.messages</h1>
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
                                        id="request-tab"
                                        data-selected="2"
                                        data-toggle="tab"
                                        href="#request"
                                        role="tab"
                                        aria-controls="request"
                                        aria-selected="false"
                                >com.fertifa.app.request</a
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
                                        <c:if test="${requestScope.ChatSessionList != null}">
                                            <c:forEach items="${requestScope.ChatSessionList}" var="ChatMenu">


                                                <li class="sessions-list__item">
                                                    <a href="" class="active get-session-item"
                                                       data-session="${ChatMenu.chatSessionToken}">
                                                        <div class="sessions-list__star">
                                                            <input class="star select-star-important"
                                                                   data-sessionId="${ChatMenu.chatSessionToken}"
                                                                   type="checkbox" title="bookmark page"/>
                                                        </div>
                                                        <div class="sessions-list__center">
                                                            <h3 class="sessions-list__center-title">${ChatMenu.chatSessionName}</h3>
                                                            <span class="sessions-list__center-code">ID ${ChatMenu.chatSessionToken}</span>
                                                        </div>
                                                        <div class="sessions-list__right">
                                                            <%
                                                                Date date = new Date();
                                                            %>
                                                            <c:set value="${ChatMenu.chatCreationDate}"
                                                                   var="CreationDate"
                                                                   scope="request"/>
                                                            <%
                                                                String dateBeforeString = String.valueOf(request.getAttribute("CreationDate"));
                                                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                                                Date startDate = sdf.parse(dateBeforeString);
                                                            %>
                                                            <%!
                                                                private static String dateWithoutTime(Date one) {
                                                                    DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");
                                                                    return outputFormatter.format(one);
                                                                }
                                                            %>
                                                            <span class="sessions-list__right-date"><%=dateWithoutTime(startDate)%></span>

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
                                    id="request"
                                    role="tabpanel"
                                    aria-labelledby="request-tab"
                                    data-selected-content="2"
                            >
                                <div class="search-place">
                                    <button class="search-place__button"></button>
                                    <input
                                            type="text"
                                            class="form-control search-place__input"
                                            id="search_request"
                                            placeholder="Search"
                                    />
                                </div>
                                <div class="sessions-block scrollbar">
                                    <ul class="sessions-list"></ul>
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
                                    <ul class="sessions-list"></ul>
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
    var roomIdFromFrontUser = ${requestScope.ID};
    var selfId = ${requestScope.ID};
    var sessionRoomFromFront = "jok2f4fud_9";
</script>


<script src="../js/bundle.js"></script>
<%--<jsp:include page="../includes/CookieManagerUser.jsp"/>--%>
</body>
</html>
