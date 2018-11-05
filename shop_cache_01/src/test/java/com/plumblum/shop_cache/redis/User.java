package com.plumblum.shop_cache.redis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 测试用户Model类
 * @author Administrator
 *
 */
@Entity
@Table(name = "SYS_USER", schema = "shop_cache", catalog = "")
public class User {

	@Id
	@Column(name = "id")
	private String id;

	/**
	 * 测试用户姓名
	 */
	@Column(name = "NAME")
	private String name;
	/**
	 * 测试用户年龄
	 */
	@Column(name = "AGE")
	private Integer age;

	@Column(name = "PASSWORD")
	private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
}
