package com.ff.shop.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.ff.common.base.BaseServiceImpl;
import com.ff.shop.dao.ShippingCarrierMapper;
import com.ff.shop.model.ShippingCarrier;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class ShippingCarrierServiceImpl extends BaseServiceImpl<ShippingCarrier> implements ShippinpCarrierService{

    @Autowired
    ShippingCarrierMapper shippingCarrierMapper;


}
