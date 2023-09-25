package step.learning.servlets;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import step.learning.db.dao.UserDao;
import step.learning.db.dao.WebTokenDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Singleton
public class InstallServlet extends HttpServlet {
    @Inject
    private UserDao userDao;
    @Inject
    private WebTokenDao webTokenDao;



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bd = "03.10.1979";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date birthdate = null;
        try {
            birthdate = dateFormat.parse(bd);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
                     //  userDao.delete();
                       webTokenDao.delete();
//            userDao.install() ;
           webTokenDao.install();
            resp.getWriter().print("Install OK");
        } catch (RuntimeException ex) {
            resp.getWriter().print("Install Error. Look at logs");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
