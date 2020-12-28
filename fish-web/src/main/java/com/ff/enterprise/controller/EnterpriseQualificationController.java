package com.ff.enterprise.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ff.ad.model.EnterpriseQualification;
import com.ff.ad.model.SmallCard;
import com.ff.ad.service.EnterpriseQualificationService;
import com.ff.common.base.BaseController;
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
@RequestMapping("/enterprises/qualification")
public class EnterpriseQualificationController extends BaseController {

    @Reference
    private EnterpriseQualificationService enterpriseQualificationService;

    @RequiresPermissions(value = "enterprises:qualification:list")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelMap map) {

        return new ModelAndView("enterprises/qualification/list",map);

    }


    @RequiresPermissions(value = "enterprises:qualification:list")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doList(String key, HttpServletRequest request) {
        Page<EnterpriseQualification> page = getPage(request);
        EntityWrapper<EnterpriseQualification> ew=new EntityWrapper();
        ew.orderBy("id",false);
        Page<EnterpriseQualification> data= enterpriseQualificationService.selectPage(page,ew);
        Map<String, Object> resultMap = new LinkedHashMap();

        resultMap.put("data",data);
        return resultMap;
    }

    @RequiresPermissions("enterprises:qualification:edit")
    @RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id")Integer id, ModelMap map){
        EnterpriseQualification enterpriseQualification = enterpriseQualificationService.findByPrimaryKey(id.toString());

        map.put("enterpriseQualification", enterpriseQualification);
        return new ModelAndView("/enterprises/qualification/edit",map);
    }
    @RequiresPermissions("enterprises:qualification:edit")
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> doEdit(EnterpriseQualification model,HttpServletRequest request){
        Map<String, Object> resultMap = new LinkedHashMap();
        model.setBrief(StringEscapeUtils.unescapeHtml(model.getBrief()));
        Integer id=enterpriseQualificationService.updateByPrimaryKey(model);
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
}
