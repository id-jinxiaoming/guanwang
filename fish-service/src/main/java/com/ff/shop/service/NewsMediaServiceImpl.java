package com.ff.shop.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.ff.common.base.BaseServiceImpl;
import com.ff.shop.model.NewsMedia;

@Service
public class NewsMediaServiceImpl extends BaseServiceImpl<NewsMedia> implements NewsMediaService {

    @Override
    public Integer setDefault(Integer id) {
        NewsMedia map=new NewsMedia();
        map.setIsRoofPlacement(0);
        mapper.update(map,null);
        map.setIsRoofPlacement(1);
        map.setId(id);

        return mapper.updateById(map);
    }
}
