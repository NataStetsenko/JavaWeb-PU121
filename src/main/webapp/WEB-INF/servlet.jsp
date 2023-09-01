<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
  <p>
    Servlets - спеціальні класи Java для мережевих задач
    Робота з сервлетами вимагає встановлення servlet-api
    <!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api -->
    Для веб-розробки частіше за все береться нащадок HttpServlet
    HttpServlet - можна вважати аналогом контроллера у версі API
  </p>
  <p>
    Після складання сервлет треба зареєструвати. Є два способи (без ІоС)
    - через налаштування сервера web.xml
    - за допомогою анотацій (servlet-api 3 та вище)
  </p>
  <p>
    Через web.xml
    "+" централізовані декларації - усі в одному місці
    гарантований порядок декларацій
    сумісність зі старими технологіями
    "-" більш громіздкі інструкції
  </p>
  <p>
    &lt;servlet>
    &lt;servlet-name>Home&lt;/servlet-name><br/>
    &lt;servlet-class>step.learning.servlets.HomeServlet&lt;/servlet-class><br/>
    &lt;/servlet><br/>
    &lt;servlet-mapping><br/>
    &lt;servlet-name>Home&lt;/servlet-name><br/>
    &lt;url-pattern>/&lt;/url-pattern><br/>
    &lt;/servlet-mapping><br/>
  </p>
</div>
</body>
</html>
