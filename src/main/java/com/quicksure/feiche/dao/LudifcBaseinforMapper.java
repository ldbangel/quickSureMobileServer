package com.quicksure.feiche.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.quicksure.feiche.entity.LudifcBaseinfor;
import com.quicksure.feiche.entity.LudifcBaseinforExample;

public interface LudifcBaseinforMapper {
    int countByExample(LudifcBaseinforExample example);

    int deleteByExample(LudifcBaseinforExample example);

    int deleteByPrimaryKey(Integer orderid);

    int insert(LudifcBaseinfor record);

    int insertSelective(LudifcBaseinfor record);

    List<LudifcBaseinfor> selectByExample(LudifcBaseinforExample example);

    LudifcBaseinfor selectByPrimaryKey(Integer orderid);
    
    LudifcBaseinfor selectByOrderno(String orderno);

    int updateByExampleSelective(@Param("record") LudifcBaseinfor record, @Param("example") LudifcBaseinforExample example);

    int updateByExample(@Param("record") LudifcBaseinfor record, @Param("example") LudifcBaseinforExample example);

    int updateByPrimaryKeySelective(LudifcBaseinfor record);

    int updateByPrimaryKey(LudifcBaseinfor record);
    
    
}