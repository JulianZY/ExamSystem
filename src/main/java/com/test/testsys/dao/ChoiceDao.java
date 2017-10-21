package com.test.testsys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.test.testsys.entity.Choice;
/**
 * @author Julia
 * 选择题数据连接对象
 * */
@Repository("choiceDao")
public interface ChoiceDao {
	
	/**
	 * 存储操作
	 * */
	public void save(Choice t);
	
	/**
	 * 更新操作
	 * */
	public void update(Choice t);
	
	/**
	 * 批量删除操作
	 * */
	public void deleteByIds(List<Integer> ids);
	
	/**
	 * 批量查询操作
	 * */
	public List<Choice> queryByIds(List<Integer> ids);
	
	/**
	 * 全查询操作
	 * */
	public List<Choice> queryAll();
}
