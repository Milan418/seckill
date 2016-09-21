package com.milan.exception;
/**
 * 重复秒杀异常(运行时异常)
 * @author Milan.Ma
 *
 */
public class RepeatKillException extends SecKillException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1005855592278979149L;

	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepeatKillException(String message) {
		super(message);
	}
}
