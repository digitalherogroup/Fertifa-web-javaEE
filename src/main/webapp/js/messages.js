$(function () {
    'use strict';

    /*
    $('.start-conversation-session').on('click', function() {
        
        var sessionTitle = $('.fertifa-session-box').find('#sessionTitle').val();

        // get active tab for message type
        var sessionType = 3; // = .attr('id') //1-request, 2-notification, 3-chat
        if ($('#custom-tabs-one-tab a[data-toggle="pill"].active').attr('data-selected')) {
            sessionType = $('#custom-tabs-one-tab a[data-toggle="pill"].active').attr('data-selected');
        }

        var _data = {
            sessionTitle: sessionTitle,
            sessionType: sessionType
        }

        console.log('_data', _data);
        
        $.ajax({
            type: 'POST',
            url: create_session_for_chat,
            cache: false,
            dataType: "html",
            data: _data,
            beforeSend: function () {
                
            },
            success: function (json) {
                console.log('json', json);

                $('.session-box').hide();
                //$('.loader-place').html(data);
            },
            error: function (html) {
                $('.loader-place').html('error message');
                $('.session-box').hide();
                console.log('Error!', html);
            }
        });
    });*/
});


window.onload = function() {
    console.log('message');

    const messageContainer = document.getElementById('direct-chat-messages');
    
    const socket = io.connect('https://fertifanew.herokuapp.com', { //http://localhost:4000
        transports: ['websocket'],
        upgrade: false,
    });
    
    const sessionRoom = sessionRoomFromFront;
    const roomIdFromFront = roomIdFromFrontUser;
    const user_id = selfId;
    const messageForm = document.getElementById('messForm');
    const messageInput = document.getElementById('message');

    socket.emit('join_message', {user_id, sessionRoom}, (error) => {
        if(error) {
            alert(error);
        }
        console.log('connect');
    });

    if (messageForm) {
        messageForm.addEventListener('submit', e => {
            e.preventDefault();
            const message = messageInput.value;
            
            if (message === '') {
                return false;
            }
            
            socket.emit('send_message', {
                user_id: user_id, 
                sessionRoom: sessionRoom, 
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

    socket.on('chat_message', data => { 
        appendMessage(data.message, data.date, data.user_id);
        messageContainer.scrollTop = messageContainer.clientHeight
    });

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
    }
};