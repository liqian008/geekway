//****************************************************************
//* 名　　称：isEmpty
//* 功    能：判断是否为空
//* 入口参数：fData：要检查的数据
//* 出口参数：True：空                              
//*           False：非空
//*****************************************************************
function isEmpty(fData) {
	return ((fData == null) || (fData.length == 0))
}

//****************************************************************
//* 名　　称：IsDigit
//* 功    能：判断是否为数字
//* 入口参数：fData：要检查的数据
//* 出口参数：True：是0到9的数字                              
//*           False：不是0到9的数字 
//*****************************************************************
function isDigit(fData) {
	return ((fData >= "0") && (fData <= "9"))
}

//****************************************************************
//* 名　　称：IsInteger
//* 功    能：判断是否为正整数
//* 入口参数：fData：要检查的数据
//* 出口参数：True：是整数，或者数据是空的                            
//*           False：不是整数
//*****************************************************************
function isInteger(fData) {
	//如果为空，返回true
	if (isEmpty(fData))
		return true
	if ((isNaN(fData)) || (fData.indexOf(".") != -1)
			|| (fData.indexOf("-") != -1))
		return false

	return true
}

//****************************************************************
//* 名　　称：isEmail
//* 功    能：判断是否为正确的Email地址
//* 入口参数：fData：要检查的数据
//* 出口参数：True：正确的Email地址，或者空                              
//*           False：错误的Email地址
//*****************************************************************
function isEmail(fData) {
	if (isEmpty(fData))
		return true
	if (fData.indexOf("@") == -1)
		return false
	var NameList = fData.split("@");
	if (NameList.length != 2)
		return false
	if (NameList[0].length < 1)
		return false
	if (NameList[1].indexOf(".") <= 0)
		return false
	if (fData.indexOf("@") > fData.indexOf("."))
		return false
	if (fData.indexOf(".") == fData.length - 1)
		return false

	return true
}

//****************************************************************
//* 名　　称：IsPhone
//* 功    能：判断是否为正确的电话号码（可以含"()"、"（）"、"+"、"-"和空格）
//* 入口参数：fData：要检查的数据
//* 出口参数：True：正确的电话号码，或者空                              
//*           False：错误的电话号码
//* 错误信息：
//*****************************************************************
function isPhone(fData) {
	var str;
	var fDatastr = "";
	if (isEmpty(fData))
		return true
	for ( var i = 0; i < fData.length; i++) {
		str = fData.substring(i, i + 1);
		if (str != "(" && str != ")" && str != "（" && str != "）" && str != "+"
				&& str != "-" && str != " ")
			fDatastr = fDatastr + str;
	}
	//alert(fDatastr);  
	if (isNaN(fDatastr))
		return false
	return true
}

//****************************************************************
//* 名　　称：IsPlusNumeric
//* 功    能：判断是否为正确的正数（可以含小数部分）
//* 入口参数：fData：要检查的数据
//* 出口参数：True：正确的正数，或者空                              
//*           False：错误的正数
//* 错误信息：
//*****************************************************************
function isPlusNumeric(fData) {
	if (isEmpty(fData))
		return true
	if ((isNaN(fData)) || (fData.indexOf("-") != -1))
		return false
	return true
}

//****************************************************************
//* 名　　称：IsNumeric
//* 功    能：判断是否为正确的数字（可以为负数，小数）
//* 入口参数：fData：要检查的数据
//* 出口参数：True：正确的数字，或者空                              
//*           False：错误的数字
//* 错误信息：
//*****************************************************************
function isNumeric(fData) {
	if (isEmpty(fData))
		return true
	if (isNaN(fData))
		return false

	return true
}

//****************************************************************
//* 名　　称：IsIntegerInRange
//* 功    能：判断一个数字是否在指定的范围内
//* 入口参数：fInput：要检查的数据
//*           fLower：检查的范围下限，如果没有下限，请用null
//*           fHigh：检查的上限，如果没有上限，请用null
//* 出口参数：True：在指定的范围内                              
//*           False：超出指定范围
//*****************************************************************
function isIntegerInRange(fInput, fLower, fHigh) {
	if (fLower == null)
		return (fInput <= fHigh)
	else if (fHigh == null)
		return (fInput >= fLower)
	else
		return ((fInput >= fLower) && (fInput <= fHigh))
}

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
	try {
		m += s1.split(".")[1].length
	} catch (e) {
	}
	try {
		m += s2.split(".")[1].length
	} catch (e) {
	}
	return Number(s1.replace(".", "")) * Number(s2.replace(".", ""))
			/ Math.pow(10, m)
}

//加法函数，用来得到精确的加法结果     
//说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。     
//调用：accAdd(arg1,arg2)     
//返回值：arg1加上arg2的精确结果   
function accAdd(arg1, arg2) {
	var r1, r2, m, c;
	try {
		r1 = arg1.toString().split(".")[1].length
	} catch (e) {
		r1 = 0
	}
	try {
		r2 = arg2.toString().split(".")[1].length
	} catch (e) {
		r2 = 0
	}
	c = Math.abs(r1 - r2);
	m = Math.pow(10, Math.max(r1, r2))
	if (c > 0) {
		var cm = Math.pow(10, c);
		if (r1 > r2) {
			arg1 = Number(arg1.toString().replace(".", ""));
			arg2 = Number(arg2.toString().replace(".", "")) * cm;
		} else {
			arg1 = Number(arg1.toString().replace(".", "")) * cm;
			arg2 = Number(arg2.toString().replace(".", ""));
		}
	} else {
		arg1 = Number(arg1.toString().replace(".", ""));
		arg2 = Number(arg2.toString().replace(".", ""));
	}
	return (arg1 + arg2) / m
}

//给Number类型增加一个add方法，调用起来更加方便。  
Number.prototype.add = function(arg) {
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