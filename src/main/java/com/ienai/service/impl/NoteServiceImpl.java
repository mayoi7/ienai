package com.ienai.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ienai.dto.NoteMetaRecord;
import com.ienai.mapper.NoteMapper;
import com.ienai.mapper.UserMapper;
import com.ienai.mapper.UserNoteMapper;
import com.ienai.po.Note;
import com.ienai.po.NoteExample;
import com.ienai.po.User;
import com.ienai.po.UserNote;
import com.ienai.po.UserNoteExample;
import com.ienai.service.NoteService;
import com.ienai.util.NoteUtil;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private NoteMapper noteMapper;
	
	@Autowired
	private UserNoteMapper userNoteMapper;
	

	@Override
	public List<Note> findNoteByPageInTimeOrder(Integer pageNum, Integer userId, boolean isMe) throws Exception {
		PageHelper.startPage(pageNum, NoteUtil.NOTE_PAGE_NUM);
		NoteExample exp = new NoteExample();
		exp.setOrderByClause("id DESC");
		if(userId != null) {
			com.ienai.po.NoteExample.Criteria cr = exp.createCriteria();
			cr.andUserIdEqualTo(userId);
			if(isMe == true) {	// 为true则只查询我写的，否则就只查询写给我的
				cr.andUserIdEqualTo(userId);
			} else {	// 这里其实可以从Session中获取，但是为了避免controller层的过度操作，所以使用sql查询
				User user = userMapper.selectByPrimaryKey(userId);
				if(user == null) throw new Exception(userId + "号用户不存在");
				cr.andRecipientEqualTo(user.getUsername());	// 只要求名字一样即可，不一定是写给这个用户本人
			}
		}
		
		List<Note> notes = noteMapper.selectByExample(exp);
		
		return notes;
	}

	@Override
	public List<Note> findNoteByPageInLikeOrder(Integer pageNum, Integer userId) throws Exception {
		PageHelper.startPage(pageNum, NoteUtil.NOTE_PAGE_NUM);
		NoteExample exp = new NoteExample();
		exp.setOrderByClause("like_count DESC");
		if(userId != null) {
			com.ienai.po.NoteExample.Criteria cr = exp.createCriteria();
			cr.andUserIdEqualTo(userId);
		}
		
		List<Note> notes = noteMapper.selectByExample(exp);
		
		return notes;
	}

	@Override
	public NoteMetaRecord findAndSetNoteLikeAndCareById(Integer userId, Integer noteId) throws Exception {
		UserNoteExample ex = new UserNoteExample();
		com.ienai.po.UserNoteExample.Criteria cr = ex.createCriteria();
		cr.andUserIdEqualTo(userId).andNoteIdEqualTo(noteId);
		
		List<UserNote> list = userNoteMapper.selectByExample(ex);
		
		NoteMetaRecord record = new NoteMetaRecord();
		record.setNoteId(noteId);
		
		if(list.isEmpty() || list == null) {	// 说明用户没有点赞，所以肯定也没有关心
			record.setLiked(false);
			record.setCared(false);
		} else {
			record.setLiked(true);
			record.setCared(list.get(0).getIsCare());
		}
		
		return record;
	}

}
