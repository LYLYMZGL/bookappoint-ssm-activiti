package com.pdsu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pdsu.dto.AppointExecution;
import com.pdsu.dto.Result;
import com.pdsu.entity.Appointment;
import com.pdsu.entity.Book;
import com.pdsu.enums.AppointStateEnum;
import com.pdsu.exception.NoNumberException;
import com.pdsu.exception.RepeatAppointException;
import com.pdsu.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookService bookService;
	
	@Resource(name="repositoryService")
	private RepositoryService repositoryService;
	
	@Resource(name="runtimeService")
	private RuntimeService runtimeService;
	
	@Resource(name="taskService")
	private TaskService taskService;
	
	@Resource(name="historyService")
	private HistoryService historyService;
	
	//获取图书列表
	@RequestMapping(value="/list",method = RequestMethod.GET)
	private String List(Model model){
		List<Book> list = bookService.getList();
		model.addAttribute("list", list);
		return "list";
	}
	//搜索是否有某图书
	@RequestMapping(value="/search",method = RequestMethod.POST)
	private void  search(HttpServletRequest req,HttpServletResponse resp,Map<String,Object> map) 
								throws ServletException, IOException{
		//编码问题，需要进行转码
		req.setCharacterEncoding("UTF-8");
		//接收页面的值
		String name=req.getParameter("name");
		name=name.trim();
		//向页面传值
		req.setAttribute("name", name);
		req.setAttribute("list", bookService.getSomeList(name)); 
		req.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(req, resp); 
	}
	//查看某图书的详细情况
	@RequestMapping(value = "/{bookId}/detail", method = RequestMethod.GET)
	private String detail(@PathVariable("bookId") Long bookId, Model model){
		if(bookId==null){
			return "redirect:/book/list";
		}
		Book book=bookService.getById(bookId);
		if(book==null){
			return "forward:/book/list"; 
		}
		model.addAttribute("book",book);
		System.out.println(book);
		
		return "detail";
	}
	//执行预约的逻辑
	@RequestMapping(value = "/appointment", method = RequestMethod.POST, produces = {
	"application/json; charset=utf-8" })
	@ResponseBody
	private Result<AppointExecution> execute(@RequestParam("bookId") Long bookId,@RequestParam("studentId") Long studentId,Map<String,Object> map,HttpServletRequest req){
		Result<AppointExecution> result;
		AppointExecution execution=null;
		//部署流程
		Deployment deploy = repositoryService.createDeployment().addClasspathResource("bpmn/BookAppoint3.bpmn").deploy();
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deploy.getId()).singleResult();
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());
		map.put("processInstanceId", processInstance.getId());
		System.out.println("流程已经启动");
		System.out.println("流程实例的Id为:"+processInstance.getId());
		
		//预约图书
		Task task = taskService.createTaskQuery().taskName("预约图书").singleResult();
		taskService.setOwner(task.getId(), studentId.toString());
		HttpSession session = req.getSession();
		session.setAttribute("taskName", "2.png");
		System.out.println("当前任务为:"+task.getName());
		taskService.complete(task.getId());
		
		try{//手动try catch,在调用appoint方法时可能报错
			execution=bookService.appoint(bookId, studentId);
			result=new Result<AppointExecution>(true,execution); 
			
			//预约成功
			Task task1 = taskService.createTaskQuery().taskName("预约成功").singleResult();
			System.out.println("当前任务为:"+task1.getName());
			taskService.setOwner(task1.getId(), studentId.toString());
			session.setAttribute("taskName", "3.png");
			taskService.complete(task1.getId());
			
			//由于使用的是并行网关，所以还需要将其它两个无关任务完成
			Task result1 = taskService.createTaskQuery().taskName("库存不足").singleResult();
			taskService.complete(result1.getId());
			result1=taskService.createTaskQuery().taskName("重复预约").singleResult();
			taskService.complete(result1.getId());
				return result; 
				
		} catch(NoNumberException e1) {
			execution=new AppointExecution(bookId,AppointStateEnum.NO_NUMBER);
			result=new Result<AppointExecution>(true,execution);
			
			//库存不足
			Task task2 = taskService.createTaskQuery().taskName("库存不足").singleResult();
			System.out.println("当前任务为:"+task2.getName());
			taskService.setOwner(task2.getId(), studentId.toString());
			session.setAttribute("taskName", "4.png");
			taskService.complete(task2.getId());
			
			//由于使用的是并行网关，所以还需要将其它两个无关任务完成
			Task result1 = taskService.createTaskQuery().taskName("预约成功").singleResult();
			taskService.complete(result1.getId());
			result1=taskService.createTaskQuery().taskName("重复预约").singleResult();
			taskService.complete(result1.getId());
				return result;
		}catch(RepeatAppointException e2){
			execution=new AppointExecution(bookId,AppointStateEnum.REPEAT_APPOINT);
			result=new Result<AppointExecution>(true,execution);
			
			//重复预约
			Task task3 = taskService.createTaskQuery().taskName("重复预约").singleResult();
			System.out.println("当前任务为:"+task3.getName());
			taskService.setOwner(task3.getId(), studentId.toString());
			session.setAttribute("taskName", "5.png");
			taskService.complete(task3.getId());
			
			//由于使用的是并行网关，所以还需要将其它两个无关任务完成
			Task result1 = taskService.createTaskQuery().taskName("库存不足").singleResult();
			taskService.complete(result1.getId());
			result1=taskService.createTaskQuery().taskName("预约成功").singleResult();
			taskService.complete(result1.getId());
				return result;
		}catch (Exception e){
			execution=new AppointExecution(bookId,AppointStateEnum.INNER_ERROR); 
			result=new Result<AppointExecution>(true,execution);
				return result;
		} 
	}
	@RequestMapping(value ="/appoint")
	private String appointBooks(@RequestParam("studentId") long studentId,Model model){
		
		List<Appointment> appointList=new ArrayList<Appointment>();
		appointList=bookService.getAppointByStu(studentId);
		model.addAttribute("appointList", appointList);
		 
		return "appointBookList";
	}
	
	@RequestMapping("/exit")
	public String exit(HttpSession session) {
		session.invalidate();
		return "redirect:/students/login";
	}
	
	@RequestMapping("/queryHisTask/{taskowner}")
	public String queryHisTask(@PathVariable("taskowner") String taskowner,Map<String,Object> map) {
		List<HistoricTaskInstance> tasklist = historyService.createHistoricTaskInstanceQuery().finished().taskOwner(taskowner).list();
		map.put("tasklist", tasklist);
		return "tasklist";
	}
	
	@RequestMapping("/queryCurrentTask")
	public String queryCurrentTask(HttpSession session,HttpServletRequest req) {
		String attribute = (String) session.getAttribute("taskName");
		System.out.println(attribute);
		if(attribute==null) {
			return "redirect:/error.jsp";
		}
		return "redirect:/img/"+attribute;
	}
	
}
