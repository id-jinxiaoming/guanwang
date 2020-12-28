package com.ff.api.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ff.ad.model.EnterpriseQualification;
import com.ff.ad.service.EnterpriseQualificationService;
import com.ff.common.base.BaseController;
import com.ff.common.model.ResponseData;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/enterpriseQualification")
public class EnterpriseQualificationApiController extends BaseController {

    @Reference
    private EnterpriseQualificationService enterpriseQualificationService;

    @CrossOrigin(value = "*",maxAge = 3600)
    @ApiOperation(value = "获取荣誉资质",notes = "获取荣誉资质")
    @RequestMapping(value = "/getDetails",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData getDetails(HttpServletRequest request, HttpServletResponse response){
        ResponseData result = new ResponseData();
        Page<EnterpriseQualification> page = getPage(request);
        EntityWrapper<EnterpriseQualification> ew = new EntityWrapper<>();
        ew.orderBy("id",true);
        Page<EnterpriseQualification> list = enterpriseQualificationService.selectPage(page,ew);
        result.setDatas(list.getRecords());
        result.setMessage("成功");
        result.setState(200);
        return result;
    }
}
