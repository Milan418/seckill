package com.milan.dao;

import org.apache.ibatis.annotations.Param;

import com.milan.bean.SuccessKilled;

public interface SuccessKilledDao {

	/**
	 * 插入购买明细，可过滤重复
	 * @param seckillId
	 * @param userPhone
	 * @return 插入的行数
	 */
	int addSuccessKilled(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
	
	/**
	 * 根据seckillId查询SuccessKilled对象，并携带SecKill
	 * @param seckillId
	 * @return
	 */
	SuccessKilled getEntityById(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
}
