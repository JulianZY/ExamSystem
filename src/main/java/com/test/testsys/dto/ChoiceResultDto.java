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
	
	private Integer state; //答题状态 -1：未答,0：答错,1：答对

	public ChoiceResultDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChoiceResultDto(String uuid, String questionText, String choiceA, String choiceB, String choiceC,
			String choiceD, String rightAnswer) {
		super();
		this.uuid = uuid;
		this.questionText = questionText;
		this.choiceA = choiceA;
		this.choiceB = choiceB;
		this.choiceC = choiceC;
		this.choiceD = choiceD;
		this.rightAnswer = rightAnswer;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
}
