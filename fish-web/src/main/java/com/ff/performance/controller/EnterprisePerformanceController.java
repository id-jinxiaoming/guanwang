package com.ff.performance.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ff.common.base.BaseController;
import com.ff.common.model.JsonTreeData;
import com.ff.shop.model.EnterprisePerformance;
import com.ff.shop.model.Goods;
import com.ff.shop.model.GoodsAlbum;
import com.ff.shop.model.GoodsCate;
import com.ff.shop.service.EnterprisePerformanceService;
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
@RequestMapping("/enterprise/performance")
public class EnterprisePerformanceController extends BaseController {


    @Reference
    private EnterprisePerformanceService enterprisePerformanceService;

    @RequiresPermissions(value = "enterprise:performance:list")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelMap map) {


        return new ModelAndView("enterprise/performance/list",map);

    }


    @RequiresPermissions(value = "enterprise:performance:list")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doList( HttpServletRequest request) {
        Page<EnterprisePerformance> page = getPage(request);
        EntityWrapper<EnterprisePerformance> ew=new EntityWrapper();
        ew.orderBy("id",false);
        Page<EnterprisePerformance> data= enterprisePerformanceService.selectPage(page,ew);
        Map<String, Object> resultMap = new LinkedHashMap();

        resultMap.put("data",data);
        return resultMap;
    }

    @RequiresPermissions("enterprise:performance:delete")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String[] id) {
        enterprisePerformanceService.deleteByPrimaryKey(id);
        Map<String, Object> resultMap = new LinkedHashMap();
        resultMap.put("msg","删除成功");
        resultMap.put("status",200);
        return resultMap;
    }


    @RequiresPermissions("enterprise:performance:add")
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public ModelAndView add(ModelMap map){

        return new ModelAndView("/enterprise/performance/add",map);
    }
    @RequiresPermissions("enterprise:performance:add")
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> doAdd(EnterprisePerformance model,HttpServletRequest request){
        Map<String, Object> resultMap = new LinkedHashMap();


        Integer id=enterprisePerformanceService.insert(model);
        if(id!=0){
            resultMap.put("status", 200);
            resultMap.put("message", "添加成功");
        }
        else
        {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败");
        }
        return resultMap;
    }

    @RequiresPermissions("enterprise:performance:edit")
    @RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id")Integer id, ModelMap map){

        map.put("enterprisePerformance",enterprisePerformanceService.findByPrimaryKey(id.toString()));

        return new ModelAndView("/enterprise/performance/edit",map);
    }
    @RequiresPermissions("enterprise:performance:edit")
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> doEdit(EnterprisePerformance model,HttpServletRequest request){
        Map<String, Object> resultMap = new LinkedHashMap();

        Integer id=enterprisePerformanceService.updateByPrimaryKey(model);
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
