package com.pdsu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pdsu.entity.Book;

public interface BookMapper {
	/*
	 * 根据id查询书
	 * 
	 */
	Book queryById(long id);
	List<Book> querySome(String name);
	/*
	 * 当输入的参数中有多个时，使用以下方法，即可保证参数能够寻找的到
	 * 1）可以加上@Param注解
	 * 2）在SQL语句中参数设置为#{索引值}，索引值为当前参数在该SQL中所有参数的位置（索引从0开始）
	 * 3）将多个参数封装到一个Entity中
	 * 4）将多个参数封装到Map中
	 */
	List<Book> queryAll(@Param("offset") int offset,@Param("limit") int limit);
	
	/* 减少管存数量
	 * 用返回值可判断当前库存是否还有书
	 */
	 int reduceNumber(long bookId);
}
