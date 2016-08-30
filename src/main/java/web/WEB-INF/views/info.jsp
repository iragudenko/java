<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users information</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
    <h3>Users information</h3>

    <form class="form-inline" role="form" action="/search" method="post" accept-charset="utf-8">
        <input type="text" class="form-control" name="pattern" placeholder="Search">
        <input type="submit" class="btn btn-info" value="Search">
    </form>

    <table class="table table-striped">
        <thead>
        <tr>
            <td><b>Photo</b></td>
            <td><b>Photo_name</b></td>
            <td><b>Name</b></td>
            <td><b>Soname</b></td>
            <td><b>Gender</b></td>
            <td><b>Birthday</b></td>
            <td><b>Age</b></td>
            <td><b>Phone</b></td>
            <td><b>Action</b></td>
        </tr>
        </thead>
        <c:forEach items="${advs}" var="adv">
            <tr>
                <td><img height="40" width="40" src="/image/${adv.photo.id}" /></td>
                <td>${adv.photo.name}</td>
                <td>${adv.name}</td>
                <td>${adv.soname}</td>
                <td>${adv.gender}</td>
                <td>${adv.birthday}</td>
                <td>${adv.age}</td>
                <td>${adv.phone}</td>
                <td>
                    <a href="/move_to_tray?id=${adv.id}">Move to tray</a>
                    <br>
                    <a href="/edit?id=${adv.id}">Edit</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <table class="table table-hover">
        <tr>
            <td>
                <form action="/add_page" method="post">
                    <input type="submit" value="Add new" class="btn btn-info" >
                </form>
            </td>
            <td>
                <a href="/tray">View tray</a>
            <td>
                <form  enctype="multipart/form-data" class="" role="form" action="/import_xml" method="post">
                    <table>
                    <tr>
                        <td > <input type="file" name="xmlfile" class="btn btn-info"></td>
                        <td width="150"> <input type="submit" value="Import XML" class="btn btn-info"></td>
                    </tr>
                    </table>
                </form>
            </td>
        </tr>
    </table>

</div>
</body>
</html>