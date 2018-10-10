package com.ienai.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * <p>Title: GenNote</p>
 * <p>Description: 负责作为json传递给网页的note数据</p>
 * @author akira
 * @date 2018年10月1日 上午10:01:43
 */
public class GenNote {

	private Integer id;
	
	private String title;
	
	private Integer userId;	// 查出来的username就是from
	
	private String from;	// 即note中userid对应的username
	
	private String general;	// 概要
	
	private String complete;	// 即content
	
	private Integer like;	// 即like_count
	
	private Integer interest;	// 即care_count
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;	// 创建时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getGeneral() {
		return general;
	}

	public void setGeneral(String general) {
		this.general = general;
	}

	public String getComplete() {
		return complete;
	}

	public void setComplete(String complete) {
		this.complete = complete;
	}

	public Integer getLike() {
		return like;
	}

	public void setLike(Integer like) {
		this.like = like;
	}

	public Integer getInterest() {
		return interest;
	}

	public void setInterest(Integer interest) {
		this.interest = interest;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
