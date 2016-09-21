package com.milan.bean;

import java.util.Date;

/**
 * 秒杀实体类
 * @author Milan.Ma
 * @version 1.0
 * @since 2016-07-14
 */
public class SecKill {

	private long id;//ID
	private String goodName;//商品名称
	private int number;//库存
	private Date startTime;//开始时间
	private Date endTime;//结束时间
	private Date createTime;//创建时间
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Override
	public String toString() {
		return "SecKill [id=" + id + ", goodName=" + goodName + ", number=" + number + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", createTime=" + createTime + "]";
	}
	
}
