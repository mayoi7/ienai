package com.ienai.mapper;

import com.ienai.po.UserNote;
import com.ienai.po.UserNoteExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserNoteMapper {
    int countByExample(UserNoteExample example);

    int deleteByExample(UserNoteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserNote record);

    int insertSelective(UserNote record);

    List<UserNote> selectByExample(UserNoteExample example);

    UserNote selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserNote record, @Param("example") UserNoteExample example);

    int updateByExample(@Param("record") UserNote record, @Param("example") UserNoteExample example);

    int updateByPrimaryKeySelective(UserNote record);

    int updateByPrimaryKey(UserNote record);
}