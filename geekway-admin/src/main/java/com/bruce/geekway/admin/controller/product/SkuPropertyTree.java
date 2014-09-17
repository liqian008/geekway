package com.bruce.geekway.admin.controller.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkuPropertyTree {

	private List<String> skuPropertyList;
	
	private SkuPropertyTree child;
	
	public SkuPropertyTree(List<String> skuPropertyList){
		this.skuPropertyList = skuPropertyList;
	}

	public void addChildToTail(SkuPropertyTree child){
		//判断child对象是否合法
		if(child!=null&&child.skuPropertyList!=null&&child.skuPropertyList.size()>0){
			//递归添加子节点，加到树的最底
			if(this.child!=null){
				this.child.addChildToTail(child);
			}else{
				this.child = child;
			}
		}
	}
	
	/**
	 * 递归构造skuProperties，自身的skuPropery + 子skuPropery
	 * @param node
	 * @return
	 */
	public static List<String> recursiveBuildSkuProperties(SkuPropertyTree node){
		if(node.childEmpty()){
			return node.skuPropertyList;
		}else{
			List<String> tempList = new ArrayList<String>();
			for(String skuProperty: node.skuPropertyList){
				for(String childSkuProperty: recursiveBuildSkuProperties(node.child)){
					tempList.add(skuProperty + childSkuProperty);
				}
			}
			return tempList;
		}
	}
	
	public boolean childEmpty(){
		return child==null||child.skuPropertyList==null||child.skuPropertyList.size()==0;
	}
	
	
	public static void main(String[] args) {
		SkuPropertyTree xx = new SkuPropertyTree(Arrays.asList("1:1;","1:2;"));
		SkuPropertyTree yy = new SkuPropertyTree(Arrays.asList("2:11;","2:12;", "2:13;"));
		SkuPropertyTree zz = new SkuPropertyTree(Arrays.asList("3:111;","3:112;"));
		
		xx.addChildToTail(yy);
		xx.addChildToTail(zz);
		System.out.println(xx);
		
		System.out.println(SkuPropertyTree.recursiveBuildSkuProperties(xx));
	}
	
}
