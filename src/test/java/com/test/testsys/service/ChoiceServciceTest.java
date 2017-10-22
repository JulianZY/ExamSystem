package com.test.testsys.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.druid.support.json.JSONUtils;
import com.test.testsys.entity.Choice;
import com.test.testsys.entity.ResultData;
import com.test.testsys.util.ChoiceSelectEnum;
import com.test.testsys.util.OperateUtils;
import com.test.testsys.util.PropertiesUtil;
@RunWith(SpringJUnit4ClassRunner.class)   
@ContextConfiguration(locations = {"classpath*:spring-servlet.xml","classpath*:applicationContext.xml"})
public class ChoiceServciceTest {

	private Logger log = LoggerFactory.getLogger(ChoiceServciceTest.class);
	
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
		int round = 50;
		int size = 10;
		Random r = new Random(System.currentTimeMillis());
		Map<Integer,String> selectMap = new HashMap<Integer,String>();
		selectMap.put(ChoiceSelectEnum.A.getSelectCode(), ChoiceSelectEnum.A.getSelectValue());
		selectMap.put(ChoiceSelectEnum.B.getSelectCode(), ChoiceSelectEnum.B.getSelectValue());
		selectMap.put(ChoiceSelectEnum.C.getSelectCode(), ChoiceSelectEnum.C.getSelectValue());
		selectMap.put(ChoiceSelectEnum.D.getSelectCode(), ChoiceSelectEnum.D.getSelectValue());
		for(int j = 0;j < round;j++) {
			for(int i = 0;i < size;i++) {
				c = new Choice();
				c.setChoiceA(j + "-哈哈哈-" + i + i);
				c.setChoiceB(j + "-呵呵呵-" + i + i);
				c.setChoiceC(j + "-好好好-" + i + i);
				c.setChoiceD(j + "哼哼哼" + i + i);
				c.setQuestionText(j + "+++++++++++++++++============字符测试=====================-question-======================================+++++++++++++++++++++++" + i + i);
				int index = r.nextInt(100)%4 + 1;
				c.setRightAnswer(selectMap.get(index));
				result = choiceService.addQuestion(c);
				System.out.println(result.getCode() + "," + result.getMsg());
			}		
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
		result = choiceService.queryRandomQuestions(5);
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
	
	@Test
	public void testProperty() {
		log.info("log test info");
		log.debug("log test debug");
		log.error("log test error");
		log.warn("log test warn");
		String result = PropertiesUtil.getProperty("totalTime");
		System.out.println(result);
		System.out.println("over6");
	}
	
	@Test
	public void testRandom() {
		Integer num = 400;
		Integer maxNum = 1000;
		List<Integer> randomList = OperateUtils.generateRandomArray(num, maxNum);
		if(randomList != null && randomList.size() > 0) {
			System.out.println(JSONUtils.toJSONString(randomList));
		}
		System.out.println("over7");
	}

}
