/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package filter;

import entity.User;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author legion
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST},
        urlPatterns = {"/moder/*"})
public class ModeratorFilter extends HttpFilter implements Filter {

    public ModeratorFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String uri = req.getRequestURI();
        User user = (User) session.getAttribute("currentUser");
        if ((user == null || user.getRole() != 1) && !(uri.contains("login.jsp"))) {
            res.sendRedirect(req.getContextPath() + "/errorpage.jsp");
        } else {
            fc.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

}
