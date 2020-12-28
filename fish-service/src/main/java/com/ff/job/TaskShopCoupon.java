package com.ff.job;

import com.ff.shop.model.ShopCouponLogs;
import com.ff.shop.service.ShopCouponLogsService;
import com.ff.shop.service.ShopCouponService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by Yzx on 2017/5/19.
 */
@DisallowConcurrentExecution
public class TaskShopCoupon implements Job {

    @Autowired
    protected ShopCouponLogsService shopCouponLogsService;
    @Autowired
    protected ShopCouponService shopCouponService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {


        ShopCouponLogs logs=new ShopCouponLogs();
        logs.setStatus(0);

    }
}
