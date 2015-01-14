package com.bruce.geekway.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bruce.geekway.constants.ConstMemc;
import com.bruce.geekway.service.IWxJsTicketService;

@Service
public class WxJsTicketServiceImpl implements IWxJsTicketService {

	@Override
	@Cacheable(value=ConstMemc.MEMCACHE_CACHE_VALUE+"#7000", key="'"+ConstMemc.MEMCACHE_KEY_MP_JSTICKET+"'")
	public String getCachedJsTicket() {
		return null;
	}
	
}