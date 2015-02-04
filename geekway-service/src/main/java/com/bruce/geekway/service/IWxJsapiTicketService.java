package com.bruce.geekway.service;


public interface IWxJsapiTicketService{
	
	public String getCachedJsTicket(String appId, String secretKey);
	
	public String getCachedJsTicket(String accessToken);
	
}