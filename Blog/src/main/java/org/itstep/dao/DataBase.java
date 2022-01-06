package org.itstep.dao;

import jakarta.servlet.ServletConfig;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBase {
    @SneakyThrows
    public void createDB(ServletConfig config) {
        Connection connection = MysqlConnection.getInstance().getConnection();
        StringBuilder sqlCommand = new StringBuilder();
        try (InputStreamReader reader = new InputStreamReader(config.getServletContext()
                .getResourceAsStream(config.getServletContext().getInitParameter("createDB")), StandardCharsets.UTF_8)) {
            int oneByte = -1;
            while ((oneByte = reader.read()) != -1) {
                sqlCommand.append((char) oneByte);
                if ((char) oneByte == ';') {
                    try (PreparedStatement statement = connection.prepareStatement
                            (String.valueOf(sqlCommand))) {
                        statement.executeQuery();
                    } catch (SQLException ignored) {

                    }
                    System.out.println(sqlCommand);
                    sqlCommand = new StringBuilder();
                }
            }
        } catch (IOException e) {
            System.err.println("Файл не найден");
            e.printStackTrace();
        }

    }
}
