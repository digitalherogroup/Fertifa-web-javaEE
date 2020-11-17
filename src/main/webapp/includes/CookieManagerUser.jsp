<%--
  Created by IntelliJ IDEA.
  User: Shant
  Date: 3/24/2020
  Time: 2:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    $(document).on('click', '.click-logout-action', function (e) {
        e.preventDefault();

        console.log('testtstts log');

        $.ajax({
            url: logout_request,
            type: 'GET',
            dataType: 'text',
            beforeSend: function () {

            },
            success: function (data) {
                console.log('data', data);
                window.location.href = "https://www.fertifabenefits.com/EmployeeLogin";
            },
            error: function (data) {
                console.log('error', data)
            }
        });
    })
</script>

<script>
    $(document).on('click', '.click-logout-action-company', function (e) {
        e.preventDefault();

        console.log('testtstts log');

        $.ajax({
            url: logout_request,
            type: 'GET',
            dataType: 'text',
            beforeSend: function () {

            },
            success: function (data) {
                console.log('data', data);
                window.location.href = "https://www.fertifabenefits.com/EmployerLogin";
            },
            error: function (data) {
                console.log('error', data)
            }
        });
    })
</script>
