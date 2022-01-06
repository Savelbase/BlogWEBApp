package org.itstep.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.itstep.dao.UserDaoImpl;
import org.itstep.domain.User;

import java.io.IOException;

public class SignUpServlet extends BaseServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = new User(1L, login , password , firstName , lastName);
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.createUser(user);
        resp.sendRedirect("admin");
    }
}
