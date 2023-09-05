
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String result = "";
  if ((String) request.getAttribute("result") != null) {
    result = (String) request.getAttribute("result");
    request.removeAttribute("result");
  }

%>
<div class="container">
  <div class="card-panel  grey lighten-5">
    <div class="row">
      <form action="hash" method="post" class="col s12">
        <div class="row">
          <div class="input-field col s6">
            <input id="input_text" name="input_text" type="text" data-length="10"
                   pattern="[0-9]*" title="Можна вводити лише цифри">
            <label style="font-size: 18px;" for="input_text">Введіть рядок:</label>
            <br>
            <button style="margin-left: 60%" type="submit" class="btn waves-effect waves-light red lighten-3" value="Відправити">Відправити
              <i class="material-icons right">send</i></button>
          </div>
        </div>
        <div class="row">
          <div class="input-field col s12">
            <textarea id="textarea2" class="materialize-textarea" data-length="120" readonly>
              <%=result%></textarea>
            <label style="font-size: 18px;" for="textarea2">Результат:</label>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>




