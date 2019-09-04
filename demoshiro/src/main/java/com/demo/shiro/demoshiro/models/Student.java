package com.demo.shiro.demoshiro.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author blue_huang
 * 2018下午9:04:13
 * 描述：用户实体
 */
@Entity
@Table(name="student")
public class Student {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	/**
	 * windows不区分大小写，linux 严格区分大小写
	 */
	@Column(name = "user_name")
	private String userName;

	public Student() {
		super();
	}

	public Student(long id, String name, String password, String userName) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.userName = userName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", password=" + password + ", userName=" + userName + "]";
	}

}
