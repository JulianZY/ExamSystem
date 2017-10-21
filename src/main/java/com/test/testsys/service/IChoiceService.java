/**
 * 
 */
package com.test.testsys.service;

import java.util.List;

import com.test.testsys.entity.Choice;

/**
 * 选择题业务操作接口
 * @author Julia
 *
 */
public interface IChoiceService {
	
	/**
	 * 添加或更新题目
	 * */
	public void addQuestion(Choice c); 
	
	/**
	 * 删除选定的题目
	 * */
	public void deleteQuestionsByIds(List<Integer> ids); 
	
	/**
	 * 根据id查询题目
	 * */
	public List<Choice> queryQuestionsByIds(List<Integer> ids);
	
	/**
	 * 查询全部题目
	 * */
	public List<Choice> queryAllQuestions();
	
	/**
	 * 随机生成指定数目的题目
	 * */
	public List<Choice> queryRandomQuestions(int num);
	
}
