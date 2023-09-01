
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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

