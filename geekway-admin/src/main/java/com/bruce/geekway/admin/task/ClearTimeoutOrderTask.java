package com.bruce.geekway.admin.task;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bruce.geekway.service.product.IProductOrderService;

/**
 * 超时自动释放订单所占库存的task
 * @author liqian
 *
 */
@Component
public class ClearTimeoutOrderTask implements Runnable{

	@Autowired
	private IProductOrderService productOrderService;
	
	private boolean running;
	
	@Override
	public void run() {
		
		if(!running){
			try{
				productOrderService.clearTimeoutOrderList(null);
			}catch(Exception e){
				running = false;
			}
		}
		running = false;
		
	}
	
}
