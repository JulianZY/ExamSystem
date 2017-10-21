/**
 * 
 */
package com.test.testsys.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.test.testsys.dao.ChoiceDao;
import com.test.testsys.dto.ChoiceResultDto;
import com.test.testsys.entity.Choice;
import com.test.testsys.entity.ResultData;
import com.test.testsys.service.IChoiceService;
import com.test.testsys.util.ChoiceStateEnum;

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
	public ResultData addQuestion(Choice c){
		ResultData result = new ResultData();
		try {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			if(c != null && c.getId() == null) { // 添加题目
				c.setUuid(UUID.randomUUID().toString());
				c.setCreateTime(now);
				c.setUpdateTime(now);
				choiceDao.save(c);
				result.setMsg("save success");
			} else if( c != null && c.getId() != null) { // 修改题目
				c.setUpdateTime(now);
				choiceDao.update(c);
				result.setMsg("update success");
			} else {
				result.setCode("-1");
				result.setMsg("object is null");
			}
		} catch(Exception e) {
			e.printStackTrace();
			result.setCode("-1");
			result.setMsg("add or update failed");
		}
		return result;
	}

	@Override
	public ResultData deleteQuestionsByIds(List<Integer> ids) {
		ResultData result = new ResultData();
		try {
			if( ids != null && !ids.isEmpty()) { // 删除题目
				choiceDao.deleteByIds(ids);
				result.setMsg("delete success");
			} else {
				result.setCode("-1");
				result.setMsg("ids is null");
			}
		} catch(Exception e) {
			e.printStackTrace();
			result.setCode("-1");
			result.setMsg("delete failed");
		}
		return result;
	}
	
	@Override
	public ResultData deleteQuestionsByUuids(List<String> uuids) {
		ResultData result = new ResultData();
		try {
			if( uuids != null && !uuids.isEmpty()) { // 删除题目
				choiceDao.deleteByUuids(uuids);
				result.setMsg("delete success");
			} else {
				result.setCode("-1");
				result.setMsg("uuids is null");
			}
		} catch(Exception e) {
			e.printStackTrace();
			result.setCode("-1");
			result.setMsg("delete failed");
		}
		return result;
	}

	@Override
	public List<Choice> queryQuestionsByIds(List<Integer> ids) {
		List<Choice> result = null;
		try {
			if( ids != null && !ids.isEmpty()) { // 删除题目
				result = choiceDao.queryByIds(ids);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public List<Choice> queryQuestionsByUuids(List<String> uuids) {
		List<Choice> result = null;
		try {
			if( uuids != null && !uuids.isEmpty()) { // 删除题目
				result = choiceDao.queryByUuids(uuids);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Choice> queryAllQuestions() {
		List<Choice> result = null;
		try {
			result = choiceDao.queryAll();				
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Choice> queryRandomQuestions(int num) {
		List<Choice> result = null;
		try {
			List<String> uuidList = choiceDao.queryAllUuids();
			int capSize = uuidList.size();
			if(uuidList == null || capSize < num) {
				throw new Exception("题库为空或题库数目不足");
			}
			boolean capFlag = false; // num 大小为题库总数的一半以上,采用剔除策略,默认不采用剔除策略
			if(num > capSize/2) {
				capFlag = true;
			}
			Set<Integer> indexSet = new HashSet<Integer>(); //不重复的下标列表
			List<String> selectUuidList = new ArrayList<String>();
			int index = 0;
			if(!capFlag) { //不采用剔除策略
				while(indexSet.size() < num) { // 生成题号随机编码
					index = (int)(Math.random()*capSize);
					if(index < capSize) {
						indexSet.add(index);
					}
				}
				for(Integer i : indexSet) {			
					selectUuidList.add(uuidList.get(i));
				}
			} else { //采用剔除策略,indexSet存储不选定的index
				while(indexSet.size() <= capSize - num) {
					index = (int)(Math.random()*capSize);
					if(index < capSize) {
						indexSet.add(index);
					}
				}
				for(int i = 0;i < capSize;++i) {
					if(!indexSet.contains(i)) { 
						selectUuidList.add(uuidList.get(i));
					}
				}
			}
			//根据选定的uuid列表获取返回题目列表
			result = choiceDao.queryByUuids(selectUuidList);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<ChoiceResultDto> generateReturn(List<Choice> choices, boolean isFilterAnswer) {
		List<ChoiceResultDto> result = null;
		try {
			if(choices != null && choices.size() > 0) {
				result = new ArrayList<ChoiceResultDto>();
				ChoiceResultDto tempChoice = null;
				for(Choice c : choices) { 
					tempChoice = transChoiceResultDto(c,null,isFilterAnswer);
					result.add(tempChoice);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ChoiceResultDto transChoiceResultDto(Choice c, ChoiceStateEnum choiceState, boolean isFilterAnswer) {
		ChoiceResultDto result = null;
		try {
			result = new ChoiceResultDto();
			result.setUuid(c.getUuid());
			result.setChoiceA(c.getChoiceA());
			result.setChoiceB(c.getChoiceB());
			result.setChoiceC(c.getChoiceC());
			result.setChoiceD(c.getChoiceD());
			result.setQuestionText(c.getQuestionText());
			if(choiceState == null) {
				result.setState(ChoiceStateEnum.UNKNOWN.getStateCode());
			} else {
				result.setState(choiceState.getStateCode());
			}
			if(!isFilterAnswer) {
				result.setRightAnswer(c.getRightAnswer());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
