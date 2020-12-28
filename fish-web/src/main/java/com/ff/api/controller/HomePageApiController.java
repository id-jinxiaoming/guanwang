package com.ff.api.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ff.ad.model.HomePage;
import com.ff.ad.service.HomePageService;
import com.ff.common.base.BaseController;
import com.ff.common.model.ResponseData;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/homePage")
public class HomePageApiController extends BaseController {

    @Reference
    private HomePageService homePageService;

    @CrossOrigin(value = "*",maxAge = 3600)
    @ApiOperation(value = "获取首页底部内容",notes = "获取首页底部内容")
    @RequestMapping(value = "/getFloorContent",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData getFloorContent(HttpServletRequest request){
        ResponseData result = new ResponseData();

        Page<HomePage> page = getPage(request);
        EntityWrapper<HomePage> ew = new EntityWrapper<>();
        ew.orderBy("id",true);
        Page<HomePage> list = homePageService.selectPage(page,ew);
        result.setState(200);
        result.setDatas(list.getRecords());
        result.setMessage("成功");
        return result;
    }
}
