package com.bruce.geekway.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.bruce.geekway.model.WxUser;
import com.bruce.geekway.service.IWxUserService;

@RunWith(SpringJUnit4ClassRunner.class)// 使用junit4进行测试
@ContextConfiguration({ "/spring/*.xml" }) // 加载配置文件
public class AccessTokenTest {

	@Resource // 自动注入,默认按名称
	private IWxUserService wxUserService;

	@Test // 标明是测试方法
	@Transactional // 标明此方法需使用事务
	@Rollback(false) // 标明使用完此方法后事务不回滚,true时为回滚
	public void testInsertUserAssign() {
		List<WxUser> userList = wxUserService.queryAll();
		Assert.notEmpty(userList);
	}

}
