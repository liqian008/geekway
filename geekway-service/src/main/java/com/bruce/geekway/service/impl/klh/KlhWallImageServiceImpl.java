package com.bruce.geekway.service.impl.klh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.klh.IKlhWallImageDao;
import com.bruce.geekway.data.PagingData;
import com.bruce.geekway.model.KlhWallImage;
import com.bruce.geekway.service.klh.IKlhWallImageService;

@Service
public class KlhWallImageServiceImpl implements IKlhWallImageService{
	
	@Autowired
	private IKlhWallImageDao klhWallImageDao;
	
	@Override
	public int save(KlhWallImage t) {
		return klhWallImageDao.save(t);
	}

	@Override
	public int updateById(KlhWallImage t) {
		return klhWallImageDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return klhWallImageDao.deleteById(id);
	}

	@Override
	public KlhWallImage loadById(Integer id) {
		return klhWallImageDao.loadById(id);
	}

	@Override
	public List<KlhWallImage> queryAll() {
		return klhWallImageDao.queryAll();
	}
	
	@Override
	public int increaseLike(int wallImageId) {
		KlhWallImage wallImage =klhWallImageDao.loadById(wallImageId);
		if(wallImage!=null){
			KlhWallImage updateWallImage = new KlhWallImage();
			updateWallImage.setId(wallImageId);
			updateWallImage.setLikeCount(wallImage.getLikeCount()+1);
			return klhWallImageDao.updateById(updateWallImage);
		}
		return 0;
	}
	
	
	

	public IKlhWallImageDao getKlhWallImageDao() {
		return klhWallImageDao;
	}

	public void setKlhWallImageDao(IKlhWallImageDao klhWallImageDao) {
		this.klhWallImageDao = klhWallImageDao;
	}

//	@Override
//	public List<KlhWallImage> queryLatestImages(int pageSize) {
//		return klhWallImageDao.queryLatestImages(pageSize);
//	}
//
//	@Override
//	public List<KlhWallImage> queryHotestImages(int pageSize) {
//		return klhWallImageDao.queryHotestImages(pageSize);
//	}
	
	public PagingData<KlhWallImage> pagingLatestImages(int pageNo, int pageSize){
		return klhWallImageDao.pagingLatestImages(pageNo, pageSize);
	}

	public PagingData<KlhWallImage> pagingHotestImages(int pageNo, int pageSize){
		return klhWallImageDao.pagingHotestImages(pageNo, pageSize);
	}


	
}