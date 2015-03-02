package com.bruce.geekway.service;

import com.bruce.geekway.model.exception.CachedException;


public interface IWxJsapiTicketService{
	
	public String getCachedJsTicket() throws CachedException; 
	
}