const url = 'http://second.fertifabenefits.com/'
let chatManager = '';
let adminUsersList = '';
let userSingleObject = '';
let newMessages = new Map();
let selectedUser = '';
let destination = '';
let webSocketsHolder = {};
let chatType = '';
let lastPart = window.location.href.split("/").pop();
let ordersData = '';

function onPageLoad() {
    selectedUser = participant;
    chatManager = pageOwner;
    userSingleObject = to_json_data;
    destination = type;
    ordersData = orders;

    drawUserDetail();
    connectToChat(chatManager)
    drawOrders();
    fetchAdminUsersBootResponse();
    drawChatBody();
}

function selectOrder() {

}

function drawChatBody() {
    chatType = chatting;
    console.log(chatType.code)
    let chatData = chatType.data;
    let usernameToShow = '';
    let templateResponse = '';
    let user_to = '';
    let messageToShow = '';

    if (chatType.code >= 200 && chatType.code <= 299) {
        console.log(chatType.data)
        for (let i = 0; i <= chatData.length; i++) {
            scrollToBottom();
            user_to = chatData[i].messageTo;
            if (user_to === chatManager) {
                templateResponse = Handlebars.compile($("#message-template-data").html());
                usernameToShow = chatData[i].messageTo
                messageToShow = chatData[i].messageBody;
            } else {
                templateResponse = Handlebars.compile($("#message-response-template-data").html());
                usernameToShow = chatData[i].messageFrom
                messageToShow = chatData[i].messageBody;
            }
            let contextResponse = {
                response: messageToShow,
                time: chatData[i].created,
                userName: usernameToShow
            };

            $chatHistoryList.append(templateResponse(contextResponse));
        }
    }
    console.log(chatType.data);
}

function sendMsg(from, text) {
    stompClient.send("/app/chat/" + selectedUser, {}, JSON.stringify({
        from: selectedUser,
        message: text,
        to: chatManager,
    }));
    getRestData();
}

$(document).on('click', '#message-to-send', function (e) {
    $.get(url + "api/v1/updateChats/" + selectedUser + "/" + chatManager, function (response) {
    });
    $.get(url + "api/v1/updateList/" + chatManager, function (responseData) {
        fetchAdminUsersBootResponse(responseData);
    });
})


function getRestData() {
    $.get(url + "/chat/" + selectedUser, function (response) {
        console.log("inside getRestData")
        if (response.code >= 200 && response.code <= 299) {
            console.log("response.code")
            fetchAdminUsersBootResponse(response);
        }
    });
}

function reconnect(socketUrl, successCallback,userName) {
    let connected = false;
    let reconInv = setInterval(() => {
        let socket = new SockJS(url + '/chat');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, (frame) => {
            clearInterval(reconInv);
            connected = true;
            successCallback(userName);
        }, () => {
            if (connected) {
                reconnect(socketUrl, successCallback,userName);
            }
        });
    }, 3000);
}

function connectToChat(userName) {
    stompClient = null;
    let socket = new SockJS(url + '/chat');
    console.log("socket " ,socket)
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("frame ",frame)
        successCallback(userName);
    }, () => {
      //  reconnect(url, successCallback,userName);
    });
}


function successCallback(userName) {
    stompClient.subscribe("/topic/messages/" + userName, function (response) {
        console.log(response)
        let data = JSON.parse(response.body);
        if (selectedUser === data.to && chatManager === data.from) {
            render(data.message, data.from);
        } else {
            newMessages.set(data.from, data.message);
        }
    });
}

function registration() {
    let userName = document.getElementById("userName").value;
    $.get(url + "/registration/" + userName, function (response) {
        connectToChat(userName);
    }).fail(function (error) {
        if (error.status === 400) {
            alert("Login is already busy!")
        }
    })
}

function selectUser(userName) {
    // let userName = document.getElementById("userName").value;
    registration(userName)
    if (destination === 'admin') {
        window.location.href = url + '/api/v1/' + chatManager + '/' + userName;
    } else if (destination === 'employee') {
        window.location.href = url + '/api/v1/employee/' + chatManager + '/' + userName;
    } else {

    }
}

