package com.example.demo.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Data
@Entity
public class Course extends AbstractAuditableEntity<User, Long> implements Serializable{

	private @Column @GeneratedValue UUID mCourseId;
	private String mOfferedSchool;
	private String mCourseDescription;
	private String mCourseTutor;
	private Double mCoursePrice;
	private Timestamp mCourseStartDate;
	private Timestamp mCourseEndDate;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


	public Course(String CourseDescription, String SchoolName, String CourseTutor, Double CoursePrice, String CourseStartDate, String CourseEndDate) {
		this.mCourseDescription = CourseDescription;
		this.mOfferedSchool = SchoolName;
		this.mCourseTutor = CourseTutor;
		try {
			Date p = sdf.parse(CourseStartDate);
			Date q = sdf.parse(CourseEndDate);
			this.mCourseStartDate = new java.sql.Timestamp(p.getDate());
			this.mCourseEndDate = new java.sql.Timestamp(q.getDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		this.mCoursePrice = CoursePrice;
	}
	
	public Course() {}
	
	public String getDuration() {
		return "[" + this.mCourseStartDate.toGMTString() + " ," + this.mCourseEndDate.toGMTString() + "]";
	}

}
