// //get json object
// let jsonUserObject = admins_json_data;
//
// //get json data objects
// let adminDataObject = jsonUserObject.data;
//
// // check the response code of success
// if (admins_json_data.code >= 200 && admins_json_data.code <= 299) {
//     // let listItemString = $('.adminListItem').html();
//     // // get the url
//     // let url = window.location.href;
//     // // get the id from url
//     // let urlId = parseInt(url.substring(url.lastIndexOf('/') + 1));
//     // adminDataObject.forEach(buildNewList);
//     //
//     // //start the building function
//     // function buildNewList(item, index) {
//     //     let listItem = '';
//     //     //check if the id from url matches the id from json
//     //     if (urlId === item.id) {
//     //         //Show all div section html with active
//     //         listItem = $('<li class="adminListItem active" data-id=' + item.id + '>' + listItemString + '</li>');
//     //     } else {
//     //         listItem = $('<li class="adminListItem" data-id=' + item.id + '>' + listItemString + '</li>');
//     //     }
//     //     //get image and add to html
//     //     let listItemImage = $('.image', listItem);
//     //     listItemImage.html("<img class=\"user-image\" src='/api/img/AdminLTELogo.png'>");
//     //
//     //     //get full name  and add to html
//     //     let listItemFullName = $('.full-name', listItem);
//     //     listItemFullName.html(item.firstName + ' ' + item.lastName);
//     //
//     //     //append all items to html
//     //     $('#usersDataList').append(listItem);
//     // }
// }
//
// //############################ FOR USERS ON CLICK FUNCTION RETURN ID AND DATA OF USER ##################################
// // $(function () {
// //     let urlId = current_url;
// //     console.log("urlid",urlId);
// //     $('.adminListItem').on('click', function () {
// //         let dataId = $(this).attr('data-id');
// //         window.location.href = "http://localhost:9090/api/v1/employee/8941/" + dataId;
// //     })
// // })
//
// //############################ FOR ADMIN ON CLICK FUNCTION RETURN ID ##################################
// //############################ FOR ADMIN BY ID ##################################
// if (admin_json_data !== null) {
//     //get json object
//     let jsonAdminObject = admin_json_data;
//     //get json data objects
//     let adminDataObject = jsonAdminObject.data;
//     // check the response code of success
//     if (admin_json_data.code >= 200 && admin_json_data.code <= 299) {
//         let adminDataObject = admin_json_data.data;
//
//         let adminListString = $('.adminObjectItem').html();
//
//         // show all div section html
//         let userItem = $('<li class="adminObjectItem">' + adminListString + '</li>');
//
//         //get image and add to html
//         let listItemUserImage = $('.user-image', userItem);
//         listItemUserImage.html("<img class=\"img-circle medium-image profile-info\" src='/api/img/AdminLTELogo.png'>");
//
//         // get full name  and add to html
//         let listUserItemFullName = $('.user-full-name', userItem);
//         listUserItemFullName.html(adminDataObject.firstName + ' ' + adminDataObject.lastName);
//
//         // get email and add to html
//         let listUserItemEmail = $('.user-mail', userItem);
//         listUserItemEmail.html("email: " + adminDataObject.email);
//
//         // get phone number and add to html
//         let listUserItemPhoneNumber = $('.admin-phone-number', userItem);
//         listUserItemPhoneNumber.html("Phone number: " + adminDataObject.phoneNumber);
//
//         // get user age and add to html
//         // let listUserItemChatId = $('.user-chat-room-id', userItem);
//         // listUserItemChatId.html(usersDataObject.chatRoom.chatRoomId);
//
//         // $('#userObject').append(userItem);
//         $('#adminObject').html(userItem);
//     } else {
//         console.log("No Data")
//     }
// } else {
//     console.log("No JSON")
// }
// //############################ FOR ADMIN LIST IN THE CHAT IP ##################################