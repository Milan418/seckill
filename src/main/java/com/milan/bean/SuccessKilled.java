package com.milan.bean;

import java.util.Date;

/**
 * 秒杀记录实体
 * @author Milan.Ma
 * @version 1.0
 * @since 2016-07-14
 */
public class SuccessKilled {

	private long seckillId;//秒杀商品ID
	private long userPhone;//用户手机
	private short state;//秒杀状态：-1(无效),0(成功),1(已付款),2(已发货)
	private Date createTime;//创建时间
	
	//多对一
	private SecKill secKill;//秒杀商品
	
	public long getSeckillId() {
		return seckillId;
	}
	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}
	public long getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(long userPhone) {
		this.userPhone = userPhone;
	}
	public short getState() {
		return state;
	}
	public void setState(short state) {
		this.state = state;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public SecKill getSecKill() {
		return secKill;
	}
	public void setSecKill(SecKill secKill) {
		this.secKill = secKill;
	}
	
}
