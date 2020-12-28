package com.ff.api.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ff.common.base.BaseController;
import com.ff.common.model.ResponseData;
import com.ff.shop.model.EnterprisePerformance;
import com.ff.shop.service.EnterprisePerformanceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/enterprisePerformance")
public class EnterprisePerformanceApiController extends BaseController {


    @Reference
    private EnterprisePerformanceService enterprisePerformanceService;

    @CrossOrigin(origins = "*",maxAge = 3600)
    @ApiOperation(value = "获取首页业绩分类图片内容",notes = "获取首页业绩分类图片内容")
    @RequestMapping(value = "/getAllList",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData getAllList(HttpServletRequest request, HttpServletResponse response){
        ResponseData result = new ResponseData();
        Page<EnterprisePerformance> page =getPage(request);
        EntityWrapper<EnterprisePerformance> ew = new EntityWrapper<>();
        ew.orderBy("id",true);
        Page<EnterprisePerformance> list=enterprisePerformanceService.selectPage(page,ew);
        result.setState(200);
        result.setMessage("成功");
        result.setDatas(list.getRecords());
        return result;
    }
}
