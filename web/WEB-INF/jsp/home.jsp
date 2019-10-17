<%--
  Created by IntelliJ IDEA.
  User: llu
  Date: 16/10/2019
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="no-js" lang="fr">
  <head>
    <title>$Title$</title>
  </head>
  <link rel="stylesheet" type="text/css"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"/>
  <link rel="stylesheet" type="text/css"
        href="<%= request.getContextPath()%>/vendor/foundation-6.5.1/css/foundation.min.css"/>
  <link rel="stylesheet" href="<%= request.getContextPath()%>/css/home.css">
  <body>
    <div>
        <div>
          <img src="<%= request.getContextPath()%>/pict/calcul.jpg" alt="">
        </div>
      <div>
        <a class="button" href="/calculMental_war_exploded/calculs">Commencer les calculs</a>
      </div>
    </div>
    <script src="<%= request.getContextPath()%>/vendor/foundation-6.5.1/js/vendor/jquery.js"></script>
    <script src="<%= request.getContextPath()%>/vendor/foundation-6.5.1/js/vendor/foundation.min.js"></script>
  </body>
</html>
