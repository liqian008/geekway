package com.alipay.util;

/**
 * for ios test 
 * @author liqian
 *
 */
public class WidthTest {

	/**
	 * 计算左右各item的宽度
	 * @param leftItemCount
	 * @param rightItemCount
	 * @param totalWidth
	 * @return
	 */
	public static double[] calcItemsWidth(int leftItemCount, int rightItemCount, int totalWidth) {
		// 左右侧栏数据均不为0情况下
		if (leftItemCount > 0 || rightItemCount > 0) {
			
			double[] itemWidthArray = new double[leftItemCount+rightItemCount];
			
			// 比较左右侧栏的数量
			if (leftItemCount == rightItemCount) {//如果两侧数量
				int leftTotalWidth =  (totalWidth/2);
				//计算最小item的宽度
				double perCeilWidth = leftTotalWidth/(Math.pow(2, leftItemCount)-1);
				//构造左侧各item的宽度（宽度自小至大，比例为1:2:4）
				for(int i=0;i<leftItemCount;i++){
					itemWidthArray[i] = Math.pow(2, i)*perCeilWidth;
				}
				//构造右侧各item的宽度（宽度自大至小，比例为4:2:1）
				for(int i=0;i<rightItemCount;i++){
					itemWidthArray[leftItemCount+i] = Math.pow(2, (rightItemCount-1-i))*perCeilWidth;
				}
				return itemWidthArray;
			}else if (leftItemCount > rightItemCount) {//左侧条目多于右侧
				
				//计算左侧所占宽度比重
				int leftWidthWeight = (int) Math.pow(2, leftItemCount)-1;
				//计算右侧所占宽度比重
				int rightWidthWeight = ((int) Math.pow(2, leftItemCount)-1)- ((int) Math.pow(2, leftItemCount-rightItemCount)-1);
				
				//计算左侧需要分配的总宽度
				double leftTotalWidth =  (totalWidth*leftWidthWeight)/(leftWidthWeight+rightWidthWeight);
				//计算最小item的宽度
				double perCeilWidth = leftTotalWidth/(Math.pow(2, leftItemCount)-1);
				//构造左侧各item的宽度（宽度自小至大，比例为1:2:4）
				for(int i=0;i<leftItemCount;i++){
					itemWidthArray[i] = Math.pow(2, i)*perCeilWidth;
				}
				//构造右侧各item的宽度（宽度自大至小，比例为4:2）
				for(int i=0;i<rightItemCount;i++){
					itemWidthArray[leftItemCount+i] = Math.pow(2, ((leftItemCount-1-i)))*perCeilWidth;
				}
				return itemWidthArray; 
			}else if (leftItemCount < rightItemCount) {//右侧条目多于左侧
				
				//计算左侧所占宽度比重
				int leftWidthWeight = ((int) Math.pow(2, rightItemCount)-1)- ((int) Math.pow(2, rightItemCount-leftItemCount)-1);
				//计算右侧所占宽度比重
				int rightWidthWeight =  (int) Math.pow(2, rightItemCount)-1;
				
				//计算左侧需要分配的总宽度
				double rightTotalWidth =  (totalWidth*rightWidthWeight)/(leftWidthWeight+rightWidthWeight);
				//计算最小item的宽度
				double perCeilWidth = rightTotalWidth/(Math.pow(2, rightItemCount)-1);
				//构造左侧各item的宽度（宽度自小至大，比例为2:4）
				for(int i=0;i<leftItemCount;i++){
//					itemWidthArray[i] = Math.pow(2, i)*perCeilWidth;
					itemWidthArray[i] = Math.pow(2, ((rightItemCount-leftItemCount)+i))*perCeilWidth;
				}
				//构造右侧各item的宽度（宽度自大至小，比例为4:2:1）
				for(int i=0;i<rightItemCount;i++){
					itemWidthArray[leftItemCount+i] = Math.pow(2, rightItemCount-1-i)*perCeilWidth;
				}
				return itemWidthArray; 
			}
		}else{//左右item个数均为0的情况
			//请自行处理该部分逻辑
		}
		
		return null;
	}
	

	public static void main(String[] args) {
//		double a = log(4, 2);
		double a = Math.pow(2, 3);
//		System.out.println(a);

		double[] widths = calcItemsWidth(0,5, 478);
		if(widths!=null){
			for(double width: widths){
				System.out.println(width);
			}
		}
		
	}
}
