package com.pdsu.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pdsu.dto.Result;

@Controller
@RequestMapping("/test")
public class TestController {
	@Resource(name="repositoryService")
	private RepositoryService repositoryService;
	
	@Resource(name="runtimeService")
	private RuntimeService runtimeService;
	
	@Resource(name="taskService")
	private TaskService taskService;
	
	@Resource(name="historyService")
	private HistoryService historyService;
	
	@RequestMapping("/queryTaskSize")
	@ResponseBody
	public Object queryTaskSize() {
		repositoryService.createDeployment().addClasspathResource("bpmn/Test.bpmn").deploy();
		runtimeService.startProcessInstanceByKey("myProcess");
		List<Task> list = taskService.createTaskQuery().list();
		Result<Integer> result=new Result<Integer>();
		result.setData(list.size());
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/startProcess")
	public Object startProcess() {
		Result<String> result=new Result<String>();
		Deployment deploy = repositoryService.createDeployment().addClasspathResource("bpmn/BookAppoint.bpmn").deploy();
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deploy.getId()).singleResult();
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());
		result.setData(processInstance.getId());
		System.out.println("流程实例的Id为:"+processInstance.getId());
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/queryCurrentTask")
	public Object queryCurrentTask(Map<String,String> map) {
		Result<String> result=new Result<String>();
		Task task = taskService.createTaskQuery().singleResult();
		System.out.println("当前任务的名称为:"+task.getName());
		map.put("currentTaskName", task.getName());
		result.setData(task.getName());
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/queryHistoryTask/{studentId}")
	public Object queryHistoryTask(@PathVariable("studentId") String studentId) {
		Result result=new Result();
		List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().taskOwner(studentId).list();
		for(HistoricTaskInstance historicTask:list) {
			System.out.println(historicTask.getName());
			result.setData(historicTask.getName());
		}
		return result;
	}
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
}
