package com.ff.shop.service;

import com.ff.common.base.BaseService;
import com.ff.shop.model.NewsMedia;

public interface NewsMediaService extends BaseService<NewsMedia> {

    Integer setDefault(Integer id);
}
