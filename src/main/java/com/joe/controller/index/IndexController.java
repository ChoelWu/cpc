package com.joe.controller.index;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/12/2
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 
// +----------------------------------------------------------------------

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index/index")
public class IndexController {

    @RequestMapping("/index.do")
    public String index() {
        return "Index/Index/index";
    }
}