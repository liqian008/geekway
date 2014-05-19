package com.bruce.geekway.service.ito;

import java.util.List;

import com.bruce.geekway.model.ItoSlider;
import com.bruce.geekway.service.IBaseService;

public interface IItoSliderService extends IBaseService<ItoSlider, Integer>{
	
	public List<ItoSlider> querySortedList();
    
	}