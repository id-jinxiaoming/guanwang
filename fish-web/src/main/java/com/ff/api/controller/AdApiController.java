package com.ff.api.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ff.ad.model.Ad;
import com.ff.ad.service.AdService;
import com.ff.common.base.BaseController;
import com.ff.common.model.ResponseData;
import com.ff.shop.model.Goods;
import com.ff.shop.model.GoodsAlbum;
import com.ff.shop.model.GoodsCate;
import com.ff.shop.model.GoodsReview;
import com.ff.shop.service.GoodsAlbumService;
import com.ff.shop.service.GoodsCateService;
import com.ff.shop.service.GoodsReviewService;
import com.ff.shop.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Api(value="广告接口",description="广告接口")
@Controller
@RequestMapping(value="/api/ad")
public class AdApiController extends BaseController {
    @Reference
    private AdService adService;


    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value="获取广告",notes="获取广告")
    @RequestMapping(value="/getAdList",method= RequestMethod.POST)
    @ResponseBody
    public ResponseData getAdList(HttpServletRequest request, HttpServletResponse response
         ){

        ResponseData result = new ResponseData();
        Page<Ad> page = getPage(request);
        EntityWrapper<Ad> ew=new EntityWrapper();
        ew.orderBy("ad_id",true);
        ew.eq("status",1);
        Page<Ad> list=adService.selectPage(page,ew);
        result.setState(200);
        result.setDatas(list.getRecords());
        result.setMessage("成功");
        return result;
    }





}
