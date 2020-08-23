package com.chengzzz.stepservice.schedu;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chengzzz.stepservice.callback.resultCallBack;
import com.chengzzz.stepservice.dao.UpstepsDao;
import com.chengzzz.stepservice.entity.Upsteps;
import com.chengzzz.stepservice.service.UpstepsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 定时任务
 * </p>
 *
 * @author 等什么柠檬君
 * @since 2020/8/22
 */

@Component
public class UpdateSchedu {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UpstepsDao upstepsDao;
    @Autowired
     private  UpstepsService upstepsService;
    @Scheduled(cron = "0 0 12 * * ?")
    public void schedu() {
        QueryWrapper<Upsteps> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("flag",1);
        List<Upsteps> userList = upstepsDao.selectList(queryWrapper);
        logger.info("时间到了"+userList);
        for (Upsteps single : userList){
            upstepsService.updateStep(single.getPhone(), single.getPassword(), single.getSteps(), single.getFlag(), new resultCallBack() {
                @Override
                public void updateResult(String msg) {
                    logger.info("用户:"+single.getPhone()+" 步数:"+single.getSteps()+" 返回："+msg);
                }
            });
        }

    }

}


