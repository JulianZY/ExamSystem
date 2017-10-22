/**
 * 
 */
package com.test.testsys.dto;

/**
 * 记录答题状态对象
 * @author Julia
 *
 */
public class ChoiceStateDto {
	
	private Integer quesNo; //题号
	
	private String uuid; // 题目唯一标识符
	
	private String rightAnswer; //正确答案
	
	private String selectChoice; // 考生选择
	
	private Integer state; //答题状态 ,-1 未回答,0 回答错误,1 回答正确

	public ChoiceStateDto() {
		super();
	}

	public ChoiceStateDto(Integer quesNo, String uuid, String rightAnswer, String selectChoice, Integer state) {
		super();
		this.quesNo = quesNo;
		this.uuid = uuid;
		this.rightAnswer = rightAnswer;
		this.selectChoice = selectChoice;
		this.state = state;
	}

	public Integer getQuesNo() {
		return quesNo;
	}

	public void setQuesNo(Integer quesNo) {
		this.quesNo = quesNo;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

	public String getSelectChoice() {
		return selectChoice;
	}

	public void setSelectChoice(String selectChoice) {
		this.selectChoice = selectChoice;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
}
