package com.bruce.geekway.dao.impl.klh;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.klh.IKlhWallImageDao;
import com.bruce.geekway.dao.mapper.KlhWallImageMapper;
import com.bruce.geekway.data.PagingData;
import com.bruce.geekway.model.KlhWallImage;
import com.bruce.geekway.model.KlhWallImageCriteria;


@Repository
public class KlhWallImageDaoImpl implements IKlhWallImageDao, InitializingBean {

     @Autowired
    private KlhWallImageMapper klhWallImageMapper;

    @Override
    public int save(KlhWallImage t) {
        return klhWallImageMapper.insert(t);
    }

    @Override
    public int updateById(KlhWallImage t) {
        return klhWallImageMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return klhWallImageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public KlhWallImage loadById(Integer id) {
        return klhWallImageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<KlhWallImage> queryAll() {
        return klhWallImageMapper.selectByExample(null);
    }

    @Override
    public List<KlhWallImage> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    
//    @Override
//	public List<KlhWallImage> queryLatestImages(int pageSize) {
//    	KlhWallImageCriteria criteria = new KlhWallImageCriteria();
//    	criteria.createCriteria().andStatusEqualTo((short)1);
//    	criteria.setOrderByClause(" id desc");
//    	return klhWallImageMapper.selectByExample(criteria);
//	}
//
//	@Override
//	public List<KlhWallImage> queryHotestImages(int pageSize) {
//		KlhWallImageCriteria criteria = new KlhWallImageCriteria();
//    	criteria.createCriteria().andStatusEqualTo((short)1);
//    	criteria.setOrderByClause(" like_count desc");
//    	return klhWallImageMapper.selectByExample(criteria);
//	}
    
	
//	/**
//	 * 瀑布流方式加载更多
//	 */
//	@Override
//	public List<KlhWallImage> fallLoadLatestImages(int imageTailId, int limit) {
//		KlhWallImageCriteria criteria = new KlhWallImageCriteria();
//		KlhWallImageCriteria.Criteria subCriteria = criteria.createCriteria();
//		subCriteria.andStatusEqualTo((short)1);
//		if(imageTailId>0){
//			subCriteria.andIdLessThan(imageTailId);
//		}
//		criteria.setLimit(limit);
//	    criteria.setOrderByClause("id desc");
//        List<KlhWallImage> albumList = klhWallImageMapper.selectByExample(criteria);
//        return albumList;
//	}
	
	
	
	/**
	 * 分页展示列表
	 */
	@Override
    public PagingData<KlhWallImage> pagingLatestImages(int pageNo, int pageSize){
        if(pageNo<0) pageNo = 1;
        int start = (pageNo-1) * pageSize;
        KlhWallImageCriteria criteria = new KlhWallImageCriteria();
        criteria.createCriteria();
        criteria.setStart(start);
        criteria.setLimit(pageSize);
        criteria.setOrderByClause("id desc");
        List<KlhWallImage> dataList = klhWallImageMapper.selectByExample(criteria); 
        int totalCount = klhWallImageMapper.countByExample(criteria);//总条数
        com.bruce.geekway.data.PagingData<KlhWallImage> pagingData = new PagingData<KlhWallImage>(dataList, totalCount, pageNo, pageSize);
        return pagingData;
    }
	
	/**
	 * 分页展示列表
	 */
	@Override
    public PagingData<KlhWallImage> pagingHotestImages(int pageNo, int pageSize){
        if(pageNo<0) pageNo = 1;
        int start = (pageNo-1) * pageSize;
        KlhWallImageCriteria criteria = new KlhWallImageCriteria();
        criteria.createCriteria();
        criteria.setStart(start);
        criteria.setLimit(pageSize);
        criteria.setOrderByClause("like_count desc");
        List<KlhWallImage> dataList = klhWallImageMapper.selectByExample(criteria); 
        int totalCount = klhWallImageMapper.countByExample(criteria);//总条数
        com.bruce.geekway.data.PagingData<KlhWallImage> pagingData = new PagingData<KlhWallImage>(dataList, totalCount, pageNo, pageSize);
        return pagingData;
    }
	

    @Override
    public void afterPropertiesSet() throws Exception {

    }
    
    public KlhWallImageMapper getKlhWallImageMapper() {
		return klhWallImageMapper;
	}

	public void setKlhWallImageMapper(KlhWallImageMapper klhWallImageMapper) {
		this.klhWallImageMapper = klhWallImageMapper;
	}


	

}
