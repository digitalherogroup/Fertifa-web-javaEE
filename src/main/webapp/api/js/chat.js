//############################ FOR USERS LIST IN THE CHAT IP ##################################
//get json object
let jsonObject = users_json_data;
//get json data objects
let dataObject = jsonObject.data;
//get admin id
let admin_id = adminId;
// check the response code of success
if (jsonObject.code >= 200 && jsonObject.code <= 299) {
    let listItemString = $('.userListItem').html();
    // get the url
    let url = window.location.href;
    // get the id from url
    let urlId = parseInt(url.substring(url.lastIndexOf('/') + 1));
    dataObject.forEach(buildNewList);

    //start the building function
    function buildNewList(item, index) {
        let listItem = '';
        //check if the id from url matches the id from json
        if (urlId === item.userId) {
            //Show all div section html with active
            listItem = $('<li class="userListItem active" data-id=' + item.userId + '>' + listItemString + '</li>');
        } else {
            listItem = $('<li class="userListItem" data-id=' + item.userId + '>' + listItemString + '</li>');
        }
        //get image and add to html
        let listItemImage = $('.image', listItem);
        listItemImage.html("<img class=\"user-image\" " +
            "onerror=\"this.onerror=null;this.src='/api/img/avatar-empty.svg'\" " +
            "src='http://second.fertifabenefits.com/" + item.userImage + "'>");

        //get full name  and add to html
        let listItemFullName = $('.full-name', listItem);
        listItemFullName.html(item.fullName);

        //get company name add to html
        let listItemCompanyName = $('.company-name', listItem);
        listItemCompanyName.html("<input type=\"hidden\" name-id=\"name\" class=\"username-submit\" value='" + item.fullName + "'/>");

        //get created date and add to html
        let listItemDesc = $('.chat-date', listItem);
        listItemDesc.html(item.updated);

        //append all items to html
        $('#usersDataList').append(listItem);
    }
}
//############################ FOR USERS LIST IN THE CHAT IP ##################################
//############################ FOR USERS ON CLICK FUNCTION RETURN ID AND DATA OF USER ##################################
'use strict';
$(function () {
    $('.userListItem').on('click', function () {
        let dataId = $(this).attr('data-id');
        window.location.href = "http://localhost:9090/api/v1/1/" + dataId;
    })
})

//############################ FOR USERS ON CLICK FUNCTION RETURN ID ##################################

//############################ FOR USERS BY ID ##################################
'use strict';
if (user_json_data !== null) {
    //get json object
    let jsonUserObject = user_json_data;
    //get json data objects
    let userDataObject = jsonUserObject.data;
    // check the response code of success
    if (user_json_data.code >= 200 && user_json_data.code <= 299) {
        let usersDataObject = user_json_data.data;

        let userListString = $('.userObjectItem').html();

        // show all div section html
        let userItem = $('<li class="userObjectItem">' + userListString + '</li>');

        //get image and add to html
        let listItemUserImage = $('.user-image', userItem);
        listItemUserImage.html("<img class=\"img-circle medium-image profile-info\" " +
            "onerror=\"this.onerror=null;this.src='/api/img/avatar-empty.svg'\" " +
            "src='http://second.fertifabenefits.com/" + usersDataObject.userImage + "'>");

        // get full name  and add to html
        let listUserItemFullName = $('.user-full-name', userItem);
        listUserItemFullName.html(usersDataObject.fullName);

        // get email and add to html
        let listUserItemEmail = $('.user-mail', userItem);
        listUserItemEmail.html(usersDataObject.email);

        // get phone number and add to html
        let listUserItemPhoneNumber = $('.user-phone-number', userItem);
        listUserItemPhoneNumber.html(usersDataObject.phoneNumber);

        // get user age and add to html
        // let listUserItemChatId = $('.user-chat-room-id', userItem);
        // listUserItemChatId.html(usersDataObject.chatRoom.chatRoomId);

        // $('#userObject').append(userItem);
        $('#userObject').html(userItem);
    } else {
        console.log("No Data")
    }
} else {
    console.log("No JSON")
}
//############################ FOR USERS LIST IN THE CHAT IP ##################################
