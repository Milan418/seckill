package com.milan.exception;

/**
 * 秒杀关闭异常
 * @author Milan.Ma
 *
 */
public class SecKillCloseException extends SecKillException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 485317023382104350L;

	public SecKillCloseException(String message, Throwable cause) {
		super(message, cause);
	}

	public SecKillCloseException(String message) {
		super(message);
	}
}
