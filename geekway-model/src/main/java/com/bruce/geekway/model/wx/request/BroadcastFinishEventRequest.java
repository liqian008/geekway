package com.bruce.geekway.model.wx.request;

/**
 * 群发完毕的通知消息
 * @author liqian
 *
 */
public class BroadcastFinishEventRequest extends EventRequest {

    private static final long serialVersionUID = 1L;
    
    private String MsgID;
    private int TotalCount;
    private int FilterCount;
    private int SentCount;
    private int ErrorCount;
    
    public BroadcastFinishEventRequest() {
		super();
	}
    
    public BroadcastFinishEventRequest(String msgID, int totalCount,
			int filterCount, int sentCount, int errorCount) {
		super();
		MsgID = msgID;
		TotalCount = totalCount;
		FilterCount = filterCount;
		SentCount = sentCount;
		ErrorCount = errorCount;
	}

	public String getMsgID() {
		return MsgID;
	}

	public void setMsgID(String msgID) {
		MsgID = msgID;
	}

	public int getTotalCount() {
		return TotalCount;
	}

	public void setTotalCount(int totalCount) {
		TotalCount = totalCount;
	}

	public int getFilterCount() {
		return FilterCount;
	}

	public void setFilterCount(int filterCount) {
		FilterCount = filterCount;
	}

	public int getSentCount() {
		return SentCount;
	}

	public void setSentCount(int sentCount) {
		SentCount = sentCount;
	}

	public int getErrorCount() {
		return ErrorCount;
	}

	public void setErrorCount(int errorCount) {
		ErrorCount = errorCount;
	}

	@Override
    public String toString() {
        return "SubscribeEventRequest [Event=" + event + ", ToUserName=" + toUserName + ", FromUserName=" + fromUserName + ", CreateTime=" + createTime
                + ", MsgType=" + msgType + ", MsgId=" + msgId + "]";
    }

}
