package com.ff.shop.service;

import com.ff.common.base.BaseService;
import com.ff.shop.model.ShowImages;

public interface ShowImagesService extends BaseService<ShowImages> {

    Integer deleteByShowId(Integer id);
}
