package com.ienai.util;

import java.util.ArrayList;
import java.util.List;

import com.ienai.dto.GenNote;
import com.ienai.po.Note;

/**
 * 
 * <p>Title: NoteUtil</p>
 * <p>Description: 对note信息的操作</p>
 * @author akira
 * @date 2018年10月1日 上午11:04:31
 */
public class NoteUtil {

	public static final Integer NOTE_PAGE_NUM = 15;	// 默认一页的数量
	
	/**
	 * 把note转化为genNote
	 * 注意，from为空
	 */
	public static GenNote changeNoteToGen(Note note) {
		GenNote newNote = new GenNote();
		
		newNote.setId(note.getId());
		newNote.setUserId(note.getUserId());
		newNote.setTitle(note.getTitle());
		newNote.setComplete(note.getContent());
		newNote.setGeneral(note.getContent().substring(0, 12));
		newNote.setLike(note.getLikeCount());
		newNote.setInterest(note.getCareCount());
		newNote.setCreateTime(note.getGmtCreate());
		
		return newNote;
	}
	
	/**
	 * 把note的list转化为genNote的list
	 * 注意，from为空
	 * @param noteList
	 * @return
	 */
	public static List<GenNote> changeNoteListToGen(List<Note> noteList) {
		List<GenNote> genNoteList = new ArrayList<GenNote>();
		
		for (int i = 0; i < noteList.size(); i++) {
			genNoteList.add(changeNoteToGen(noteList.get(i)));
		}
		
		return genNoteList;
	}
	
}
