package com.ff.rechage.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.ff.common.base.BaseService;
import com.ff.rechage.model.RechageOrder;
import com.ff.rechage.model.bo.RechageOrderBO;

import java.util.List;

public interface RechageOrderService extends BaseService<RechageOrder> {

    Boolean payment(RechageOrder model);


    List<RechageOrderBO> selectList(Page<RechageOrderBO> page, RechageOrder model);




}
