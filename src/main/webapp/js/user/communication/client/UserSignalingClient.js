$(function () {

    const modalHTML = "<div class=\"modal fade videocall-custom-modal\" style='display:none' id=\"user-incoming-video-call\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalCenterTitle\"\n" +
        "            aria-hidden=\"true\">\n" +
        "            <div class=\"modal-dialog modal-dialog-centered\" role=\"document\">\n" +
        "                <div class=\"modal-content\">\n" +
        "                    <div class=\"modal-body\">\n" +
        "                        <img src=\"/img/logo.svg\" alt=\"Logo\">\n" +
        "                    </div>\n" +
        "                    <div class=\"modal-footer\">\n" +
        "                        <div class=\"modal-user-info\">\n" +
        "                            <span id=\"modal-username\"></span>\n" +
        "                        </div>\n" +
        "                        <div class=\"footer-buttons\">\n" +
        "                            <button type=\"button\" class=\"btn btn-danger decline-call\" id='decline-call' data-dismiss=\"modal\">Decline</button>\n" +
        "                            <button type=\"button\" class=\"btn btn-success answer-call\" id='answer-call'>Answer</button>\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "        </div>";


    var myId = roomIdFromFrontUser;
    myId = parseInt(myId);

    /*$(document).on('click', '.click-start-call', function (e) {
        e.preventDefault();
        startIncomingCall()
    });*/

    const socket = io.connect('https://fertifa-ourchat.herokuapp.com', {
        transports: ['websocket'],
        upgrade: false,
    });

    socket.on('connect', data => {
        const connectedToServerData = {
            fr: myId,
            s: 'PARTICIPANT_CONNECTED'
        };
        const connectedToServerJson = JSON.stringify(connectedToServerData);
        sendMessage(connectedToServerJson);
    });

    socket.on('disconnect', data => {
        const disconnectedToServerData = {
            fr: myId,
            s: 'PARTICIPANT_LEAVE'
        };
        const disconnectedToServerJson = JSON.stringify(disconnectedToServerData);
        sendMessage(disconnectedToServerJson);
    });

    socket.on('CALLING_EVENT_NAME', data => {
        const message = JSON.parse(data);
        const eventType = message["s"];
        const fr = message["fr"];
        const frName = message["frName"];
        const to = message["to"];

        if (eventType === "INCOMING_CALL") {
            showIncomingModal(frName, fr)
        } else if (eventType === "REJECT_CALL") {
            hideModal()
        }
    });

    function showIncomingModal(username, remoteUserId) {

        const pageRootBody = document.getElementById("employee-incoming-call-modal-container");

        let modalView = document.getElementById("user-incoming-video-call");

        if (!modalView) {
            pageRootBody.innerHTML = modalHTML;
            modalView = document.getElementById("user-incoming-video-call")
        }

        const videoCallModal = document.getElementById("user-incoming-video-call")
        if (videoCallModal) {
            videoCallModal.querySelector('#modal-username').innerHTML = username
        }

        modalView.style.display = 'block';
        modalView.style.opacity = '1';

        document.getElementById("answer-call").addEventListener("click", function () {
            const generatedRoomName = Math.random().toString(36).substr(2, 9);
            // send answer event to signaling server
            const sendCallAnswerData = {
                fr: myId,
                to: remoteUserId,
                n: generatedRoomName,
                s: "ACCEPT_CALL"
            }
            const sendCallAnswerDataJson = JSON.stringify(sendCallAnswerData);
            sendMessage(sendCallAnswerDataJson);

            // go to
            const callUrl = "https://fertifacommunications.com/#/conference/" + generatedRoomName;
            hideModal();
            openInNewTab(callUrl);
        });

        document.getElementById("decline-call").addEventListener("click", function () {
            const sendCallAnswerData = {
                fr: myId,
                to: remoteUserId,
                s: "REJECT_CALL"
            };
            const sendCallAnswerDataJson = JSON.stringify(sendCallAnswerData);

            sendMessage(sendCallAnswerDataJson);
            hideModal();
        });

        playIncomingCallAudio(true);
    }

    function playIncomingCallAudio(play) {
        /*if (play === true) {
            //const audio = new Audio('http://www.fertifabenefits.com/upload/you-have-an-incoming-call.mp3');
            audio.loop = false;
            audio.play().then(r => {
                console.log("THEN", r)
            });
        } else {
            audio.pause();
            audio.currentTime = 0;
        }*/
    }

    function hideModal() {

        const pageRootBody = document.getElementById("employee-incoming-call-modal-container");

        let modalView = document.getElementById("user-incoming-video-call");

        if (!modalView) {
            return false
        }

        pageRootBody.innerHTML = '';
        playIncomingCallAudio(false);
    }

    function openInNewTab(url) {
        const win = window.open(url, '_blank');
        win.focus();
        return false;
    }

    function startIncomingCall() {

        const startIncomingCallData = {
            fr: 'USER ID',
            to: 'ADMIN ID',
            s: 'INCOMING_CALL'
        };
        const startIncomingCallJson = JSON.stringify(startIncomingCallData);
        sendMessage(startIncomingCallJson);
    }

    function sendMessage(data) {
        socket.emit('CALLING_EVENT_NAME', data, (error) => {
            if (error) {
                alert(error);
            }
            console.log('connect');
        });
    }
});
