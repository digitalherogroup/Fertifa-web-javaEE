const url = 'http://second.fertifabenefits.com/'
let chatManager = '';
let adminUsersList = '';
let userSingleObject = '';
let newMessages = new Map();
let selectedUser = '';
let destination = '';

function onPageLoad() {
    selectedUser = participant;
    chatManager = pageOwner;
    adminUsersList = object_list;
    userSingleObject = to_json_data;
    destination = type;
    fetchAdminUsers();
    drawUserDetail();
    connectToChat()
}


function connectToChat() {
    stompClient = null;
    console.log("connecting to chat...")
    let socket = new SockJS(url + '/chat');
    console.log("Socket ",socket);
    stompClient = Stomp.over(socket);
    console.log("StompClient ,", stompClient);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
    });
}

function selectUser() {
    let userName = document.getElementById("userName").value;
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

function fetchAdminUsers() {
    let usersTemplateHTML = "";
    console.log("fetchAdminUsers()", adminUsersList.code);
    if (adminUsersList.code >= 200 && adminUsersList.code <= 299) {
        let jsonUsersData = adminUsersList.data;
        for (let i = 0; i < jsonUsersData.length; i++) {
            if (jsonUsersData[i].role === 'ADMIN' || jsonUsersData[i].role === 'NURSE') {
                usersTemplateHTML = usersTemplateHTML +
                    '<li onclick="selectUser(\'' + jsonUsersData[i].id + '\')" class="contacts-item">\n' +
                    '                    <img alt="" class="img-circle medium-image user-image" src="http://second.fertifabenefits.com/img/AdminLTELogo.png">\n' +
                    '                    <div class="vcentered info-combo">\n' +
                    '                        <h3 class="no-margin-bottom name userName"> ' + jsonUsersData[i].firstName + ' ' + jsonUsersData[i].lastName + '</h3>\n' +
                    '                    <input type="hidden" name="admin" value="' + chatManager + '" id="admin" />\n' +
                    '                    <input type="hidden" name="userName" value="' + jsonUsersData[i].id + '" id="userName" />\n' +
                    '                    </div>\n' +
                    '                    <div class="contacts-add">\n' +
                    '                        <div id="counter" class="' + (jsonUsersData[i].count === 0 ? 'name ' : 'name message-count') + '">' + (jsonUsersData[i].count === 0 ? '' : jsonUsersData[i].count) + '</div>\n' +
                    '                    </div>\n' +
                    '                </li>'
            } else {
                usersTemplateHTML = usersTemplateHTML +
                    '<li onclick="selectUser(\'' + jsonUsersData[i].id + '\')" class="contacts-item">\n' +
                    '                    <img alt="" class="img-circle medium-image user-image" src="http://second.fertifabenefits.com/' + checkImageError(jsonUsersData[i].userImage) + '\">\n' +
                    '                    <div class="vcentered info-combo">\n' +
                    '                        <h3 class="no-margin-bottom name userName"> ' + jsonUsersData[i].fullName + '</h3>\n' +
                    '                    <input type="hidden" name="admin" value="' + chatManager + '" id="admin" />\n' +
                    '                    <input type="hidden" name="userName" value="' + jsonUsersData[i].id + '" id="userName" />\n' +

                    '                    <div class="contacts-add">\n' +
                    '                        <span class="message-time" >' + dateConverter(jsonUsersData[i].updated) + '</span>\n' +
                    '                       <div id="counter" class="' + (jsonUsersData[i].count === 0 ? 'name ' : 'name message-count') + '">' + (jsonUsersData[i].count === 0 ? '' : jsonUsersData[i].count) + '</div>\n' +
                    '                    </div>\n' +
                    '                    </div>\n' +
                    '                </li>'
            }
            $('#usersDataList').html(usersTemplateHTML);
        }
    }
}

function checkCount(counts) {
    $(document).ready(function() {
        if(counts === 0){
            return '';
        }else{
            return counts;
        }
    });
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

function drawUserDetail() {
    if (userSingleObject !== null) {
        let userTemplateHtml = "";
        if (userSingleObject !== '') {
            let jsonUserData = userSingleObject.data;
            if (userSingleObject.code >= 200 && userSingleObject.code <= 299) {
                if (jsonUserData.role === 'ADMIN' || jsonUserData.role === 'NURSE') {
                    userTemplateHtml = userTemplateHtml + ' ' +
                        '<li class="userObjectItem">\n' +
                        '                <span class="user-image">\n' +
                        '                <img alt="" class="profile-info" src="http://second.fertifabenefits.com/img/AdminLTELogo.png">\n' +
                        '                    </span>\n' +
                        '                    <div class="vcentered info-combo">\n' +
                        '                        <h3 class="name user-full-name">' + jsonUserData.firstName + " " + jsonUserData.lastName + '</h3>\n' +
                        '                        <h5 class="user-mail"> Email : ' + jsonUserData.email + ' </h5>\n' +
                        '                    </div>\n' +
                        '                </li>'
                    $('#userObject').html(userTemplateHtml);
                }
            } else {

                userTemplateHtml = userTemplateHtml + ' ' +
                    '<li class="userObjectItem">\n' +
                    '                <span class="user-image">\n' +
                    '                <img alt="" class="profile-info" src=\"http://second.fertifabenefits.com/' + jsonUserData.userImage + '\">\n' +
                    '                    </span>\n' +
                    '                    <div class="vcentered info-combo">\n' +
                    '                        <h3 class="name user-full-name">' + jsonUserData.fullName + '</h3>\n' +
                    '                        <h5 class="user-mail"> Email : ' + jsonUserData.email + ' </h5>\n' +
                    '                        <h5 class="user-phone-number"> Phone number: ' + jsonUserData.phoneNumber + '  </h5>\n' +
                    '                    </div>\n' +
                    '                </li>'
                $('#userObject').html(userTemplateHtml);

            }
        } else {
            userSingleObject = '';
        }
    }
}