package com.example.demo.web;

import java.util.UUID;

public class CourseNotFoundException extends RuntimeException{
	public CourseNotFoundException(UUID id) {
		// TODO Auto-generated constructor stub
		super("Cannot find the course "+ id);
	}
}
