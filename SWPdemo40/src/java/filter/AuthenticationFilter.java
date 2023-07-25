package filter;

import DTO.Employee;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        //this.context.log("Requested Resource::"+uri);

        HttpSession session = req.getSession(false);
        Employee user = null;
        if (session != null) {
            user = (Employee) session.getAttribute("USER");
        }

        if (user == null && !uri.endsWith("Login.jsp") && !uri.endsWith("LoginServlet") && !uri.endsWith(".css") && !uri.endsWith(".png") && !uri.endsWith(".jpg")) {
            this.context.log("Unauthorized access request");
            res.sendRedirect("Login.jsp");
        } else {
            chain.doFilter(request, response);
        }
        
    }

    @Override
    public void destroy() {
        //close any resources here
    }
}