$(document).ready(function () {
    $(".user-image").on("error", function () {
        $(this).attr('src', '/api/img/avatar-empty.svg');
    });
    $(".profile-info").on("error", function () {
        $(this).append('src', '/api/img/avatar-empty.svg');
    });
});

function comp(a, b) {
    return new Date(b.updated).getTime() - new Date(a.updated).getTime();
}

function fetchAdminUsersBootResponse(e) {
    let usersTemplateHTML = "";
    let jsonUsersBootData = "";
    if (typeof e !== 'undefined') {
        adminUsersList = e;
    } else {
        adminUsersList = object_list;
    }
    if (adminUsersList.code >= 200 && adminUsersList.code <= 299) {
        jsonUsersBootData = adminUsersList.data;
        for (let i = 0; i < jsonUsersBootData.length; i++) {
            if (jsonUsersBootData[i].role === 'ADMIN' || jsonUsersBootData[i].role === 'NURSE') {
                usersTemplateHTML = usersTemplateHTML +
                    '<a href="#" class="contacts-item" onclick="selectUser(\'' + jsonUsersBootData[i].id + '\')">' +
                    '<li class="' + (jsonUsersBootData[i].id === parseInt(lastPart) ? 'active' : '') + '">\n' +
                    '                    <img alt="" class="user-image" src="http://second.fertifabenefits.com/img/AdminLTELogo.png">\n' +
                    '                    <div class="vcentered info-combo">\n' +
                    '                        <h3 class="name userName"> ' + jsonUsersBootData[i].firstName + ' ' + jsonUsersBootData[i].lastName + '</h3>\n' +
                    '                    <input type="hidden" name="admin" value="' + chatManager + '" id="admin" />\n' +
                    '                    <input type="hidden" name="userName" value="' + jsonUsersBootData[i].id + '" id="userName" />\n' +
                    '                    </div>\n' +
                    '                    <div class="contacts-add">\n' +
                    '                       <div id="counter" class="' + (jsonUsersBootData[i].count === 0 ? 'name ' : 'name message-count') + '">' + (jsonUsersBootData[i].count === 0 ? '' : jsonUsersBootData[i].count) + '</div>\n' +
                    '                    </div>\n' +
                    '                </li>' +
                    '</a>'
            } else {
                usersTemplateHTML = usersTemplateHTML +
                    '<a href="#" class="contacts-item" onclick="selectUser(\'' + jsonUsersBootData[i].id + '\')">' +
                    '<li class="' + (jsonUsersBootData[i].id === parseInt(lastPart) ? 'active' : '') + '">\n' +
                    '                    <img alt="" class="user-image" src=\"http://second.fertifabenefits.com/' + checkImageError(jsonUsersBootData[i].userImage) + '\">\n' +
                    '                    <div class="vcentered info-combo">\n' +
                    '                        <h3 class="name userName"> ' + jsonUsersBootData[i].fullName + '</h3>\n' +
                    '                    <input type="hidden" name="admin" value="' + chatManager + '" id="admin" />\n' +
                    '                    <input type="hidden" name="userName" value="' + jsonUsersBootData[i].id + '" id="userName" />\n' +

                    '                    <div class="contacts-add">\n' +
                    '                        <span class="message-time" >' + dateConverter(jsonUsersBootData[i].updated) + '</span>\n' +
                    '                       <div id="counter" class="' + (jsonUsersBootData[i].count === 0 ? 'name ' : 'name message-count') + '">' + (jsonUsersBootData[i].count === 0 ? '' : jsonUsersBootData[i].count) + '</div>\n' +
                    '                    </div>\n' +
                    '                    </div>\n' +
                    '                </li>' +
                    '</a>'
            }
            $('#usersDataList').html(usersTemplateHTML);
        }
    }
}

