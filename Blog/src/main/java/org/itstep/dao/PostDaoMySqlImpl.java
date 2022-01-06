package org.itstep.dao;

import lombok.SneakyThrows;
import org.itstep.domain.Post;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostDaoMySqlImpl implements PostDao {
    @SneakyThrows
    @Override
    public Integer save(Post data) {
        Connection connection = MysqlConnection.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO posts (name_post , text_post , date_post , imgUrl) " +
                "values ((?),(?),(?),(?))")) {
            statement.setString(1, data.getName());
            statement.setString(2, data.getText());
            statement.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(data.getDate()));
            statement.setString(4, data.getFileUrl());
            statement.executeQuery();
            System.out.println("Пост успешно добавлен в БД");
        } catch (SQLException e) {
            return null;
        }
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select last_insert_id() from posts");
        if (resultSet.next()) {
            Integer id = resultSet.getInt(1);
            System.out.println(id + " - is ID");
            return id;
        }
        return null;
    }

    @SneakyThrows
    @Override
    public List<Post> findAll() {
            ArrayList<Post> posts = new ArrayList<>();
            Post post;
            Long id = 0L;
            String title = "";
            String text = "";
            Date date = null;
            String imgUrl = "";
            Connection connection = MysqlConnection.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement
                    ("select * from posts")) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    id = (long) resultSet.getInt(1);
                    title = resultSet.getString(2);
                    text = resultSet.getString(3);
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        date = sdf.parse(resultSet.getString(4));
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    imgUrl = resultSet.getString(5);
                    post = new Post(title, text, date, imgUrl);
                    post.setId(id);
                    posts.add(post);
                }
            } catch (SQLException e) {
                return null;
            }
            return posts;
    }

    @SneakyThrows
    @Override
    public Post findById(Integer id) {
        Post post = null;
        Long post_id = 0L;
        String title = "";
        String text = "";
        Date date = null;
        String imgUrl = "";
        Connection connection = MysqlConnection.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement
                ("select * from posts where id=?")) {
            statement.setInt(1 , id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                post_id = (long) resultSet.getInt(1);
                title = resultSet.getString(2);
                text = resultSet.getString(3);
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    date = sdf.parse(resultSet.getString(4));
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                imgUrl = resultSet.getString(5);
                post = new Post(title, text, date, imgUrl);
                post.setId(post_id);
            }
        } catch (SQLException e) {
            return null;
        }
        return post;
    }

    @SneakyThrows
    @Override
    public void delete(Integer integer) {
        Connection connection = MysqlConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("delete from posts where id = ?");
        statement.setInt(1 , integer);
        statement.executeQuery();
    }

    @Override
    public Post update(Post data) {
        return null;
    }
}
