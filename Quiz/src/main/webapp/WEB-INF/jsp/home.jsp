<%--
  Created by IntelliJ IDEA.
  User: drake
  Date: 11/27/2022
  Time: 3:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>home</title>
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
    <h1>${sessionScope.msg}</h1>
    <div class="container">
        <form method="post" action="home">
            <h3>Select a quiz</h3>
            <c:forEach items="${sessionScope.QuizList}" var="quiz">
                <div class="form-check">
                    <label class="form-check-label">
                        <input type="radio" name="quizSelection" class="form-check-input" value="${quiz.id}"/>${quiz.description}
                    </label>
                </div>
            </c:forEach>
            <button class="btn btn-primary" type="submit">submit</button>
        </form>
    </div>

</body>
</html>
