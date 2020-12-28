package com.ff.api.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ff.common.base.BaseController;
import com.ff.common.model.ResponseData;
import com.ff.shop.model.JoinOur;
import com.ff.shop.service.JoinOurService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/api/joinOur")
public class JoinOurApiController extends BaseController {

    @Reference
    private JoinOurService joinOurService;

    @CrossOrigin(value = "*",maxAge = 3600)
    @ApiOperation(value = "获取联系我们内容",notes = "获取联系我们内容")
    @RequestMapping(value = "/getContent",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData getContent(HttpServletRequest request){
        ResponseData result = new ResponseData();

        List<JoinOur> joinOur = joinOurService.selectAll();
        result.setDatas(joinOur);
        result.setMessage("成功");
        result.setState(200);
        return result;
    }
}
