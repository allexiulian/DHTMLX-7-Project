package com.bean;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Employee {
	
private Long id;	
private String name;
private String phone;
private String email;
private LocalDate birthdate;
private String address;
private String country;
private String password;
private Set<Role> role = new HashSet<>();

public Employee() {
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public LocalDate getBirthdate() {
	return birthdate;
}

public void setBirthdate(LocalDate birthdate) {
	this.birthdate = birthdate;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getCountry() {
	return country;
}

public void setCountry(String country) {
	this.country = country;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}



public Set<Role> getRole() {
	return role;
}

public void setRole(Set<Role> role) {
	this.role = role;
}
public void addRole(Role role) {
	this.role.add(role);
}
@Override
public String toString() {
	return "Employee [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", birthdate="
			+ birthdate + ", address=" + address + ", country=" + country + ", password=" + password + ", role=" + role
			+ "]";
}

}