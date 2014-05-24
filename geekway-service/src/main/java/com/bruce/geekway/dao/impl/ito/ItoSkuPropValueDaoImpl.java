package com.bruce.geekway.dao.impl.ito;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.ito.IItoSkuPropValueDao;
import com.bruce.geekway.dao.mapper.ItoSkuPropValueMapper;
import com.bruce.geekway.model.ItoSkuPropValue;
import com.bruce.geekway.model.ItoSkuPropValueCriteria;
import com.bruce.geekway.model.WxCustomizeMenu;


@Repository
public class ItoSkuPropValueDaoImpl implements IItoSkuPropValueDao, InitializingBean {

     @Autowired
    private ItoSkuPropValueMapper itoSkuPropValueMapper;

    @Override
    public int save(ItoSkuPropValue t) {
        return itoSkuPropValueMapper.insert(t);
    }

    @Override
    public int updateById(ItoSkuPropValue t) {
        return itoSkuPropValueMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return itoSkuPropValueMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ItoSkuPropValue loadById(Integer id) {
        return itoSkuPropValueMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ItoSkuPropValue> queryAll() {
        return itoSkuPropValueMapper.selectByExample(null);
    }

    @Override
    public List<ItoSkuPropValue> fallLoadList(Integer tailId, int limit) {
        return null;
    }

//    public List<ItoSkuPropValue> queryCombiledSkuPropValueListByProductId(int productId){
//    	return itoSkuPropValueMapper.querySkuPropValueListByProductId(productId);
//    }
    
    public List<ItoSkuPropValue> querySortedSkuPropValues(){
    	ItoSkuPropValueCriteria criteria = new ItoSkuPropValueCriteria();
    	criteria.createCriteria();
    	criteria.setOrderByClause(" sku_prop_id, sort ");
    	return itoSkuPropValueMapper.selectByExample(criteria);
    }
    
    
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public ItoSkuPropValueMapper getItoSkuPropValueMapper() {
		return itoSkuPropValueMapper;
	}

	public void setItoSkuPropValueMapper(ItoSkuPropValueMapper itoSkuPropValueMapper) {
		this.itoSkuPropValueMapper = itoSkuPropValueMapper;
	}

	

}
