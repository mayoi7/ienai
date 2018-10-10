package com.ienai.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ienai.dto.GenNote;
import com.ienai.dto.IndexInfo;
import com.ienai.dto.NoteMetaRecord;
import com.ienai.po.Note;
import com.ienai.po.User;
import com.ienai.service.NoteService;
import com.ienai.service.UserService;
import com.ienai.util.CustomStringUtil;
import com.ienai.util.HttpUtil;
import com.ienai.util.InsuranceUtil;
import com.ienai.util.NoteUtil;

/**
 * 
 * <p>Title: NoteController</p>
 * <p>Description: 返回和note相关的信息</p>
 * @author akira
 * @date 2018年10月1日 上午10:23:13
 */
@RestController
@RequestMapping("note")
public class NoteController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private NoteService noteService;
	
	private static final Logger logger = LoggerFactory.getLogger(NoteController.class);
	
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 返回主页需要的note和user信息
	 */
	@GetMapping("/returnUserAndNote")
	public IndexInfo returnUserAndNote() throws Exception {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		if(null == user) {
			logger.info(CustomStringUtil
					.returnInfoLog(HttpUtil.getIpAddrByRequest(request), null, 
							"未登录用户请求调用returnUserAndNote接口"));
		}
		
		logger.info(CustomStringUtil
				.returnInfoLog(HttpUtil.getIpAddrByRequest(request), user.getUsername(), 
						"请求查询主页所需的user和note信息"));
		IndexInfo indexInfo = new IndexInfo();
		// 默认初始查询第一页的全部数据
		List<Note> noteList = noteService.findNoteByPageInTimeOrder(1, null, true);
		
		logger.info(CustomStringUtil
				.returnInfoLog(HttpUtil.getIpAddrByRequest(request), user.getUsername(), 
						"查询完毕，开始做note的修正"));
		List<GenNote> genNoteList = NoteUtil.changeNoteListToGen(noteList);	// 把po转化为dto
		
		// 添加list中缺少的from信息
		for (int i = 0; i < genNoteList.size(); i++) {
			User temp = userService.findUserById(genNoteList.get(i).getUserId());
			if(null == temp) {
				logger.error(CustomStringUtil
						.returnInfoLog(HttpUtil.getIpAddrByRequest(request), user.getUsername(), 
								"userId为" + genNoteList.get(i).getUserId()+ "的note数据查询不到user，请复查"));
			} else {
				// 设置from信息
				genNoteList.get(i).setFrom(temp.getUsername());
			}
		}
		
		indexInfo.setNoteList(genNoteList);
		
		logger.info(CustomStringUtil
				.returnInfoLog(HttpUtil.getIpAddrByRequest(request), user.getUsername(), 
						"即将查询并设置id为" + user.getId() + "用户的信息"));
		
		indexInfo.setUser(InsuranceUtil
				.changeUserToHomeUser(userService.findUserById(user.getId())));
		
		return indexInfo;
	}
	
	/**
	 * like操作很简单，
	 * 如果isLiked为真，说明用户已经点赞过了，即将进行的操作就是取消点赞，即删除这条记录
	 * 如果isLiked为假，说明用户没有给这条note点赞，即将进行的操作就是添加一条点赞记录进入数据库
	 * @throws Exception
	 */
	@PostMapping("likeNote")
	public boolean likeNote(Integer id, boolean isLiked) throws Exception {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		if(true == isLiked) {	// 用户已经点赞过了，进行取消点赞操作
			logger.info(CustomStringUtil
					.returnInfoLog(HttpUtil.getIpAddrByRequest(request), user.getUsername(),
							"将取消" + user.getId() + "号用户对 " + id + "号note的like记录"));
			
			userService.cancelLikeByNoteId(user.getId(), id);
			
		} else {	// 用户还没点赞过，进行添加点赞记录操作
			logger.info(CustomStringUtil
					.returnInfoLog(HttpUtil.getIpAddrByRequest(request), user.getUsername(),
							"将添加" + user.getId() + "号用户对 " + id + "号note的like记录"));
			
			userService.addLikeByNoteId(user.getId(), id);
		}
		
		return true;
	}
	
	/**
	 * 当前用户care一个note，注意，care的同时需要对一个note进行like操作
	 * 如果isCared为真，说明用户已经关注过了，再次点击就是取消，应从数据库中取消当前用户的关注
	 * 如果isCared为假，说明用户即将要进行关注，再次点击就是添加关注，应从数据库中添加该用户对id为当前id的note的点赞和关注
	 */
	@PostMapping("careNote")
	public boolean careNote(Integer id, boolean isCared) throws Exception {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		
		if(true == isCared) {	// 只取消关注，而不取消点赞
			logger.info(CustomStringUtil
					.returnInfoLog(HttpUtil.getIpAddrByRequest(request), user.getUsername(),
							"将取消" + user.getId() + "号用户对 " + id + "号note的care数据"));
			
			userService.cancelCareByNoteId(user.getId(), id);
			
			logger.info(CustomStringUtil
					.returnInfoLog(HttpUtil.getIpAddrByRequest(request), user.getUsername(),
							user.getId() + "号用户对 " + id + "号note的care数据已取消"));
		} else {
			logger.info(CustomStringUtil
					.returnInfoLog(HttpUtil.getIpAddrByRequest(request), user.getUsername(),
							"将添加" + user.getId() + "号用户对 " + id + "号note的care数据，注意，同时会添加like数据"));
			
			userService.addCareAndLikeByNoteId(user.getId(), id);	// 不能单独添加care，需要同时添加care和like
			
		}
		
		return true;
	}

	/**
	 * 查询第pageNum页的数据，并按时间排序
	 * @return 以List形式返回这一页数据
	 */
	@GetMapping("returnPageInTimeOrder")
	public List<GenNote> returnPageInTimeOrder(Integer pageNum) throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		logger.info(CustomStringUtil
				.returnInfoLog(HttpUtil.getIpAddrByRequest(request), user.getUsername(), 
						"请求访问第" + pageNum + "页数据，并按时间排序"));
		
		List<Note> noteList = noteService.findNoteByPageInTimeOrder(pageNum, null, true);
		if(noteList.isEmpty()) {	// 不能用 (noteList == null) 来判断
			logger.info(CustomStringUtil
					.returnInfoLog(HttpUtil.getIpAddrByRequest(request), user.getUsername(),
							"超过最大页面数，将返回空"));
			return null;
		}
		
		List<GenNote> genList = NoteUtil.changeNoteListToGen(noteList);
		
		logger.info(CustomStringUtil
				.returnInfoLog(HttpUtil.getIpAddrByRequest(request), user.getUsername(),
						"已获得第" + pageNum + "页数据，并按时间排序，即将返回"));
		
		return genList;
	}
	
	/**
	 * 返回第pageNum页的点赞顺序排序结果
	 */
	@GetMapping("returnPageInLikeOrder")
	public List<GenNote> returnPageInLikeOrder(Integer pageNum) throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		logger.info(CustomStringUtil
				.returnInfoLog(HttpUtil.getIpAddrByRequest(request), user.getUsername(), 
						"请求访问第" + pageNum + "页数据，并按点赞数排序"));
		
		List<Note> noteList = noteService.findNoteByPageInLikeOrder(pageNum, null);
		if(noteList.isEmpty()) {	// 不能用 (noteList == null) 来判断
			logger.info(CustomStringUtil
					.returnInfoLog(HttpUtil.getIpAddrByRequest(request), user.getUsername(),
							"超过最大页面数，将返回空"));
			return null;
		}
		
		List<GenNote> genList = NoteUtil.changeNoteListToGen(noteList);
		
		logger.info(CustomStringUtil
				.returnInfoLog(HttpUtil.getIpAddrByRequest(request), user.getUsername(),
						"已获得第" + pageNum + "页数据，并按点赞数排序，即将返回"));
		
		return genList;
	}
	
	/**
	 * 返回所有写给我的note，强制使用时间排序
	 */
	@GetMapping("returnPageSendMeInTimeOrder")
	public List<GenNote> returnPageSendMeInTimeOrder(Integer pageNum) throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		logger.info(CustomStringUtil
				.returnInfoLog(HttpUtil.getIpAddrByRequest(request), user.getUsername(), 
						"请求访问第" + pageNum + "页数据，要求查询所有写给我的note，并按时间排序"));
		
		List<Note> noteList = noteService.findNoteByPageInTimeOrder(pageNum, user.getId(), false);
		if(noteList.isEmpty()) {	// 不能用 (noteList == null) 来判断
			logger.info(CustomStringUtil
					.returnInfoLog(HttpUtil.getIpAddrByRequest(request), user.getUsername(),
							"超过最大页面数或未查询出结果，将返回空"));
			return null;
		}
		
		List<GenNote> genList = NoteUtil.changeNoteListToGen(noteList);
		
		logger.info(CustomStringUtil
				.returnInfoLog(HttpUtil.getIpAddrByRequest(request), user.getUsername(),
						"已获得第" + pageNum + "页数据，要求查询所有写给我的note，并按时间排序，即将返回"));
		
		return genList;
	}
	
	/**
	 * 返回所有我写的note，强制使用时间排序
	 */
	@GetMapping("returnPageFromMeInTimeOrder")
	public List<GenNote> returnPageFromMeInTimeOrder(Integer pageNum) throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		logger.info(CustomStringUtil
				.returnInfoLog(HttpUtil.getIpAddrByRequest(request), user.getUsername(), 
						"请求访问第" + pageNum + "页数据，要求查询所有自己所写的note，并按时间排序"));
		
		List<Note> noteList = noteService.findNoteByPageInTimeOrder(pageNum, user.getId(), true);
		if(noteList.isEmpty()) {	// 不能用 (noteList == null) 来判断
			logger.info(CustomStringUtil
					.returnInfoLog(HttpUtil.getIpAddrByRequest(request), user.getUsername(),
							"超过最大页面数或未查询出结果，将返回空"));
			return null;
		}
		
		List<GenNote> genList = NoteUtil.changeNoteListToGen(noteList);
		
		logger.info(CustomStringUtil
				.returnInfoLog(HttpUtil.getIpAddrByRequest(request), user.getUsername(),
						"已获得第" + pageNum + "页数据，要求查询所有自己所写的note，并按时间排序，即将返回"));
		
		return genList;
	}
	
	/**
	 * 返回当前登陆用户对当前页面的所有note的点赞和interest记录
	 */
	@PostMapping("returnUserLikeAndInterest")
	public List<NoteMetaRecord> returnUserLikeAndInterest(int[] idList) throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		logger.info(CustomStringUtil
				.returnInfoLog(HttpUtil.getIpAddrByRequest(request), user.getUsername(),
						"将要查询用户对一系列note的点赞和关心数据"));
		
		List<NoteMetaRecord> resRecords = new ArrayList<NoteMetaRecord>();
		
		for (int i = 0; i < idList.length; i++) {
			// 装入生成的对象
			resRecords.add(noteService.findAndSetNoteLikeAndCareById(user.getId(), idList[i]));
		}
		return resRecords;
	}
}
