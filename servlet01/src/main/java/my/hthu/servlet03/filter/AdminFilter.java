package my.hthu.servlet03.filter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;


public class AdminFilter implements Filter {

    private FilterConfig filterConfig;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
                                                                                                                  IOException,
                                                                                                                  ServletException {

        PrintWriter writer = servletResponse.getWriter();
        String username = filterConfig.getInitParameter("username");
        String password = filterConfig.getInitParameter("password");
        if (isLoginValid(username, password)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            servletResponse.setContentType("text/html");
            writer.println("Username or password is not valid");
            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("admin-login.html");
            requestDispatcher.include(servletRequest, servletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override public void destroy() {

    }

    private boolean isLoginValid(String username, String password) {
        return username.equals("admin") && password.equals("123456");
    }
}
