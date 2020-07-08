package com.codermy.myspringsecurity.controller.api;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author codermy
 * @createTime 2020/6/21
 */
@Controller
@RequestMapping("${api-url}")
@Api(tags = "转发")
public class ApiController {
    @RequestMapping("/getPage")
    public ModelAndView getPage(ModelAndView modelAndView,String pageName){
        modelAndView.setViewName(pageName);
        return modelAndView;
    }
}
