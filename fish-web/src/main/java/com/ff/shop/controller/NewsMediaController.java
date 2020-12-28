package com.ff.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ff.common.base.BaseController;
import com.ff.common.model.JsonTreeData;
import com.ff.common.util.DateUtils;
import com.ff.shop.model.Goods;
import com.ff.shop.model.GoodsAlbum;
import com.ff.shop.model.GoodsCate;
import com.ff.shop.model.NewsMedia;
import com.ff.shop.service.NewsMediaService;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping("/news/media")
public class NewsMediaController extends BaseController {

    @Reference
    private NewsMediaService newsMediaService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(String key, ModelMap map) {



        key= StringEscapeUtils.unescapeHtml(key);
        try {
            key = new String(key.getBytes("iso-8859-1"), "utf-8");
        } catch (Exception e) {
            // TODO: handle exception
        }

        map.put("key", key);
        return new ModelAndView("news/media/list",map);

    }


    @RequiresPermissions(value = "news:media:list")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doList(String key, HttpServletRequest request) {
        Page<NewsMedia> page = getPage(request);
        EntityWrapper<NewsMedia> ew=new EntityWrapper();
        ew.like("title","%"+key+"%");
        ew.orderBy("id",false);
        Page<NewsMedia> data= newsMediaService.selectPage(page,ew);
        Map<String, Object> resultMap = new LinkedHashMap();

        resultMap.put("data",data);
        return resultMap;
    }

    @RequiresPermissions("news:media:delete")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Object delete(String[] id) {
        newsMediaService.deleteByPrimaryKey(id);
        Map<String, Object> resultMap = new LinkedHashMap();
        resultMap.put("msg","成功删除");
        resultMap.put("status",200);
        return resultMap;
    }


    @RequiresPermissions("news:media:add")
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public ModelAndView add(ModelMap map){


        return new ModelAndView("/news/media/add",map);
    }

    @RequiresPermissions("news:media:add")
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> doAdd(NewsMedia model,HttpServletRequest request){
        Map<String, Object> resultMap = new LinkedHashMap();
        String newDate = DateUtils.getDate();
        String year = newDate.substring(0,4);
        String monthDay = newDate.substring(5,10);
        model.setCreateYear(year);
        model.setMonthDay(monthDay);
        model.setTitle(StringEscapeUtils.unescapeHtml(model.getTitle()));
        model.setDescribe(StringEscapeUtils.unescapeHtml(model.getDescribe()));
        model.setBrief(StringEscapeUtils.unescapeHtml(model.getBrief()));
        Integer id=newsMediaService.insert(model);
        if(id!=0){
            resultMap.put("status", 200);
            resultMap.put("message", "添加成功");
        } else {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败");
        }
        return resultMap;
    }

    @RequiresPermissions("news:media:edit")
    @RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id")Integer id, ModelMap map){

        map.put("newsMedia",newsMediaService.findByPrimaryKey(id.toString()));

        return new ModelAndView("/news/media/edit",map);
    }
    @RequiresPermissions("news:media:edit")
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> doEdit(NewsMedia model,HttpServletRequest request){
        Map<String, Object> resultMap = new LinkedHashMap();
        model.setTitle(StringEscapeUtils.unescapeHtml(model.getTitle()));
        model.setDescribe(StringEscapeUtils.unescapeHtml(model.getDescribe()));
        model.setBrief(StringEscapeUtils.unescapeHtml(model.getBrief()));
        Integer id=newsMediaService.updateByPrimaryKey(model);
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

    @RequiresPermissions(value = "news:media:check")
    @RequestMapping(value = "/check/{id}", method = RequestMethod.GET)
    public ModelAndView check(@PathVariable("id")Integer id,ModelMap map) {
        map.put("id", id);
        return new ModelAndView("news/media/check",map);
    }

    @RequiresPermissions(value = "news:media:check")
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> check(HttpServletRequest request, Integer id,Integer status) {
        Map<String, Object> resultMap = new LinkedHashMap();
        Integer number = newsMediaService.setDefault(id);
        if(number>0){
            resultMap.put("status", 200);
            resultMap.put("message", "置顶成功");
        }else {
            resultMap.put("status", 500);
            resultMap.put("message", "置顶失败");
        }
        return resultMap;
    }
}
