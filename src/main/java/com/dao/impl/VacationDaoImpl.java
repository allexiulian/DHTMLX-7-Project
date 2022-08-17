package com.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Vacation;
import com.dao.VacationDao;
import com.google.gson.JsonElement;
import com.util.DataBaseUtil;

public class VacationDaoImpl implements VacationDao {

	@Override
	public List<Vacation> findAllByEmployee(Long id) {
		String sql = "select * from project_emp.vacation where emp_id = ?";
		List<Vacation> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs;
		try {
			conn = DataBaseUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(createVacantion(rs));
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(null, stmt, conn);
		}
		return list;
	}

	private Vacation createVacantion(ResultSet rs) throws SQLException {
		Vacation bean = new Vacation();
		bean.setId(rs.getLong(1));
		bean.setVacationFrom(rs.getDate(2).toLocalDate());
		bean.setVacationTo(rs.getDate(3).toLocalDate());
		bean.setReason(rs.getString(4));
		bean.setEmployeeId(rs.getLong(5));
		return bean;
	}

	@Override
	public boolean save(Vacation bean) {
		String sql = "insert into project_emp.vacation (\"from\",\"to\", \"reason\", \"emp_id\") values( ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DataBaseUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setDate(1, Date.valueOf(bean.getVacationFrom()));
			stmt.setDate(2, Date.valueOf(bean.getVacationTo()));
			stmt.setString(3, bean.getReason());
			stmt.setLong(4, bean.getEmployeeId());
			return stmt.executeUpdate() > 0;
		} catch (ClassNotFoundException | SQLException e1) {
			System.out.println("failed");
			e1.printStackTrace();
			return false;
		} finally {
			DataBaseUtil.closeConnection(null, stmt, conn);
		}
	}

	@Override
	public List<Vacation> getAllVacations() {
		String sql = "select * from project_emp.vacation";
		List<Vacation> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs;
		try {
			conn = DataBaseUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(createVacantion(rs));
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(null, stmt, conn);
		}
		return list;
	}

	@Override
	public boolean saveAll(List<Vacation> result) {
		String sql = "insert into project_emp.vacation (\"from\",\"to\", \"reason\", \"emp_id\") values( ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DataBaseUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			for (Vacation bean : result) {
				stmt.setDate(1, Date.valueOf(bean.getVacationFrom()));
				stmt.setDate(2, Date.valueOf(bean.getVacationTo()));
				stmt.setString(3, bean.getReason());
				stmt.setLong(4, bean.getEmployeeId());
				stmt.addBatch();
			}
			stmt.executeBatch();
			return true;
		} catch (ClassNotFoundException | SQLException e1) {
			System.out.println("failed");
			e1.printStackTrace();
			return false;
		} finally {
			DataBaseUtil.closeConnection(null, stmt, conn);
		}
	}

}
