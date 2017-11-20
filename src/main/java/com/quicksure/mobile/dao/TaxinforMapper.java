package com.quicksure.mobile.dao;

import com.quicksure.mobile.entity.Taxinfor;

public interface TaxinforMapper {
    int deleteByPrimaryKey(Integer taxinforid);

    int insert(Taxinfor record);

    int insertSelective(Taxinfor record);

    Taxinfor selectByPrimaryKey(Integer taxinforid);

    int updateByPrimaryKeySelective(Taxinfor record);

    int updateByPrimaryKey(Taxinfor record);
}