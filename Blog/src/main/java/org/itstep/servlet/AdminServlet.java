package org.itstep.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.itstep.dao.UserDaoImpl;
import org.itstep.domain.User;


public class AdminServlet extends BaseServlet{

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        UserDaoImpl userDao = new UserDaoImpl();
        if(req.getParameter("add")!=null){
            resp.sendRedirect("/add");
        }
        if(req.getParameter("signup")!=null){
            resp.sendRedirect("/signup");
        }
        if(req.getParameter("login") != null && req.getParameter("password") != null){
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            if(userDao.getUser(login , password)==null){
                System.out.println("Нет такого пользователя!");
            }{
                User admin = userDao.getUser(login , password);
                if(admin==null){
                    System.out.println("USER IS NULL ! ");
                }
                if (admin != null){
                    req.getSession().setAttribute("admin" , admin );
                }
            }
            resp.sendRedirect("/");
        }

    }
}
