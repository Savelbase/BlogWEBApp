package org.itstep.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.itstep.dao.PostDao;
import org.itstep.dao.PostDaoMySqlImpl;
import org.itstep.domain.Post;

import java.io.IOException;

public class PostServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        PostDao dao = new PostDaoMySqlImpl();
        Post post = dao.findById(id);
        req.getSession().setAttribute("poster", post);
        super.doGet(req, resp);
    }
}
