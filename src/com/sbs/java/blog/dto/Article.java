package com.sbs.java.blog.dto;

import java.util.Map;

public class Article extends Dto {
	private int cateItemId;
	private String updateDate;
	private String title;
	private String body;
	private String cateItemName;

	public Article() {
		super();
		this.updateDate = getUpdateDate();
		this.title = getTitle();
		this.body = getBody();
		this.cateItemId = getCateItemId();
	}

	public Article(Map<String, Object> row) {
		super(row);
		this.updateDate = (String) row.get("updateDate");
		this.title = (String) row.get("title");
		this.body = (String) row.get("body");
		this.cateItemId = (int) row.get("cateItemId");
		this.cateItemName = (String) row.get("cateItemName");
	}

	public int getCateItemId() {
		return cateItemId;
	}

	public void setCateItemId(int cateItemId) {
		this.cateItemId = cateItemId;
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

	public String getCateItemName() {
		return cateItemName;
	}

	public void setCateItemName(String cateItemName) {
		this.cateItemName = cateItemName;
	}

	@Override
	public String toString() {
		return "Article [cateItemId=" + cateItemId + ", updateDate=" + updateDate + ", title=" + title + ", body="
				+ body + "]";
	}

}
