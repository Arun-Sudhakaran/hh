package com.hh.sukku.common.exception;

import com.hh.sukku.common.util.ErrorCodes;

/**
 * 
 * @author arun.sudhakaran
 *
 * 04-Apr-2023 10:07:23 pm
 */

public class CommonException extends RuntimeException {

	private static final long serialVersionUID = -857968536935667808L;

	private String statusCode;

	public String getStatusCode() {
		return statusCode;
	}

	public CommonException() {
		super();
	}

	public CommonException(String msg) {
		super(msg);
		this.statusCode = ErrorCodes.COMMON_ERROR;
	}
	
	public CommonException(String msg, String statusCode) {
		super(msg);
		this.statusCode = (statusCode);
	}

}
