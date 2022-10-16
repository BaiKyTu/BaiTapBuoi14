package com.cybersoft.repository;

import com.cybersoft.config.MysqlConnection;
import com.cybersoft.model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoginRepository {
    public List<UserModel> getUserByEmailAndPassword(String email, String password) {
        List<UserModel> list = new ArrayList<>();

        try {
            String query = "select * from user u where u.email = ? and u.password = ?";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UserModel userModel = new UserModel();
                userModel.setEmail(resultSet.getString("email"));

                list.add(userModel);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Error getUserByEmailAndPassword" + e.getMessage());
        }
        return list;
    }
}
