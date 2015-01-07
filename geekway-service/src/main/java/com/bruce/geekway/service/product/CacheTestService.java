package com.bruce.geekway.service.product;

import org.springframework.cache.annotation.Cacheable;


public class CacheTestService{
	
	@Cacheable(value="storageCache#5", key="'test'")
	public String cacheTest(){
		System.out.println("---------load from db --------");
		return "12345Str";
		
	}
	
	@Cacheable(value="secondCache#5", key="'test'")
	public String secondCache(){
		System.out.println("---------load from db --------");
		return "12345Str";
		
	}
	
}