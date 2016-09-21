package com.milan.dto;

import com.milan.bean.SuccessKilled;
import com.milan.enums.SecKillStateEnum;

/**
 * 封装秒杀执行后结果
 * @author Milan.Ma
 *
 */
public class SecKillExecution {

	private long seckillId;
	
	private int state;//秒杀执行结果状态
	
	private String stateInfo;//状态表示
	
	private SuccessKilled successKilled;//秒杀成功对象
	
	public SecKillExecution(long seckillId, SecKillStateEnum stateEnum, SuccessKilled successKilled) {
		super();
		this.seckillId = seckillId;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.successKilled = successKilled;
	}
	
	public SecKillExecution(long seckillId, SecKillStateEnum stateEnum) {
		super();
		this.seckillId = seckillId;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}

	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}
}
