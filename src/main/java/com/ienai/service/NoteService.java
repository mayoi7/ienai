package com.ienai.service;

import java.util.List;

import com.ienai.dto.NoteMetaRecord;
import com.ienai.po.Note;

/**
 * 
 * <p>Title: NoteService</p>
 * <p>Description: note信息的接口</p>
 * @author akira
 * @date 2018年10月1日 上午10:35:57
 */
public interface NoteService {

	/**
	 * 按时间顺序分页查询（最新的在最前面）
	 * @param pageNum 页号（第几页，起始为第一页）
	 * @param userId 如果不填就查询全部数据，如果填写就只查询这个user的数据
	 * @param isMe true表示是当前user自己写的，false表示是写给user的，即recipient是这个user，
	 * 		且只有当userId不为空时这个参数才有意义
	 * @throws Exception
	 */
	public List<Note> findNoteByPageInTimeOrder(Integer pageNum, Integer userId, boolean isMe) 
			throws Exception;
	
	/**
	 * 按点赞数排序（降序）
	 * @param pageNum 页号（第几页，起始为第一页）
	 * @param userId 如果不填就查询全部数据，如果填写就只查询这个user的数据
	 * @throws Exception
	 */
	public List<Note> findNoteByPageInLikeOrder(Integer pageNum, Integer userId)
			throws Exception;

	/**
	 * 查询id为userId的用户对record中保存的noteId的点赞和关心记录，同时将查询结果设置到这个record中
	 * @param id
	 */
	public NoteMetaRecord findAndSetNoteLikeAndCareById(Integer userId, Integer noteId) 
			throws Exception;
}
