package net.fuzui.model;

/**
 * 错误码
 * @author Ze.Wang
 * @date 2020-11-04 00:07:41
 */
public enum ErrorCodeEnum {
	/**
     * 00000：正常；
     */
    SUCCESS("00000", "Success"),
    FAILURE("A0001", "Failure"),
    ERROR_CONNFIG("A0002", "缺少配置"),
    NOT_EXPRESS_COMPANY_NO("A0003", "快递公司编码不能为空"),
    NOT_TEL("A0004", "顺丰快递必填手机号"),
    NO_AUTH("A0005", "无权访问"),
    NOT_EXPRESS_NO("A0006", "单号为空"),
    EXPRESS_NO_NOT_EXIST("A0007", "单号不存在");

    private String code;
    private String msg;
    
    
	private ErrorCodeEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public String getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
    
}
