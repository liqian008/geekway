package com.bruce.geekway.dao.impl.ito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.ito.IItoProductSkuValueDao;
import com.bruce.geekway.dao.mapper.ItoProductSkuValueMapper;
import com.bruce.geekway.dao.mapper.ItoSkuPropValueMapper;
import com.bruce.geekway.model.ItoProductSkuValue;
import com.bruce.geekway.model.ItoProductSkuValueCriteria;
import com.bruce.geekway.model.ItoSkuPropValue;
import com.bruce.geekway.model.ItoSkuPropValueCriteria;


@Repository
public class ItoProductSkuValueDaoImpl implements IItoProductSkuValueDao, InitializingBean {

    @Autowired
    private ItoProductSkuValueMapper itoProductSkuValueMapper;
    @Autowired
    private ItoSkuPropValueMapper itoSkuPropValueMapper;

    @Override
    public int save(ItoProductSkuValue t) {
        return itoProductSkuValueMapper.insert(t);
    }

    @Override
    public int updateById(ItoProductSkuValue t) {
        return itoProductSkuValueMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return itoProductSkuValueMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ItoProductSkuValue loadById(Integer id) {
        return itoProductSkuValueMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ItoProductSkuValue> queryAll() {
        return itoProductSkuValueMapper.selectByExample(null);
    }

    @Override
    public List<ItoProductSkuValue> fallLoadList(Integer tailId, int limit) {
        return null;
    }

    /**
     * 查询商品配置的sku信息
     */
	@Override
	public List<Integer> querySkuValueIdListByProductId(int productId) { 
		ItoProductSkuValueCriteria criteria = new ItoProductSkuValueCriteria();
		criteria.createCriteria().andProductIdEqualTo(productId);
		List<ItoProductSkuValue> valueList = itoProductSkuValueMapper.selectByExample(criteria);
		if(valueList!=null&&valueList.size()>0){
			List<Integer> valueIdList = new ArrayList<Integer>();
			for(ItoProductSkuValue productSkuValue: valueList){
				valueIdList.add(productSkuValue.getSkuPropValueId());
			}
			return valueIdList;
		}
		return null;
	}
	
	/**
	 * 
	 */
	@Override
	public List<ItoSkuPropValue> querySkuValueListByProductId(int productId) {
		List<Integer> valueIdList = querySkuValueIdListByProductId(productId);
		if(valueIdList!=null&&valueIdList.size()>0){
			ItoSkuPropValueCriteria criteria = new ItoSkuPropValueCriteria();
			criteria.createCriteria().andIdIn(valueIdList);
			return itoSkuPropValueMapper.selectByExample(criteria);
		}
		return null;
	}

	@Override
	public int deleteSkuValuesByProductId(int productId) {
		ItoProductSkuValueCriteria criteria = new ItoProductSkuValueCriteria();
		criteria.createCriteria().andProductIdEqualTo(productId);
		return itoProductSkuValueMapper.deleteByExample(criteria);
	}

	@Override
	public int saveProductSkuValues(int productId, List<Integer> productSkuValueIdList) {
		if(productId>0&&productSkuValueIdList.size()>0){
			Date currentTime = new Date();
			for(int skuValueId: productSkuValueIdList){
				ItoProductSkuValue productSkuValue = new ItoProductSkuValue();
				productSkuValue.setProductId(productId);
				productSkuValue.setSkuPropValueId(skuValueId);
				productSkuValue.setCreateTime(currentTime);
				productSkuValue.setUpdateTime(currentTime);
				itoProductSkuValueMapper.insert(productSkuValue);
			}
			return productSkuValueIdList.size();
		}
		return 0;
	}

    @Override
    public void afterPropertiesSet() throws Exception {

    }

	
}
