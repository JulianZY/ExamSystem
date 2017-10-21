package com.test.testsys.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.testsys.entity.Choice;
import com.test.testsys.entity.ResultData;
@RunWith(SpringJUnit4ClassRunner.class)   
@ContextConfiguration(locations = {"classpath*:spring-servlet.xml","classpath*:applicationContext.xml"})
public class ChoiceServciceTest {

	@Resource
	IChoiceService choiceService; 
	
	@Test
	public void testAddQuestion() {
		Choice c = new Choice();
		c.setChoiceA("哈哈哈");
		c.setChoiceB("呵呵呵");
		c.setChoiceC("好好好");
		c.setChoiceD("哼哼哼");
		c.setQuestionText("question1");
		c.setRightAnswer("A");
		ResultData result = choiceService.addQuestion(c);
		System.out.println("over1");
	}
	
	@Test
	public void testAddQuestion2() {
		Choice c = null;
		ResultData result = null;
		int size = 10;
		for(int i = 0;i < size;i++) {
			c = new Choice();
			c.setChoiceA("哈哈哈" + i + i);
			c.setChoiceB("呵呵呵" + i + i);
			c.setChoiceC("好好好" + i + i);
			c.setChoiceD("哼哼哼" + i + i);
			c.setQuestionText("question" + i + i);
			c.setRightAnswer("A");
			result = choiceService.addQuestion(c);
			System.out.println(result.getCode() + "," + result.getMsg());
		}		
		System.out.println("over2");
	}
	
	@Test
	public void testQueryById() {
		List<Choice> result = null;
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		List<String> uuids = new ArrayList<String>();
		uuids.add("307e2d56-4ca9-4fc2-a40f-eb2d23a7fabf");
		result = choiceService.queryQuestionsByUuids(uuids);
		System.out.println("over3");
	}
	
	@Test
	public void testDelete() {
		List<Choice> result = null;
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(4);
		List<String> uuids = new ArrayList<String>();
		uuids.add("003938ed-7bcb-4c5f-8e1f-ff9342ad4429");
		choiceService.deleteQuestionsByUuids(uuids);
		System.out.println("over4");
	}
	
	@Test
	public void testUpdate() {
		List<Choice> results = choiceService.queryAllQuestions();
		List<String> uuids = new ArrayList<String>();
		uuids.add("4369c098-efef-4959-abff-9e9991cc1fd8");
		List<Choice> result = choiceService.queryQuestionsByUuids(uuids);
		Choice c = result.get(0);
		c.setQuestionText("question-update222");
		ResultData resultd = choiceService.addQuestion(c);
		System.out.println("over5");
	}

}
