package com.pdsu.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.pdsu.entity.Student;

public interface StudentMapper {
	/**
	 * 向数据库验证输入的密码是否正确
	 */
	Student quaryStudent(@Param("studentId") long studentId, @Param("password") long password);

	@Select("select * from student where student_id=#{studentId} and password=#{password}")
	Student login(Student student);
}
