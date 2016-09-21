package com.milan.dao;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.milan.bean.SecKill;

/**
 * 配置Spring和junit整合，junit启动时加载springIOC容器
 * spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-jdbc.xml")
public class SecKillDaoTest {
	
	@Resource
	private SecKillDao seckillDao;

	@Test
	public void testReduceNumber() {
		long id = 1000;
		System.out.println(seckillDao.reduceNumber(id, new Date()));
	}

	@Test
	public void testGetEntityById() {
		long id = 1000L;
		SecKill seckill = seckillDao.getEntityById(id);
		System.out.println(seckill.getGoodName()+"-----------------");
	}

	@Test
	public void testGetAll() {
		List<SecKill> list = seckillDao.getAll(0, 100);
		for(SecKill seckill:list){
			System.out.println(seckill.getGoodName()+"-----------------");
		}
	}

}
