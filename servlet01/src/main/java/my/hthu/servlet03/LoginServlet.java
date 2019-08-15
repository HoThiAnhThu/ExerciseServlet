package my.hthu.servlet03;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                           IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("userName");
        String password = request.getParameter("userPass");

        if (isLoginSuccess(username, password)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("home");
            requestDispatcher.forward(request, response);
        } else {
            out.println("Username or password is not correct. Please try again !");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.html");
            requestDispatcher.include(request, response);
        }
    }

    private boolean isLoginSuccess(String username, String password) {
        return username.equals("user") && password.equals("123456");
    }
}
