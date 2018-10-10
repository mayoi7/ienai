package com.ienai.mapper;

import com.ienai.po.Invcode;
import com.ienai.po.InvcodeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface InvcodeMapper {
    int countByExample(InvcodeExample example);

    int deleteByExample(InvcodeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Invcode record);

    int insertSelective(Invcode record);

    List<Invcode> selectByExample(InvcodeExample example);

    Invcode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Invcode record, @Param("example") InvcodeExample example);

    int updateByExample(@Param("record") Invcode record, @Param("example") InvcodeExample example);

    int updateByPrimaryKeySelective(Invcode record);

    int updateByPrimaryKey(Invcode record);
}