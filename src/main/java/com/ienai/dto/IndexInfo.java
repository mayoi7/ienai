package com.ienai.dto;

import java.util.List;

import com.ienai.po.User;

/**
 * 
 * <p>Title: IndexInfo</p>
 * <p>Description: 显示主页的需要展示的信息，包括User和GenNote</p>
 * @author akira
 * @date 2018年10月1日 上午10:18:45
 */
public class IndexInfo {

	private User user;
	
	private List<GenNote> noteList;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<GenNote> getNoteList() {
		return noteList;
	}

	public void setNoteList(List<GenNote> noteList) {
		this.noteList = noteList;
	}

}
