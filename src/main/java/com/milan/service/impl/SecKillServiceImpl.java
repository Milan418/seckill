package com.milan.service.impl;

import java.util.Date;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.milan.bean.SecKill;
import com.milan.bean.SuccessKilled;
import com.milan.dao.SecKillDao;
import com.milan.dao.SuccessKilledDao;
import com.milan.dto.Exposer;
import com.milan.dto.SecKillExecution;
import com.milan.enums.SecKillStateEnum;
import com.milan.exception.RepeatKillException;
import com.milan.exception.SecKillCloseException;
import com.milan.exception.SecKillException;
import com.milan.service.SecKillService;

@Service("seckillService")
public class SecKillServiceImpl implements SecKillService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SecKillDao seckillDao;
	
	@Autowired
	private SuccessKilledDao successKilledDao;
	
	private final String slat = "fdsljflksjfj@34e$$dfljdslfj1231&%!";

	public List<SecKill> getSecKillList() {
		return seckillDao.getAll(0, 4);
	}

	public SecKill getById(long id) {
		return seckillDao.getEntityById(id);
	}

	public Exposer exportSecKillUrl(long seckillId) {
		//获取秒杀对象
		SecKill seckill = seckillDao.getEntityById(seckillId);
		if(seckill==null){
			return new Exposer(false, seckillId);
		}
		long startTime = seckill.getStartTime().getTime();
		long endTime = seckill.getEndTime().getTime();
		long nowTime = new Date().getTime();
		//在秒杀前或秒杀结束后
		if(nowTime < startTime || nowTime > endTime){
			return new Exposer(false, seckillId, nowTime, startTime, endTime);
		}
		//转换为特定字符，不可逆
		String md5 = getMD5(seckillId);
		return new Exposer(true, md5, seckillId);
	}
	

	@Transactional
	public SecKillExecution execuetSeckill(long seckillId, long userPhone, String md5)
			throws SecKillException, RepeatKillException, SecKillCloseException {
		//先判断md5是否相等
		if(md5==null || !getMD5(seckillId).equals(md5)){
			throw new SecKillException("seckill data rewrite");
		}
		//执行秒杀逻辑:减库存，记录购买行为
		Date now = new Date();
		try {
			int updateCount = seckillDao.reduceNumber(seckillId, now);
			if(updateCount<=0){//减库存失败,now>秒杀结束时间
				throw new SecKillCloseException("秒杀结束!");
			}else{
				int insertCount = successKilledDao.addSuccessKilled(seckillId, userPhone);
				if(insertCount<=0){//记录购买行为失败，重复秒杀
					throw new RepeatKillException("重复秒杀!");
				}else{
					//秒杀成功
					SuccessKilled successKilled = successKilledDao.getEntityById(seckillId, userPhone);
					return new SecKillExecution(seckillId, SecKillStateEnum.SUCCESS, successKilled);
				}
			}
		} catch (SecKillCloseException e1) {
			throw e1;
		}catch (RepeatKillException e2) {
			throw e2;
		}catch (SecKillException e3) {
			throw e3;
		}catch (Exception e) {//除了以上异常外可能还有数据库连接异常等异常
			//将所有编译时异常转换为运行时异常
			throw new SecKillException("seckill inner error:"+e.getMessage());
		}
	}
	
	/**
	 * 生成MD5
	 * @param seckillId
	 * @return
	 */
	private String getMD5(long seckillId){
		String base = seckillId +"/"+slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

}
