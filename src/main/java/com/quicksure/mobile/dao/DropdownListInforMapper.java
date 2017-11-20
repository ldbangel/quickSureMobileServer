package com.quicksure.mobile.dao;

import com.quicksure.mobile.entity.DropdownListInfor;

public interface DropdownListInforMapper {
    int deleteByPrimaryKey(Integer dropdownlistid);

    int insert(DropdownListInfor record);

    int insertSelective(DropdownListInfor record);

    DropdownListInfor selectByPrimaryKey(Integer dropdownlistid);

    int updateByPrimaryKeySelective(DropdownListInfor record);

    int updateByPrimaryKey(DropdownListInfor record);
}