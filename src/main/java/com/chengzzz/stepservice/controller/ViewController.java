package com.chengzzz.stepservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chengzzz.stepservice.callback.resultCallBack;
import com.chengzzz.stepservice.dao.UpstepsDao;
import com.chengzzz.stepservice.entity.Upsteps;
import com.chengzzz.stepservice.service.UpstepsService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * <p>
 * ViewController
 * </p>
 *
 * @author 等什么柠檬君
 * @since 2020/8/10
 */

@RestController
@Slf4j
@RequestMapping(value="Service")
public class ViewController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    static  String tx = "手机号非法";
    @Autowired
    private UpstepsService upstepsService;
    @Autowired
    private UpstepsDao upstepsDao;
    @RequestMapping(value="updateStep",method = RequestMethod.GET)
    public String login(@RequestParam("phone") String phone, @RequestParam("password")String password,@RequestParam("steps")String step,@RequestParam("flag")int flag, Model model){


     if (phone.length() != 11){
         return "{\"code\":510,\"msg\":\"手机号长度错误\"}";
     }else {
         if (flag==1) {
             Upsteps upsteps =new Upsteps(phone,password,step,flag,"");
             upstepsService.updateOrInsert(upsteps);
             return "{\"code\":508,\"msg\":\"已加入数据库进行定时任务\"}";
         } else if (flag==0){
            upstepsService.updateStep(phone, password, step,flag, new resultCallBack() {
                @Override
                public void updateResult(String msg) {
                    getMsg(msg);
                    logger.info("控制器返回的msg"+msg);
                }
            });

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return tx;
        }else {
             return "{\"code\":509,\"msg\":\"参数错误\"}";
         }


     }


    }
    public static void getMsg(String msg){
       tx=msg;
    }
}


