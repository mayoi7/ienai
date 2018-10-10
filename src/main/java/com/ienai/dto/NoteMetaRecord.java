package com.ienai.dto;

/**
 * 
 * <p>Title: NoteMetaRecord</p>
 * <p>Description: 用户对一个note的操作记录，即是否点赞/关心
 * 	这个用户即为当前存在session中的已登录用户
 * </p>
 * @author akira
 * @date 2018年10月5日 上午8:20:21
 */
public class NoteMetaRecord {

	private Integer noteId;
	
	// 注意，返回的对象中是liked和cared，而不是isLiked和isCared
	private boolean isLiked;	// 用户'当前'是否对这条note点赞过，true为是
	
	private boolean isCared;	// 用户'当前'是否对这条note关心过，true为是
	
	public Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}

	public boolean isLiked() {
		return isLiked;
	}

	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}

	public boolean isCared() {
		return isCared;
	}

	public void setCared(boolean isCared) {
		this.isCared = isCared;
	}

	
}
