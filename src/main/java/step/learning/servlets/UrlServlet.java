package step.learning.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/partsOfUrl")
public class UrlServlet extends HttpServlet {
    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        req.setAttribute("pageName", "partsOfUrl");
        req.getRequestDispatcher("WEB-INF/_layout.jsp").forward(req, resp);
    }
}
