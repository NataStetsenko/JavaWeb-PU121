package step.learning.servlets;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import step.learning.servises.HashService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

//@WebServlet("/jsp")
@Singleton
public class JspServlet extends HttpServlet {
    private final Logger logger;
    private final HashService hashService;
    @Inject
    public JspServlet(Logger logger, HashService hashService) {
        this.logger = logger;
        this.hashService = hashService;
    }

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        logger.log(Level.INFO, "INFO");
        logger.log(Level.WARNING, "WARNING" + hashService.hash("123"));
        req.setAttribute("pageName", "jsp");
        req.getRequestDispatcher("WEB-INF/_layout.jsp").forward(req, resp);
    }}
