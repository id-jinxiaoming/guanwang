package com.ff.api.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ff.ad.model.Ad;
import com.ff.ad.model.SmallCard;
import com.ff.ad.service.SmallCardService;
import com.ff.common.base.BaseController;
import com.ff.common.model.ResponseData;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/smallCard")
public class SmallCardApiController extends BaseController {

    @Reference
    private SmallCardService smallCardService;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation(value="获取卡片内容",notes="获取卡片内容")
    @RequestMapping(value="/getSmallCardList",method= RequestMethod.POST)
    @ResponseBody
    public ResponseData getAdList(HttpServletRequest request, HttpServletResponse response
    ){
        ResponseData result = new ResponseData();

        Page<SmallCard> page = getPage(request);
        EntityWrapper<SmallCard> ew=new EntityWrapper();
        ew.orderBy("id",true);
        Page<SmallCard> list=smallCardService.selectPage(page,ew);
        result.setState(200);
        result.setDatas(list.getRecords());
        result.setMessage("成功");
        return result;
    }
}
