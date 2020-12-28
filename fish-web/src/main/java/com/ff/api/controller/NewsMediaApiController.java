package com.ff.api.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ff.common.base.BaseController;
import com.ff.common.model.ResponseData;
import com.ff.common.util.StringUtils;
import com.ff.shop.model.NewsMedia;
import com.ff.shop.model.bo.Nextnews;
import com.ff.shop.model.bo.UpperNews;
import com.ff.shop.service.NewsMediaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/newMedia")
public class NewsMediaApiController extends BaseController {

    @Reference
    private NewsMediaService newsMediaService;


    //type:1行业资讯2：公司新闻 page 1 rows 5
    @CrossOrigin(value = "*",maxAge = 3600)
    @ApiOperation(value = "获取新闻列表",notes = "获取新闻列表")
    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData getList(HttpServletRequest request,
                                @ApiParam(required=true,name="type",value="type")@RequestParam(value="type",required=false)Integer type){

        ResponseData result = new ResponseData();
        Page<NewsMedia> page = getPage(request);
        EntityWrapper<NewsMedia> ew = new EntityWrapper<>();
        ew.orderBy("id",false);
        ew.eq("type",type);
        Page<NewsMedia> list = newsMediaService.selectPage(page,ew);
        Map<String,Object> data=new HashMap<>();
        data.put("pages", list.getPages());
        data.put("size", list.getSize());
        data.put("total", list.getTotal());
        data.put("data",list.getRecords());
        result.setState(200);
        result.setMessage("成功");
        result.setDatas(data);
        return result;
    }

    @CrossOrigin(value = "*",maxAge = 3600)
    @ApiOperation(value = "获取新闻详情",notes = "获取新闻详情")
    @RequestMapping(value = "/getSingleNews",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData getSingleNews(HttpServletRequest request,
                                @ApiParam(required=true,name="id",value="id")@RequestParam(value="id",required=false)Integer id){
        ResponseData result = new ResponseData();

        if(StringUtils.isEmpty(id.toString())){
            result.setDatas(500);
            result.setMessage("参数为空");
            return result;
        }

        NewsMedia newsMedia = newsMediaService.findByPrimaryKey(id.toString());
        Integer type = newsMedia.getType();
        UpperNews upperNews = getUpperNews(id.toString(),type);
        Nextnews nextnews = getNextnews(id.toString(),type);

        Map<String,Object> data=new HashMap<>();
        data.put("newsMedia",newsMedia);
        data.put("upperNews",upperNews);
        data.put("nextNews",nextnews);

        result.setState(200);
        result.setDatas(data);
        result.setMessage("成功");
        return result;
    }



    public UpperNews getUpperNews(String id, Integer type){

        UpperNews upperNews = new UpperNews();

        String preid = "";
        NewsMedia newsMedia = new NewsMedia();
        newsMedia.setType(type);
        List<NewsMedia> list = newsMediaService.selectByT(newsMedia);
        int count = list.size();
        //所有该类型的id存放数组
        String [] strid = new String [count];
        for(int i=0; i<count; i++){
            strid[i] = list.get(i).getId().toString();
        }
        for(int j=0; j<count; j++){
            if(strid[j].equals(id)){
                if(j != 0) preid = strid[j-1];
            }
        }
        if("".equals(preid)){
            upperNews.setId(0);
            upperNews.setTitle("已经到顶了");
        }else{
            NewsMedia newsMedia1 = newsMediaService.findByPrimaryKey(preid);
            upperNews.setId(Integer.parseInt(preid));
            upperNews.setTitle(newsMedia1.getTitle());
        }
        return upperNews;
    }

    public Nextnews getNextnews(String id, Integer type) {

        Nextnews next = new Nextnews();

        String nextid = "";
        NewsMedia newsMedia = new NewsMedia();
        newsMedia.setType(type);
        List<NewsMedia> list = newsMediaService.selectByT(newsMedia);
        int count = list.size();
        //所有该类型的id存放数组
        String [] strid = new String [count];
        for(int i=0; i<count; i++){
            strid[i] = list.get(i).getId().toString();
        }

        for(int j=0; j<count; j++){
            if(strid[j].equals(id)){
                if(j != count-1) nextid = strid[j+1];
            }
        }
        if("".equals(nextid)){
            next.setId(0);
            next.setTitle("已经到底了");
        }else{
            NewsMedia  newsMediaed = newsMediaService.findByPrimaryKey(nextid);
            next.setId(Integer.parseInt(nextid));
            next.setTitle(newsMediaed.getTitle());
        }
        return next;
    }

    //获取首页新闻中心上面一条数据 page rows
    @CrossOrigin(value = "*",maxAge = 3600)
    @ApiOperation(value = "获取首页新闻中心数据",notes = "获取首页新闻中心数据")
    @RequestMapping(value = "/getHomeNews",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData getHomeNews(HttpServletRequest request){
        ResponseData result = new ResponseData();
        NewsMedia newsMedia = new NewsMedia();
        newsMedia.setIsRoofPlacement(1);
        List<NewsMedia> list = newsMediaService.selectByT(newsMedia);

        Page<NewsMedia> page = getPage(request);
        EntityWrapper<NewsMedia> ew = new EntityWrapper<>();
        ew.eq("is_roof_placement",0);
        ew.orderBy("id",false);
        Page<NewsMedia> listed = newsMediaService.selectPage(page,ew);

        Map map = new HashMap();
        map.put("top",list);
        map.put("floor",listed.getRecords());

        result.setDatas(map);
        result.setState(200);
        result.setMessage("成功");
        return result;
    }






}
