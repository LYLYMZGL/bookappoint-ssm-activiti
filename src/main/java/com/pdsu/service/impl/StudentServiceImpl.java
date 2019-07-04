package com.pdsu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdsu.entity.Student;
import com.pdsu.mapper.StudentMapper;
import com.pdsu.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public Student login(Student student) {
		Student s=studentMapper.login(student);
		return s;
	}
	
}
