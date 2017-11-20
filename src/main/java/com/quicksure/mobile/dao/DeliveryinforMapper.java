package com.quicksure.mobile.dao;

import com.quicksure.mobile.entity.Deliveryinfor;

public interface DeliveryinforMapper {
    int deleteByPrimaryKey(Integer deliveryid);

    int insert(Deliveryinfor record);

    int insertSelective(Deliveryinfor record);

    Deliveryinfor selectByPrimaryKey(Integer deliveryid);

    int updateByPrimaryKeySelective(Deliveryinfor record);

    int updateByPrimaryKey(Deliveryinfor record);
    
    int getDevlieryIdByCreateTime();
    
    Deliveryinfor getDevlieryByorderNo(String orderno);
}