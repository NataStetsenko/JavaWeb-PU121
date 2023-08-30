<%--
  Created by IntelliJ IDEA.
  User: Natali
  Date: 29.08.2023
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"  media="screen,projection"/>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>About JSP</title>
</head>
<body class="container">
<jsp:include page="nav.jsp"></jsp:include>
<div class="container">
    <div class="card-panel">
        <h1 class="center-align red-text text-lighten-3">Основи JSP</h1>
        <p>
            <b>JSP</b> - Java Server Pages - технологія веб-розробки, аналогічна ASP чи РНР.
        </p>
        <p>
            Які можливості зазвичай додають до HTML засоби препроцессінгу?<br/>
            - змінні <br/>
            - вирази (обчислення) <br/>
            - умовні операції (умовна верстка) <br/>
            - циклові конструкції <br/>
            - композиція (підключення окремих файлів)
        </p>
        <p>
            Код Java вставляється у довільне місце JSP-файлу за допомогою конструкції
            &lt;% %&gt;
            <%
                int x = 10 ;
                String str = "Hello" ;
            %>
            Виведення (вставлення в HTML) здійснюється конструкцією &lt;%= %&gt;
            x = <%= x %>, str + 'World' = <%= str + " World" %>
        </p>
        <p>
            Умовні конструкції утворюються наступним чином<br/>
            &lt;% if( condition ) { %&gt;<br/>
            &nbsp;    Тіло умовного блоку (як HTML, так і вставки коду)<br/>
            &lt;% } %&gt;<br/>
            <% if( x < 10 ) { %>
            <b>х менший за 10</b>
            <% } else { %>
            <i>х більший або рівний 10</i>
            <% } %>
        </p>
        <p>
            Циклова конструкція<br/>
            &lt;% цикл() { %&gt;<br/>
            &nbsp;  тіло циклу<br/>
            &lt;% } %&gt;<br/>
            <% for (int i = 0; i < 10; i++) { %>
            <b><%= i + 1 %></b>. <i>index: <%= i %></i> <br/>
            <% } %>
        </p>
    </div>

</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
