package com.bruce.geekway.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.dao.mapper.WxProductTagMapper;
import com.bruce.geekway.model.WxProductTag;
import com.bruce.geekway.model.WxProductTagCriteria;
import com.bruce.geekway.service.product.IWxProductTagService;

@Service
public class WxProductTagServiceImpl implements IWxProductTagService {

	@Autowired
	private WxProductTagMapper wxProductTagMapper;

	@Override
	public int save(WxProductTag t) {
		return wxProductTagMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxProductTag t) {
		return wxProductTagMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxProductTag t, WxProductTagCriteria criteria) {
		return wxProductTagMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxProductTagMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxProductTagCriteria criteria) {
		return wxProductTagMapper.deleteByExample(criteria);
	}

	@Override
	public WxProductTag loadById(Integer id) {
		return wxProductTagMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxProductTag> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxProductTag> queryAll(String orderByClause) {
		WxProductTagCriteria criteria = new WxProductTagCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxProductTag> queryByCriteria(WxProductTagCriteria criteria) {
		return wxProductTagMapper.selectByExample(criteria);
	}

	@Override
	public List<WxProductTag> fallloadByCriteria(int pageSize,
			WxProductTagCriteria criteria) {
		return null;
	}

	@Override
	public PagingResult<WxProductTag> pagingByCriteria(int pageNo,
			int pageSize, WxProductTagCriteria criteria) {
		pageNo = pageNo <= 0 ? 1 : pageNo;// 确保pageNo合法
		pageSize = pageNo <= 0 ? ConstConfig.PAGE_SIZE_DEFAULT : pageSize;// 确保pageSize合法
		int offset = (pageNo - 1) * pageSize;

		// 构造查询条件
		if (criteria == null) {
			criteria = new WxProductTagCriteria();
		}

		criteria.setLimitOffset(offset);
		criteria.setLimitRows(pageSize);

		int count = wxProductTagMapper.countByExample(criteria);
		List<WxProductTag> dataList = wxProductTagMapper.selectByExample(criteria);
		// 返回分页数据
		return new PagingResult<WxProductTag>(pageNo, pageSize, count, dataList);
	}


	public WxProductTagMapper getWxProductTagMapper() {
		return wxProductTagMapper;
	}

	public void setWxProductTagMapper(WxProductTagMapper wxPayProductTagMapper) {
		this.wxProductTagMapper = wxPayProductTagMapper;
	}

}