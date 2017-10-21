package com.test.testsys.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.test.testsys.entity.Choice;
import com.test.testsys.entity.ResultData;
import com.test.testsys.service.IChoiceService;

/**
 * 主页面controller
 * create by zhengxin 2017年10月15日18:10:11
 */
@Controller
@RequestMapping("/question")
public class MainViewController {
	
	@Resource
	IChoiceService choiceService;

    /**
     * 无用的方法，应删除
     * @return
     */
    @RequestMapping(value = "/index.action")
    public ModelAndView index(){
        ModelAndView mv =new ModelAndView();
        mv.addObject("spring", "spring mvc");
        mv.setViewName("index");
        return mv;
    }

    /**
     * 显示答题页面
     * @return
     */
    @RequestMapping(value = "/answerFrame.action")
    public ModelAndView showAnswerPage() {
        ModelAndView mv =new ModelAndView();
        List<Choice> list = new ArrayList<Choice>();
        Date date = new Date();
        for (int i = 0; i < 100; i++) {
            Choice choice1 = new Choice(i + 1, "123" + i, "test", "testa", "testb", "testc", "testd", "A",
                    new Timestamp(date.getTime()), new Timestamp(date.getTime()));
            list.add(choice1);
        }
        mv.addObject("questionList",list);
        mv.addObject("totalTime",3600);
        mv.setViewName("question");
        return mv;
    }

    /**
     * 显示导入题目页面
     * @return
     */
    @RequestMapping(value = "/import.action")
    public ModelAndView showImportPage() {
        ModelAndView mv =new ModelAndView();
        mv.setViewName("import");
        return mv;
    }

    /**
     * 显示题目查看页面
     * @return
     */
    @RequestMapping(value = "/all.action")
    public ModelAndView showAllQuestionPage() {
        ModelAndView mv =new ModelAndView();

        mv.setViewName("checkAllQuestion");
        return mv;
    }

    /**
     * 获取所有试题
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllQuestions.action", method = RequestMethod.GET)
    public JSONObject getAllQuestion() {
    	JSONObject result = new JSONObject();
        try {
            /*
                调用service层接口，返回一个list
             */
//            List<Choice> list = new ArrayList<>();
//            Date date = new Date();
//            Choice choice1 = new Choice(1, "123", "test", "testa", "testb", "testc", "testd", "A",
//                    new Timestamp(date.getTime()), new Timestamp(date.getTime()));
//            Choice choice2 = new Choice(2, "124", "test", "testa", "testb", "testc", "testd", "A",
//                    new Timestamp(date.getTime()), new Timestamp(date.getTime()));
//            list.add(choice1);
//            list.add(choice2);
            List<Choice> list = choiceService.queryAllQuestions();
            if(list != null && list.size() > 0) {
            	result.put("total", list.size());
                result.put("rows", list);
            } else {
            	result.put("total", 0);
                result.put("rows",  new Object[]{});
            }           
            result.put("success", true);
        } catch(Exception e) {
        	result.put("total", 0);
            result.put("rows", new Object[]{});
            result.put("success", false);
        }
        return result;
    }

    /**
     * 删除勾选试题
     * @param checkList
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete.action", method = RequestMethod.POST)
    public JSONObject delete(@RequestBody List<String> checkList) {
        JSONObject result = new JSONObject();
        try {
        	if(checkList != null && checkList.size() > 0) {
        		choiceService.deleteQuestionsByUuids(checkList);
        		result.put("message", "delete success");
        		result.put("success", true);
        	} else {
        		result.put("message", "list is empty");
        		result.put("success", false);
        	}       	
        } catch(Exception e) {
            result.put("success", false);
        }       
        result.put("success", true);  //这句话不要删除，前端要做判断，删除失败赋值false
        return result;
    }

    /**
     * 添加单个试题(页面点击完成按钮)
     * @param choice
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add.action", method = RequestMethod.POST)
    public JSONObject add(@RequestBody Choice choice) {
    	 JSONObject result = new JSONObject();
         try {
        	ResultData resultData = choiceService.addQuestion(choice);
      		if(resultData.success()) {
      			result.put("message", resultData.getMsg());
          		result.put("success", true);
      		} else {
      			result.put("message", resultData.getMsg());
          		result.put("success", false);
      		}    	
         } catch(Exception e) {
             result.put("success", false);
         }       

         return result;
    }

    /**
     * 获取试卷
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getExamPaper.action", method = RequestMethod.GET)
    public JSONObject getAll() {
        JSONObject result = new JSONObject();

        List<Choice> list = new ArrayList<>();
        Date date = new Date();
        for (int i = 0; i < 100; i++) {
            Choice choice1 = new Choice(i + 1, "123" + i, "test", "testa", "testb", "testc", "testd", "A",
                    new Timestamp(date.getTime()), new Timestamp(date.getTime()));
            list.add(choice1);
        }
        result.put("data", list);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getTimmer.action", method = RequestMethod.GET)
    public JSONObject getTimmer() {
        JSONObject result = new JSONObject();

        result.put("data", 60 * 60);
        return result;
    }

}
