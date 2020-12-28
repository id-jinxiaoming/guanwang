package com.ff.shop.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.ff.common.base.BaseServiceImpl;
import com.ff.shop.model.ShowPerformance;
import com.ff.shop.dao.ShowPerformanceMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ShowPerformanceServiceImpl extends BaseServiceImpl<ShowPerformance> implements ShowPerformanceService {

    @Autowired
    ShowPerformanceMapper showPerformanceMapper;

    @Override
    public int insert(ShowPerformance showPerformance){
        showPerformanceMapper.insert(showPerformance);
        return showPerformance.getId();
    }
}
