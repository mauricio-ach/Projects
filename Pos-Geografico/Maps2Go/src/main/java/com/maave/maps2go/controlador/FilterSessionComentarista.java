package com.maave.maps2go.controlador;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/comentarista/*")
public class FilterSessionComentarista implements Filter{
    public void init(FilterConfig request) {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(true);
        if (session.getAttribute("usuario") == null) {
            res.sendRedirect(req.getContextPath() + "/index.xhtml");
        } else {
            SessionCtrl.UsuarioLogged u = (SessionCtrl.UsuarioLogged) session.getAttribute("usuario");
            if(u.getRol() == 3){
                chain.doFilter(req, res);
            } else {
                res.sendRedirect(req.getContextPath() + "/index.xhtml");
            }
        }
    }

    public void destroy() {
    }

}
