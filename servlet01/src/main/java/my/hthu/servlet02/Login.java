package my.hthu.servlet02;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginUser")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
                                                                                            IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        req.getRequestDispatcher("login.html").include(req, resp);
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        LoginDAO loginDAO = new LoginDAO();
        try {
            if (loginDAO.checkValidate(username, password)) {
                writer.println("<h3> Welcome " + username + " </h3>");
            } else {
                req.getRequestDispatcher("error.html").include(req, resp);
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        writer.close();

    }
}
