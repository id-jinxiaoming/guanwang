package com.ff.order.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.ff.common.base.BaseService;
import com.ff.order.model.Aftersales;
import com.ff.order.model.bo.AftersalesBO;

public interface AftersalesService extends BaseService<Aftersales> {

    Page<AftersalesBO> selectByPage(Page<AftersalesBO> page);
}
