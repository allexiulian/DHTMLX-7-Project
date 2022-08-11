package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.bean.User;
import com.dao.UserDao;
import com.util.DataBaseUtil;

public class UserDaoImpl implements UserDao{
	
	@Override
	public Optional<User> findByUserNameAndPassword(String userName, String password) {
		String SQL = "select * from project_emp.user where username = ? and \"password\" = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DataBaseUtil.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, userName);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(createUser(rs));
            }
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        } finally {
            DataBaseUtil.closeConnection(rs, stmt, conn);
        }
		return Optional.empty();
	}
	
	private User createUser(ResultSet rs) throws SQLException {
        User bean = new User();
        bean.setId(rs.getLong(1));
        bean.setUserName(rs.getString(2));
        bean.setEmp_id(rs.getLong(4));
        return bean;
    }

}
