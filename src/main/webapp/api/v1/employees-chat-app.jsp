<%--
  Created by IntelliJ IDEA.
  User: Meghrig Janetsian and Shant Khayalian
  Date: 9/9/20
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" href="${root}/api/css/chat.css">
    <link rel="stylesheet" href="${root}/api/css/main.css">
    <%--    <link rel="stylesheet" href="${root}/api/css/style.css">--%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.0/handlebars.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/list.js/1.1.1/list.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <jsp:include page="${root}/api/chat-includes/chat-header.jsp"/>
</head>

<body class="hold-transition sidebar-mini layout-fixed" style="background-color: #f5f5f0" onload="onPageLoad()">
<div class="row chat">
    <!--Please do not remove the below line. -->
    <!--<div class="header"></div>-->
    <!--Please do not remove the below line. -->
    <!-- Side bar Section  -->
    <div class="col-3 chat-sidebar" >
        <ul class="list-unstyled contacts" id="usersDataList">

        </ul>
    </div>
    <!-- Side bar Section  -->
    <c:if test="${toObject != null}">
        <!-- Chat window -->
        <div class="col-6 message-place" id="chat-page">
            <div class="message-chat">
                <div class="chat-about">
                    <div class="chat-with" id="selectedUserId"></div>
                    <div class="chat-num-messages"></div>
                </div>
                <div class="chat-history">
                    <ul>

                    </ul>

                </div> <!-- end chat-history -->

                <div class="chat-message chat-footer d-flex clearfix">
                    <div class="more-options">
                        <div class="dropdown">
                            <button class="btn dropdown-toggle more-options-btn" type="button" id="dropdownMenuButton" data-toggle="dropdown">
                                <img class="icon-options"
                                     src="../../../img/icon-options.png">
                            </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                <a class="dropdown-item" href="">Notes</a>
                                <a class="dropdown-item" href="">Raise Invoice</a>
                                <a class="dropdown-item" href="">Something else here</a>
                            </div>
                        </div>
                    </div>
                    <div class="select-file-place-chat">
                        <input type="file" id="file">
                        <label for="file"><i class="fas fa-paperclip"></i></label>
                    </div>
                    <div class="video-call">
                        <button>
                            <i class="fa fa-video-camera"></i>
                        </button>
                    </div>
                    <textarea id="message-to-send" name="message-to-send" placeholder="Type your message..." rows="3"></textarea>

                    <button id="sendBtn" class="send-message-btn"></button>

                </div> <!-- end chat-message -->
            </div>
        </div>
        <!-- Chat window -->
        <!-- Info and profile bar -->
        <div class="col-3 chat-infoBar">
            <ul class="list-unstyled info-chat" id="userObject">

            </ul>

            <div class="tabPanel-widget">
                <ul class="nav nav-tabs custom-tabs">
                    <li class="nav-item">
                        <a href="#orders" class="nav-link item active" aria-selected="true" id="ordersTab" data-toggle="tab">Orders</a>
                    </li>
                    <li class="nav-item">
                        <a href="#documents" class="nav-link item " aria-selected="false" id="documents-tab" data-toggle="tab">Documents</a>
                    </li>
                </ul>

                <div class="tab-content clearfix">
                    <div class="tab-pane fade show active" id="orders" role="tabpanel" aria-labelledby="ordersTab">
                        <ul class="list-unstyled orders" id="ordersDataList">

                        </ul>
                    </div>
                    <div class="tab-pane" id="documents" role="tabpanel" aria-labelledby="documents-tab">
                        <ul class="list-unstyled documents">
                            <li class="item">
                                <div class="documents-display"></div>
                                <span class="documents-name">Document 1</span>
                                <span class="documents-date">Sep 8 at 15:00</span>
                                <span class="document-link"></span>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

    </c:if>
    <c:if test="${toObject == null}">
        <div class="col-9 message-place">
            <div class="select-chat">
                <h6>Please select a chat to start messaging</h6>
            </div>
        </div>
    </c:if>
</div>
<!-- jQuery -->
<script>

    let to_json_data = ''
    let from_json_data = ''
    let object_list = ''
    let pageOwner = ''
    let participant=''
    let type=''
    let chatting = '';
    let orders = '';

    if (${toObject != null}) {
        to_json_data = JSON.parse('${toObject}')
    }
    if (${ObjectsList != null}) {
        object_list = JSON.parse('${ObjectsList}')

    }
    if (${fromObject != null}) {
        from_json_data = JSON.parse('${fromObject}')
    }

    if (${chatBody != null}) {
        chatting = JSON.parse('${chatBody}')
    }
    if(${OrdersObject != null}){
        orders = JSON.parse('${OrdersObject}')
    }

    pageOwner = '${fromId}'
    participant = '${toId}'
    type ='${destination}'

</script>
<!-- Info and profile bar -->
<script id="message-template" type="text/x-handlebars-template">
    <li class="clearfix">
        <div class="message other-message float-right">
            {{messageOutput}}
            <div class="message-info">
                <h5 class="message-data-time">{{time}}</h5>
            </div>

        </div>
    </li>
</script>

<script id="message-response-template" type="text/x-handlebars-template">
    <li class="clearfix">
        <div class="message my-message">
            {{response}}
            <div class="message-info">
                <h5 class="message-data-time">{{time}}</h5>
            </div>
        </div>
    </li>
</script>

<!-- From data -->
<script id="message-template-data" type="text/x-handlebars-template">
    <li class="clearfix">
        <div class="message-data align-right">
            <div class="message other-message float-right">
                {{response}}
                <div class="message-info">
                    <h5 class="message-data-time">{{time}}</h5>
                </div>
            </div>
        </div>
    </li>
</script>

<script id="message-response-template-data" type="text/x-handlebars-template">
    <li class="clearfix">
        <div class="message-data">
            <div class="message my-message">
                {{response}}
                <div class="message-info">
                    <h5 class="message-data-time">{{time}}</h5>
                </div>
            </div>
        </div>
    </li>
</script>

<%--<script src="${root}/api/js/chat.js"></script>--%>
<%--<script src="${root}/api/js/main.js"></script>--%>
<script src="${root}/api/js/main.js"></script>
<script src="${root}/api/js/custom.js"></script>
<jsp:include page="${root}/api/chat-includes/chat-footer.jsp"/>
</body>
</html>

