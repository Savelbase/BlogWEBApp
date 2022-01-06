package org.itstep.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.itstep.dao.PostDaoMySqlImpl;

import java.io.IOException;
import java.sql.SQLException;

public class HomeServlet extends BaseServlet {

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostDaoMySqlImpl postDao = new PostDaoMySqlImpl();
        if (postDao.findAll() != null) {
            req.setAttribute("posts", postDao.findAll());
        }
        super.doGet(req, resp);

    }

}
