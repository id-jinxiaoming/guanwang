package com.ff.api.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ff.common.base.BaseController;
import com.ff.common.model.ResponseData;
import com.ff.shop.model.Recruit;
import com.ff.shop.service.RecruitService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/recruit")
public class RecruitApiController extends BaseController {

    @Reference
    private RecruitService recruitService;


    @CrossOrigin(value = "*",maxAge = 3600)
    @ApiOperation(value = "获取招聘列表",notes = "获取招聘列表")
    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData getList(HttpServletRequest request){
        ResponseData result = new ResponseData();

        Page<Recruit> page = getPage(request);
        EntityWrapper<Recruit> ew = new EntityWrapper<>();
        ew.orderBy("id",false);
        Page<Recruit> list = recruitService.selectPage(page,ew);
        Map map = new HashMap();
        map.put("pages", list.getPages());
        map.put("size", list.getSize());
        map.put("total", list.getTotal());
        map.put("data",list);
        result.setState(200);
        result.setMessage("成功");
        result.setDatas(map);
        return result;
    }
}
