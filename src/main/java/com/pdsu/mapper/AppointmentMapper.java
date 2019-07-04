package com.pdsu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pdsu.entity.Appointment;

public interface AppointmentMapper {
	//通过图书ID和学生ID预约书籍，并插入
	int insertAppointment(@Param("bookId") long bookId, @Param("studentId") long studentId);
	 
	//通过一个学生ID查询已经预约了哪些书。
	List<Appointment> quaryAndReturn(long studentId);
}
