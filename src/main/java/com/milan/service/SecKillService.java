package com.milan.service;

import java.util.List;

import com.milan.bean.SecKill;
import com.milan.dto.Exposer;
import com.milan.dto.SecKillExecution;
import com.milan.exception.RepeatKillException;
import com.milan.exception.SecKillCloseException;
import com.milan.exception.SecKillException;

/**
 * 业务接口:站在"使用者"的角度设计接口
 * 三个方面:1.定义方法粒度；2.参数友好(不要太多参数，不要传任意参数)；3.返回类型友好(不要是任意的Map等)
 * @author Milan.Ma
 *
 */
public interface SecKillService {

	/**
	 * 查询所有秒杀记录
	 * @return
	 */
	List<SecKill> getSecKillList();
	
	/**
	 * 查询单个秒杀记录
	 * @param id
	 * @return
	 */
	SecKill getById(long id);
	
	/**
	 * 秒杀开启则输出秒杀接口地址，否则输出系统时间和秒杀时间
	 * @param seckillId
	 * @return
	 */
	Exposer exportSecKillUrl(long seckillId);
	
	/**
	 *  执行秒杀操作
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 * @return
	 * @throws SecKillException
	 * @throws RepeatKillException
	 * @throws SecKillCloseException
	 */
	SecKillExecution execuetSeckill(long seckillId,long userPhone,String md5) throws SecKillException,RepeatKillException,SecKillCloseException;
}
