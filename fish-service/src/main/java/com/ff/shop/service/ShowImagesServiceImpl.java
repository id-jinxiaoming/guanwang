package com.ff.shop.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ff.common.base.BaseServiceImpl;
import com.ff.shop.model.ShowImages;
import com.ff.shop.dao.ShowImagesMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ShowImagesServiceImpl extends BaseServiceImpl<ShowImages> implements ShowImagesService {


    @Autowired
    ShowImagesMapper showImagesMapper;

    @Override
    public Integer deleteByShowId(Integer id) {

        EntityWrapper<ShowImages> ew=new EntityWrapper();
        ew.eq("show_id",id);
        return mapper.delete(ew);
    }
}
