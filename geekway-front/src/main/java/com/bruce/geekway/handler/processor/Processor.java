package com.bruce.geekway.handler.processor;

import com.bruce.geekway.model.wx.request.BaseRequest;
import com.bruce.geekway.model.wx.response.BaseResponse;


public interface Processor {
    
    public BaseResponse process(BaseRequest request);
    
}
