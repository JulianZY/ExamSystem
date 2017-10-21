/**
 * 
 */
package com.test.testsys.util;

/**
 * 答题状况枚举类
 * @author Julia
 *
 */
public enum ChoiceStateEnum {
	
	UNKNOWN(-1,"未答"),
	WRONG(0,"错误"),
	RIGHT(1,"正确");
	
	private int stateCode;
	private String stateMessage;
	
	private ChoiceStateEnum(int stateCode, String stateMessage) {
		this.stateCode = stateCode;
		this.stateMessage = stateMessage;
	}
	public int getStateCode() {
		return stateCode;
	}
	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}
	public String getStateMessage() {
		return stateMessage;
	}
	public void setStateMessage(String stateMessage) {
		this.stateMessage = stateMessage;
	}
}
