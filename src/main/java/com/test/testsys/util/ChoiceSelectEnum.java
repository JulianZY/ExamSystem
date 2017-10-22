/**
 * 
 */
package com.test.testsys.util;

/**
 * 答题选项枚举类
 * @author Julia
 *
 */
public enum ChoiceSelectEnum {

	A(1,"A"),
	B(2,"B"),
	C(3,"C"),
	D(4,"D");
	
	private Integer selectCode;
	
	private String selectValue;

	private ChoiceSelectEnum(Integer selectCode, String selectValue) {
		this.selectCode = selectCode;
		this.selectValue = selectValue;
	}

	public Integer getSelectCode() {
		return selectCode;
	}

	public void setSelectCode(Integer selectCode) {
		this.selectCode = selectCode;
	}

	public String getSelectValue() {
		return selectValue;
	}

	public void setSelectValue(String selectValue) {
		this.selectValue = selectValue;
	}
	
}
