package demo.spring.servlets;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(urlPatterns = "/a/b")
public class MyServlet extends HttpServlet {

    /*@Autowired
    private DataSource dataSource;*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print("<html><head><title>你好，世界</title></head><body><h1>你好，世界！</h1><hr/></body></html>");
    }
}
