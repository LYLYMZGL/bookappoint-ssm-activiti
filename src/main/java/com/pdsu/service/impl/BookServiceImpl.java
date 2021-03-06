package com.pdsu.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pdsu.dto.AppointExecution;
import com.pdsu.entity.Appointment;
import com.pdsu.entity.Book;
import com.pdsu.entity.Student;
import com.pdsu.enums.AppointStateEnum;
import com.pdsu.exception.AppointException;
import com.pdsu.exception.NoNumberException;
import com.pdsu.exception.RepeatAppointException;
import com.pdsu.mapper.AppointmentMapper;
import com.pdsu.mapper.BookMapper;
import com.pdsu.mapper.StudentMapper;
import com.pdsu.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BookMapper bookMapper;
	@Autowired
	private AppointmentMapper appointmentMapper;
	@Autowired
	private StudentMapper studentMapper; 
	@Override
	public Book getById(long bookId) { 
		return bookMapper.queryById(bookId);
	}  
	@Override
	public List<Book> getList() { 
		return bookMapper.queryAll(0, 1000);
	} 
	@Override
	public Student validateStu(Long studentId,Long password){
		return studentMapper.quaryStudent(studentId, password);
	}
	@Override
	public List<Book> getSomeList(String name) {
		 
		return bookMapper.querySome(name);
	} 
	@Override
	public List<Appointment> getAppointByStu(long studentId) {//DOTO
		return appointmentMapper.quaryAndReturn(studentId);
		 
	}
	
	@Override
	@Transactional
	public AppointExecution appoint(long bookId, long studentId) {//在Dao的基础上组织逻辑，形成与web成交互用的方法
		try{													  //返回成功预约的类型。		
			int update=bookMapper.reduceNumber(bookId);//减库存
			if(update<=0){//已经无库存！
				throw new NoNumberException("no number");
			}else{
				//执行预约操作
				int insert=appointmentMapper.insertAppointment(bookId, studentId);
				if(insert<=0){//重复预约
					throw new RepeatAppointException("repeat appoint");
				}else{//预约成功
					return new AppointExecution(bookId,AppointStateEnum.SUCCESS);
				}
				
			}
		} catch (NoNumberException e1) {
			throw e1;
		} catch (RepeatAppointException e2) {
			throw e2;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// 所有编译期异常转换为运行期异常
			throw new AppointException("appoint inner error:" + e.getMessage());
		}
	} 
}
