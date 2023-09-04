<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
    String azurePath = "https://app-javaweb-pu121.azurewebsites.net/";

    String pageName =                   // Вилучаємо значення атрибуту (закладеного у сервлеті)
            (String)                    // оскільки значення Object, необхідне пряме перетворення
                    request             // об'єкт request доступний у всіх JSP незалежно від сервлетів
                            .getAttribute("pageName")     // збіг імен зі змінною - не вимагається
                    + ".jsp";          // Параметри можна модифікувати
    //
%>

<html>
<head>
    <meta charset="UTF-8">
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"
          media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="style.css"/>
    <link type="text/css" rel="stylesheet" href="normalize.css">
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Title</title>
</head>
<nav>
    <div class="nav-wrapper container">
<%--        <a href="<%=azurePath%>" class="brand-logo" style="margin-left: 10px">Logo</a>--%>
<a href="<%=contextPath%>" class="brand-logo" style="margin-left: 10px">Logo</a>

<ul id="nav-mobile" class="right hide-on-med-and-down">
    <li <% if ("jsp.jsp".equals(pageName)) { %> class="active" <% } %> >
        <a href="<%=contextPath%>/jsp">JSP</a>

    </li>
    <li <% if ("servlet.jsp".equals(pageName)) { %> class="active" <% } %> >
        <a href="<%=contextPath%>/servlet">Servlet</a>
    </li>
    <li><a href="#">JavaWeb</a></li>
</ul>
</div>
</nav>
<body>
<jsp:include page="<%=pageName%>"/>


<script type="text/javascript"
src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
<footer class="page-footer">
<div class="container">
<div class="container">
    © 2023 Java web
    <a class="grey-text text-lighten-4 right" href="#!">More Links</a>
</div>
</div>
</footer>
</html>
