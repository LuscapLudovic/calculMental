<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:forEach var="expression" items="${sessionScope.expressions}" varStatus="status">
        <div>
            ${expression.calcul} = ${expression.result}
        </div>
    </c:forEach>
</body>
</html>
