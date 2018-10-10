package com.ienai.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ienai.mapper.InvcodeMapper;
import com.ienai.mapper.NoteMapper;
import com.ienai.mapper.UserMapper;
import com.ienai.mapper.UserNoteMapper;
import com.ienai.po.Invcode;
import com.ienai.po.InvcodeExample;
import com.ienai.po.Note;
import com.ienai.po.User;
import com.ienai.po.UserExample;
import com.ienai.po.UserNote;
import com.ienai.po.UserNoteExample;
import com.ienai.service.UserService;
import com.ienai.util.CustomDateUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private InvcodeMapper invcodeMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserNoteMapper userNoteMapper;
	
	@Autowired
	private NoteMapper noteMapper;
	
	
	@Override
	public Invcode checkInvcode(String invcode) throws Exception {
		InvcodeExample example = new InvcodeExample();
		com.ienai.po.InvcodeExample.Criteria criteria = example.createCriteria();
		criteria.andCodeEqualTo(invcode);
		
		List<Invcode> user = invcodeMapper.selectByExample(example);
		if(user == null || user.isEmpty() || user.get(0).getIsUsed()) {	
			// 假如没有查询到，说明没有这个邀请码
			// 注意，邀请码被使用过了也算没查询到
			return null;
		} else {	// 否则就返回邀请码的id
			return user.get(0);
		}
	}

	@Override
	public User checkUserAccount(String username, String password) throws Exception {
		UserExample example = new UserExample();
		com.ienai.po.UserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		
		List<User> user = userMapper.selectByExample(example);
		
		if(user == null || user.isEmpty()) { // 说明没有查询到
			return null;
		} else if(user.get(0).getPassword().equals(password)) {	// 校验通过
			return user.get(0);
		} else {	// 密码错误
			return null;
		}
	}

	@Override
	public boolean updateUserInfo(Integer id, User user) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User findUserById(Integer id) throws Exception {
		
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer findIdByUsername(String Username) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertUser(User user) throws Exception {
		// 设置默认信息和时间信息
		user.setIsAdmin(false);
		user.setIsBanned(false);
		user.setGmtCreate(CustomDateUtil.returnCurrentTimeInSql());
		user.setGmtModified(CustomDateUtil.returnCurrentTimeInSql());
		
		userMapper.insert(user);
	}

	@Override
	public User deleteUserById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCodeInUsedById(Integer codeId, Invcode record) {
		// 测试阶段先暂时不用更新isUsed值
		// record.setIsUsed(true);
		record.setGmtModified(CustomDateUtil.returnCurrentTimeInSql());
		
		invcodeMapper.updateByPrimaryKey(record);
	}

	@Override
	public boolean checkUsername(String username) throws Exception {
		UserExample example = new UserExample();
		com.ienai.po.UserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		
		List<User> user = userMapper.selectByExample(example);
		if(null == user || user.isEmpty()) {	// 没有重名
			return true;
		}
		return false;
	}

	@Override
	public boolean checkAdminPermissionById(Integer userId) throws Exception {
		User user = userMapper.selectByPrimaryKey(userId);
		
		if(user == null) return false;
		return user.getIsAdmin();	// 是管理员就返回真
	}

	@Override
	public boolean checkAdminPermissionByAccount(String username, String password) throws Exception {
		return false;
	}

	@Override
	public void cancelCareByNoteId(Integer userId, Integer noteId) throws Exception {
		UserNoteExample example = new UserNoteExample();
		com.ienai.po.UserNoteExample.Criteria criteria = example.createCriteria();
		criteria
			.andUserIdEqualTo(userId)
			.andNoteIdEqualTo(noteId);
		
		List<UserNote> records = userNoteMapper.selectByExample(example);
		if(null == records || records.isEmpty()) {
			throw new Exception("查询不到userId为" + userId + "noteId为" + noteId + "的记录");
		}

		UserNote record = records.get(0);
		record.setIsCare(false);
		userNoteMapper.updateByPrimaryKey(record);
		
		// 对note记录进行修改
		Note noteRecord = noteMapper.selectByPrimaryKey(noteId);
		Integer careCnt = noteRecord.getCareCount();
		if(careCnt <= 0) throw new Exception("出错，note的care数异常");
		noteRecord.setCareCount(careCnt-1);
		noteMapper.updateByPrimaryKey(noteRecord);
	}

	@Override
	public void addCareAndLikeByNoteId(Integer userId, Integer noteId) throws Exception {
		UserNoteExample example = new UserNoteExample();
		com.ienai.po.UserNoteExample.Criteria criteria = example.createCriteria();
		criteria
			.andUserIdEqualTo(userId)
			.andNoteIdEqualTo(noteId);
		
		List<UserNote> records = userNoteMapper.selectByExample(example);
		
		// 注意，和取消操作不一样的地方是，当不存在userId对noteId的操作记录时，不抛出异常而是添加一条记录
		if(null == records || records.isEmpty()) {	// 说明用户未点赞这条note
			UserNote record = new UserNote();
			
			record.setIsCare(true);
			record.setIsRead(false);
			record.setNoteId(noteId);
			record.setUserId(userId);
			record.setGmtCreate(CustomDateUtil.returnCurrentTimeInSql());
			
			userNoteMapper.insert(record);
					
			// 对note记录进行修改
			Note noteRecord = noteMapper.selectByPrimaryKey(noteId);
			Integer careCnt = noteRecord.getCareCount();
			Integer likeCnt = noteRecord.getLikeCount();
			noteRecord.setCareCount(careCnt+1);
			noteRecord.setLikeCount(likeCnt+1);
			noteMapper.updateByPrimaryKey(noteRecord);
		} else {
			UserNote record = records.get(0);
			record.setIsCare(true);
			userNoteMapper.updateByPrimaryKey(record);
			
			// 对note记录进行修改
			Note noteRecord = noteMapper.selectByPrimaryKey(noteId);
			Integer careCnt = noteRecord.getCareCount();
			noteRecord.setCareCount(careCnt+1);
			// 注意，这里不能再进行对like_count进行加1操作了，因为用户已经点赞过了
			noteMapper.updateByPrimaryKey(noteRecord);
		}
		
	}

	@Override
	public void addLikeByNoteId(Integer userId, Integer noteId) throws Exception {
		UserNote record = new UserNote();
		
		record.setIsCare(false);
		record.setIsRead(false);
		record.setNoteId(noteId);
		record.setUserId(userId);
		record.setGmtCreate(CustomDateUtil.returnCurrentTimeInSql());
		
		userNoteMapper.insert(record);
		
		// 对note记录进行修改
		Note noteRecord = noteMapper.selectByPrimaryKey(noteId);
		Integer likeCnt = noteRecord.getLikeCount();
		noteRecord.setLikeCount(likeCnt+1);
		noteMapper.updateByPrimaryKey(noteRecord);
	}

	@Override
	public void cancelLikeByNoteId(Integer userId, Integer noteId) throws Exception {
		UserNoteExample example = new UserNoteExample();
		com.ienai.po.UserNoteExample.Criteria criteria = example.createCriteria();
		criteria
			.andUserIdEqualTo(userId)
			.andNoteIdEqualTo(noteId);
		
		List<UserNote> records = userNoteMapper.selectByExample(example);
		if(null == records || records.isEmpty()) {
			throw new Exception("查询不到userId为" + userId + "noteId为" + noteId + "的记录");
		} else {
			// 否则就删除这条记录
			userNoteMapper.deleteByPrimaryKey(records.get(0).getId());
			
			// 对note记录进行修改
			Note noteRecord = noteMapper.selectByPrimaryKey(noteId);
			Integer likeCnt = noteRecord.getLikeCount();
			if(likeCnt <= 0) throw new Exception("出错，note的like数异常");
			noteRecord.setLikeCount(likeCnt-1);
			noteMapper.updateByPrimaryKey(noteRecord);
		}
	}

}
