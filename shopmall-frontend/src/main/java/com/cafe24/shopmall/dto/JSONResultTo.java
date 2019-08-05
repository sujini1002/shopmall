package com.cafe24.shopmall.dto;


//보내는 용
public class JSONResultTo {
	private String result;
	private String message;
	private Object data;

	public static JSONResultTo success(Object data) {
		return new JSONResultTo("success", null, data);
	}

	public static JSONResultTo fail(String message) {
		return new JSONResultTo("fail", message, null);
	}

	private JSONResultTo(String result, String message, Object data) {
		this.result = result;
		this.message = message;
		this.data = data;
	}

	public String getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}
}

