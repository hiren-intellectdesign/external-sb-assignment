package EmployeeManagement.com.test.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import EmployeeManagement.com.test.exceptions.InvalidDateOfBirthExceptoin;

public class Employee {
	
	private String id;
	private String fName;
	private String lName;
	private String email;
	private Integer pinCode;
	private Date birthDate;
	private boolean isActive;
	
	public Employee(EmployeeTo employeeTo,String id) {
		SimpleDateFormat sdf=new SimpleDateFormat("DD-MMM-YYYY");
	    this.id=id;
		this.fName=employeeTo.getfName();
		this.lName=employeeTo.getlName();
		this.email=employeeTo.getEmail();
		this.pinCode=employeeTo.getPinCode();
		String birtDate=employeeTo.getBirthDate();
		this.setActive(true);
		try {
			Date dob=sdf.parse(birtDate);
			if(dob.before(new Date()))
			{
				this.setBirthDate(sdf.parse(birtDate));
			}
			else
			{
				throw new  InvalidDateOfBirthExceptoin("Entered DOB is Future date.Please enter valid date of birth");
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getPinCode() {
		return pinCode;
	}
	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	} 
	
	

}
