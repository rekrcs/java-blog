package com.sbs.java.blog.dto;

import java.util.Map;

public class Dto {
	private int id;
	private String regDate;

	public Dto() {
		this.id = getId();
		this.regDate = getRegDate();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
}
