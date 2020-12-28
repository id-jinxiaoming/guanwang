package com.ff.small.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ff.ad.model.SmallCard;
import com.ff.ad.service.SmallCardService;
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
@RequestMapping("/small/card")
public class SmallCardController extends BaseController {

    @Reference
    private SmallCardService smallCardService;


    @RequiresPermissions(value = "small:card:list")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelMap map) {

        return new ModelAndView("small/card/list",map);

    }


    @RequiresPermissions(value = "small:card:list")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doList(String key, HttpServletRequest request) {
        Page<SmallCard> page = getPage(request);
        EntityWrapper<SmallCard> ew=new EntityWrapper();
        ew.orderBy("id",false);
        Page<SmallCard> data= smallCardService.selectPage(page,ew);
        Map<String, Object> resultMap = new LinkedHashMap();

        resultMap.put("data",data);
        return resultMap;
    }

    @RequiresPermissions("small:card:add")
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public ModelAndView add(ModelMap map){

        return new ModelAndView("/small/card/add",map);
    }

    @RequiresPermissions("small:card:add")
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> doAdd(SmallCard model,HttpServletRequest request){
        Map<String, Object> resultMap = new LinkedHashMap();
        model.setSmallTitle(StringEscapeUtils.unescapeHtml(model.getSmallTitle()));
        Integer id=smallCardService.insert(model);
        if(id>0){
            resultMap.put("status", 200);
            resultMap.put("message", "操作成功");
        }else {
            resultMap.put("status", 500);
            resultMap.put("message", "操作失败");
        }
            return resultMap;
    }


    @RequiresPermissions("small:card:edit")
    @RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id")Integer id, ModelMap map){
        SmallCard smallCard = smallCardService.findByPrimaryKey(id.toString());

        map.put("smallCard", smallCard);
        return new ModelAndView("/small/card/edit",map);
    }
    @RequiresPermissions("small:card:edit")
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> doEdit(SmallCard model,HttpServletRequest request){
        Map<String, Object> resultMap = new LinkedHashMap();
        model.setSmallTitle(StringEscapeUtils.unescapeHtml(model.getSmallTitle()));
        Integer id=smallCardService.updateByPrimaryKey(model);
        if(id!=0){
            resultMap.put("status", 200);
            resultMap.put("message", "操作成功");
        }else
        {
            resultMap.put("status", 500);
            resultMap.put("message", "操作失败");
        }
        return resultMap;
    }

    @RequiresPermissions("small:card:delete")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String[] id) {
        smallCardService.deleteByPrimaryKey(id);
        Map<String, Object> resultMap = new LinkedHashMap();
        resultMap.put("msg","删除成功");
        resultMap.put("status",200);
        return resultMap;
    }
}
