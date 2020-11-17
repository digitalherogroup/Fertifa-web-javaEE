<%--
  Created by IntelliJ IDEA.
  User: rooter
  Date: 1/8/20
  Time: 12:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
<!-- overlayScrollbars -->
<script src="${pageContext.request.contextPath}/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/js/adminlte.min.js"></script>
<!-- Toastr -->
<script src="${pageContext.request.contextPath}/plugins/toastr/toastr.min.js"></script>
<!-- Summernote -->
<script src="${pageContext.request.contextPath}/plugins/summernote/summernote-bs4.min.js"></script>
<!-- bs-custom-file-input -->
<script src="${pageContext.request.contextPath}/plugins/bs-custom-file-input/bs-custom-file-input.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/chart.js/Chart.min.js"></script>
<!-- Daterangepicker -->
<script src="${pageContext.request.contextPath}/plugins/daterangepicker/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.js"></script>
<!-- DataTables -->
<script src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datatables-bs4/js/dataTables.bootstrap4.js"></script>
<!-- Summernote -->
<script src="${pageContext.request.contextPath}/plugins/summernote/summernote-bs4.min.js"></script>
<!-- bs-custom-file-input -->
<script src="${pageContext.request.contextPath}/plugins/bs-custom-file-input/bs-custom-file-input.min.js"></script>

<script src="${pageContext.request.contextPath}/plugins/bootstrap-datepicker/bootstrap-datepicker.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${pageContext.request.contextPath}/js/demo.js"></script>
<script src="${pageContext.request.contextPath}/js/bookings.js"></script>
<script src="${pageContext.request.contextPath}/js/sessions.js"></script>
<script src="${pageContext.request.contextPath}/plugins/jquery-validation/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/axios.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<script>
    $("#company-manage").validate({
        rules: {
            phone: {
                required: true,
                digits: true
            }
        }
    });
</script>

<script>
   /* var logout_request_admin = 'logout_request_admin'*/

    $(document).on('click', '.click-logout-action-admin', function (e) {
        e.preventDefault();

        console.log('testtstts log');

        $.ajax({
            url: logout_request_admin,
            type: 'GET',
            dataType: 'text',
            beforeSend: function () {

            },
            success: function (data) {
                console.log('data', data);
               // window.location.href = "https://www.fertifabenefits.com/admin/admin-dashboard";
                window.location.href = "../../../admin/admin-dashboard";
            },
            error: function (data) {
                console.log('error', data)
            }
        });
    })
</script>
<!-- jquery-validation -->
