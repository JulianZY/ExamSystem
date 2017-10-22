/**
 * 
 */
package com.test.testsys.service;

import java.util.List;
import java.util.Map;

import com.test.testsys.dto.ChoiceResultDto;
import com.test.testsys.dto.ChoiceStateDto;
import com.test.testsys.entity.Choice;
import com.test.testsys.entity.ResultData;

/**
 * 选择题业务操作接口
 * @author Julia
 *
 */
public interface IChoiceService {
	
	/**
	 * 添加或更新题目
	 * */
	public ResultData addQuestion(Choice c) ; 
	
	/**
	 * 删除选定的题目
	 * */
	public ResultData deleteQuestionsByIds(List<Integer> ids) ; 
	
	/**
	 * 删除选定的题目(根据uuid)
	 * */
	public ResultData deleteQuestionsByUuids(List<String> uuids);
	
	/**
	 * 根据id查询题目
	 * */
	public List<Choice> queryQuestionsByIds(List<Integer> ids) ;
	
	/**
	 * 根据uuid查询题目
	 * */
	public List<Choice> queryQuestionsByUuids(List<String> uuids) ;
	
	/**
	 * 查询全部题目
	 * */
	public List<Choice> queryAllQuestions() ;
	
	/**
	 * 随机生成指定数目的题目
	 * */
	public List<Choice> queryRandomQuestions(int num) ;	

	/**
	 * 过滤掉返回列表的answer项
	 * */
	public List<ChoiceResultDto> generateReturn(List<Choice> choices,boolean isFilterAnswer);
	
	/**
	 * 转换ChoiceResultDto对象
	 * */
	public ChoiceResultDto transChoiceResultDto(Choice c, boolean isFilterAnswer);
	
	/**
	 * 判定答题状态
	 * @param inputList 考生提交列表
	 * @param configMap 存储总分等对象，不为空
	 * */
	public List<ChoiceStateDto> judgeResult(List<ChoiceStateDto> inputList,Map<String,Object> configMap);
}
