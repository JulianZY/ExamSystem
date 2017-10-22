/**
 * 
 */
package com.test.testsys.dto;

/**
 * 记录题目基本信息及题目回答状态
 * @author Julia
 *
 */
public class ChoiceResultDto {
	
	private String uuid;
	
	private String questionText;
	
	private String choiceA;
	
	private String choiceB;
	
	private String choiceC;
	
	private String choiceD;
	
	private String rightAnswer;
	
	private Integer quesNo; //题号
	
	private String selectChoice = "";

	public ChoiceResultDto() {
		super();
	}	

	public ChoiceResultDto(String uuid, String questionText, String choiceA, String choiceB, String choiceC,
			String choiceD, String rightAnswer, Integer quesNo, String selectChoice) {
		super();
		this.uuid = uuid;
		this.questionText = questionText;
		this.choiceA = choiceA;
		this.choiceB = choiceB;
		this.choiceC = choiceC;
		this.choiceD = choiceD;
		this.rightAnswer = rightAnswer;
		this.quesNo = quesNo;
		this.selectChoice = selectChoice;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getChoiceA() {
		return choiceA;
	}

	public void setChoiceA(String choiceA) {
		this.choiceA = choiceA;
	}

	public String getChoiceB() {
		return choiceB;
	}

	public void setChoiceB(String choiceB) {
		this.choiceB = choiceB;
	}

	public String getChoiceC() {
		return choiceC;
	}

	public void setChoiceC(String choiceC) {
		this.choiceC = choiceC;
	}

	public String getChoiceD() {
		return choiceD;
	}

	public void setChoiceD(String choiceD) {
		this.choiceD = choiceD;
	}

	public String getRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

	public Integer getQuesNo() {
		return quesNo;
	}

	public void setQuesNo(Integer quesNo) {
		this.quesNo = quesNo;
	}

	public String getSelectChoice() {
		return selectChoice;
	}

	public void setSelectChoice(String selectChoice) {
		this.selectChoice = selectChoice;
	}
	
}
