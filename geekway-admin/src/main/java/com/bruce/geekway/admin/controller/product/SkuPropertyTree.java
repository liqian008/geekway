package com.bruce.geekway.admin.controller.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * sku的递归算法tree，含递归算法
 * @author liqian
 *
 */
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
	
	
	

	//计算对应的sku矩阵， sku的递归算法，千万保留
//	if(productSkuValueList!=null&&productSkuValueList.size()>0){
//		//map的key为skuPropId, value为选定的skuPropValueList
//		HashMap<Integer, List<WxSkuPropValue>> skuPropGroupMap = new HashMap<Integer, List<WxSkuPropValue>>();
//		for(WxSkuPropValue skuPropValue: productSkuValueList){
//			int skuPropId = skuPropValue.getSkuPropId();
//			
//			List<WxSkuPropValue> skuGroupList = skuPropGroupMap.get(skuPropId);
//			if(skuGroupList==null || skuGroupList.size()<=0){//不存在则初始化（默认值1）
//				skuGroupList = new ArrayList<WxSkuPropValue>();
//				skuPropGroupMap.put(skuPropId, skuGroupList);
//			} 
//			skuGroupList.add(skuPropValue);
//		}
//		
//		if(skuPropGroupMap!=null&&skuPropGroupMap.size()>0){
//			SkuPropertyTree root = null;
//			//遍历map，以构造tree
//			for(Entry<Integer, List<WxSkuPropValue>> skuPropEntry: skuPropGroupMap.entrySet()){
//				Integer skuPropId = skuPropEntry.getKey();
//				List<WxSkuPropValue> skuPropValueList = skuPropEntry.getValue();
//				if(skuPropValueList!=null&&skuPropValueList.size()>0){
//					List<String> skuPropertyList = new ArrayList<String>();
//					for(WxSkuPropValue skuPropValue: skuPropValueList){
//						skuPropertyList.add(getSkuCode(skuPropId, skuPropValue.getId()));
//					}
//					SkuPropertyTree tempNode = new SkuPropertyTree(skuPropertyList);
//					if(root==null){//第一次遍历
//						root = tempNode;
//					}else{//添加子，构造一棵父子树，供递归遍历生成skuProperties
//						root.addChildToTail(tempNode);
//					}
//				}
//			}
//			
//			List<String> skuPropertiesNameList = SkuPropertyTree.recursiveBuildSkuProperties(root);
//			
//			//根据商品的sku配置，生成sku数据，填写价格
//			//创建新商品sku数据
//			for(String skuPropertiesName: skuPropertiesNameList){
//				WxProductSku wxProductSku = new WxProductSku();
//				wxProductSku.setProductId(productId);
//				//设置各sku的缩略图
//				wxProductSku.setSkuPicUrl(product.getProductPicUrl());
//				wxProductSku.setSkuThumbPicUrl(product.getProductThumbPicUrl());
//				
//				wxProductSku.setOriginPrice(product.getOriginPrice());
//				wxProductSku.setPrice(product.getPrice());
//				wxProductSku.setNum(0);
//				
//				wxProductSku.setPropertiesName(skuPropertiesName);
//				
//				wxProductSku.setCreateTime(currentTime);
//				//保存sku
//				wxProductSkuService.save(wxProductSku);
//			}
//		}
//	}
	
}
