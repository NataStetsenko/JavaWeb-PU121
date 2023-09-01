
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container ">
  <div class="card-panel  grey lighten-5">
    <table >
      <tr>
        <th class="table-danger"><span style="padding: 20px; font-size: large;" class="red-text text-lighten-3">Day</span></th>
        <th><span class="red-text text-lighten-3">Full Name</span></th>
        <th><span class="red-text text-lighten-3">Abbreviation</span></th>
      </tr>
      <%
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        String[] abb = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        String[] color = {"red accent-3", "orange accent-3", "light-green accent-3", "light-blue accent-3",
                "indigo accent-3", "deep-purple accent-1", "lime accent-1"};
        for (int i = 0; i < days.length; i++) {
      %>
      <tr>
        <td class="<%= color[i]%>"><span style="padding: 20px; font-size: large;"><%= i + 1 %></span> </td>
        <td class="<%= color[i]%>"> <%= days[i] %></td>
        <td class="<%= color[i]%>"> <%= abb[i] %></td>
      </tr>
      <%
        }
      %>
    </table>
  </div>
</div>

