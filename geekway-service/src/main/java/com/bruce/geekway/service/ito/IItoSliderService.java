package com.bruce.geekway.service.ito;

import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.ItoSlider;
import com.bruce.geekway.model.ItoSliderCriteria;

public interface IItoSliderService extends IFoundationService<ItoSlider, Integer, ItoSliderCriteria>{
	
	public List<ItoSlider> querySortedList();
    
	}