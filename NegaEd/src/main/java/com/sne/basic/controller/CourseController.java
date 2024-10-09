package com.sne.basic.controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import com.sne.basic.entity.*;
@RestController
@RequestMapping("/api/courses")
public class CourseController {
	 private List<Course> courses = new ArrayList<>();
	    private Long courseIdCounter = 1L;

	    // Get all courses
	    @GetMapping
	    public List<Course> getAllCourses() {
	        return courses;
	    }

	    // Create a new course
	    @PostMapping
	    public Course createCourse(@RequestBody Course course) {
	        course.setId(courseIdCounter++);
	        courses.add(course);
	        return course;
	    }

	    // Update a course by ID
	    @PutMapping("/{id}")
	    public Course updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
	        for (Course course : courses) {
	            if (course.getId().equals(id)) {
	                course.setTitle(updatedCourse.getTitle());
	                course.setDescription(updatedCourse.getDescription());
	                course.setDuration(updatedCourse.getDuration());
	                return course;
	            }
	        }
	        return null; // Handle case if course not found
	    }

	    // Delete a course by ID
	    @DeleteMapping("/{id}")
	    public String deleteCourse(@PathVariable Long id) {
	        courses.removeIf(course -> course.getId().equals(id));
	        return "Course deleted";
	    }
}
