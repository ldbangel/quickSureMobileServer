package com.quicksure.mobile.dao;

import com.quicksure.mobile.entity.RadarInfor;

public interface RadarInforMapper {

	    int deleteByPrimaryKey(Integer radarinforid);

	    int insert(RadarInfor record);

	    int insertSelective(RadarInfor record);

	    RadarInfor selectByPrimaryKey(String orderNo);
	    
	    int updateByPrimaryKeySelective(RadarInfor record);

	    int updateByPrimaryKey(RadarInfor record);
	    
}
