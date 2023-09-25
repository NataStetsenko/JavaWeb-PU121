<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<div class="container">

    <div class="row">
        <div class="col s12 m5 l3 ">
            <div class="collection card">
                <a href="daysOfWeek" class="collection-item">
                    <span class="red-text text-lighten-3">
                    <span class="badge red-text text-lighten-3">1</span>Homework 1</span></a>
                <a href="partsOfUrl" class="collection-item">
                 <span class="red-text text-lighten-3">
                    <span class="badge red-text text-lighten-3">1</span>Homework 2</span></a>
                <a href="hash" class="collection-item">
                    <span class="red-text text-lighten-3">
                         <span class="new badge red lighten-3">1</span>Homework 4</span></a>
                <a href="#!" class="collection-item"><span class="red-text text-lighten-3">Homework 5</span></a>
            </div>
        </div>
        <div style="position: fixed; left: 64%; top: 13%">
            <form action="home" method="post">
                   <button class="btn waves-effect waves-light" type="submit" name="action">Текстовый файл отчета
                    <i class="material-icons right">send</i>
                </button>
            </form>
        </div>
        <div class="col s12 m7 l9">
            <div class="card-panel">
                <h1 class="center-align red-text text-lighten-3">Java web</h1>
                <p>
                    Створюємо новий проєкт, Maven archetype -- ...-webapp
                </p>
                <p>
                    Налаштовуємо конфігурацію запуску.
                    Веб-проєкт запускається через веб-сервер, який не входить
                    у "коробку" Java і вимагає окремого встановлення.
                    Для цього існують:
                </p>
                <ul>
                    <li>Apache Tomcat (для Java-8 -- До версії 9, рекомендовано 8)</li>
                    <li>Glassfish (4 або 5)</li>
                    <li>JBoss / WildFly (до 20)</li>
                    <li>GAE - Google App Engine та інші хмарні сервіси</li>
                </ul>
                <p>
                    Скачуємо будь-який сервер, у більшості випадків його достатньо
                    розпакувати з архіву, окремого встановлення не вимагається.
                    Не рекомендується створювати папки з не-ASCII символами в імені.
                </p>
                <p>
                    Edit Configuration -- Add -- Tomcat local -- зазначаємо папку сервера (
                    куди був розпакований).
                    Встановлюємо артефакт для вивантаження (deploy) -- ...war exploded
                    За бажанням змінюємо контекст артефакту (не обов'язково)
                    OK
                </p>
                <p>
                    Запускаємо, за наявності збою кодування додаємо у самий початок файлу
                    &lt;%@ page contentType="text/html;charset=UTF-8" %>
                    та додаємо тег &lt;meta charset="utf-8" /> у заголовок HTML
                </p>
                <p class="test">
                    Деплой проєкту виглядає наступним чином: <br/>
                    - відбувається збірка (...war_exploded war - web archive)<br/>
                    - цей архів переноситься у спеціальну папку, на яку "дивиться" сервер<br/>
                    - сервер при першому звертанні до сайту розпаковує архів і працює з його файлами<br/>
                    Особливості - після запуску відкривається браузер та кнопка запуску
                    перетворюється на "перезапуск", яка пропонує декілька варіантів
                </p>
                <ul>
                    <li>Update resources - замінити у папці деплою лише статичні ресурси (html, css, ...)</li>
                    <li>Update classes and resources - перекомпілювати класи та оновити їх та ресурси</li>
                    <li>Redeploy - повторити процедуру деплою</li>
                    <li>Restart Server - зупинити сервер та запустити його та процедуру деплою</li>
                </ul>
            </div>
        </div>
    </div>

</div>

