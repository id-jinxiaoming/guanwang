package com.ff.join.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ff.ad.model.HomePage;
import com.ff.common.base.BaseController;
import com.ff.shop.model.JoinOur;
import com.ff.shop.service.JoinOurService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/join/our")
public class JoinOurController extends BaseController {

    @Reference
    private JoinOurService joinOurService;

    @RequiresPermissions(value = "join:our:list")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelMap map) {


        return new ModelAndView("join/our/list",map);

    }


    @RequiresPermissions(value = "join:our:list")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doList(HttpServletRequest request) {
        Page<JoinOur> page = getPage(request);
        EntityWrapper<JoinOur> ew=new EntityWrapper();
        ew.orderBy("id",false);
        Page<JoinOur> data= joinOurService.selectPage(page,ew);
        Map<String, Object> resultMap = new LinkedHashMap();

        resultMap.put("data",data);
        return resultMap;
    }


    @RequiresPermissions("join:our:edit")
    @RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id")Integer id, ModelMap map){

        map.put("joinOur",joinOurService.findByPrimaryKey(id.toString()));

        return new ModelAndView("/join/our/edit",map);
    }
    @RequiresPermissions("join:our:edit")
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> doEdit(JoinOur model,HttpServletRequest request){
        Map<String, Object> resultMap = new LinkedHashMap();

        Integer id=joinOurService.updateByPrimaryKey(model);
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
