<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
 Created by IntelliJ IDEA.
 User: Shant
 Date: 4/18/2020
 Time: 11:00 PM
 To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Modal -->
<%--<script>
    setTimeout(function(){
        window.location.reload(1);
    }, 10000);
</script>--%>
<%
    ServletContext context = getServletConfig().getServletContext();
   // if(context.getAttribute("calling") != null){

    //if (request.getSession().getAttribute("show") == "call") {
%>
<script>
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.status === 200) {
            $("#incomingVideoCall").modal()
        }
    };
    xhttp.open("POST", "#incomingVideoCall",true);
    xhttp.send();
</script>

<div class="modal fade" id="incomingVideoCall" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalCenterTitle">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>

<%--<script>
    $(document).ready(function () {
        console.log('test')
        $("#incomingVideoCall").modal()
    })
</script>--%>
<%--<div class="modal fade" id="incomingVideoCall" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalCenterTitle">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>

</div>--%>


<%--<%
    }
    //request.getSession().removeAttribute("show");
    context.setAttribute("calling","");
%>--%>
