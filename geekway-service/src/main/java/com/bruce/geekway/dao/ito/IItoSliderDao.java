package com.bruce.geekway.dao.ito;

import java.util.List;

import com.bruce.geekway.dao.IBaseDao;
import com.bruce.geekway.model.ItoSlider;

public interface IItoSliderDao extends IBaseDao<ItoSlider, Integer> {

	public List<ItoSlider> querySortedList();
    
}
