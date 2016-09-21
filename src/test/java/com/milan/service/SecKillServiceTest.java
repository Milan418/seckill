package com.milan.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.milan.bean.SecKill;
import com.milan.dto.Exposer;
import com.milan.dto.SecKillExecution;
import com.milan.exception.RepeatKillException;
import com.milan.exception.SecKillCloseException;
import com.milan.exception.SecKillException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-jdbc.xml","classpath:spring/spring-service.xml"})
public class SecKillServiceTest {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SecKillService seckillService;

	@Test
	public void testGetSecKillList() {
		List<SecKill> list = seckillService.getSecKillList();
		logger.info("list={}",list);
	}

	@Test
	public void testGetById() {
		SecKill seckill = this.seckillService.getById(1000);
		logger.info("seckill={}",seckill);
	}

	@Test
	public void testSecKillLogic() {
		long seckillId = 1000L;
		long userPhone = 13288478662L;
		Exposer exposer = this.seckillService.exportSecKillUrl(seckillId);
		if(exposer!=null && exposer.isExposed()){
			logger.info("exposer={}",exposer);
			try {
				this.seckillService.execuetSeckill(seckillId, userPhone, exposer.getMd5());
			} catch (RepeatKillException e) {
				logger.error(e.getMessage());
			} catch (SecKillCloseException e2) {
				logger.error(e2.getMessage());
			} catch (SecKillException e3){
				logger.error(e3.getMessage());
			}
		}else{
			logger.info("exposer={}",exposer);
		}
	}

}
