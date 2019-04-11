package com.cognizant.app.modal;

public class User {
	
	private String name;
	private int empId;
	private String phoneNo;
	
	public User(String name, int empId, String phoneNo) {
		this.name=name;
		this.empId=empId;
		this.phoneNo=phoneNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
}
