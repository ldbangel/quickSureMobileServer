package com.quicksure.mobile.dao;

import com.quicksure.mobile.entity.Interfaceslogs;
import com.quicksure.mobile.entity.InterfaceslogsWithBLOBs;

public interface InterfaceslogsMapper {
    int deleteByPrimaryKey(Integer interfaceslogsid);

    int insert(InterfaceslogsWithBLOBs record);

    int insertSelective(InterfaceslogsWithBLOBs record);

    InterfaceslogsWithBLOBs selectByPrimaryKey(Integer interfaceslogsid);

    int updateByPrimaryKeySelective(InterfaceslogsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(InterfaceslogsWithBLOBs record);

    int updateByPrimaryKey(Interfaceslogs record);
}