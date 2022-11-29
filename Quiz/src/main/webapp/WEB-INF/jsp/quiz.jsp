<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quiz</title>
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
</ul>
<h1>${sessionScope.msg}</h1>
<div class="container">
    <form method="post" action="quiz">
        <h3>${sessionScope.QuestionDescription.description}</h3>


        <c:forEach items="${sessionScope.QuestionOption}" var="Option">
            <div class="form-check">
                <label class="form-check-label">
                    <input type="checkbox" name="math" class="form-check-input" value="${Option.id}"/>${Option.description}
                </label>
            </div>
        </c:forEach>
        <c:if test="${sessionScope.num > 1}">
            <button class="btn btn-primary" name="btn" type="submit" value="previous">Previous</button>
        </c:if>
        <c:if test="${sessionScope.num < 10}">
            <button class="btn btn-primary" name="btn" type="submit" value="next">Next</button>
        </c:if>

        <c:if test="${sessionScope.num > 0}">
            <button class="btn btn-primary" name="btn" type="submit" value="submit">submit</button>
        </c:if>

        <br>
        <h4>Nav:</h4>
        <c:if test="${sessionScope.num > 0}">
            <button class="btn btn-primary" name="btn" type="submit" value="1">Question 1</button>
            <button class="btn btn-primary" name="btn" type="submit" value="2">Question 2</button>
            <button class="btn btn-primary" name="btn" type="submit" value="3">Question 3</button>
            <button class="btn btn-primary" name="btn" type="submit" value="4">Question 4</button>
            <button class="btn btn-primary" name="btn" type="submit" value="5">Question 5</button>
            <button class="btn btn-primary" name="btn" type="submit" value="6">Question 6</button>
            <button class="btn btn-primary" name="btn" type="submit" value="7">Question 7</button>
            <button class="btn btn-primary" name="btn" type="submit" value="8">Question 8</button>
            <button class="btn btn-primary" name="btn" type="submit" value="9">Question 9</button>
            <button class="btn btn-primary" name="btn" type="submit" value="10">Question 10</button>
        </c:if>

    </form>
</div>
</body>
</html>
