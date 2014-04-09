package com.bruce.geekway.handler.processor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.model.WxTextCode;
import com.bruce.geekway.model.wx.request.*;
import com.bruce.geekway.model.wx.response.*;
import com.bruce.geekway.service.IWxTextCodeService;

/**
 * 根据Key值，返回DB中对应的数据
 * 目前仅支持文本与菜单类型消息
 * @author liqian
 *
 */
@Service
public class KeycodeCmsProcessor implements Processor{
    
	@Autowired
    private IWxTextCodeService textCodeService;
    
    public BaseResponse process(BaseRequest request){
        if(request instanceof TextRequest){
            String code = ((TextRequest)request).getContent();
            
            WxTextCode textCode = textCodeService.loadByCode(code);
            if(textCode!=null){
            	return new TextResponse("", "", textCode.getReplyContent());
            }
        }else if(request instanceof ClickEventRequest){
            String key = ((EventRequest)request).getEventKey();
//            return textCodeService.loadByMenuCode(key);
        }
        
        return null;
    }
    
    
}
