package EmployeeManagement.com.test.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import EmployeeManagement.com.test.entity.EmployeeTo;
import EmployeeManagement.com.test.services.EmployeeService;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	@RequestMapping(method=RequestMethod.POST,value="/add")
	public ResponseEntity<String>  addEmplyee(@RequestBody EmployeeTo employee)
	{
		String id=empService.addEmplyee(employee);
		
		if(id!=null)
		{
			return new ResponseEntity<String>(id, HttpStatus.CREATED);
		}
		else
		{
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/deleteEmployee/{id}")
	public ResponseEntity<Boolean>  deleteEmplyee(@PathVariable("id") String id)
	{
		Boolean isDeleted=empService.deleteEmplyee(id);
		return new ResponseEntity<Boolean>(isDeleted, HttpStatus.OK);
		
	}

	
	@RequestMapping(method=RequestMethod.PUT,value="/updateEmployee/{id}")
	public ResponseEntity<Boolean>  updateEmployee(@PathVariable("id") String id,@RequestBody EmployeeTo employeeTo)
	{
		Boolean isDeleted=empService.updateEmployee(id,employeeTo);
		return new ResponseEntity<Boolean>(isDeleted, HttpStatus.OK);
		
	}
}
