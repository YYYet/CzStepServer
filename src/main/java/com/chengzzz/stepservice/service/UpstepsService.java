package com.chengzzz.stepservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chengzzz.stepservice.callback.resultCallBack;
import com.chengzzz.stepservice.entity.Upsteps;

/**
 * (Upsteps)表服务接口
 *
 * @author 等什么柠檬君
 * @since 2020-08-21 20:56:46
 */
public interface UpstepsService extends IService<Upsteps> {
    void updateStep(String phone, String pwd, String Steps, int flag, resultCallBack resultCallBack);

    void updateOrInsert(Upsteps upsteps);

}