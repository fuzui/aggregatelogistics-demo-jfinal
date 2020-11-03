package net.fuzui.model;

import java.io.Serializable;
/**
 * 响应结果
 * 
 * @author Ze.Wang
 * @date 2020-11-04 00:08:38
 */
public class Res<T> implements Serializable {
    private String code;

    private String msg;

    private T data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
	public Res() {
		super();
	}

	public Res(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	public Res(String code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	private static <T> Res<T> restResult(T data, String code, String msg) {
		Res<T> apiResult = new Res<>();
		apiResult.setCode(code);
		apiResult.setData(data);
		apiResult.setMsg(msg);
		return apiResult;
	}
	public static <T> Res<T> ok() {
		return restResult(null, ErrorCodeEnum.SUCCESS.getCode(), null);
	}

	public static <T> Res<T> ok(T data) {
		return restResult(data, ErrorCodeEnum.SUCCESS.getCode(), null);
	}

	public static <T> Res<T> ok(T data, String msg) {
		return restResult(data, ErrorCodeEnum.SUCCESS.getCode(), msg);
	}

	public static <T> Res<T> failed() {
		return restResult(null, ErrorCodeEnum.FAILURE.getCode(), null);
	}

	public static <T> Res<T> failed(String msg) {
		return restResult(null, ErrorCodeEnum.FAILURE.getCode(), msg);
	}

	public static <T> Res<T> failed(T data) {
		return restResult(data, ErrorCodeEnum.FAILURE.getCode(), null);
	}

	public static <T> Res<T> failed(T data, String msg) {
		return restResult(data, ErrorCodeEnum.FAILURE.getCode(), msg);
	}

	@Override
	public String toString() {
		return "R [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
}
