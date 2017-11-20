package com.quicksure.mobile.dao;

import java.util.List;

import com.quicksure.mobile.entity.InterfaceDetails;

public interface InterfaceDetailsMapper {
    int deleteByPrimaryKey(Integer interfaceid);

    int insert(InterfaceDetails record);

    int insertSelective(InterfaceDetails record);

    InterfaceDetails selectByPrimaryKey(Integer interfaceid);
    
    List<InterfaceDetails>  selectAll();

    int updateByPrimaryKeySelective(InterfaceDetails record);

    int updateByPrimaryKey(InterfaceDetails record);
}