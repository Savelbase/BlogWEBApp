package org.itstep.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.itstep.dao.DataBase;

import java.io.IOException;

@MultipartConfig
public abstract class BaseServlet extends HttpServlet {
    protected String pagesPath;
    protected String page;

    public String getUploadDir() {
        return uploadDir;
    }

    private String uploadDir;

    public String getPath() {
        return String.format("%s/%s", pagesPath, page);
    }

    @SneakyThrows
    @Override
    public void init(ServletConfig config) throws ServletException {
        config.getServletContext().getClassLoader().loadClass("jakarta.servlet.annotation.MultipartConfig");
        DataBase dataBase = new DataBase();
        dataBase.createDB(config);
        pagesPath = config.getServletContext().getInitParameter("pagesPath");
        page = config.getInitParameter("page");
        uploadDir = config.getServletContext().getInitParameter("imagesDir");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(getPath()).forward(req, resp);
    }
}