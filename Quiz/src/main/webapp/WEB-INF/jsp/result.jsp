<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Result</title>
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
<h1>${sessionScope.User.username}, you completed quiz: ${sessionScope.CurrentQuiz.description}! Your score is ${sessionScope.score}</h1>
<h1>Start: ${sessionScope.StartTime}. End: ${sessionScope.EndTime}</h1>
<c:choose>
    <c:when test="${sessionScope.score >= 60}"><h1 class="text-success">You passed!</h1></c:when>
    <c:otherwise><h1 class="text-danger">You failed!</h1></c:otherwise>
</c:choose>
<h3>Return <a href="/home">home</a> to take another quiz. </h3>
<h2>Details:</h2>

<c:forEach items="${sessionScope.QuestionList}" var="question">
    <div style="font-size: large" class="form-check">
        Question ${question.id}: ${question.description}.&nbsp;&nbsp;&nbsp;&nbsp; Correct answers:
        <c:forEach items="${sessionScope.OptionList}" var="correctOption">
            <c:if test="${correctOption.questionId == question.id && correctOption.correct}">
                "${correctOption.description}"&nbsp;&nbsp;
            </c:if>
        </c:forEach>
        &nbsp;&nbsp;&nbsp;&nbsp;
        Your answers:
        <c:forEach items="${sessionScope.SubmissionDetailList}" var="submissionDetail">
            <c:if test="${submissionDetail.questionId == question.id}">
                <c:forEach items="${sessionScope.OptionList}" var="userOption">
                    <c:if test="${userOption.id == submissionDetail.userChoice}">
                        "${userOption.description}"&nbsp;&nbsp;
                    </c:if>
                </c:forEach>
            </c:if>
        </c:forEach>
        <br>
    </div>
</c:forEach>


</body>

</html>