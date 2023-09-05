package step.learning.servlets;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import step.learning.servises.HashService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class HashServlet extends HttpServlet {
    private final HashService hashService2;
    @Inject
    public HashServlet(HashService hashService) {
        this.hashService2 = hashService;
    }

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        req.setAttribute("pageName", "hash");
        req.getRequestDispatcher("WEB-INF/_layout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getParameter("input_text");
        String result = hashService2.hash(text);
        System.out.println("Полученная строка: " + result );
        req.setAttribute("pageName", "hash");
        req.setAttribute("result", result);
        req.getRequestDispatcher("WEB-INF/_layout.jsp").forward(req, resp);
    }
}
