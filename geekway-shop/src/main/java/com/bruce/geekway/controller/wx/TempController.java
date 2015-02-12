package com.bruce.geekway.controller.wx;

import java.io.File;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;

import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.foundation.util.Md5Util;
import com.bruce.geekway.model.upload.UploadImageResult;
import com.bruce.geekway.service.upload.IUploadService;
import com.bruce.geekway.utils.UploadUtil;

/**
 * 商品controller
 * @author liqian
 *
 */
@Controller
public class TempController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(TempController.class);
	
	
	
	
	/**
	 * 首页
	 * @param model
	 * @param request
	 * @return
	 * @throws MemcachedException 
	 * @throws InterruptedException 
	 * @throws TimeoutException 
	 */
	@RequestMapping(value = {"temp"})
	public String index(Model model, HttpServletRequest request) throws TimeoutException, InterruptedException, MemcachedException {
		
		//		boolean memSet = memcachedClient.set("memkey", 60, "test");
//		System.out.println("mem set result: "+memSet);
//		String xxx = memcachedClient.get("memkey");
//		System.out.println("cache get result: "+xxx);
		
		try {
//			File file = new File("/home/liqian/Desktop/pic/hands-plant-870x450.jpg");
//			byte[] bytesData = UploadUtil.file2bytes(file);
//			String result = uploadService.uploadFile(bytesData, "", file.getName());
//			System.out.println(result);
//			
//			UploadImageResult uploadResult = qiniuUploadService.uploadImage(new File("/home/liqian/Desktop/pic/hands-plant-870x450.jpg"), "", IUploadService.IMAGE_SPEC_LARGE, IUploadService.IMAGE_SPEC_MEDIUM, IUploadService.IMAGE_SPEC_SMALL);
//			File file = new File("/home/liqian/Desktop/pic/hands-plant-870x450.jpg");
//			byte[] bytesData = UploadUtil.file2bytes(file);
//			UploadImageResult imageUploadResult = uploadService.uploadImage(bytesData, "", "hands-plant-870x450.jpg", IUploadService.IMAGE_SPEC_LARGE, IUploadService.IMAGE_SPEC_MEDIUM, IUploadService.IMAGE_SPEC_SMALL);
//			System.out.println(imageUploadResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "temp";
	}
	
	
	public static void main(String[] args) {
		String text = Md5Util.md5Encode("Geekway,Meiniur_liqian008");
		System.out.println(text);
		System.out.println(text.length());
	}
	
	
	
	
}
