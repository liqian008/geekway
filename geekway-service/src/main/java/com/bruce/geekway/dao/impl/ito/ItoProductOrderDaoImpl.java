package com.bruce.geekway.dao.impl.ito;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.ito.IItoProductOrderDao;
import com.bruce.geekway.dao.mapper.ItoProductOrderMapper;
import com.bruce.geekway.model.ItoProductOrder;
import com.bruce.geekway.model.ItoProductOrder;
import com.bruce.geekway.model.ItoProductOrderCriteria;


@Repository
public class ItoProductOrderDaoImpl implements IItoProductOrderDao, InitializingBean {

     @Autowired
    private ItoProductOrderMapper itoProductOrderMapper;

    @Override
    public int save(ItoProductOrder t) {
        return itoProductOrderMapper.insert(t);
    }

    @Override
    public int updateById(ItoProductOrder t) {
//        return itoProductOrderMapper.updateByPrimaryKey(t);
    	return itoProductOrderMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return itoProductOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ItoProductOrder loadById(Integer id) {
        return itoProductOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ItoProductOrder> queryAll() {
        return itoProductOrderMapper.selectByExample(null);
    }

    @Override
    public List<ItoProductOrder> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    
    

    @Override
   	public ItoProductOrder loadByOrderSn(String orderSn) {
       	ItoProductOrderCriteria criteria = new ItoProductOrderCriteria();
   		criteria.createCriteria().andOrderSnEqualTo(orderSn);
   		List<ItoProductOrder> orderList = itoProductOrderMapper.selectByExample(criteria);
   		if(orderList!=null&&orderList.size()==1){
   			return orderList.get(0);
   		}
   		return null;
   	}

    @Override
   	public ItoProductOrder loadByOrderSn(String orderSn, short payType) {
       	ItoProductOrderCriteria criteria = new ItoProductOrderCriteria();
   		criteria.createCriteria().andOrderSnEqualTo(orderSn).andPayTypeEqualTo(payType);
   		List<ItoProductOrder> orderList = itoProductOrderMapper.selectByExample(criteria);
   		if(orderList!=null&&orderList.size()==1){
   			return orderList.get(0);
   		}
   		return null;
   	}

//	@Override
//	public int changeOrderStatus(ItoProductOrder order) {
//		//TODO 
//		return 0;
//	}
    
    

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public ItoProductOrderMapper getItoProductOrderMapper() {
		return itoProductOrderMapper;
	}

	public void setItoProductOrderMapper(ItoProductOrderMapper itoProductOrderMapper) {
		this.itoProductOrderMapper = itoProductOrderMapper;
	}


	

}
