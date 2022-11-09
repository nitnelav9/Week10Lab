/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
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

/**
 *
 * @author valentinmorales
 */
public class AdminFilter implements Filter {
    
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
            
            if(user.getRole().getRoleId() != 1)
            {
                httpResponse.sendRedirect("notes");
                return;
            }
            
            chain.doFilter(request, response); // execute the servlet
            

    }

    /**
     * Return the filter configuration object for this filter.
     */
    
    public void destroy() {        
    }


    public void init(FilterConfig filterConfig) {        

    }
    
}
