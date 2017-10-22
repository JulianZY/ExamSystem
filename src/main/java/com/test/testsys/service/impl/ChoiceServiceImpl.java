/**
 * 
 */
package com.test.testsys.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.test.testsys.dao.ChoiceDao;
import com.test.testsys.dto.ChoiceResultDto;
import com.test.testsys.dto.ChoiceStateDto;
import com.test.testsys.entity.Choice;
import com.test.testsys.entity.ResultData;
import com.test.testsys.service.IChoiceService;
import com.test.testsys.util.ChoiceSelectEnum;
import com.test.testsys.util.ChoiceStateEnum;
import com.test.testsys.util.OperateUtils;

/**
 * 选择题目业务层
 * @author Julia
 *
 */
@Service("choiceService")
public class ChoiceServiceImpl implements IChoiceService {
	
	private Logger log = LoggerFactory.getLogger(ChoiceServiceImpl.class);
	
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
			log.error("add or update failed-log", e);
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
			log.error("delete failed-log", e);
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
			log.error("delete failed-log", e);
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
			log.error("qurey by ids failed-log", e);
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
			log.error("query by uuids failed-log", e);
		}
		return result;
	}

	@Override
	public List<Choice> queryAllQuestions() {
		List<Choice> result = null;
		try {
			result = choiceDao.queryAll();				
		} catch(Exception e) {
			log.error("query all failed-log", e);
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
			List<Integer> indexList = OperateUtils.generateRandomArray(num, capSize); //不重复的下标列表
			List<String> selectUuidList = new ArrayList<String>();
			for(Integer i : indexList) {			
				selectUuidList.add(uuidList.get(i));
			}
			//根据选定的uuid列表获取返回题目列表
			List<Choice> tempList = choiceDao.queryByUuids(selectUuidList);
			List<Integer> randomIndexList = OperateUtils.generateRandomArray(num, num);
			result = new ArrayList<Choice>();
			for(Integer index : randomIndexList) {
				result.add(tempList.get(index));
			}
		} catch(Exception e) {
			log.error("random generate failed-log," + e.getMessage(), e);
		}
		return result;
	}

	@Override
	public List<ChoiceResultDto> generateReturn(List<Choice> choices, boolean isFilterAnswer) {
		List<ChoiceResultDto> result = null;
		try {
			if(choices != null && choices.size() > 0) {
				result = new LinkedList<ChoiceResultDto>();
				ChoiceResultDto tempChoice = null;
				int quesNo = 1;
				for(Choice c : choices) { 
					tempChoice = transChoiceResultDto(c,isFilterAnswer);
					tempChoice.setQuesNo(quesNo++);
					result.add(tempChoice);
				}
			}
		} catch(Exception e) {
			log.error("trand choiceResultDtoList failed-log", e);
		}
		return result;
	}

	@Override
	public ChoiceResultDto transChoiceResultDto(Choice c, boolean isFilterAnswer) {
		ChoiceResultDto result = null;
		try {
			result = new ChoiceResultDto();
			result.setUuid(c.getUuid());
			result.setChoiceA(c.getChoiceA());
			result.setChoiceB(c.getChoiceB());
			result.setChoiceC(c.getChoiceC());
			result.setChoiceD(c.getChoiceD());
			result.setQuestionText(c.getQuestionText());
			if(!isFilterAnswer) {
				result.setRightAnswer(c.getRightAnswer());
			}
		} catch(Exception e) {
			log.error("transfer choiceResultDto failed-log", e);
		}
		return result;
	}

	@Override
	public List<ChoiceStateDto> judgeResult(List<ChoiceStateDto> inputList, Map<String, Object> configMap) {
		List<ChoiceStateDto> result = null;
		try {
			if(inputList != null && inputList.size() > 0) {
				//获取题目列表
				List<String> uuids = new ArrayList<String>();
				for(ChoiceStateDto csd : inputList) {
					uuids.add(csd.getUuid());
				}
				List<Choice> quesList = choiceDao.queryByUuids(uuids);
				if(quesList != null && quesList.size() > 0) {
					Map<String,Choice> quesMap = new HashMap<String,Choice>();
					for(Choice c : quesList) {
						quesMap.put(c.getUuid(), c);
					}
					result = new ArrayList<ChoiceStateDto>();
					ChoiceStateDto tempDto = null;
					int mark = 0; // 总分
					for(ChoiceStateDto csdo : inputList) {
						tempDto = new ChoiceStateDto();
						tempDto.setQuesNo(csdo.getQuesNo());
						tempDto.setSelectChoice(csdo.getSelectChoice());
						tempDto.setUuid(csdo.getUuid());
						tempDto.setRightAnswer(quesMap.get(csdo.getUuid()).getRightAnswer());
						if(tempDto.getRightAnswer().equals(csdo.getSelectChoice())) {
							tempDto.setState(ChoiceStateEnum.RIGHT.getStateCode());
							++mark;
						} else if(!ChoiceSelectEnum.A.getSelectValue().equals(csdo.getSelectChoice())
									&& !ChoiceSelectEnum.B.getSelectValue().equals(csdo.getSelectChoice())
									&& !ChoiceSelectEnum.C.getSelectValue().equals(csdo.getSelectChoice())
									&& !ChoiceSelectEnum.D.getSelectValue().equals(csdo.getSelectChoice())) {
							tempDto.setState(ChoiceStateEnum.UNKNOWN.getStateCode());
						} else {
							tempDto.setState(ChoiceStateEnum.WRONG.getStateCode());
						}
						result.add(tempDto);
					}
					Collections.sort(result, new Comparator<ChoiceStateDto>() {
						@Override
						public int compare(ChoiceStateDto c1,ChoiceStateDto c2) {
							return c1.getQuesNo().compareTo(c2.getQuesNo());
						}
					});
					configMap.put("mark", mark);
				} else {
					log.error("所选题目不存在或已删除");
				}			
			}
		} catch(Exception e) {
			log.error("transfer choiceResultDto failed-log", e);
		}
		return result;
	}
	
}
