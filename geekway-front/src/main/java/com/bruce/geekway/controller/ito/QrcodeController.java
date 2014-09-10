package com.bruce.geekway.controller.ito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.geekway.model.ito.json.ItoWwjQrcodeResult;
import com.bruce.geekway.utils.JsonUtil;
import com.bruce.geekway.utils.JsonViewBuilderUtil;
import com.bruce.geekway.utils.WxHttpUtil;

/**
 * 二维码的图片
 * @author liqian
 *
 */
@Deprecated
@Controller
@RequestMapping(value={"ito"})
public class QrcodeController {
	
//	/*用户请求二维码的次数*/
//	private static final String USER_APPLIED_TIME = "user_appled_time";
//	
//	/**
//	 * 
//	 * @param model
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/lotteryQrcode")
//	public String lotteryQrcode(Model model, HttpServletRequest request, HttpServletResponse response) {
//		int userApplyedTime = 0;
//		//检查cookie中的
//		Cookie[] cookies = request.getCookies();
//		if(cookies!=null&&cookies.length>0){
//			for(Cookie cookie: cookies){
//				if(USER_APPLIED_TIME.equals(cookie.getName())){
//					userApplyedTime = NumberUtils.toInt(cookie.getValue(), 0);
//					System.out.println("======cookied userApplyedTime==="+userApplyedTime);
//				}
//			}
//		}
//		
//		//重新写入cookie
//		userApplyedTime++;
//		Cookie cookie = new Cookie(USER_APPLIED_TIME, String.valueOf(userApplyedTime));
//		cookie.setMaxAge(999999999);
//		response.addCookie(cookie);
//		
//		if(userApplyedTime>2){//2次免费次数已经用光
//			model.addAttribute("errorMsg", "对不起，您的两次免费游戏机会已全部用完，感谢您的参与！");
//			return "ito/lotteryQrcode/error";
//		}else{
//			//从第三方服务获取二维码&展示给用户
//			String qrcodeUrl = null;
//			
//			String wwjUrl = "http://itocases.eicp.net:8733/design_time_addresses/qrgamecontrollerservice/qrurl";
//			String result = WxHttpUtil.sendGetRequest(wwjUrl, null);
//			if(!StringUtils.isBlank(result)){
//				ItoWwjQrcodeResult wwjResult = JsonUtil.gson.fromJson(result, ItoWwjQrcodeResult.class);
//				if(wwjResult!=null&&wwjResult.getErrorCode()==0){
//					qrcodeUrl = wwjResult.getImageUrl();
//				}
//			}
//			model.addAttribute("qrcodeUrl", qrcodeUrl);
//			model.addAttribute("userApplyedTime", userApplyedTime);
//			return "ito/lotteryQrcode/success";
//		}
//	}
//	
	
	
	/**
	 * 印刷二维码，取300个
	 * @return
	 */
	@RequestMapping(value = "/qrcodePublish")//, method=RequestMethod.POST
	public String qrcodePublish(int number) {
		
		String wwjUrl = "http://itocases.eicp.net:8733/design_time_addresses/qrgamecontrollerservice/qrurl";
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<number;i++){
			String result = WxHttpUtil.getRequest(wwjUrl, null);
			ItoWwjQrcodeResult wwjResult = JsonUtil.gson.fromJson(result, ItoWwjQrcodeResult.class);
			if(wwjResult!=null&&wwjResult.getErrorCode()==0){
				String qrcodeUrl = wwjResult.getImageUrl();
				if(!StringUtils.isBlank(qrcodeUrl)){
//					qrcodeList.add(qrcodeUrl);
					sb.append(qrcodeUrl+",");
				}
			}
		}
		System.out.println(sb.toString());
		return "ito/lotteryQrcode/success";
	}
	
	
	public static void main(String[] args) {
		
		String qrcodeStr = "http://qr.liantu.com/api.php?text=CB|0B|2A|D1|1D|93|18|C2|95|13|94|B6|57|FB|6D|0F|0E|44|F4|CC|DC|9D|A5|9E|63|84|5E|6F|E0|89|A2|4C|B7|6C|20|DF|EF|DD|50|C6,http://qr.liantu.com/api.php?text=FA|4B|EA|98|81|E7|50|5F|F7|E7|33|82|69|70|5D|48|15|F7|F1|9B|00|AA|D0|F8|1F|73|8C|D5|C7|64|74|16|C0|A9|E8|43|FA|2A|55|FA,http://qr.liantu.com/api.php?text=E6|18|EA|6D|A1|FA|6D|BC|0D|2E|A4|6C|CE|F1|65|E7|E2|43|3B|43|24|B5|72|4B|0D|AA|89|D6|DE|52|EE|5E|10|98|FE|B7|40|BD|D8|2D,http://qr.liantu.com/api.php?text=56|BC|49|8C|55|B9|B3|C4|7B|CE|0F|D5|9F|98|97|0B|2B|30|D8|5E|EA|5E|C7|DB|4D|B4|E3|64|85|11|6F|53|3D|1F|04|68|58|48|E4|4C,http://qr.liantu.com/api.php?text=EF|C0|24|D2|4E|F2|B5|5C|AD|04|40|3C|F4|4C|5E|1F|8A|61|C0|76|5E|25|E7|D2|A8|78|76|38|46|E7|19|48|86|86|65|DE|82|31|EC|80,http://qr.liantu.com/api.php?text=3C|7C|91|82|EC|0D|35|1B|AD|B0|AF|EB|67|D4|1D|7C|C3|BE|C0|65|BF|21|9C|8E|B1|47|7E|A8|EA|D8|9E|5D|05|E7|2F|B8|01|94|5A|6C,http://qr.liantu.com/api.php?text=E6|CC|0E|7E|5B|53|2D|F9|D5|B6|63|6C|DB|72|EA|B0|03|A2|79|50|DF|64|70|D5|02|20|60|D8|DC|C9|58|C5|F3|AD|4F|AA|31|92|F5|87,http://qr.liantu.com/api.php?text=1E|90|ED|F8|7A|D5|C0|59|BD|71|86|48|70|72|DD|4B|9B|89|80|4C|33|B2|E7|7E|39|37|C2|82|D0|74|32|8F|34|6A|B7|0B|0E|34|7F|C4,http://qr.liantu.com/api.php?text=3F|7B|EF|DF|FF|E9|82|EB|37|C7|A6|A1|4D|21|A0|76|FC|A2|0D|43|78|F3|67|19|12|31|DB|27|B9|3A|CD|FA|C3|DE|D5|1E|10|EC|FA|73,http://qr.liantu.com/api.php?text=8D|40|C2|4C|9D|31|A0|15|8D|4E|37|B1|D6|3F|0D|AE|92|CF|A2|1F|69|96|E4|6A|7A|77|FE|E1|37|FB|1B|2A|40|E3|2D|95|32|9D|C1|A2,http://qr.liantu.com/api.php?text=8A|B2|39|3D|B2|18|AC|A0|E3|BB|08|48|34|65|EF|55|55|2A|C9|00|DD|9A|BE|13|F7|05|5B|7B|B3|52|A6|96|DA|70|10|B4|A3|6C|F9|30,http://qr.liantu.com/api.php?text=F9|97|42|7C|9A|28|6D|F5|8A|63|55|5A|4D|8B|10|65|6A|47|2D|2E|B7|4F|49|54|C5|3D|A9|35|8C|03|F7|30|11|9E|F2|F9|05|7E|C7|F4,http://qr.liantu.com/api.php?text=E2|8F|0F|BE|E6|97|7C|F4|A9|B4|F1|9E|75|02|A4|0A|B4|F0|97|A4|FE|81|79|84|1D|58|DE|91|4F|D6|56|F3|DA|21|F1|1D|49|5E|8B|45,http://qr.liantu.com/api.php?text=3D|1A|6F|40|7A|31|5B|34|9A|F6|1B|94|79|95|E1|B5|EB|10|C1|19|A3|9C|87|A8|45|51|3A|2D|D1|E0|DF|71|CB|15|C0|D8|2C|B2|79|50,http://qr.liantu.com/api.php?text=D8|BA|86|25|1F|13|5E|8F|07|DD|19|42|85|7B|5C|EA|C2|A2|68|AD|D9|E7|35|26|64|04|48|01|D9|24|66|62|12|28|6F|4E|DF|37|28|2A,http://qr.liantu.com/api.php?text=CC|DC|52|57|57|F7|A7|14|28|1B|FD|27|A6|55|DC|30|18|98|91|52|A4|18|53|4C|FC|64|B5|86|35|E6|DC|B8|63|40|46|F0|98|64|DE|29,http://qr.liantu.com/api.php?text=E2|21|09|91|14|7D|37|DF|AC|05|51|BF|94|A4|EE|1C|AF|BC|97|10|7C|CE|69|DF|67|DB|B7|55|21|6A|E1|67|FA|E5|36|8C|F2|30|FB|98,http://qr.liantu.com/api.php?text=A0|80|9A|13|20|2F|9C|85|E6|22|30|DC|18|97|07|AC|DE|35|55|06|F0|01|DE|48|80|3F|DF|37|EB|62|34|16|53|AE|7E|80|B8|17|13|F2,http://qr.liantu.com/api.php?text=62|3D|EC|44|06|23|F7|B5|FB|58|A6|32|41|52|A2|DA|FB|63|50|07|9D|25|89|37|9C|56|1F|EA|3B|B0|72|A8|0E|FA|6A|D7|49|9D|A4|04,http://qr.liantu.com/api.php?text=DE|06|A6|26|4E|83|F5|20|6D|5D|77|1B|4C|93|91|07|99|41|67|CC|20|0F|A3|D8|5D|4E|C0|F4|BE|0E|4E|58|49|00|C1|5E|AB|1A|03|7D,http://qr.liantu.com/api.php?text=3C|6F|E9|65|B2|2C|36|19|10|8C|71|EC|15|77|1C|FC|26|8A|6E|1F|EC|3A|25|08|93|75|8B|E9|F9|B3|74|EA|86|B5|9A|95|73|68|07|41,http://qr.liantu.com/api.php?text=A9|9C|DE|15|E5|BD|59|A5|79|49|01|FC|C7|2D|9C|F0|93|53|53|B6|19|06|51|F7|AB|21|03|B6|49|15|3A|1E|6B|18|4C|67|8C|3B|73|0A,http://qr.liantu.com/api.php?text=4C|44|A5|61|5F|E7|A5|75|37|3E|14|6E|A2|31|04|CF|AB|26|86|E1|D3|1E|59|7B|19|50|72|15|AF|D8|06|B9|17|E1|0F|8F|12|A6|48|EC,http://qr.liantu.com/api.php?text=42|43|2B|28|58|F3|AB|EC|F3|D8|53|EE|80|E9|49|89|EA|0B|8E|A9|F4|03|8D|55|FC|F1|E0|79|E4|5D|41|5E|D5|90|92|C5|DF|97|4D|DF,http://qr.liantu.com/api.php?text=2E|79|06|0C|05|71|61|DD|97|22|4E|5E|A2|BF|E2|A8|FF|96|AA|0C|55|52|8A|96|89|05|3F|C0|2F|3E|A4|68|66|04|0A|A0|D9|F8|5E|84,http://qr.liantu.com/api.php?text=6C|F5|5A|44|28|44|D8|90|34|E2|8E|27|18|2E|17|FA|22|2B|3F|94|F7|D1|5D|D4|84|2F|33|30|8D|3B|4D|62|C3|B3|B4|66|FC|83|8C|AE,http://qr.liantu.com/api.php?text=54|8B|51|45|55|6D|DA|69|46|46|D7|FC|8E|8B|E2|5B|3E|C7|2A|90|4A|A8|D5|27|AE|D6|CD|FD|DF|F9|AA|3C|9A|E7|85|BD|C2|41|64|DE,http://qr.liantu.com/api.php?text=88|A7|98|85|C2|F9|BF|62|55|92|74|77|54|80|46|FE|99|74|03|92|91|6F|28|B8|65|09|A1|1D|02|92|20|E2|E8|56|60|C3|57|D9|9A|2B,http://qr.liantu.com/api.php?text=6B|D1|09|EF|F4|A4|C7|76|18|23|D5|67|AE|65|40|1F|E5|07|9D|6E|D3|E4|BA|56|F0|36|10|2D|11|C0|95|56|5E|E6|40|5E|6C|6F|35|06,http://qr.liantu.com/api.php?text=8A|19|DD|3D|AF|B2|8B|6F|21|CB|8E|00|DA|88|08|CB|B3|2C|FF|61|8E|ED|64|27|14|68|A0|2F|C2|6C|F3|8B|31|59|23|69|6C|7B|5C|B5,http://qr.liantu.com/api.php?text=C3|39|C5|07|02|7D|71|A0|3C|8E|B5|C4|90|66|06|4E|09|FC|29|D7|AE|FA|08|D0|69|FA|76|E7|30|82|8A|A3|EA|90|54|32|AC|26|1B|F2,http://qr.liantu.com/api.php?text=3D|69|D0|8D|29|06|6F|AF|CE|80|4F|C5|B3|34|C2|C0|03|24|C9|A8|88|4C|47|F5|20|BF|73|C0|8A|3F|58|D5|1F|2F|D4|EB|15|50|D8|E4,http://qr.liantu.com/api.php?text=3F|5F|7A|13|4E|FC|97|F6|12|49|4C|2D|0C|1F|1B|89|06|1F|D1|1B|75|1A|53|17|26|2E|FF|CA|3C|CF|4E|14|E4|25|A1|83|6D|79|8A|58,http://qr.liantu.com/api.php?text=44|01|1B|E6|2F|E2|31|44|06|EF|FE|89|A1|DC|81|89|E5|70|44|37|67|E8|D5|8C|61|A3|7A|43|D2|69|B7|BA|B0|69|44|81|B3|C0|38|2A,http://qr.liantu.com/api.php?text=FD|8B|83|10|65|EB|FB|72|12|6C|5B|F8|FB|C6|9C|CC|B6|7B|99|8B|39|50|CA|44|55|17|9C|64|FF|29|FA|3A|5E|7A|97|6B|60|C8|B3|2C,http://qr.liantu.com/api.php?text=27|B5|A4|C0|A5|86|FD|58|85|E4|4D|50|50|62|05|30|44|99|00|14|5A|A3|5B|71|2A|8A|40|C4|D8|FC|FD|24|A1|F3|1E|34|A3|CA|A3|56,http://qr.liantu.com/api.php?text=D8|C3|E6|83|51|E8|02|9B|1E|76|64|60|AF|5B|CC|DF|6F|AF|7C|CC|FC|49|E6|FE|0B|66|E8|8F|4F|A5|62|FC|19|88|AE|8F|A2|8F|D8|BA,http://qr.liantu.com/api.php?text=95|7B|E5|AB|81|CD|77|85|05|4E|AF|0C|2A|41|A0|48|46|75|DC|E2|4D|41|1E|CE|3D|5C|2B|B7|A9|89|85|19|E1|21|84|20|61|73|8B|8D,http://qr.liantu.com/api.php?text=60|EB|13|A1|E8|F9|7E|F1|30|7B|35|E1|3D|CC|AD|74|EE|AA|17|23|9C|5E|36|95|62|C0|2B|B5|F7|86|00|F9|E5|B2|F5|98|25|1B|32|1A,http://qr.liantu.com/api.php?text=F4|EE|BB|F0|99|F6|F0|38|44|3E|63|D9|84|03|F2|41|44|A5|4C|E8|FC|C1|D7|00|FD|12|C2|8E|96|BE|7D|62|A0|2D|0A|CC|0C|ED|6F|86,http://qr.liantu.com/api.php?text=03|AB|C7|53|E8|7D|2E|C6|15|3E|E2|E8|A0|8E|45|6C|6F|FF|50|6F|BD|6A|C4|40|39|96|B6|4E|40|E7|A0|32|C8|82|86|0B|44|23|87|4E,http://qr.liantu.com/api.php?text=A9|91|C5|73|CA|77|55|53|82|08|3C|4D|68|68|AB|9B|88|4F|79|08|B8|93|05|5D|FA|99|A7|4A|B4|5B|D6|45|68|6F|8F|0F|02|68|B9|FF,http://qr.liantu.com/api.php?text=05|F0|65|C3|C7|69|B7|89|B4|B6|5E|90|D9|DB|73|1E|1B|E8|B0|AD|26|19|7E|87|48|CE|3F|60|15|7D|22|D4|70|EC|E5|60|B0|2E|E1|31,http://qr.liantu.com/api.php?text=54|3E|BA|CD|9F|DB|A7|51|10|D9|BF|ED|02|88|E7|C8|7E|E2|74|84|3E|F6|24|C5|FD|02|DE|48|C5|8C|57|EE|C3|52|4F|76|3B|36|ED|68,http://qr.liantu.com/api.php?text=A0|C4|C4|ED|C9|86|6C|E5|1C|69|8E|F5|5E|7A|EB|78|6D|66|70|7C|40|C7|C4|48|41|C9|5B|81|71|E8|D1|D3|0C|D2|EE|2E|2E|6A|4C|92,http://qr.liantu.com/api.php?text=D1|23|05|40|96|0F|C4|6D|D1|CD|B0|30|4A|98|67|20|C1|16|B0|5F|6B|34|44|39|47|76|36|57|49|77|E4|8B|7D|12|BA|09|AE|F9|2A|CB,http://qr.liantu.com/api.php?text=4F|63|77|91|66|C2|7D|72|38|E5|03|49|9A|F6|01|E0|A4|24|B3|E2|3B|9B|9D|EB|5A|1C|E0|B0|0D|80|96|06|C4|A8|8C|71|6A|68|E7|F3,http://qr.liantu.com/api.php?text=32|CC|64|55|A9|84|04|BF|C8|38|18|E6|7B|12|E8|FF|40|9F|EB|F9|F0|70|B1|35|7A|E0|F8|59|B5|7A|C1|05|AB|1C|5D|04|56|8D|53|1B,http://qr.liantu.com/api.php?text=F7|81|28|C6|46|28|01|38|CC|33|25|2C|B7|E7|72|3B|A4|91|BA|BD|05|17|0C|4D|4F|18|35|93|AE|5B|26|CA|DD|0D|2D|B8|2F|BA|7B|7F,http://qr.liantu.com/api.php?text=24|6E|4B|94|32|91|23|16|28|50|D4|41|DC|0C|D1|08|1D|66|15|53|13|FA|74|CC|4B|06|41|54|6F|0B|28|61|55|CA|7C|F4|3B|9E|82|F3,http://qr.liantu.com/api.php?text=2F|8E|BA|98|BD|EB|5C|72|87|40|4F|59|4F|66|1E|F6|10|72|75|CD|6D|E8|BD|95|2E|64|74|FE|4A|E2|CD|0E|E1|64|11|0E|45|CB|F4|A9,http://qr.liantu.com/api.php?text=4B|39|93|17|57|F9|7D|C6|F6|DD|ED|9F|F4|CA|E1|FE|21|8A|1D|F2|12|68|9C|48|2F|AA|FA|3E|84|5E|E8|5F|A0|55|FE|05|D9|BA|73|9A,http://qr.liantu.com/api.php?text=E5|5D|1D|21|CF|C0|1A|07|48|BB|3A|D1|FC|7B|72|F5|04|95|5F|EA|50|96|90|D9|BD|AD|00|5B|00|49|B6|3E|D6|5A|F5|DC|BF|E9|BC|38,http://qr.liantu.com/api.php?text=7C|EC|42|A4|B6|45|35|3F|81|01|E6|21|8B|7A|66|0A|B5|52|9E|AB|B4|46|7B|F3|96|20|28|70|73|B4|FF|B5|69|56|5A|2A|1D|91|93|2D,http://qr.liantu.com/api.php?text=12|39|F7|EC|98|BA|10|08|A7|5D|A0|46|E0|42|72|9E|A4|65|C3|68|9D|8D|69|34|CA|8F|C7|7C|31|BB|E4|05|5E|FA|73|20|CD|28|55|7C,http://qr.liantu.com/api.php?text=D5|CD|EE|7C|36|39|29|55|9E|FA|DC|5C|44|50|44|2A|8A|E1|74|F9|F3|81|D4|EF|D9|A8|63|6D|1B|1E|B3|EB|EC|17|DB|42|8B|D3|3C|1E,http://qr.liantu.com/api.php?text=3A|A7|D1|DA|D3|88|E0|13|C7|56|B5|AA|A2|05|F1|8F|EE|8B|B1|DD|BA|22|B2|8D|90|B0|56|31|66|26|E6|29|92|6C|6D|F7|9F|1F|17|7A,http://qr.liantu.com/api.php?text=20|B1|23|A0|EA|69|BF|1C|DA|69|C4|96|21|4C|2F|5F|BA|0D|18|82|35|83|35|51|9F|C7|EA|F8|DB|D1|AE|10|6E|8C|1F|18|55|FC|93|0D,http://qr.liantu.com/api.php?text=22|10|5A|F7|0B|B7|2D|BC|31|29|56|C2|2E|D3|B8|75|16|09|DA|44|9F|3E|39|69|43|59|9C|64|EC|B6|23|2D|8F|3A|E5|D5|2E|FE|6D|34,http://qr.liantu.com/api.php?text=C1|31|C1|C2|E9|64|0B|C1|08|C6|AE|14|AE|A1|8D|F6|CE|BA|18|1A|0B|71|5A|A1|0D|F2|61|15|7B|8D|17|4B|23|5E|79|B7|F2|69|DA|9C,http://qr.liantu.com/api.php?text=44|AA|52|D5|74|CB|DE|C4|85|79|C3|18|F1|FD|D0|EF|ED|2B|5C|25|A0|13|2D|91|A0|3F|21|0F|E2|DF|CC|DC|2B|D4|D8|27|EB|AF|BD|7B,http://qr.liantu.com/api.php?text=92|71|A3|2F|7F|09|2E|F4|A5|D9|AE|B4|1C|71|DC|F2|8E|E2|67|4E|DB|74|4A|9B|20|75|25|90|AD|86|6D|25|BB|72|81|26|89|DA|75|3A,http://qr.liantu.com/api.php?text=90|7B|D1|5E|61|57|72|CE|C3|A7|73|E5|C6|6D|14|DD|9F|18|61|94|6D|DD|94|C0|B8|3D|88|A3|4C|74|B8|FA|E3|64|14|6C|D5|F7|AF|10,http://qr.liantu.com/api.php?text=7A|87|4E|25|3F|86|78|E8|7E|01|65|0E|89|B5|A3|19|A7|DB|37|6C|03|2A|87|75|1A|E8|CF|76|AB|39|3A|28|37|DA|1A|14|65|1F|62|29,http://qr.liantu.com/api.php?text=21|07|00|63|D9|B8|65|45|4F|C3|B4|56|B6|C0|68|6A|F5|8F|1C|E6|82|C3|CD|4B|C8|94|F3|4A|60|B2|A2|98|71|3C|A6|E8|8E|F6|14|8F,http://qr.liantu.com/api.php?text=F6|0E|48|75|13|53|D3|23|9F|A2|4F|D2|EC|BC|FE|22|9D|CA|B7|65|70|F8|24|03|FE|56|59|E4|C2|22|87|7F|1F|2F|D4|EB|15|50|D8|E4,http://qr.liantu.com/api.php?text=79|3D|86|95|46|69|47|D5|25|DC|B3|AD|B1|D1|50|C6|FF|3A|4E|50|70|AC|46|88|9B|AC|C6|9F|F8|41|87|46|7D|1B|BC|5E|25|55|29|E6,http://qr.liantu.com/api.php?text=B2|F3|26|E4|16|78|CF|09|09|C2|3A|2A|0C|57|74|1A|07|AF|92|7D|C7|38|48|06|A8|91|50|B4|4C|AB|C7|22|64|26|D2|0F|66|2B|8C|31,http://qr.liantu.com/api.php?text=F4|59|BD|49|93|1A|8C|42|74|A9|74|61|D2|F9|52|EC|EC|8D|32|7B|1F|D9|C3|E1|28|A0|43|52|97|80|A7|69|7C|A8|30|90|AF|CF|21|65,http://qr.liantu.com/api.php?text=B6|BD|1A|06|DC|BA|86|02|F0|72|77|0A|2E|52|C0|4B|19|EC|1D|83|23|20|ED|89|A0|30|70|80|D4|D7|35|07|6F|19|68|59|C3|5C|62|83,http://qr.liantu.com/api.php?text=16|AE|ED|C9|3C|44|1D|7E|8E|AE|7B|D2|FA|AF|FA|41|67|E5|A9|1E|A6|B1|0B|56|15|46|91|DC|65|A3|6D|16|AB|79|D4|02|AD|23|37|F2,http://qr.liantu.com/api.php?text=90|EC|E7|AE|DC|2B|3D|82|F2|EC|94|71|0E|85|66|EC|C2|45|DC|C7|94|1F|09|A1|9C|20|A1|81|95|F0|9C|C7|0F|87|96|D6|3D|16|8E|93,http://qr.liantu.com/api.php?text=A3|D4|57|B3|E3|8B|EB|A0|97|37|6A|A6|1C|62|C8|F7|F6|4F|23|00|04|C3|D6|C6|C1|35|24|CF|82|34|F7|54|5D|9C|D1|0F|A1|30|81|76,http://qr.liantu.com/api.php?text=FA|AD|53|18|FF|BA|32|FB|A6|38|93|67|C9|C2|D2|7F|8D|31|EC|DA|8C|73|E6|13|05|35|A7|E1|77|A6|E0|2D|C8|EE|C5|24|5F|45|AF|74,http://qr.liantu.com/api.php?text=E5|0E|03|FE|C2|BF|49|76|C9|BB|1D|4B|01|E8|AD|73|68|C4|57|73|9A|92|D8|EC|88|5E|A7|88|2C|2F|3A|8B|CD|EE|E3|EB|38|6E|02|2D,http://qr.liantu.com/api.php?text=24|7F|08|92|A1|D3|7F|95|15|C4|44|E5|6D|CC|80|86|94|FA|AD|FF|4A|79|10|C9|4C|A0|F9|E4|1B|C3|25|FC|42|A2|BA|FE|AE|15|9A|29,http://qr.liantu.com/api.php?text=2D|5E|06|12|89|A8|BC|27|2B|07|C3|DE|0E|CD|1F|70|C2|38|C9|FC|1D|92|0B|53|55|0A|01|FA|4B|28|43|BA|2F|4F|6F|86|DE|B1|74|5F,http://qr.liantu.com/api.php?text=8A|4A|72|75|55|FA|C5|28|DA|28|55|49|92|1F|99|E6|EC|D3|48|F6|11|71|6F|69|0D|9B|0A|C0|BA|19|F0|D8|BD|AC|86|14|03|00|66|6B,http://qr.liantu.com/api.php?text=6A|C6|59|41|EA|6D|E2|B6|56|C0|A0|AC|B2|18|F2|66|0C|FB|04|F3|16|8B|C6|92|A0|88|4F|97|35|94|8A|27|52|19|49|CD|F2|7A|1D|DE,http://qr.liantu.com/api.php?text=40|96|BE|DF|8D|CF|84|3C|4E|5F|51|62|A9|AB|7B|E1|CF|17|20|D3|7D|B7|86|46|8F|28|AF|C8|0D|90|EB|BF|65|FD|68|C4|A6|BD|82|D0,http://qr.liantu.com/api.php?text=27|40|56|96|4F|0A|C7|3B|61|EF|33|AA|40|35|52|73|01|FB|61|91|10|D2|31|92|AB|04|C4|E4|31|F9|F3|13|62|B3|6D|1F|63|98|22|29,http://qr.liantu.com/api.php?text=4B|2B|33|1C|6A|8B|17|05|7A|6E|E0|57|A1|42|95|D5|8D|5E|EB|D3|C8|05|D9|F7|6D|DA|F5|A4|F4|44|F4|9C|CE|2D|3D|B3|8A|BF|A5|00,http://qr.liantu.com/api.php?text=C4|7D|FB|44|A0|C2|A5|F9|C1|F0|5C|AD|15|93|A6|74|2B|84|AD|A8|6E|B2|5B|2E|E5|F9|1F|8A|EA|09|A2|01|16|43|46|17|B9|A6|D4|73,http://qr.liantu.com/api.php?text=06|A0|89|2E|A3|E2|13|55|5D|06|6A|0F|56|06|CA|F0|59|E7|E7|5F|0D|A2|0B|1E|CF|A6|4F|4C|11|D2|61|3C|51|00|51|44|47|81|AB|32,http://qr.liantu.com/api.php?text=FC|0B|F9|D4|21|5B|1A|17|06|46|8D|A3|48|6F|6B|58|CF|20|D0|C2|80|D3|3D|6D|EF|80|03|0D|4F|16|5E|9F|6B|22|EE|6A|99|EF|4A|F1,http://qr.liantu.com/api.php?text=4C|BE|9B|51|A6|32|C5|EE|13|3C|3F|F9|93|19|97|17|BF|B9|E7|D0|B5|D8|2F|D8|FF|5A|1A|63|AB|EF|0D|2A|F9|69|6F|1B|D7|B5|05|1D,http://qr.liantu.com/api.php?text=40|4F|BE|51|2B|0A|53|5C|CB|63|E0|78|0F|F8|65|9B|5A|AA|26|E1|3E|99|0C|A4|0C|4F|9E|89|48|8B|05|F4|9A|C7|39|4D|B3|C3|03|DD,http://qr.liantu.com/api.php?text=B5|8C|B0|A7|04|E7|C7|C0|A1|84|D1|64|36|34|07|20|54|46|32|5F|DA|A3|D2|43|E8|97|EA|F7|12|FF|A8|55|98|3D|0C|72|5A|13|7F|09,http://qr.liantu.com/api.php?text=B4|03|8A|7D|53|3F|4E|E6|74|7E|8E|82|ED|F8|B5|82|B9|B8|59|89|78|58|80|5C|77|3E|01|54|27|BA|EA|25|F9|0B|5D|A8|C3|29|64|8D,http://qr.liantu.com/api.php?text=70|E1|D4|B8|8E|D5|6F|FF|43|C6|F7|62|B3|B1|04|B5|5F|74|F9|4C|54|B3|24|11|24|68|1B|20|55|B1|FE|CD|EE|43|2A|7C|19|8B|B4|A2,http://qr.liantu.com/api.php?text=7B|B6|12|57|2E|9A|6D|76|5E|FC|50|C5|C6|B4|9E|FA|EA|FB|13|23|3B|04|3F|D4|36|B9|40|FE|3A|20|1F|8E|6D|35|4F|F2|A4|84|2D|5D,http://qr.liantu.com/api.php?text=A9|1B|F6|77|7C|39|CB|95|C2|2B|91|C7|A8|57|07|54|5E|26|05|C5|F8|ED|0B|46|F3|F6|AA|DD|C8|55|8A|CE|B3|1C|AC|A6|48|A7|D1|C7,http://qr.liantu.com/api.php?text=7D|42|D5|95|98|D6|8C|29|F7|00|9F|BB|59|F4|45|FE|68|63|B6|15|F3|5F|77|F6|D8|A3|3F|E6|46|59|92|33|5A|24|FB|1C|71|41|4F|FF,http://qr.liantu.com/api.php?text=42|48|60|12|46|16|F1|70|7E|AA|63|87|A8|2B|8F|6F|A0|0D|0D|A0|39|62|06|5B|5C|F3|0B|7B|B1|00|28|9F|E2|69|C4|A6|0A|6B|92|F3,http://qr.liantu.com/api.php?text=56|0D|E8|43|F9|40|3B|B1|A8|BE|74|66|B4|60|B3|F8|68|C9|75|9E|2A|9C|EA|E3|01|33|0E|18|A2|FA|06|48|B4|1D|4E|BC|02|01|AC|9F,http://qr.liantu.com/api.php?text=C8|3A|1A|12|48|21|DF|64|F4|18|5A|6D|E1|6A|9D|7B|1E|3B|7A|30|70|A7|DB|64|17|2F|C5|2E|03|04|40|42|93|81|02|C3|47|3E|6B|D2,http://qr.liantu.com/api.php?text=BF|A3|98|AB|5B|B8|A6|7E|20|8E|F6|A3|1C|67|98|58|49|A1|4C|58|33|8C|C1|E1|99|AD|65|21|AA|4A|79|CA|89|41|02|23|3D|8F|E3|CA,http://qr.liantu.com/api.php?text=48|2E|FF|10|49|0D|1D|27|33|43|09|06|92|DB|A8|E7|EF|AC|C2|B9|F3|B0|79|AA|A2|54|A0|32|D0|6D|1A|70|17|CC|1B|C3|3D|F3|8C|4E,http://qr.liantu.com/api.php?text=FF|9B|81|6D|29|D3|6C|F8|7A|62|88|4C|19|0C|85|62|87|1D|B1|74|00|B1|FB|B7|AC|5F|4F|A7|66|E1|39|9E|7D|C8|45|6F|24|D0|0B|1E,http://qr.liantu.com/api.php?text=60|6B|FD|4D|47|4D|30|19|FF|F1|82|0B|40|37|AC|C7|5F|81|12|4E|DD|EC|55|3C|14|D9|C2|FD|08|BC|F2|DD|FC|3B|09|44|C4|F1|59|CC,";
		String[] qrcodes = qrcodeStr.split(",");
		System.out.println("length: "+qrcodes.length);
		StringBuilder sb= new StringBuilder();
		sb.append("<table border='1'>");
		for(int i=1;i<=qrcodes.length;i++){
			sb.append("<tr><td>ID "+i+"</td><td><img src='"+qrcodes[i-1]+"'/></td></tr>");
		}
		sb.append("</table>");
		System.out.println(sb);
	}
	
//	public static void main(String[] args) {
//		QrcodeController instance = new QrcodeController();
//		instance.qrcodePublish(100);
//	}
}
