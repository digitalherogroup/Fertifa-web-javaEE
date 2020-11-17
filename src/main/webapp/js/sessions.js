// const socket = io.connect('http://5.9.1.58:4000', { //http://localhost:4000 //https://fertifachat.herokuapp.com
//     transports: ['websocket'],
//     upgrade: false,
// });

$(function () {
    'use strict';
    $('.generate-new-session-chat').on('click', function() {
        $('.fertifa-session-box').show();
    });

    $('.close-session-box').on('click', function() {
        $('.fertifa-session-box').find('#sessionTitle').val('');
        $('.fertifa-session-box').hide();
    });

});


window.onload = function() {

    const sessionRoom = sessionRoomFromFront;
    const roomIdFromFront = roomIdFromFrontUser;
    const user_id = selfId;

    const messageInput = document.getElementById('message');
    const messageForm = document.getElementById('messForm');
    const messageContainer = document.getElementById('direct-chat-messages');

    socket.emit('join', {user_id, roomIdFromFront}, (error) => {
        if(error) {
            console.log('front error', error);
        }
    });

    if (messageContainer != null) {
        messageContainer.scrollTop = messageContainer.scrollHeight;
    }

    if (document.getElementById('createSession') != null) {

        var sessionType = 3;

        document.getElementById('createSession').addEventListener('click', function(event) {

            // if (document.querySelector(".custom-sessions-tab-link.active").getAttribute('data-selected')) {
            //     sessionType = document.querySelector(".custom-sessions-tab-link.active").getAttribute('data-selected');
            // }

            if (document.getElementById("sessionTitle").value === '') {
                return false;
            }

            console.log( {
                sessionName: document.getElementById("sessionTitle").value,
                roomIdFromFront: roomIdFromFront,
                messageType: sessionType,
                userId: user_id,
                from: user_id,
                to: roomIdFromFront
            })

            socket.emit('create_session', {
                sessionName: document.getElementById("sessionTitle").value,
                roomIdFromFront: roomIdFromFront,
                messageType: sessionType,
                userId: user_id,
                from: user_id,
                to: roomIdFromFront
            }, (error) => {

                if (error) {
                    console.log('some error', error);
                }
            })
        })
    }

    socket.on('session_created', data => {
        // console.log('data -> ',  data)

        if (document.querySelector(".custom-tab-content__tab.active") == null) {
            //return false;
        }

        appendSession(data);
        document.querySelector('#sessionTitle').value = '';
        document.querySelector('.fertifa-session-box').style.display = 'none';
    });

    socket.emit('join_message', {user_id, sessionRoom}, (error) => {
        if(error) {
            //alert(error);
        }
        console.log('connect');
    });

    if (messageForm) {
        messageForm.addEventListener('submit', e => {
            e.preventDefault();
            const message = messageInput.value;

            if (message.trim() === '') {
                return false;
            }

            socket.emit('send_message', {
                user_id: user_id,
                sessionRoom: sessionRoom,
                roomIdFromFront: roomIdFromFront,
                message: message,
                from: user_id,
                to: roomIdFromFront
            }, (error) => {

                if (error) {
                    console.log('some eddor', error);
                }
            });

            messageInput.value = '';
        });
    }

    if (messageForm) {
        let imageUpload = document.getElementById("message_file");
        if (typeof imageUpload !== 'undefined' && imageUpload !== null) {
            imageUpload.onchange = function() {
                let file = this.files[0];
                var formData = new FormData();
                formData.append("image", file);
                axios.post('http://second.fertifabenefits.com/second/myresource/Upload', formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                })

                    .then(response => {
                        socket.emit('send_message', {
                            user_id: user_id,
                            sessionRoom: sessionRoom,
                            message: response.data,
                            roomIdFromFront: roomIdFromFront,
                            messageType: 2,
                            from: user_id,
                            to: roomIdFromFront
                        }, (error) => {
                            if (error) {
                                console.log('some error', error);
                            }
                        })
                    })
                    .catch(
                        error => console.log('error message', error) // Handle the error response object
                    );
            }
        }
    }

    console.log('document.querySelector(\'.select-star-important\')', document.querySelector('.select-star-important'));
    if (document.querySelector('.select-star-important')) {
        console.log('stex hasnuma')
        document.querySelector('.select-star-important').addEventListener('click', function(e) {

            const sessionId = this.getAttribute('data-sessionId');

            console.log('sessionId', sessionId);
            axios.post('http://second.fertifabenefits.com/second/myresource/important', {
                chatSessionToken: sessionId,
                chatFromId: user_id,
                importantFor: 'admin'
            }, {
                headers: {
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*'
                }
            })
                .then(response => response.json())
                .then(result => console.log('result', result))
                .catch(
                    error => console.log('error message', error) // Handle the error response object
                );
        })
    }

    socket.on('chat_message', data => {
        appendMessage(data.message, data.date, data.user_id);
        messageContainer.scrollTop = messageContainer.clientHeight;
    });

    socket.on('sned_notification', data => {
        console.log('data', data);

        if (data.userId == selfId) {
            return false;
        }

        appendNotificationContent(data.userId, data.notificationText, data.url, data.message, data.date, data.sessionId);
        addNotificationQnt(data.sessionId);
    });

    if (document.querySelector('.close-conversation')) {
        document.querySelector('.close-conversation').addEventListener('click', function(e) {
            e.preventDefault();
            console.log('click test');

            socket.emit('close_conversation', {
                sessionRoom: sessionRoom
            }, (error) => {
                if (error) {
                    console.log('some error', error);
                }
            });

            document.getElementById('close-conversation-modal-btn').click();
        })
    }

    socket.on('conversation_closed', data => {
        if (data.status === 'success') {
            closeMessageSend();
        }
    });

    const appendSession = (data) => {

        let sessionsContainer = document.querySelector("#custom-tabs-one-chat").querySelector(".sessions-list");

        document.querySelectorAll(".custom-tab-content-tab").forEach(el => {
            if (el.getAttribute('data-selected-content') === data.messageType) {
                sessionsContainer = el.querySelector(".sessions-list");
            }
        });

        const {date, messageType, sessionId, sessionName} = data;

        const sessionElement = document.createElement('li');
        sessionElement.setAttribute('class', 'item');

        const aElement = document.createElement('a');
        aElement.setAttribute('class', 'block-href get-session-item');
        if (data.chatToId == 1){
            data.chatToId = data.chatFromId;
        }

        let chatParam = '';
        const pathArray = window.location.pathname.split('/');
        for (i = 0; i < pathArray.length; i++) {
            console.log(pathArray[i])
            if (pathArray[i] === 'conversation') {
                chatParam = pathArray[i]
            }
            if (pathArray[i] === 'directchat') {
                chatParam = 'directchat'
            }
        }

        let conversationParam = 'chat/conversation?id=';
        if (chatParam !== '') {
            if (chatParam === 'directchat'){
                conversationParam = 'directchat?id=';
            } else {

                conversationParam = 'conversation?id=';
            }
        }

        aElement.setAttribute('href', conversationParam + data.chatToId + '&sessionid=' + sessionId); //ChatPageAdmin?id=1051&sessionid=i5ceelnes_1051
        aElement.setAttribute('data-session', sessionId);

        const starElement = document.createElement('div');
        starElement.setAttribute('class', 'product-img');
        const inputElement = document.createElement('input');
        inputElement.setAttribute('class', 'star');
        inputElement.setAttribute('type', 'checkbox');
        starElement.append(inputElement);

        const centerElement = document.createElement('div');
        centerElement.setAttribute('class', 'product-info');
        centerElement.innerText = sessionName;

        const dateElement = document.createElement('span');
        dateElement.setAttribute('class', 'float-right');
        dateElement.innerText = date;
        const codeElement = document.createElement('span');
        codeElement.setAttribute('class', 'product-description');
        codeElement.innerText = 'ID ' + sessionId;
        centerElement.append(dateElement);
        centerElement.append(codeElement);

        aElement.appendChild(starElement);
        aElement.appendChild(centerElement);
        sessionElement.appendChild(aElement);
        sessionsContainer.prepend(sessionElement);
    };

    const appendMessage = (message, date, user_id) => {
        const messageElement = document.createElement('div');

        if (user_id !== selfId) {
            messageElement.setAttribute('class', 'direct-chat-msg');
        } else {
            messageElement.setAttribute('class', 'direct-chat-msg right');
        }

        const infosElement = document.createElement('div');
        infosElement.setAttribute('class', 'direct-chat-infos clearfix');

        const directElement = document.createElement('div');
        directElement.setAttribute('class', 'direct-chat-text');
        directElement.innerText = message;
        infosElement.append(directElement);

        const dateElement = document.createElement('span');
        dateElement.setAttribute('class', 'direct-chat-timestamp float-right');
        dateElement.innerText = date;

        infosElement.append(dateElement);
        messageElement.append(infosElement);
        messageContainer.append(messageElement);
    };

    const appendNotificationContent = (userId, notificationText, url, message, date, sessionId) => {

        let notificationPlace = document.querySelector('#notification-menu-list');

        if (!notificationPlace) {
            return false;
        }

        if (notificationPlace.querySelector('.dropdown-footer') != null) {
            notificationPlace.querySelector('.dropdown-footer').remove();
        }

        const aElement = document.createElement('a');
        aElement.setAttribute('class', 'dropdown-item');
        aElement.setAttribute('href', 'chat/conversation?id=' + userId + '&sessionid=' + sessionId); //DirectChat?id=1051&sessionid=v7x3vcqld_1051

        const mediaElement = document.createElement('div');
        mediaElement.setAttribute('class', 'media');
        const mediaBodyElement = document.createElement('div');
        mediaBodyElement.setAttribute('class', 'media-body');
        const titleElement = document.createElement('h3');
        titleElement.setAttribute('class', 'dropdown-item-title');
        titleElement.innerText = sessionId;

        const smElement = document.createElement('p');
        smElement.setAttribute('class', 'text-sm');
        smElement.innerText = message;

        const dateElement = document.createElement('p');
        dateElement.setAttribute('class', 'text-sm');
        dateElement.innerText = date;

        mediaBodyElement.append(titleElement);
        mediaBodyElement.append(smElement);
        mediaBodyElement.append(dateElement);
        mediaElement.append(mediaBodyElement);
        aElement.append(mediaElement);

        notificationPlace.prepend(aElement);
    };

    const addNotificationQnt = (sessionId) => {
        const notificationQntElement =  document.querySelector('#notification-qnt-top-badge');

        if (!notificationQntElement) {
            return false;
        }

        let notifQnt = notificationQntElement.textContent;

        notifQnt = parseInt(notifQnt) + 1;
        notificationQntElement.innerText = notifQnt;
    };

    const closeMessageSend = () => {
        document.querySelector('.direct-chat').querySelector('.card-footer').innerHTML = '';
        const elem = document.querySelector('.position-absolute-chat-btn');
        elem.parentNode.removeChild(elem);
    }
};
