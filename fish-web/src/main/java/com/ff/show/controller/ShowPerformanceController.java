package com.ff.show.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ff.common.base.BaseController;
import com.ff.shop.model.ShowImages;
import com.ff.shop.model.ShowPerformance;
import com.ff.shop.service.ShowImagesService;
import com.ff.shop.service.ShowPerformanceService;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/show/performance")
public class ShowPerformanceController extends BaseController {


    @Reference
    private ShowPerformanceService showPerformanceService;

    @Reference
    private ShowImagesService showImageService;

    @RequiresPermissions(value = "show:performance:list")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(String key, ModelMap map) {

        key= StringEscapeUtils.unescapeHtml(key);
        try {
            key = new String(key.getBytes("iso-8859-1"), "utf-8");
        } catch (Exception e) {
            // TODO: handle exception
        }

        map.put("key", key);
        return new ModelAndView("show/performance/list",map);

    }


    @RequiresPermissions(value = "show:performance:list")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doList(String key, HttpServletRequest request) {
        Page<ShowPerformance> page = getPage(request);
        EntityWrapper<ShowPerformance> ew=new EntityWrapper();
        ew.like("ch_title","%"+key+"%");
        ew.orderBy("id",false);
        Page<ShowPerformance> data= showPerformanceService.selectPage(page,ew);
        Map<String, Object> resultMap = new LinkedHashMap();

        resultMap.put("data",data);
        return resultMap;
    }

    @RequiresPermissions("show:performance:delete")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String[] id) {
        showPerformanceService.deleteByPrimaryKey(id);
        Map<String, Object> resultMap = new LinkedHashMap();
        resultMap.put("msg","删除成功");
        resultMap.put("status",200);
        return resultMap;
    }

    @RequiresPermissions("show:performance:add")
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public ModelAndView add(ModelMap map){

        return new ModelAndView("/show/performance/add",map);
    }

    @RequiresPermissions("show:performance:add")
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> doAdd(ShowPerformance model,HttpServletRequest request){
        Map<String, Object> resultMap = new LinkedHashMap();
        model.setChTitle(StringEscapeUtils.unescapeHtml(model.getChTitle()));
        model.setEnTitle(StringEscapeUtils.unescapeHtml(model.getEnTitle()));
        model.setDescribe(StringEscapeUtils.unescapeHtml(model.getDescribe()));
        model.setContent(StringEscapeUtils.unescapeHtml(model.getContent()));
        Integer id= showPerformanceService.insert(model);
        if(id!=0){
            String[] photo=request.getParameterValues("photo");
            if(photo!=null)
            {
                for (String item:photo) {
                    ShowImages album=new ShowImages();
                    album.setShowId(id);
                    album.setImage(item);
                    showImageService.insert(album);
                }
            }

            resultMap.put("status", 200);
            resultMap.put("message", "操作成功");
        }
        else
        {
            resultMap.put("status", 500);
            resultMap.put("message", "操作失败");
        }
        return resultMap;
    }

    @RequiresPermissions("show:performance:edit")
    @RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id")Integer id, ModelMap map){

        map.put("showPerformance",showPerformanceService.findByPrimaryKey(id.toString()));
        ShowImages goodsMap=new ShowImages();
        goodsMap.setShowId(id);
        List<ShowImages> album=showImageService.selectByT(goodsMap);
        map.put("album", album);
        return new ModelAndView("/show/performance/edit",map);
    }
    @RequiresPermissions("show:performance:edit")
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> doEdit(ShowPerformance model,HttpServletRequest request){
        Map<String, Object> resultMap = new LinkedHashMap();
        model.setChTitle(StringEscapeUtils.unescapeHtml(model.getChTitle()));
        model.setEnTitle(StringEscapeUtils.unescapeHtml(model.getEnTitle()));
        model.setDescribe(StringEscapeUtils.unescapeHtml(model.getDescribe()));
        model.setContent(StringEscapeUtils.unescapeHtml(model.getContent()));
        Integer id=showPerformanceService.updateByPrimaryKey(model);
        if(id!=0){
            //删除之前数据
            showImageService.deleteByShowId(model.getId());
            String[] photo=request.getParameterValues("photo");
            if(photo!=null)
            {

                for (String item:photo) {
                    ShowImages album=new ShowImages();
                    album.setShowId(model.getId());
                    album.setImage(item);
                    showImageService.insert(album);
                }
            }

            resultMap.put("status", 200);
            resultMap.put("message", "操作成功");
        }
        else
        {
            resultMap.put("status", 500);
            resultMap.put("message", "操作失败");
        }
        return resultMap;
    }


}
