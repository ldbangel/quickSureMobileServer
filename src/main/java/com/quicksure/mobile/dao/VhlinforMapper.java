package com.quicksure.mobile.dao;

import com.quicksure.mobile.entity.Vhlinfor;

public interface VhlinforMapper {
    int deleteByPrimaryKey(Integer vhiinforid);

    int insert(Vhlinfor record);

    int insertSelective(Vhlinfor record);

    Vhlinfor selectByPrimaryKey(Integer vhiinforid);
    
    Vhlinfor selectByorderno(String orderno);

    int updateByPrimaryKeySelective(Vhlinfor record);

    int updateByPrimaryKey(Vhlinfor record);
    
    Vhlinfor csrgetVhlinforByorderno(String orderno);
}