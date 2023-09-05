package step.learning.filters;

import com.google.inject.Singleton;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
@Singleton
public class LogFilter implements Filter {
//    private PrintWriter logger;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        try {
//            logger = new PrintWriter("notebook.txt");
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest ;
        HttpServletResponse response = (HttpServletResponse) servletResponse ;
        request.setCharacterEncoding( StandardCharsets.UTF_8.name() ) ;
        response.setCharacterEncoding( StandardCharsets.UTF_8.name() ) ;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = sdf.format(new Date());

        String method = request.getMethod();
        String url = request.getRequestURL().toString();
        try(FileWriter writer = new FileWriter("notebook.txt",true))
        {
            writer.write("|" + timestamp + "| " + method + " " + url + '\n');
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
//        logger.println("|" + timestamp + "| " + method + " " + url + '\n');

        // не забути - передати роботу по ланцюгу
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
//        if (logger != null) {
//            logger.close();
//        }
    }
}
