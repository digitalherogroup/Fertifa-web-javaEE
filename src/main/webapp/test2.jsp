<%--
  Created by IntelliJ IDEA.
  User: Shant
  Date: 1/14/2020
  Time: 6:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body><%
    HttpServletResponse httpResponse = response;
    httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0
    httpResponse.setDateHeader("Expires", 0); // Proxies.
%>
<section class="content">

    <div class="row justify-content-center">
        <div class="col-md-12">
            <div class="card card-primary custom-card">
                <div class="card-header">
                    <h3 class="card-title">Create com.fertifa.app.adminSide.news</h3>
                </div>
                <!-- /.card-header -->
                <!-- form start -->
                <form class="form-horizontal" action="UploadingData" method="post" enctype="multipart/form-data" >
                    <div class="card-body">
                        <div class="form-group row">
                            <label for="Image" class="col-sm-2 col-form-label">Excel</label>
                            <div class="col-sm-10">
                                <div class="input-group">
                                    <div class="custom-file">
                                        <input type="file"  class="custom-file-input" name="Image" id="image">
                                        <label class="custom-file-label" for="image">Choose image</label>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <!-- /.card-body -->
                    <div class="card-footer">
                        <button type="submit" class="btn btn-primary custom-btn float-right">Create</button>
                    </div>
                    <!-- /.card-footer -->
                </form>
            </div>
        </div>
    </div>
</section>
</body>
</html>
