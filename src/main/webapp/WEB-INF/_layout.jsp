<%@ page import="java.io.File" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
   String javaPath = "/JavaWeb-PU121/target/JavaWeb-PU121/upload/ad5f69a1-6.jpg";
   //String javaPath = contextPath+"/img/Java_Logo.png";


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
<body>
<nav>
    <div class="nav-wrapper container">
        <a href="<%=url3%>" class="brand-logo" style="margin-left: 10px">Logo</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="#front">Front</a></li>
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
<div>
    <img id="javaPath" src="<%= javaPath %>" width="50" class="left" style="margin-left: 50px" />
    <jsp:include page="<%= pageName %>" />
</div>

<%-- Materialize Modal (Auth block) --%>
<!-- Modal Structure -->
<div id="auth-modal" class="modal">
    <div class="modal-content">
        <h4>Автентифікація</h4>
        <div class="row valign-wrapper">
            <div class="input-field col s6">
                <i class="material-icons prefix">account_circle</i>
                <input id="auth-login" name="auth-login" type="text" class="validate">
                <label for="auth-login">Логін</label>
            </div>
            <div class="input-field col s6">
                <i class="material-icons prefix">mode_edit</i>
                <input id="auth-password" name="auth-password" type="text" class="validate">
                <label for="auth-password">Пароль</label>
            </div>
        </div>
        <p id="result" style="color: red"></p>
    </div>
    <div class="modal-footer">
        <a href="<%= contextPath %>/signup" class="modal-close waves-effect waves-green btn-flat teal lighten-3">Реєстрація</a>
        <a href="#!" class="modal-close waves-effect waves-green btn-flat indigo lighten-3">Забув пароль</a>
        <a href="#!" class="waves-effect waves-green btn-flat green lighten-3" id="sign-button">Вхід</a>
    </div>
</div>

<!--JavaScript at end of body for optimized loading-->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const elems = document.querySelectorAll('.modal');
        M.Modal.init(elems, {
            opacity: 0.5
        } ) ;
        initModalButtons() ;
        window.addEventListener('hashchange', frontRouter) ;
        frontRouter() ;
    });

    function frontRouter() {
        console.log(location.hash);
        switch( location.hash ) {
            case '#front':
                loadFrontPage();
                break;
            default:
        }
    }

    function loadFrontPage() {
        const token = window.localStorage.getItem('token');
        const headers = (token == null) ? {} : {
            'Authorization': `Bearer ${token}`
        }
        <%--fetch('<%= contextPath %>/front', {--%>
        <%--    method: 'GET',--%>
        <%--    headers: headers--%>
        <%--})--%>
        <%--    .then(response => {--%>
        <%--        if (response.ok) {--%>
        <%--            console.log("Avatar path:");--%>
        <%--            return response.json();--%>
        <%--        }--%>
        <%--        throw new Error("not ok.");--%>
        <%--    })--%>
        <%--    .then(data => {--%>
        <%--        if(data!=null){--%>
        <%--            console.log(data);--%>
        <%--            const imgElement = document.getElementById("javaPath");--%>
        <%--            imgElement.src = "/JavaWeb-PU121/target/JavaWeb-PU121/upload/ad5f69a1-6.jpg";--%>
        <%--            console.log(javaPath)--%>
        <%--            console.log("Avatar path:", data.avatarPath);--%>
        <%--        }--%>

        <%--    })--%>

        fetch('<%= contextPath %>/front', {
            method: 'GET',
            headers: headers
        })
            .then(r => r.text())
            .then(t => {
                console.log(t)
            });
    }

    function initModalButtons() {
        const signButton = document.getElementById('sign-button');
        if( signButton ) {
            signButton.addEventListener( 'click', loginClick ) ;
        }
        else {
            console.error( '#sign-button not found' );
        }
    }

    function loginClick() {
        const loginInput = document.getElementById('auth-login');
        if( ! loginInput ) throw "input id='auth-login' not found" ;
        const passwordInput = document.getElementById('auth-password');
        if( ! passwordInput ) throw "input id='auth-password' not found" ;

        const authLogin = loginInput.value.trim() ;
        if( authLogin.length < 2 ) {
            alert( "Логін занадто короткий або не введений!" ) ;
            return ;
        }
        const authPassword = passwordInput.value ;
        if( authPassword.length < 2 ) {
            alert( "Пароль занадто короткий або не введений" ) ;
            return ;
        }
        // const url = `<%= contextPath %>/signup?auth-login=${authLogin}&auth-password=${authPassword}`;
        const url = `<%= contextPath %>/signup` ;
        // let formData = new FormData();
        // formData.append('auth-login',authLogin );
        // formData.append('auth-password',authPassword );
        // fetch(url, { method: 'PUT', body: formData })
        fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                'auth-login': authLogin,
                'auth-password': authPassword
            })
        })
            .then(response => {
                return response.json(); // Парсимо відповідь як JSON
            })
            .then(data => {
// data буде об'єктом, який містить поля statusCode та message
                console.log(data);
                if( data.statusCode == 200 ) {
                    window.localStorage.setItem('token', data.message);
                    // close Material modal
                    const instance = M.Modal.getInstance(document.getElementById("auth-modal"));
                    instance.close();
                }
                else {
                    const result = document.getElementById('result');
                    result.textContent = data.message;
                }
            })
            .catch(error => {
                console.error('Fetch Error:', error);
            });
    }
</script>
</body>
</html>