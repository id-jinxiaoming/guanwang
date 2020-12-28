package com.ff.recruit.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ff.common.base.BaseController;
import com.ff.common.util.DateUtils;
import com.ff.shop.model.Recruit;
import com.ff.shop.service.RecruitService;
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
@RequestMapping("/tb/recruit")
public class RecruitController extends BaseController {

    @Reference
    private RecruitService recruitService;

    @RequiresPermissions(value = "tb:recruit:list")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(String key, ModelMap map) {

        key= StringEscapeUtils.unescapeHtml(key);
        try {
            key = new String(key.getBytes("iso-8859-1"), "utf-8");
        } catch (Exception e) {
            // TODO: handle exception
        }

        map.put("key", key);
        return new ModelAndView("tb/recruit/list",map);

    }


    @RequiresPermissions(value = "tb:recruit:list")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doList(String key, HttpServletRequest request) {
        Page<Recruit> page = getPage(request);
        EntityWrapper<Recruit> ew=new EntityWrapper();
        ew.like("title","%"+key+"%");
        ew.orderBy("id",false);
        Page<Recruit> data= recruitService.selectPage(page,ew);
        Map<String, Object> resultMap = new LinkedHashMap();

        resultMap.put("data",data);
        return resultMap;
    }

    @RequiresPermissions("tb:recruit:delete")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String[] id) {
        recruitService.deleteByPrimaryKey(id);
        Map<String, Object> resultMap = new LinkedHashMap();
        resultMap.put("msg","删除成功");
        resultMap.put("status",200);
        return resultMap;
    }

    @RequiresPermissions("tb:recruit:add")
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public ModelAndView add(ModelMap map){

        return new ModelAndView("/tb/recruit/add",map);
    }

    @RequiresPermissions("tb:recruit:add")
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> doAdd(Recruit model,HttpServletRequest request){
        Map<String, Object> resultMap = new LinkedHashMap();
        model.setCreateTime(DateUtils.getDate());
        model.setDetail(StringEscapeUtils.unescapeHtml(model.getDetail()));
        Integer id=recruitService.insert(model);
        if(id!=0){
            resultMap.put("status", 200);
            resultMap.put("message", "操作成功");
        } else {
            resultMap.put("status", 500);
            resultMap.put("message", "操作失败");
        }
        return resultMap;
    }

    @RequiresPermissions("tb:recruit:edit")
    @RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id")Integer id, ModelMap map){

        map.put("recruit",recruitService.findByPrimaryKey(id.toString()));

        return new ModelAndView("/tb/recruit/edit",map);
    }

    @RequiresPermissions("tb:recruit:edit")
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> doEdit(Recruit model,HttpServletRequest request){
        Map<String, Object> resultMap = new LinkedHashMap();
        model.setDetail(StringEscapeUtils.unescapeHtml(model.getDetail()));
        Integer id=recruitService.updateByPrimaryKey(model);
        if(id!=0){
            resultMap.put("status", 200);
            resultMap.put("message", "操作成功");
        } else {
            resultMap.put("status", 500);
            resultMap.put("message", "操作失败");
        }
        return resultMap;
    }
}
