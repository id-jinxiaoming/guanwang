package com.ff.about.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ff.common.base.BaseController;
import com.ff.shop.model.AboutCompany;
import com.ff.shop.service.AboutCompanyService;
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
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/about/company")
public class AboutCompanyController extends BaseController {

    @Reference
    private AboutCompanyService aboutCompanyService;

    @RequiresPermissions(value = "about:company:list")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelMap map) {


        return new ModelAndView("about/company/list",map);

    }


    @RequiresPermissions(value = "about:company:list")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doList(HttpServletRequest request) {
        Page<AboutCompany> page = getPage(request);
        EntityWrapper<AboutCompany> ew=new EntityWrapper();

        ew.orderBy("id",false);
        Page<AboutCompany> data= aboutCompanyService.selectPage(page,ew);
        Map<String, Object> resultMap = new LinkedHashMap();

        resultMap.put("data",data);
        return resultMap;
    }


    @RequiresPermissions("about:company:edit")
    @RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id")Integer id, ModelMap map){
        AboutCompany aboutCompany =aboutCompanyService.findByPrimaryKey(id.toString());
        map.put("aboutCompany",aboutCompany);
        return new ModelAndView("/about/company/edit",map);
    }

    @RequiresPermissions("about:company:edit")
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> doEdit(AboutCompany model,HttpServletRequest request){
        Map<String, Object> resultMap = new LinkedHashMap();
        model.setContent(StringEscapeUtils.unescapeHtml(model.getContent()));
        Integer id=aboutCompanyService.updateByPrimaryKey(model);
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
