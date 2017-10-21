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
			c.setChoiceA("哈哈哈" + i);
			c.setChoiceB("呵呵呵" + i);
			c.setChoiceC("好好好" + i);
			c.setChoiceD("哼哼哼" + i);
			c.setQuestionText("question" + i);
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
		result = choiceService.queryQuestionsByIds(ids);
		System.out.println("over3");
	}
	
	@Test
	public void testDelete() {
		List<Choice> result = null;
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(2);
		choiceService.deleteQuestionsByIds(ids);
		System.out.println("over4");
	}
	
	@Test
	public void testUpdate() {
		List<Choice> result = choiceService.queryAllQuestions();
		Choice c = result.get(0);
		c.setQuestionText("question-update");
		ResultData resultd = choiceService.addQuestion(c);
		System.out.println("over5");
	}

}
