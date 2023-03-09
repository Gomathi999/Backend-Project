package com.example.smsProject.model;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/* It is a blueprint. Here we use public getter and setter methods for private attributes.
 * In this class we use @Entity and @Table. 
 * @Entity is used so that the class name will be table name in db.
 * For avoid to write query, we go for hibernate
 * @Dynamic update annotation is used for update values instead of checking all values in that record.  
 * It will check only which value will be updated*/
@Entity
@Table(name = "Students")
@DynamicUpdate
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer studentId;
	
	@Column(name = "Name", nullable = false)
	private String studentName; //student_name
	
	private String standard;
	private long contactNo;
	
	public Student() {}
	
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public long getContactNo() {
		return contactNo;
	}
	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}
}
