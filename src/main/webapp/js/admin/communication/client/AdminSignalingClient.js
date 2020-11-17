$(function () {

    const modalHTML = "<div class=\"modal fade videocall-custom-modal\" style='display:none' id=\"admin-incoming-video-call\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalCenterTitle\"\n" +
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
        "                        </div>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "        </div>";

    const socket = io.connect('https://fertifa-ourchat.herokuapp.com', {
        transports: ['websocket'],
        upgrade: false,
    });

    $(document).on('click', '.admin-click-start-call', function (e) {

        showIncomingModal(getRemoteUserFullName(this), this);
        startIncomingCall(this)
    });

    function getMyFullName() {
        return "Fertifa Admin";
    }

    function getRemoteUserId(currentObject) {
        return parseInt($(currentObject).attr('data-id'));
    }

    function getMyId() {
        return parseInt('1');
    }

    function getRemoteUserFullName(currentObject) {
        return $(currentObject).attr('data-username');
    }

    socket.on('connect', data => {
        const connectedToServerData = {
            fr: getMyId(),
            s: 'PARTICIPANT_CONNECTED'
        };
        const connectedToServerJson = JSON.stringify(connectedToServerData);
        sendMessage(connectedToServerJson);
    });

    socket.on('disconnect', data => {
        const disconnectedToServerData = {
            fr: getMyId(),
            s: 'PARTICIPANT_LEAVE'
        };
        const disconnectedToServerJson = JSON.stringify(disconnectedToServerData);
        sendMessage(disconnectedToServerJson);
    });

    socket.on('CALLING_EVENT_NAME', data => {
        const message = JSON.parse(data);
        const eventType = message["s"];
        const fr = message["fr"];
        const roomName = message["n"];
        const frName = message["frName"];
        const to = message["to"];


        if (eventType === "ACCEPT_CALL") {

            // go to new tab for video call
            const callUrl = "https://fertifacommunications.com/#/conference/" + roomName;
            hideModal();
            openInNewTab(callUrl);
        } else if (eventType === "REJECT_CALL") {
            hideModal()
        }
    });

    function hideModal() {

        const pageRootBody = document.getElementById("admin-incoming-call-modal-container");

        let modalView = document.getElementById("admin-incoming-video-call");

        if (!modalView) {
            return false
        }

        pageRootBody.innerHTML = '';
    }

    function openInNewTab(url) {
        const win = window.open(url, '_blank');
        win.focus();
    }

    function showIncomingModal(username, currentObject) {

        const pageRootBody = document.getElementById("admin-incoming-call-modal-container");

        let modalView = document.getElementById("admin-incoming-video-call");

        if (!modalView) {
            pageRootBody.innerHTML = modalHTML;
            modalView = document.getElementById("admin-incoming-video-call")
        }

        const videoCallModal = document.getElementById("user-incoming-video-call")
        if (videoCallModal) {
            videoCallModal.querySelector('#modal-username').innerHTML = username
        }

        modalView.style.display = 'block';
        modalView.style.opacity = '1';

      /*  document.getElementById("answer-call").addEventListener("click", function () {
            alert("answer-call");
        });*/

        document.getElementById("decline-call").addEventListener("click", function () {

            const sendCallRejectedData = {
                fr: getMyId(),
                to: getRemoteUserId(currentObject),
                s: "REJECT_CALL"
            };
            const sendCallRejectDataJson = JSON.stringify(sendCallRejectedData);
            sendMessage(sendCallRejectDataJson);
            hideModal()
        });
    }

    function startIncomingCall(currentObject) {

        const startIncomingCallData = {
            fr: getMyId(),
            frName: getMyFullName(),
            to: getRemoteUserId(currentObject),
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
