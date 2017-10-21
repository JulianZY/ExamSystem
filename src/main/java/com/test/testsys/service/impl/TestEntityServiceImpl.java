/**
 * 
 */
package com.test.testsys.service.impl;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.test.testsys.dao.TestEntityDao;
import com.test.testsys.entity.ResultData;
import com.test.testsys.entity.TestEntity;
import com.test.testsys.service.ITestEntityService;

/**
 * 测试实体业务实现类
 * @author Julia
 *
 */
@Service("testEntityService")
public class TestEntityServiceImpl implements ITestEntityService {
	@Resource
	private TestEntityDao testEntityDao; 
	
	@Override
	public ResultData addEntity(TestEntity t) {
		ResultData result = new ResultData();
		try {
			if(t != null && t.getId() == null) {
				testEntityDao.save(t);
				result.setMsg("save success");
			} else if( t != null && t.getId() != null) {
				testEntityDao.update(t);
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
	
}
