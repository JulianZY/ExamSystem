/**
 * 
 */
package com.test.testsys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.test.testsys.dao.ChoiceDao;
import com.test.testsys.entity.Choice;
import com.test.testsys.service.IChoiceService;

/**
 * 选择题目业务层
 * @author Julia
 *
 */
@Service("choiceService")
public class ChoiceServiceImpl implements IChoiceService {
	
	@Resource
	ChoiceDao choiceDao;

	@Override
	public void addQuestion(Choice c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteQuestionsByIds(List<Integer> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Choice> queryQuestionsByIds(List<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Choice> queryAllQuestions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Choice> queryRandomQuestions(int num) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
