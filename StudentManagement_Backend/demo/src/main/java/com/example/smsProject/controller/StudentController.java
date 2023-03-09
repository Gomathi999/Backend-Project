package com.example.smsProject.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.smsProject.exceptionalHandling.ResourceNotFoundException;
import com.example.smsProject.model.Student;
import com.example.smsProject.repository.StudentRepository;


/* @RestController annotation is applied to a class to mark it as a request handler. 
 * This annotation itself annotated with @Controller and @ResponseBody. 
 * @Controller is used for mapping
 * @ResponseBody annotation tells a controller that the object returned is automatically serialized into JSON 
 * and passed back into the HttpResponse object. */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/studentDetails/")
public class StudentController {
	
	/* @Autowired annotation is used for automatic dependency injection. 
	 * Spring framework is built on dependency injection and 
	 * we inject the class dependencies through spring bean configuration file */
	@Autowired
	private StudentRepository repository;
	
		
	/* By using post mapping annotation, transfer data from client to server in HTTP protocol.
	 * POST carries request parameter in message body which makes it more secure
	 * way of transferring data from client to server.*/
	@PostMapping("/saveStudentDetail")
	public Student createStudent(@RequestBody Student student) {
		return repository.save(student);
	}
	
	/* This method is used to update/modify the resource 
	 * so @PutMapping annotation is used for mapping HTTP PUT requests onto specific handler methods.*/
	@PutMapping("/updateStudentDetail/{studentId}")
	public ResponseEntity<Student> updateStudent(@PathVariable Integer studentId, @RequestBody Student studentDetails){
		Student student = repository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + studentId));
		
		student.setStudentName(studentDetails.getStudentName());
		student.setStandard(studentDetails.getStandard());
		student.setContactNo(studentDetails.getContactNo());
		
		Student updatedStudent = repository.save(student);
		return ResponseEntity.ok(updatedStudent);
	}
	
	/* By using get mapping annotation, transfer data from client to server in HTTP protocol.
	 * It is used to get a single or multiple resources
	 * It carries request parameter appended in URL string */
	@GetMapping("/readAStudentDetail/{studentId}")
	public ResponseEntity<Student> getStudentById(@PathVariable Integer studentId) {
		Student student = repository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + studentId));
		return ResponseEntity.ok(student);
	}
	
	// get all Students
		@GetMapping("/readAllStudentDetails")
		public List<Student> getAllStudents(){
			return repository.findAll();
		}	
	
	/* The Delete HTTP method is used to delete the resource and @DeleteMapping annotation for mapping 
	 * HTTP DELETE requests onto specific handler methods.*/
	@DeleteMapping("/deleteStudentDetail/{studentId}")
	public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Integer studentId){
		Student student = repository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + studentId));
		
		repository.delete(student);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}	
}
