package step.learning.servlets;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import step.learning.db.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class HomeServlet extends HttpServlet {   // назва класу - довільна
    @Inject
    private UserDao userDao ;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pageName", "home");
        String text =
        request.getParameter("text");
        request.setAttribute("text", text);






        request                              // робимо внутрішній редирект - передаємо роботу
                .getRequestDispatcher(           // до іншого обробника - ***.jsp
                        "WEB-INF/_layout.jsp" )  // для того щоб прибрати прямий доступ до ***.jsp його
                .forward( request, response ) ;  // переміщують у закриту папку WEB-INF
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println(req.getParameter("icon_prefix"));




//        List<String> buf = new ArrayList<>();
//       try (FileReader reader = new FileReader("notebook.txt");
//            Scanner scanner = new Scanner(reader)) {
//            while (scanner.hasNext()) {
//                buf.add(scanner.nextLine());
//            }
//       }
//        catch (IOException ex)
//        {
//            System.out.println(ex.getMessage());
//       }
//        try {
//            FileWriter fileWriter = new FileWriter("D:\\text.txt");
//            // Создаем BufferedWriter для более эффективной записи
//            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//            // Перебираем список строк и записываем их в файл
//            for (String line : buf) {
//                bufferedWriter.write(line);
//                bufferedWriter.newLine(); // Добавляем перевод строки между строками
//            }
//            // Закрываем BufferedWriter и FileWriter
//            bufferedWriter.close();
//            fileWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        req.setAttribute("pageName", "home");
        req.getRequestDispatcher("WEB-INF/_layout.jsp").forward(req, resp);
    }
}

/*
Servlets - спеціальні класи Java для мережевих задач
Робота з сервлетами вимагає встановлення servlet-api
<!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api -->
Для веб-розробки частіше за все береться нащадок HttpServlet
HttpServlet - можна вважати аналогом контроллера у версі API

Після складання сервлет треба зареєструвати. Є два способи (без ІоС)
- через налаштування сервера web.xml
- за допомогою анотацій (servlet-api 3 та вище)

Через web.xml
 "+" централізовані декларації - усі в одному місці
     гарантований порядок декларацій
     сумісність зі старими технологіями
 "-" більш громіздкі інструкції

  <servlet>
    <servlet-name>Home</servlet-name>
    <servlet-class>step.learning.servlets.HomeServlet</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>Home</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>
 */
