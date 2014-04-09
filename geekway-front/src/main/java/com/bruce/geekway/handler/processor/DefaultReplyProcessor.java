package com.bruce.geekway.handler.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.model.WxDefaultReply;
import com.bruce.geekway.model.wx.request.*;
import com.bruce.geekway.model.wx.response.*;
import com.bruce.geekway.service.IWxDefaultReplyService;


/**
 * 默认processor
 * 用于没有任何数据返回时的文字提示内容（配置文件中配置）
 * @author liqian
 *
 */
@Service
public class DefaultReplyProcessor implements Processor{ 
    
    @Autowired
    private IWxDefaultReplyService defaultReplyService;
    
    public BaseResponse process(BaseRequest request){
        
        WxDefaultReply defaultReply = defaultReplyService.loadById(1);
        if(defaultReply==null||defaultReply.getId()<=0){
            return null;
        }
        
        if(request instanceof TextRequest){ 
            //文本默认回复
            return textReply(request, defaultReply.getTextReply());
        }else if(request instanceof ImageRequest){
            //图片默认回复
            return textReply(request, defaultReply.getImageReply());
        }else if(request instanceof VoiceRequest){
            //语音默认回复
            return textReply(request, defaultReply.getVoiceReply());
        }else if(request instanceof EventRequest){
            //菜单点击默认回复
            return textReply(request, defaultReply.getMenuClickReply());
        }else if(request instanceof LocationRequest){
            //位置默认回复
            return textReply(request, defaultReply.getLocationReply());
        }else if(request instanceof VideoRequest){
            //视频默认回复
            return textReply(request, defaultReply.getVideoReply());
        }
        return null;
    }
    
    /**
     * 文本回复
     * @param request
     * @param content
     * @return
     */
    private TextResponse textReply(BaseRequest request, String content){
        String fromUserName = request.getFromUserName();
        String toUserName = request.getToUserName();
        //交换fromUserName和toUserName
        return textReply(fromUserName, toUserName, content);
    }
    
    
    /**
     * 文本回复
     * @param toUserName
     * @param fromUserName
     * @param content
     * @return
     */
    private TextResponse textReply(String toUserName, String fromUserName, String content){
        TextResponse textResponse = new TextResponse(fromUserName, toUserName, content);
        return textResponse;
    }

	public IWxDefaultReplyService getDefaultReplyService() {
		return defaultReplyService;
	}

	public void setDefaultReplyService(IWxDefaultReplyService defaultReplyService) {
		this.defaultReplyService = defaultReplyService;
	}

}
