package step.learning.servlets;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import step.learning.servises.HashService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Singleton
public class HashServlet extends HttpServlet {
    private final HashService hashService;
    @Inject
    public HashServlet(HashService hashService) {
        this.hashService = hashService;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // перевіряємо чи є у сесії збережені дані
        HttpSession session = req.getSession();
        String result = (String) session.getAttribute("hash-result");
        if (result != null) {  // є дані
            req.setAttribute("result", result);      // переносимо їх у запит
            //session.removeAttribute( "hash-result" ) ;  // видаляємо з сесії для запобігання
            // повторів при оновленнях сторінки
        }
        req.setAttribute("pageName", "hash");
        req.getRequestDispatcher("WEB-INF/_layout.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String inputText = req.getParameter("input_text");
        HttpSession session = req.getSession();
        String result = "";
        if (inputText == null) {
            resp.setStatus(406);
            resp.getWriter().print("Required 'input_text' parameter");
            return;
        } else if (inputText != null && inputText.length() > 0) {
            session.setAttribute("inputText", inputText);
            result = String.format("hash('%s') = %s", inputText, hashService.hash(inputText));
            session.setAttribute("hash-result", result);
            System.out.println(result);
        }
        if ("download".equals(req.getParameter("mode"))) {
            inputText = (String) session.getAttribute("inputText");
            result = (String) session.getAttribute("hash-result");
            if (inputText == null) {
                return;
            }
            if (inputText.length() > 8) {
                inputText = inputText.substring(0, 8);
            }
            // натиснена кнопка "download" - режим завантаження файлу
            // Для передачі відповіді як файлу необхідно встановити заголовки:
            resp.setHeader("Content-Type", "application/octet-stream");
            // за типом вмісту або узагальнений тип "octet-stream", цей тип гарантує,
            // що браузер не буде пробувати відкрити цей файл, а перейде у скачування
            // не обов'язково - додаємо відомості про назву файлу (з якою він буде
            // зберігатись браузером)
            // resp.setHeader( "Content-Disposition", "attachment; filename=\"hash.txt\"" ) ;
            String headerValue = "attachment; filename=\"" + inputText + ".txt\"";
            resp.setHeader("Content-Disposition", headerValue);
            // вміст файлу виводимо у тіло відповіді
            resp.getWriter().print(result);
            session.removeAttribute("hash-result");
            session.removeAttribute("inputText");
            // припиняємо подальшу роботу сервлету
            return;
        }
        resp.sendRedirect(req.getRequestURI());
    }
}
/*
Робота з формами. Передача даних.
Параметри із запиту вилучаються методом req.getParameter(name)
назва параметру - як у полі форми (<input name="input_text"...)
Якщо потрібен повний перелік параметрів, то наявні методи
 req.getParameterNames() - імена параметрів
 req.getParameterMap() - імена та значення, цей метод збирає у масив значення
    параметрів з однаковими іменами

1. GET
 методом GET не можна передавати тіло запиту, відповідно, усі параметри обмежені
 query-типом (URL-параметри)
 "+" - простота, можливість зробити посилання (з даними)
 "-" - наочність (перегляд даних, що передаються), обмеження на великі об'єми даних

2. POST
 цим методом можна передавати як URL-параметри, так і тіло запиту
 req.getParameter(name) проводить пошук в обох місцях, при наявності однакових
 імен - береться перше.
 "+" - можливість тіла (великих обсягів), прихованість даних (від випадкового перегляду)
 "_" - особливості при оновленні сторінки, побудованої методом POST - браузер
        видає повідомлення та намагається повторно передати дані
       запит з тілом ---> сторінка
           оновлення? - повторити запит = повторити запит з тілом = повторна передача форми
 Вирішення цієї проблеми також відоме як "скидання ПОСТ-параметрів" полягає у тому,
 що сервер на ПОСТ-запит надсилає перенаправлення (редирект), а дані зберігає у себе.
 Повторний запит приходить без даних, методом ГЕТ і це дозволяє уникнути повторної передачі
 даних форми.
  ПОСТ(дані) -------------> оброблення, зберігання   |  ? зберігання між запитами ?
  слідування <------------- редирект                 |  НТТР-сесії - механізм такого збереження
  (новий запит) ГЕТ -------> відновлення даних,      |
     сторінка <------------- HTML
 */
