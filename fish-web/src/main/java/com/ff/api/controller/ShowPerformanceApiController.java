package com.ff.api.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ff.common.base.BaseController;
import com.ff.common.model.ResponseData;
import com.ff.common.util.StringUtils;
import com.ff.shop.model.ShowImages;
import com.ff.shop.model.ShowPerformance;
import com.ff.shop.model.bo.Performance;
import com.ff.shop.service.ShowImagesService;
import com.ff.shop.service.ShowPerformanceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/showPerformance")
public class ShowPerformanceApiController extends BaseController {

    @Reference
    private ShowPerformanceService showPerformanceService;

    @Reference
    private ShowImagesService showImagesService;

    @CrossOrigin(value = "*",maxAge = 3600)
    @ApiOperation(value = "获取业绩列表",notes = "获取业绩列表")
    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData getList(HttpServletRequest request,
                                @ApiParam(required=true,name="type",value="type")@RequestParam(value="type",required=false)Integer type){
        ResponseData result = new ResponseData();
        Page<ShowPerformance> page = getPage(request);
        EntityWrapper<ShowPerformance> ew = new EntityWrapper<>();
        ew.orderBy("id",true);
        ew.eq("type",type);
        Page<ShowPerformance> list = showPerformanceService.selectPage(page,ew);
        List<Performance> listed = new ArrayList();
        for(ShowPerformance showPerformance:list.getRecords()){
            ShowImages goodsMap=new ShowImages();
            goodsMap.setShowId(showPerformance.getId());
            List<ShowImages> album=showImagesService.selectByT(goodsMap);

            Performance performance = new Performance();
            performance.setId(showPerformance.getId());
            performance.setDescribe(showPerformance.getDescribe());
            performance.setImage(album.get(0).getImage());
            performance.setTitle(showPerformance.getChTitle());
            listed.add(performance);
        }
        Map data = new HashMap();
        data.put("pages", list.getPages());
        data.put("size", list.getSize());
        data.put("total", list.getTotal());
        data.put("data",listed);
        result.setState(200);
        result.setDatas(data);
        result.setMessage("成功");
        return result;
    }

    @CrossOrigin(value = "*",maxAge = 3600)
    @ApiOperation(value = "获取业绩详情",notes = "获取业绩详情")
    @RequestMapping(value = "/getSingleContent",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData getSingleContent(HttpServletRequest request,
                                @ApiParam(required=true,name="id",value="id")@RequestParam(value="id",required=false)Integer id){
        ResponseData result = new ResponseData();

        if(StringUtils.isEmpty(id.toString())){
            result.setState(500);
            result.setMessage("参数为空");
            return result;
        }
        ShowPerformance showPerformance = showPerformanceService.findByPrimaryKey(id.toString());

        ShowImages showImages = new ShowImages();
        showImages.setShowId(id);
        List<ShowImages> list = showImagesService.selectByT(showImages);

        Map data = new HashMap();
        data.put("showPerformance",showPerformance);
        data.put("images",list);

        result.setState(200);
        result.setMessage("成功");
        result.setDatas(data);

        return result;
    }
}
