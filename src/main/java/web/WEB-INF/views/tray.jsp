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
    <h3>Users list information</h3>

    <form action="/process_checked" method="post">
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
                <td><input type=checkbox name="selectrow[]" value="${adv.id}"></td>
                <td><img height="40" width="40" src="/image/${adv.photo.id}" /></td>
                <td>${adv.photo.name}</td>
                <td>${adv.name}</td>
                <td>${adv.soname}</td>
                <td>${adv.gender}</td>
                <td>${adv.birthday}</td>
                <td>${adv.age}</td>
                <td>${adv.phone}</td>
                <td>
                    <a href="/delete?id=${adv.id}">Delete</a>
                    <br>
                    <a href="/restore?id=${adv.id}">Restore</a>
                </td>
            </tr>
        </c:forEach>
    </table>
        <input type="submit" name="submit" class="button2-credit" value="delete" title="Del checked"/>
        <input type="submit" name="submit" class="button2-credit" value="restore" title="Restore checked"/>
    </form>

    <a href="/home">Go home</a>
</div>
</body>
</html>