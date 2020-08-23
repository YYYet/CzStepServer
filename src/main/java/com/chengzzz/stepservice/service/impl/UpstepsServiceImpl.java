package com.chengzzz.stepservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengzzz.stepservice.callback.UpdateCallBack;
import com.chengzzz.stepservice.callback.mModifyCallback;
import com.chengzzz.stepservice.callback.resultCallBack;
import com.chengzzz.stepservice.dao.UpstepsDao;
import com.chengzzz.stepservice.entity.*;
import com.chengzzz.stepservice.service.UpstepsService;
import com.google.gson.Gson;
import okhttp3.*;
import org.apache.ibatis.logging.Log;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.chengzzz.stepservice.utils.utils.*;

/**
 * (Upsteps)表服务实现类
 *
 * @author 等什么柠檬君
 * @since 2020-08-21 20:56:47
 */
@Service("upstepsService")
public class UpstepsServiceImpl extends ServiceImpl<UpstepsDao, Upsteps> implements UpstepsService {

    String cookie;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UpstepsDao upstepsDao;
/**
 * 
 * @param phone phone 
 * @param pwd pwd 
 * @param Steps Steps 
 * @param flag flag 
 * @param resultCallBack resultCallBack 
 * @return void
 * @author 等什么柠檬君
 * @since 2020/8/22
 * @description    
 */

    @Override
    public void updateStep(String phone, String pwd, String Steps, int flag, resultCallBack resultCallBack) {

            Upsteps upsteps =new Upsteps(phone,pwd,Steps,flag);
            QueryWrapper<Upsteps> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("phone",phone);
            Upsteps old = upstepsDao.selectOne(queryWrapper);
            if(null != old){
                upsteps.setId(old.getId());
                upstepsDao.updateById(upsteps);
            }else {
                upstepsDao.insert(upsteps);
            }
          LoginByPwd(new PostData(phone, md5(pwd)), Steps, new UpdateCallBack() {

              @Override
              public void updateCallback(String msg) {

                  logger.info("Callback中的Msg"+msg);
                  resultCallBack.updateResult(msg);


              }

          });



    }

    
    
    
    /**
     * 
     * @param upsteps upsteps 
     * @return void
     * @author 等什么柠檬君
     * @since 2020/8/22
     * @description    
     */
    @Override
    public void updateOrInsert(Upsteps upsteps) {
        QueryWrapper<Upsteps> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("phone",upsteps.getPhone());
        Upsteps old = upstepsDao.selectOne(queryWrapper);
        if(null != old){
            upsteps.setId(old.getId());
            upstepsDao.updateById(upsteps);
        }else {
            upstepsDao.insert(upsteps);
        }
    }

    public void postAsynModifyStepsHttp(String cookies,String steps, String userid,final  mModifyCallback callback) throws ParseException {
        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        logger.info("下面开始修改" );
        int distance = Integer.parseInt(steps)/3;
        int calories = Integer.parseInt(steps) / 4;
        logger.debug( "这是修改的卡路里: "+calories );
        logger.debug("这是修改的步数: "+steps);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        int t= (int)System.currentTimeMillis();
        Timestamp times = new Timestamp(new Date().getTime());
        Date date = new Date(t);
        Gson gson = new Gson();
        //使用Gson将对象转换为json字符串
        listdata listdata = new listdata();

        Modifydata modifydata = new Modifydata();
        listdata.setCalories(calories);
        listdata.setDistance(distance);
        listdata.setMeasurementTime(  df.format(new Date())+"");
        listdata.setUpdated(System.currentTimeMillis());
        listdata.setStep(Integer.parseInt(steps));
        listdata.setUserId(Integer.parseInt(userid));
        List data = new ArrayList();
        data.add(listdata);
        modifydata.setList(data);


        logger.info("这是修改的测试时间: "+ df.format(new Date()) );
        logger.info("这是修改的测试时间戳: "+System.currentTimeMillis() );
        logger.info("这是修改的距离: "+distance );


        logger.debug("从sp中取出的cookie: "+ cookies);
        String json = gson.toJson(modifydata);

        logger.info("这是修改的json请求体: "+json );
        //MediaType  设置Content-Type 标头中包含的媒体类型值
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , json);
        Request request = new Request.Builder()
                .url(uploadMobileStep)//请求的url
                .post(requestBody)
                .addHeader("User-Agent", "Dalvik/2.1.0 (Linux; U; Android 5.1.1; Magic2 Build/LMY48Z)")
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("Host", "sports.lifesense.com")
                .addHeader("Connection", "Keep-Alive")
                .header("Cookie", cookies)
                .build();

        //创建/Call
        final Call call = okHttpClient.newCall(request);
        //加入队列 异步操作
        call.enqueue(new Callback() {
            //请求错误回调方法
            @Override
            public void onFailure(Call call, IOException e) {
                logger.error("连接失败");
                callback.onModify("修改步数时连接失败");

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {



                String json=response.body().string();  //假设从服务拿出来的json字符串，就是上面的内容

                logger.info("提交更改步数后的响应 onResponse: "+json);
                JSONObject personObj = null;
                try {
                    personObj = new JSONObject(json);
                    String code=personObj.getString("code");
                    String msg=personObj.getString("msg");

                        callback.onModify(json);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }


    private Boolean LoginByPwd(PostData upsteps,String Steps,final  UpdateCallBack callBack) {

        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        final Gson gson = new Gson();
        String json = gson.toJson(upsteps);
        logger.debug("密码登录的请求体: "+json );
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , json);
        Request request = new Request.Builder()
                .url(loginByPwd)//请求的url
                .post(requestBody)
                .addHeader("User-Agent", "Dalvik/2.1.0 (Linux; U; Android 5.1.1; Magic2 Build/LMY48Z)")
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("Host", "sports.lifesense.com")
                .addHeader("Connection", "Keep-Alive")
                .build();

        final Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                logger.info("连接失败");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (null != response.header("Set-Cookie")) {
                   cookie = response.header("Set-Cookie");
                    logger.info("登录响应的cookie: "+cookie );

                }

                String json=response.body().string();  //假设从服务拿出来的json字符串，就是上面的内容
                logger.info("登录结果的响应: "+json );
                UserInfo account = new Gson().fromJson(json, UserInfo.class);
                if (!account.getCode().equals("200")){
                    callBack.updateCallback(json);

                }else{
                    String  userid = account.getData().getUserId().toString();
                    logger.debug(userid);
                    callBack.updateCallback(cookie);

                    try {
                        postAsynModifyStepsHttp(cookie, Steps, userid, new mModifyCallback() {
                            @Override
                            public void onModify(String msg) {
                                callBack.updateCallback(msg);

                            }
                        });
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }



            }
        });

    return true;
    }


}