function checkImageError(imageLink) {
    if (typeof imageLink === 'undefined' || imageLink === '' || imageLink === null) {
        return "img/avatar-empty.svg"
    } else {
        return imageLink;
    }
}

function dateConverter(dateData) {
    Date.prototype.ddmmyyyy = function () {
        var mm = this.getMonth() + 1;// eshutyun monthse sgsuma 0 its
        var dd = this.getDate();

        return [(dd > 9 ? '' : '0') + dd,
            (mm > 9 ? '' : '0') + mm,
            this.getFullYear()
        ].join('-');
    };

    var date = new Date(dateData);
    return date.ddmmyyyy();
}

function drawOrders() {
    let jsonOrdersBootData = '';
    if (ordersData !== null) {
        let ordersTemplateHTML = "";
        if (ordersData !== '') {
            console.log("orders =====> ", ordersData);
            if (ordersData.code >= 200 && ordersData.code <= 299) {
                console.log(ordersData.code)
                jsonOrdersBootData = ordersData.data;
                for (let i = 0; i < jsonOrdersBootData.data.length; i++) {
                    ordersTemplateHTML = ordersTemplateHTML +
                        '<a href="#" id="orderItem" class="orders-link">' +
                        '<li class="item">\n' +
                        '                    <span class="orders-name">' + jsonOrdersBootData.data[i].serviceName + '</span>\n' +
                        '                    <input type="hidden" id="orderId" value="' + jsonOrdersBootData.data[i].id + '"/>\n' +
                        '                    <span class="orders-date">' + jsonOrdersBootData.data[i].order_date + '</span>\n' +
                        '                    <span class="orders-price">' + jsonOrdersBootData.data[i].price + ' £</span>\n' +
                        '                </li>' +
                        '</a>'
                }
                $('#ordersDataList').html(ordersTemplateHTML);

            } else {
                ordersData = '';
            }
        }
    }
}

$(document).on('click', '#orderItem', function () {

});

function switchStatus(data) {
    if (data === 0) {
        return "payed"
    } else {
        return "pending";
    }
}

function drawUserDetail() {
    if (userSingleObject !== null) {
        let userTemplateHtml = "";
        if (userSingleObject !== '') {
            let jsonUserData = userSingleObject.data;
            if (userSingleObject.code >= 200 && userSingleObject.code <= 299) {
                if (jsonUserData.role === 'ADMIN' || jsonUserData.role === 'NURSE') {
                    userTemplateHtml = userTemplateHtml +
                        '<li class="userObjectItem">\n' +
                        '                <img alt="" class="profile-info" src="http://second.fertifabenefits.com/img/AdminLTELogo.png">\n' +
                        '                    <div class="vcentered info-combo info-profile">\n' +
                        '                        <h3 class="name user-full-name">' + jsonUserData.firstName + " " + jsonUserData.lastName + '</h3>\n' +
                        '                        <h5 class="user-mail"> Email : ' + jsonUserData.email + ' </h5>\n' +
                        '                    </div>\n' +
                        '                </li>'
                    $('#userObject').html(userTemplateHtml);

                } else if (typeof jsonUserData.role === 'undefined' || jsonUserData.role === 'EMPLOYEE') {
                    userTemplateHtml = userTemplateHtml +
                        '<li class="userObjectItem">\n' +
                        '                <img alt="" class="profile-info" src=\"http://second.fertifabenefits.com/' + checkImageError(jsonUserData.userImage) + '\">\n' +
                        '                    <div class="vcentered info-combo info-profile">\n' +
                        '                        <h3 class="name user-full-name">' + jsonUserData.fullName + '</h3>\n' +
                        '                        <h5 class="user-mail"> Email : ' + jsonUserData.email + ' </h5>\n' +
                        '                        <h5 class="user-phone-number"> Phone number: ' + jsonUserData.phoneNumber + '  </h5>\n' +
                        '                    </div>\n' +
                        '                </li>'
                    $('#userObject').html(userTemplateHtml);
                }
            }
        } else {
            userSingleObject = '';
        }
    }
}