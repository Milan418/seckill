package com.milan.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.milan.bean.SecKill;
import com.milan.bean.SuccessKilled;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-jdbc.xml")
public class SuccessKilledDaoTest {
	
	@Resource
	private SuccessKilledDao successKilledDao;

	@Test
	public void testAddSuccessKilled() {
		long seckillid = 1001;
		long userphone = 13288478669L;
		successKilledDao.addSuccessKilled(seckillid, userphone);
	}

	@Test
	public void testGetEntityById() {
		long seckillid = 1001;
		long userphone = 13288478669L;
		SuccessKilled bean = successKilledDao.getEntityById(seckillid,userphone);
		SecKill seckill = bean.getSecKill();
		System.out.println(bean.getUserPhone()+"------------"+bean.getCreateTime().toString()+"---------"+seckill.getGoodName());
	}

}
