package com.hh.sukku.common.beans;

import java.time.LocalDateTime;

import org.slf4j.MDC;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hh.sukku.common.util.Constants;

/**
 * 
 * @author arun.sudhakaran
 *
 * 02-Apr-2023 10:18:46 pm
 */

public class Response {

	private String transactionId;
	
	private String orderId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.PAYLOAD_DATE_FORMAT_WITH_TIME)
	private LocalDateTime timestamp;
	
	private String statusCode;
	
	private String message;
	
	private Response() {
		timestamp = LocalDateTime.now();
		transactionId = MDC.get(Constants.TXN_ID);
		orderId =  MDC.get(Constants.ORDER_ID);
	}
	
	public Response(String statusCode, String message) {
		this();
		this.statusCode = statusCode;
		this.message = message;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
