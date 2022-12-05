<%--
  Created by IntelliJ IDEA.
  User: drake
  Date: 11/28/2022
  Time: 9:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Contact</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
    <style>
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-color: #333;
        }

        li {
            float: left;
        }

        li a {
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        li a:hover {
            background-color: #111;
        }
    </style>
</head>

<body>
<ul>
    <li><a href="/home">Home</a></li>
    <li><a href="/login">Login</a></li>
    <li><a href="/register">Register</a></li>
    <li><a href="/feedback">Feedback</a></li>
    <li><a href="/contact">Contact Us</a></li>
    <li><a href="/logout">Logout</a></li>
</ul>
<h2>${sessionScope.msg}</h2>
<h3>All users:</h3>
<form method="post" action="admin">
<c:forEach items="${sessionScope.AllUsers}" var="user">
    <div class="form-check">
        ${user.id} ${user.username} ${user.password} ${user.admin} ${user.available}
            <button class="btn btn-primary" name="disable_user_btn" type="submit" value="${user.id}">Disable/Enable</button>
    </div>
</c:forEach>
</form>
<h3>All feedback:</h3>
<c:forEach items="${sessionScope.AllFeedback}" var="feedback">
    <div class="form-check">
            ${feedback.id} ${feedback.rating} ${feedback.comment}
    </div>
</c:forEach>
<h3>All Submissions:</h3>
<c:forEach items="${sessionScope.AllSubmission}" var="submission">
    <div class="form-check">
            ${submission.id} ${submission.userId} ${submission.quizId} ${submission.score} ${submission.startTime} ${submission.endTime}
    </div>
</c:forEach>

<h3>All Questions:</h3>
<c:forEach items="${sessionScope.AllQuestion}" var="question">
    <div class="form-check">
            ${question.id} ${question.quizId} ${question.description}
    </div>
</c:forEach>

</body>
</html>
