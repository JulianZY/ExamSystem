/**
 * 
 */
package com.test.testsys.entity;

import java.sql.Timestamp;

/**
 * 题目实体类
 * @author Julia
 */
public class Choice {
	
	private Integer id;
	
	private String uuid;
	
	private String questionText;
	
	private String choiceA;
	
	private String choiceB;
	
	private String choiceC;
	
	private String choiceD;
	
	private String rightAnswer;
	
	private Timestamp createTime;
	
	private Timestamp updateTime;

	public Choice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Choice(Integer id, String uuid, String questionText, String choiceA, String choiceB, String choiceC,
			String choiceD, String rightAnswer, Timestamp createTime, Timestamp updateTime) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.questionText = questionText;
		this.choiceA = choiceA;
		this.choiceB = choiceB;
		this.choiceC = choiceC;
		this.choiceD = choiceD;
		this.rightAnswer = rightAnswer;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
}
