package com.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bean.Employee;
import com.bean.User;
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

		
		String SQL="insert into project_emp.employee (\"name\",phone,email,birthdate,address,country) values (?,?,?,?,?,?)";
		
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

}
