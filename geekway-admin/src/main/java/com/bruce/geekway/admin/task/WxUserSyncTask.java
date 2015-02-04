package com.bruce.geekway.admin.task;


import org.springframework.stereotype.Component;

@Component
public class WxUserSyncTask implements Runnable{

	
	private boolean running;
	
	@Override
	public void run() {
		
		if(!running){
			try{
				
			}catch(Exception e){
				running = false;
			}
		}
		running = false;
	}
	
	
}
