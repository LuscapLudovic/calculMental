<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Répondre aux 10 calculs :</h1>
    <h3><i>Arrondissez à la valeur inférieure</i></h3>
    <h4>Exemple: 1 / 2 = 0</h4>
    <form action="<%= request.getContextPath()%>/calculs" method="post">
        <c:forEach var="expression" items="${sessionScope.expressions}" varStatus="status">
            <div>
                <h2>${expression.calcul}</h2>
                <input name="calcul" type="number">
            </div>
        </c:forEach>
        <input type="submit" value="Valider">
    </form>
</body>
</html>
