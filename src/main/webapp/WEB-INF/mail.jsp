<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    StringBuffer url = request.getRequestURL();
    String url2 = url.toString();
    String url3 = url2.replaceAll("WEB-INF/_layout.jsp", "");
%>
<div class="container ">
    <div class="card-panel  grey lighten-5">
        <h1 class="red-text text-lighten-3">Робота з електронною поштою</h1>
        <div class="row">
            <div class="col s12 m3">
                <button class="waves-effect waves-light btn red lighten-2" onclick="textMailClick()">
                    TEXT<i class="material-icons right">send</i>
                </button>
            </div>
            <div class="col s12 m3">
                <button class="waves-effect waves-light btn red lighten-2" onclick="htmlMailClick()">
                    HTML<i class="material-icons right">send</i>
                </button>
            </div>
            <div class="col s12 m3">
                <button class="waves-effect waves-light btn red lighten-2" onclick="serviceMailClick()">
                    SERVICE<i class="material-icons right">send</i>
                </button>
            </div>
        </div>
        <div class="row">
            <div class="col s12 m8 offset-m2 l6 offset-l3">
                <div class="card-panel grey lighten-5 z-depth-1">
                    <div class="row valign-wrapper">
                        <div class="col s2">
                            <i class="material-icons right red-text text-lighten-3">send</i>
                        </div>
                        <div class="col s10">
                    <span class="black-text" id="mail-result">
                        Статус повідомлення буде відображено тут після натискання на одну з кнопок
                        надсилання
                    </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function textMailClick() {
        fetch("<%=url3%>mail", {
            method: "MAIL"
        }).then(r => r.text()).then(t => {
            document.getElementById("mail-result").innerText = t;
        })
    }
    function htmlMailClick() {
        fetch("<%=url3%>mail", {
            method: "PATCH"
        }).then(r => r.text()).then(t => {
            document.getElementById("mail-result").innerText = t;
        })
    }
    function serviceMailClick() {
        fetch("<%=url3%>mail", {
            method: "LINK"
        }).then(r => r.text()).then(t => {
            document.getElementById("mail-result").innerText = t;
        })
    }
</script>
<%--uxeh uqlq odsi gmdr--%>


