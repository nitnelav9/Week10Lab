package filters;

import dataaccess.UserDB;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

public class AuthenticationFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

            // code that is executed before the servlet
            HttpServletRequest httpRequest = (HttpServletRequest)request;
            HttpSession session = httpRequest.getSession();
            String email = (String)session.getAttribute("email");
            HttpServletResponse httpResponse = (HttpServletResponse)response;
            
            
            if (email == null) {
                httpResponse.sendRedirect("login");
                return;
            }
            
            UserDB userDB = new UserDB();
            User user = userDB.get(email);
            
            if(user.getRole().getRoleId() != 2)
            {
                httpResponse.sendRedirect("admin");
                return;
            }
            
            chain.doFilter(request, response); // execute the servlet
   
            
            // code that is executed after the servlet
            
        
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void destroy() {
       
    }
}
