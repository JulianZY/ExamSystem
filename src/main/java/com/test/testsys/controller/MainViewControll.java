package com.test.testsys.controller;

import com.test.testsys.entity.ResultData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 主页面controller
 * create by zhengxin 2017年10月15日18:10:11
 */
@Controller
@RequestMapping("/question")
public class MainViewControll {
    private String view;

    @RequestMapping(value = "/index.action")
    public ModelAndView index(){
        ModelAndView mv =new ModelAndView();
        mv.addObject("spring", "spring mvc");
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value = "/answerFrame.action")
    public ModelAndView showAnswerPage() {
        ModelAndView mv =new ModelAndView();

        mv.setViewName("question");
        return mv;
    }

    @RequestMapping(value = "/import.action")
    public ModelAndView showImportPage() {
        ModelAndView mv =new ModelAndView();

        mv.setViewName("import");
        return mv;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
}
