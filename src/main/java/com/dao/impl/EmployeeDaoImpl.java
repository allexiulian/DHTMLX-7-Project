package com.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.bean.Employee;
import com.bean.Role;
import com.dao.EmployeeDao;
import com.util.DataBaseUtil;


public class EmployeeDaoImpl implements EmployeeDao{

	@Override
	public List<Employee> getAllEmployees() {
		
		
		String SQL="SELECT * from project_emp.\"employee\"";
		
		List<Employee> list =  new ArrayList();
		
		try(Connection conn = DataBaseUtil.getConnection()){
	         PreparedStatement stmt = conn.prepareStatement(SQL);
	         	       
	         ResultSet rs = stmt.executeQuery();
	      	 
	         while(rs.next()){
	            Employee obj = new Employee();
	            	obj.setId(rs.getLong(1));
	            	obj.setName(rs.getString(2));
	            	obj.setPhone(rs.getString(3));
	            	obj.setEmail(rs.getString(4));
	            	obj.setBirthdate(rs.getDate(5).toLocalDate());
	            	obj.setAddress(rs.getString(6));
	            	obj.setCountry(rs.getString(7));
	                list.add(obj);	      
	         }
	         DataBaseUtil.closeConnection(rs, stmt, conn);
	      } catch (SQLException | ClassNotFoundException e) {
	         e.printStackTrace();
	      } 
		
		return list;
	}

	@Override
	public boolean save(Employee bean) {

		
		String SQL="insert into project_emp.employee (\"name\",phone,email,birthdate,address,country,password) values (?,?,?,?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			 conn = DataBaseUtil.getConnection();
			 stmt = conn.prepareStatement(SQL);
	         stmt.setString(1, bean.getName());
	         stmt.setString(2, bean.getPhone());
	         stmt.setString(3, bean.getEmail());
	         stmt.setDate(4, Date.valueOf(bean.getBirthdate()));
	         stmt.setString(5, bean.getAddress());
	         stmt.setString(6, bean.getCountry());
	         stmt.setString(7, bean.getPassword());
	        
	         return stmt.executeUpdate() > 0;	
	         
	      } catch (SQLException | ClassNotFoundException e) {
	         e.printStackTrace();
	         return false;  
	         
	      }finally{
				DataBaseUtil.closeConnection(null, stmt, conn);			
	      }
		
		
	}

	@Override
	public boolean detele(Long id) {
		String SQL="delete from project_emp.employee where id=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			 conn = DataBaseUtil.getConnection();
			 stmt = conn.prepareStatement(SQL);		 
			 stmt.setLong(1, id);      
	         return stmt.executeUpdate() > 0;	
	         
		
	} catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
        return false;  
        
     }finally{
			DataBaseUtil.closeConnection(null, stmt, conn);
     }

}

	@Override
	public Optional<Employee> findByEmailAndPassword(String email, String password) {
		String SQL = "select * from project_emp.employee where \"email\" = ? and \"password\" = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DataBaseUtil.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if (rs.next()) {        
            return Optional.of(createEmployee(rs, conn));
            }
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        } finally {
            DataBaseUtil.closeConnection(rs, stmt, conn);
        }
		return Optional.empty();
	}

	private Employee createEmployee(ResultSet rs, Connection conn) throws SQLException {		
		Employee bean = new Employee();
		bean.setId(rs.getLong(1));
		bean.setName(rs.getString(2));
		bean.setPhone(rs.getString(3));
		bean.setEmail(rs.getString(4));
		bean.setBirthdate(rs.getDate(5).toLocalDate());
		bean.setAddress(rs.getString(6));
		bean.setCountry(rs.getString(7));
		bean.setRole(createRoles(rs.getLong(1),conn));
		return bean;
	}

	private Set<Role> createRoles(long id, Connection conn) throws SQLException {
		LinkedHashSet<Role> roles = new LinkedHashSet<>();
		String sql = "select role_name FROM project_emp.role r INNER join project_emp.employee_role er on er.role_id = r.id INNER join project_emp.employee e on er.emp_id = e.id where e.id = ?";
		PreparedStatement stmt= conn.prepareStatement(sql);
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			roles.add(createRole(rs));
		}
		stmt.close();
		rs.close();
		return roles;
	}

	private Role createRole(ResultSet rs) throws SQLException {
		Role bean = new Role();
		bean.setRole(rs.getString(1));
		return bean;
	}

	@Override
	public Optional<Employee> findByEmpID(Long id) {
		
		String sql = "select * from project_emp.employee where id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DataBaseUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return Optional.of(createEmployee(rs, conn));
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		} finally {
			DataBaseUtil.closeConnection(rs, stmt, conn);
		}
		return Optional.empty();
	}
	
	
	

}
