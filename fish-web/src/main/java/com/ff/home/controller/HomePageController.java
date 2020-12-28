package com.ff.home.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ff.ad.model.HomePage;
import com.ff.ad.service.HomePageService;
import com.ff.common.base.BaseController;
import com.ff.common.model.JsonTreeData;
import com.ff.shop.model.Goods;
import com.ff.shop.model.GoodsAlbum;
import com.ff.shop.model.GoodsCate;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/home/page")
public class HomePageController extends BaseController {


    @Reference
    private HomePageService homePageService;

    @RequiresPermissions(value = "home:page:list")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelMap map) {


        return new ModelAndView("home/page/list",map);

    }


    @RequiresPermissions(value = "home:page:list")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doList(HttpServletRequest request) {
        Page<HomePage> page = getPage(request);
        EntityWrapper<HomePage> ew=new EntityWrapper();
        ew.orderBy("id",false);
        Page<HomePage> data= homePageService.selectPage(page,ew);
        Map<String, Object> resultMap = new LinkedHashMap();

        resultMap.put("data",data);
        return resultMap;
    }


    @RequiresPermissions("home:page:add")
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public ModelAndView add(ModelMap map){

        return new ModelAndView("/home/page/add",map);
    }
    @RequiresPermissions("home:page:add")
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> doAdd(HomePage model,HttpServletRequest request){
        Map<String, Object> resultMap = new LinkedHashMap();

        Integer id=homePageService.insert(model);
        if(id!=0){
            resultMap.put("status", 200);
            resultMap.put("message", "添加成功");
        }
        else
        {
            resultMap.put("status", 500);
            resultMap.put("message", "操作失败");
        }
        return resultMap;
    }

    @RequiresPermissions("home:page:edit")
    @RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id")Integer id, ModelMap map){

        map.put("homePage",homePageService.findByPrimaryKey(id.toString()));

        return new ModelAndView("/home/page/edit",map);
    }
    @RequiresPermissions("home:page:edit")
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> doEdit(HomePage model,HttpServletRequest request){
        Map<String, Object> resultMap = new LinkedHashMap();

        Integer id=homePageService.updateByPrimaryKey(model);
        if(id!=0){
            resultMap.put("status", 200);
            resultMap.put("message", "修改成功");
        }
        else
        {
            resultMap.put("status", 500);
            resultMap.put("message", "修改失败");
        }
        return resultMap;
    }
}

