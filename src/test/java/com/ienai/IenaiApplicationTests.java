package com.ienai;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ienai.mapper.LoginMapper;
import com.ienai.mapper.NoteMapper;
import com.ienai.po.Login;
import com.ienai.po.Note;
import com.ienai.po.NoteExample;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IenaiApplicationTests {

	@Autowired
	private LoginMapper mapper;
	
	@Autowired
	private NoteMapper noteMapper;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testSelectLogInfo() {
		
		Login obj = mapper.selectByPrimaryKey(1);
		System.out.println(obj.getIp());
	}
	
	/**
	 * 测试分页查询
	 */
	@Test
	public void testSelectByPage() {
		PageHelper.startPage(3, 15);
		NoteExample ex = new NoteExample();
		ex.setOrderByClause("id DESC");
		
		List<Note> list = noteMapper.selectByExample(ex);

		if(list == null) System.out.println("空1");
		else if(list.isEmpty()) System.out.println("空2");
		
		for (Note note : list) {
			System.out.println(note.getId());
		}
	}
}
