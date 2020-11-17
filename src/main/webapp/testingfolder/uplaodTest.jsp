<%--
  Created by IntelliJ IDEA.
  User: Shant
  Date: 3/30/2020
  Time: 10:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>File com.fertifa.app.upload Example</title>
</head>
<body>
<form action="/UploadingTestFiles" method="POST" enctype="multipart/form-data">
    <p>What's your name?</p>
    <input type="text" name="name" value="Ada">
    <p>What file do you want to upload?</p>
    <input type="file" name="fileToUpload">
    <br/><br/>
    <input type="submit" value="Submit">
</form>
<p>${catalina.home}/webapps/manager</p>
</body>
</html>