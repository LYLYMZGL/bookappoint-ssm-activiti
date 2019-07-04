package com.pdsu.entity;

public class Student {
	private Long studentId;
	private Long password;
	private String nickname;
	
	public Student(){
		
	}
	public Student(Long studentId, Long password) {
		super();
		this.studentId = studentId;
		this.password = password;
	}
	public Student(Long studentId, Long password, String nickname) {
		super();
		this.studentId = studentId;
		this.password = password;
		this.nickname = nickname;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Long getPassword() {
		return password;
	}
	public void setPassword(Long password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", password=" + password + ", nickname=" + nickname + "]";
	}
}
