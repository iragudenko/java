<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Change user information</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/confirm_edit" method="post">
        <div class="form-group"><h3>Edit user information</h3></div>
        <img height="40" width="40" src="/image/${adv.photo.id}" />
        <div class="form-group"><input type="text" class="form-control" name="id" placeholder="id" value="${adv.id}" readonly></div>
        <div class="form-group"><input type="text" class="form-control" name="photo_id" placeholder="photo_id" value="${adv.photo.id}" readonly></div>
        <div class="form-group"><input type="text" class="form-control" name="photo_name" placeholder="photo_name" value="${adv.photo.name }" readonly></div>

        <div class="form-group"><input type="text" class="form-control" name="name" placeholder="Name" value="${adv.name}"></div>
        <div class="form-group"><input type="text" class="form-control" name="soname" placeholder="Soname" value="${adv.soname}"></div>

        <div class="form-group"><select class="form-control" name="gender" placeholder="Gender" value="${adv.gender}">
            <option value="Male">Male</option>
            <option value="Female">Female</option>
        </select></div>

        <div class="form-group"><input type="date" class="form-control" name="birthday" placeholder="Birthday" value="${adv.birthday}"></div>
        <div class="form-group"><input type="text" class="form-control" name="age" placeholder="Age" value="${adv.age}"></div>
        <div class="form-group"><input type="text" class="form-control" name="phone" placeholder="Phone" value="${adv.phone}"></div>

        <div class="form-group">Photo user:<input type="file" name="photo"></div>
        <div class="form-group"><input type="submit" class="btn btn-primary" value="Confirm edit"></div>
    </form>
</div>
</body>
</html>