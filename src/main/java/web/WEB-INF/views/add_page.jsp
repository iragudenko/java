<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add user</title>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-1.11.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/add" method="post">
        <div class="form-group"><h3>Add new user:</h3></div>
        <div class="form-group"><input type="text" class="form-control" name="name" placeholder="Name"></div>
        <div class="form-group"><input type="text" class="form-control" name="soname" placeholder="Soname"></div>
        <div class="form-group"><select class="form-control" name="gender" placeholder="Gender">
                <option value="Male">Male</option>
                <option value="Female">Female</option>
               </select></div>
        <div class="form-group"><input type="text" class="form-control" name="birthday" placeholder="Birthday"></div>
        <div class="form-group"><input type="text" class="form-control" name="age" placeholder="Age"></div>
        <div class="form-group"><input type="text" class="form-control" name="phone" placeholder="Phone"></div>
        <div class="form-group">Photo: <input type="file" name="photo"></div>


        <div class="form-group"><input type="submit" class="btn btn-primary" value="Add"></div>
    </form>
</div>
</body>
</html>