package com.example.demo.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.Course;

public interface CourseRepossitory extends CrudRepository<Course, UUID>{
	
	//List<Course> findByCourseDescription(String mCourseDescription);
	Optional<Course> findById(UUID id);
}
