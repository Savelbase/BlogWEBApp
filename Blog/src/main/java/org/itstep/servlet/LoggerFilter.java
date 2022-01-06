package org.itstep.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.itstep.domain.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerFilter implements Filter {

    private String path ;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        path = filterConfig.getServletContext().getInitParameter("LoggerPath");
        path = filterConfig.getServletContext().getRealPath(path);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        System.out.println(path);
        if(!Files.exists(Path.of(path))){
            Files.createDirectories(Path.of(path));
        }
        File file = new File(path + "\\log.txt");
        System.out.println(file.getPath());
        if(!file.exists()){
            Files.createFile(file.toPath());
        }
        String time = new SimpleDateFormat("dd.MM.yyyy , HH:mm:ss").format(new Date());
        User user = null;
        String url = req.getRequestURL().toString();
        if(req.getQueryString()!=null){
            url+="?"+req.getQueryString();
        }
        if(req.getSession().getAttribute("admin")!=null){
            user = (User) req.getSession().getAttribute("admin");
        }
        if(!url.contains("/resources")){
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath() , true))){
                String notice = time + " | " + url + " | " + (user!=null?user.getLogin():"not user");
                writer.write(notice+"\n");
            }
        }
        filterChain.doFilter(req, resp);
    }
}
