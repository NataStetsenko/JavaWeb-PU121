<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    StringBuffer url = request.getRequestURL();
    String url2 = url.toString();
    String url3  = url2.replaceAll("WEB-INF/_layout.jsp", "");
    String contextPath = request.getContextPath();
    String azurePath = "https://app-javaweb-pu121.azurewebsites.net/";
    String uri = request.getRequestURI();
    String pageName =                   // Вилучаємо значення атрибуту (закладеного у сервлеті)
            (String)                    // оскільки значення Object, необхідне пряме перетворення
                    request             // об'єкт request доступний у всіх JSP незалежно від сервлетів
                            .getAttribute("pageName")     // збіг імен зі змінною - не вимагається
                    + ".jsp";          // Параметри можна модифікувати

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

    <link type="text/css" rel="stylesheet" href="<%=url3%>CSS/style.css"/>
    <link type="text/css" rel="stylesheet" href="<%=url3%>CSS/normalize.css">
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Title</title>
</head>
<nav>
    <div class="nav-wrapper container">
<a href="<%=url3%>" class="brand-logo" style="margin-left: 10px">Logo</a>

<ul id="nav-mobile" class="right hide-on-med-and-down">
    <li <% if ("jsp.jsp".equals(pageName)) { %> class="active" <% } %> >
        <a href="<%=url3%>jsp">JSP</a>

    </li>
    <li <% if ("servlet.jsp".equals(pageName)) { %> class="active" <% } %> >
        <a href="<%=url3%>servlet">Servlet</a>
    </li>
    <li>
        <!-- Modal Trigger -->
        <a class="waves-effect waves-light btn modal-trigger red lighten-2"
           href="#auth-modal"><span class="material-icons">login</span></a>
    </li>
</ul>

</div>
</nav>
<body>


<jsp:include page="<%=pageName%>"/>


<%-- Materialize Modal (Auth block) --%>
<!-- Modal Structure -->
<div id="auth-modal" class="modal">
    <div class="modal-content">
        <div class="row">
            <form class="col s12" method="post">
                <div class="row">
                    <div class="input-field col s6">
                        <i class="material-icons prefix">account_circle</i>
                        <input id="icon_prefix" name="icon_prefix" type="text" class="validate">
                        <label for="icon_prefix">First Name</label>
                    </div>
                    <div class="input-field col s6">
                        <i class="material-icons prefix">phone</i>
                        <input id="icon_telephone" type="tel" class="validate">
                        <label for="icon_telephone">Telephone</label>
                    </div>
                </div>
                <button class="btn waves-effect waves-light" type="submit" name="action">Submit
                    <i class="material-icons right">send</i>
                </button>
            </form>
        </div>
    </div>
    <div class="modal-footer">
        <a href="#!" class="modal-close waves-effect waves-red btn-flat">Agree</a>
    </div>
</div>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const elems = document.querySelectorAll('.modal');
        M.Modal.init(elems, {
            opacity: 0.5
        } ) ;
    });
</script>
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
