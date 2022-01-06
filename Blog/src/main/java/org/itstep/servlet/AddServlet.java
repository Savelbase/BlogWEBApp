package org.itstep.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.SneakyThrows;
import org.itstep.dao.PostDao;
import org.itstep.dao.PostDaoMySqlImpl;
import org.itstep.domain.Post;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@MultipartConfig
public class AddServlet extends BaseServlet {

    private String path;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        path = config.getServletContext().getRealPath(getUploadDir());
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getSession().getAttribute("admin") != null) {
            super.doGet(req, resp);
        } else {
            resp.sendRedirect("/");
        }
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String title = req.getParameter("post-name");
        String text = req.getParameter("post-text");
        System.out.println(text);
        String date = req.getParameter("date");
        Part image = req.getPart("image");
        String fileName = image.getSubmittedFileName();
        if (!Files.exists(Path.of(path))) {
            Files.createDirectories(Path.of(path));
        }
        if (!Files.exists(Path.of(path + "/" + fileName))) {
            Files.copy(image.getInputStream(), Path.of(path + "/" + fileName));
        }
        Date dateof = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dateof = sdf.parse(date);
        } catch (ParseException ex) {
            dateof = new Date();
        }
        System.out.println(path + " - REAL PATH ! ");
        Post post = new Post(title, text, dateof, getUploadDir() + "/" + fileName);
        PostDao postDao = new PostDaoMySqlImpl();
        int id = postDao.save(post);
        post.setId((long) id);
        System.out.println(post + " - пост не добавлен ! ");
        resp.sendRedirect("/");
    }
}
