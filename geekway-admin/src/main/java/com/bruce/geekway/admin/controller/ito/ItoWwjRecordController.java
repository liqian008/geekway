package com.bruce.geekway.admin.controller.ito;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.ItoWwjRecord;
import com.bruce.geekway.service.ito.IItoWwjRecordService;



@Controller
@RequestMapping("/ito")
public class ItoWwjRecordController {

	@Autowired
	private IItoWwjRecordService itoWwjRecordService;
	
	
	@RequestMapping("/wwjRecordList")
	public String wwjRecordList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<ItoWwjRecord> wwjRecordList = itoWwjRecordService.queryAll();
		model.addAttribute("wwjRecordList", wwjRecordList);
		return "ito/wwjRecordList";
	}
	
	
//	/**
//	 * 保存商品，并跳转至【填写sku信息】界面
//	 * @param model
//	 * @param wwjRecord
//	 * @param wwjRecordSkuValueIds
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/saveWwjRecord", method = RequestMethod.POST)
//	public String saveWwjRecord(Model model, ItoWwjRecord wwjRecord, HttpServletRequest request) {
//		String servletPath = request.getRequestURI();
//		model.addAttribute("servletPath", servletPath);
//		
//		int result = 0;
//		
//		Date currentTime = new Date();
//		wwjRecord.setUpdateTime(currentTime);
//		if(wwjRecord!=null&&wwjRecord.getId()!=null&&wwjRecord.getId()>0){
//			result = itoWwjRecordService.updateById(wwjRecord);
//		}else{//新增
//			wwjRecord.setCreateTime(currentTime);
//			result = itoWwjRecordService.save(wwjRecord);
//		}
//		model.addAttribute("redirectUrl", "./wwjRecordList");
//		return "forward:/home/operationRedirect";
//	}
	
}
