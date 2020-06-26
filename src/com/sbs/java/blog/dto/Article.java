package com.sbs.java.blog.dto;

import java.util.Map;

public class Article extends Dto {

	private String updateDate;
	private String title;
	private String body;

	public Article() {
		super();
		this.updateDate = getUpdateDate();
		this.title = getTitle();
		this.body = getBody();
	}

	@Override
	public String toString() {
		return "article [id=" + getId() + ", regDate=" + getRegDate() + ", updateDate=" + updateDate + ", title="
				+ title + ", body=" + body + "]";
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
