package com.pdsu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pdsu.dto.Result;
import com.pdsu.entity.Student;
import com.pdsu.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@Resource(name="repositoryService")
	private RepositoryService repositoryService;
	
	@Resource(name="runtimeService")
	private RuntimeService runtimeService;
	
	@Resource(name="taskService")
	private TaskService taskService;

	@RequestMapping("/login")
	public String login(Map<String,Object> map) {
		return "/login";
	}
	
	@ResponseBody
	@RequestMapping("/judgelogin")
	public Object judgelogin(@RequestParam("username") Long studentId,@RequestParam("password") Long password,HttpServletRequest req) {
		Result result=new Result();
		try {
			Student student = new Student(studentId,password);
			Student s=studentService.login(student);
			if(s!=null) {
				result.setSuccess(true);
				HttpSession session = req.getSession();
				session.setAttribute("student", s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
}
