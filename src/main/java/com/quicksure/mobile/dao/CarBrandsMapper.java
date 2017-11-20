package com.quicksure.mobile.dao;

import com.quicksure.mobile.entity.CarBrands;

	public interface CarBrandsMapper {
        ////高风险车型查询
	    int selectBybrandNameAndDemio(CarBrands carBrands);

	}
