package my.hthu.servlet01;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
                                                                                            IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        String name = req.getParameter("name");
        writer.println("<h3> Hello " + name + "</h3>");
        writer.println("Host" + req.getRemoteHost());
        writer.println("Host" + req.getLocalAddr());
        writer.println("Header" + req.getHeader("gzip"));
        writer.close();
    }
}
