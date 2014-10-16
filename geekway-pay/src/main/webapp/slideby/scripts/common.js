//Javascript 格式化金额  s:传入的float数字 ，n:希望返回小数点几位
//格式化：  
function fmoney(s, n) {
	n = n > 0 && n <= 20 ? n : 2;
	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
	t = "";
	for (i = 0; i < l.length; i++) {
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	}
	return t.split("").reverse().join("") + "." + r;
}

//Javascript 格式化金额, 将金额格式的数字在返回成float型。rmoney(12,345.676)返回结果为：12345.676
//复原：  
function rmoney(s) {
	s = s + "";
	return parseFloat(s.replace(/[^\d\.-]/g, ""));
}



///带小数乘法运算  
///乘法函数，用来得到精确的乘法结果  
function accMul(arg1, arg2) {  
  var m = 0, s1 = arg1.toString(), s2 = arg2.toString();  
  try { m += s1.split(".")[1].length } catch (e) { }  
  try { m += s2.split(".")[1].length } catch (e) { }  
  return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m)  
}  

//加法函数，用来得到精确的加法结果     
//说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。     
//调用：accAdd(arg1,arg2)     
//返回值：arg1加上arg2的精确结果   
function accAdd(arg1, arg2) {
  var r1, r2, m, c;  
  try { r1 = arg1.toString().split(".")[1].length } catch (e) { r1 = 0 }  
  try { r2 = arg2.toString().split(".")[1].length } catch (e) { r2 = 0 }  
  c = Math.abs(r1 - r2);  
  m = Math.pow(10, Math.max(r1, r2))  
  if (c > 0) {  
      var cm = Math.pow(10, c);  
      if (r1 > r2) {  
          arg1 = Number(arg1.toString().replace(".", ""));  
          arg2 = Number(arg2.toString().replace(".", "")) * cm;  
      }  
      else {  
          arg1 = Number(arg1.toString().replace(".", "")) * cm;  
          arg2 = Number(arg2.toString().replace(".", ""));  
      }  
  }  
  else {  
      arg1 = Number(arg1.toString().replace(".", ""));  
      arg2 = Number(arg2.toString().replace(".", ""));  
  }  
  return (arg1 + arg2) / m  
}

//给Number类型增加一个add方法，调用起来更加方便。  
Number.prototype.add = function (arg) {  
  return accAdd(arg, this);  
}  

///向上取整,有小数就整数部分加1   
function mathceil(arg1) {  
  Math.ceil(arg1);  
}  
///向下取整,有小数就整数部分加1   
function mathfloor(arg1) {  
  Math.floor(arg1);  
}  
///四舍五入  
function mathround(arg1) {  
  Math.round(arg1);  
}  