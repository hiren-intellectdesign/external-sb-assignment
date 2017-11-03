package EmployeeManagement.com.test.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.springframework.stereotype.Service;

import EmployeeManagement.com.test.entity.Employee;
import EmployeeManagement.com.test.entity.EmployeeTo;
import EmployeeManagement.com.test.exceptions.InvalidDateOfBirthExceptoin;

@Service

public class EmployeeService {
	
	
	private static Map<String,Employee> employeemap=new HashMap<String, Employee>();
	
	public String addEmplyee(EmployeeTo employeeTo)
	{
		
		for (Entry<String, Employee> entry : employeemap.entrySet()) {
			if(entry.getValue().getEmail().equals(employeeTo.getEmail())&&entry.getValue().isActive())
			{
				return null;
			}
		}	
		String id=UUID.randomUUID().toString();
		Employee emp=new Employee(employeeTo, id);
		employeemap.put(id, emp);
		
		return id;
   }




	public Boolean deleteEmplyee(String id) {
		
		for (Entry<String, Employee> entry : employeemap.entrySet()) {
			if(entry.getKey().equals(id))
			{
				entry.getValue().setActive(false);
				return true;
			}
		}
		return false;
	}
	public Boolean updateEmployee(String id, EmployeeTo employeeTo) {
		SimpleDateFormat sdf=new SimpleDateFormat("DD-MMM-YYYY");
		boolean updated=false;
		for (Entry<String, Employee> entry : employeemap.entrySet()) {
			if(entry.getKey().equals(id))
			{
				Employee emp=entry.getValue();
				try {
					if(employeeTo.getBirthDate()!=null)
					{
						Date dob=sdf.parse(employeeTo.getBirthDate());
						if(dob.before(new Date()))
						{
							emp.setBirthDate(dob);
							updated=true;
						}
						else
						{
							throw new InvalidDateOfBirthExceptoin("Entered DOB is Future date.Please enter valid date of birth");
						}
					}
					if(employeeTo.getPinCode()!=null&&employeeTo.getPinCode()>0)
					{
						emp.setPinCode(employeeTo.getPinCode());
						updated=true;
					}
					return true;
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			}
		}
		return updated;
	}
	
	

}
