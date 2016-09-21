package com.milan.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.milan.bean.SecKill;

public interface SecKillDao {

	/**
	 * 减库存
	 * @param seckillId
	 * @param killTime
	 * @return 影响行数
	 */
	int reduceNumber(@Param("seckillId")long seckillId,@Param("killTime")Date killTime);
	
	/**
	 * 根据seckillId 查询秒杀对象
	 * @param seckillId
	 * @return
	 */
	SecKill getEntityById(long seckillId);
	
	/**
	 * 分页获取秒杀对象
	 * @param start
	 * @param offset
	 * @return
	 */
	List<SecKill> getAll(@Param("start")int start,@Param("offset")int offset);
}
