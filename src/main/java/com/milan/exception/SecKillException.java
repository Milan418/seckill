package com.milan.exception;
/**
 * 秒杀异常
 * @author Milan.Ma
 *
 */
public class SecKillException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5907283142162505276L;

	public SecKillException(String message, Throwable cause) {
		super(message, cause);
	}

	public SecKillException(String message) {
		super(message);
	}
}
