package com.ff.api.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ff.common.base.BaseController;
import com.ff.common.model.ResponseData;
import com.ff.shop.model.AboutCompany;
import com.ff.shop.service.AboutCompanyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/api/aboutCompany")
@Controller
public class AboutCompanyApiController extends BaseController {

    @Reference
    private AboutCompanyService aboutCompanyService;

    @CrossOrigin(origins = "*" ,maxAge = 3600)
    @ApiOperation(value = "获取首页关于艺成园",notes = "获取首页关于艺成园")
    @RequestMapping(value = "/getSingleContent",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData getSingleContent(HttpServletRequest request, HttpServletResponse response){
        ResponseData result = new ResponseData();

        Page<AboutCompany> page = getPage(request);
        EntityWrapper<AboutCompany> ew = new EntityWrapper<>();
        ew.orderBy("id",true);
        Page<AboutCompany> list = aboutCompanyService.selectPage(page,ew);
        result.setState(200);
        result.setMessage("成功");
        result.setDatas(list.getRecords());
        return result;
    }
}
