<html>
<head>
    <title>Resultat</title>
</head>
<body>
    <h2>Bravo vous avez fait un score de : ${sessionScope.resultCalcul}</h2>

    <div>
        <h2>Tableau des scores : </h2>
        <c:forEach var="user" items="${sessionScope.ten_best_user}" varStatus="status">
            <div>
                <span> ${user.username} </span>
                <span> ${user.best_score} </span>
            </div>
        </c:forEach>
    </div>
</body>
</html>
