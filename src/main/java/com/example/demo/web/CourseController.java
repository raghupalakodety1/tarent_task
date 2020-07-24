package com.example.demo.web;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Course;
import com.example.demo.repository.CourseRepossitory;

@RestController
public class CourseController {

	private final AtomicLong counter = new AtomicLong();
	private final CourseRepossitory repository;

	@PostMapping
	public Course insert(@RequestParam(value = "mCourseDescription") String CourseDescription,
						 @RequestParam(value = "mOfferedSchool") String SchoolName,
						 @RequestParam(value = "mCourseTutor") String CourseTutor,
						 @RequestParam(value = "mCoursePrice") Double CoursePrice,
						 @RequestParam(value = "mCourseStartDate") String CourseStartDate,
						 @RequestParam(value = "mCourseEndDate") String CourseEndDate) {
		return new Course(CourseDescription, SchoolName, CourseTutor, CoursePrice, CourseStartDate, CourseEndDate);
	}
	
	public CourseController(CourseRepossitory repository) {
		// TODO Auto-generated constructor stub
		this.repository = repository;
	}
	
	@GetMapping("/courses")
	List<Course> all(){
		return (List<Course>) repository.findAll();
	}
	
	@PostMapping("/courses/insert")
	Course newCourse(@RequestBody Course newCourse) {
		return repository.save(newCourse);
	}
	
	@GetMapping("/courses/{id}")
	Course getCourse(@PathVariable UUID id) {
		Course course = repository.findById(id).orElseThrow();
		return course;
	}
	
	@DeleteMapping("/courses/{id}")
	void deleteCourse(@PathVariable UUID id) {
		repository.deleteById(id);
	}
	
	@GetMapping("/courses/{id}/duration")
	String getTrainingPeriodByCourseId(@PathVariable UUID id) {
		Course course = repository.findById(id).orElseThrow();
		return course.getDuration();
	}
}
