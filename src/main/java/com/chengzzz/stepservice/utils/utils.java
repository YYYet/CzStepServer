package com.chengzzz.stepservice.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * 资源工具类
 * </p>
 *
 * @author 等什么柠檬君
 * @since 2020/8/21
 */
public class utils {

    public static final String loginByPwd="https://sports.lifesense.com/sessions_service/login" +
            "?city=%E4%B8%8A%E6%B5%B7&province=%E4%B8%8A%E6%B5%B7%E5%B8%82&devicemodel=Magic2" +
            "&areaCode=310109&osversion=5.1.1&screenHeight=1280&provinceCode=310000&version=4.5&channel=huawei" +
            "&systemType=2&promotion_channel=huawei&screenWidth=720&requestId=d6e3e55379914cbd86ebbe975b19a877" +
            "&longitude=121.492479&screenheight=1280&os_country=CN&timezone=Asia%2FShanghai&cityCode=310100" +
            "&os_langs=zh&platform=android&clientId=8e844e28db7245eb81823132464835eb&openudid=&countryCode=" +
            "&country=%E4%B8%AD%E5%9B%BD&screenwidth=720&network_type=wifi&appType=6&area=CN&latitude=31.247221&language=zh";

    public static final String uploadMobileStep ="https://sports.lifesense.com/sport_service/sport/sport/uploadMobileStepV2" +
            "?city=%E4%B8%8A%E6%B5%B7&province=%E4%B8%8A%E6%B5%B7%E5%B8%82&devicemodel=Magic2&areaCode=310109&osversion=5.1.1" +
            "&screenHeight=1280&provinceCode=310000&version=4.5&channel=huawei&systemType=2&promotion_channel=huawei&screenWidth=720" +
            "&requestId=d6e3e55379914cbd86ebbe975b19a877&longitude=121.492479&screenheight=1280&os_country=CN" +
            "&timezone=Asia%2FShanghai&cityCode=310100&os_langs=zh" +
            "&platform=android" +
            "&clientId=8e844e28db7245eb81823132464835eb" +
            "&openudid=&countryCode=&country=%E4%B8%AD%E5%9B%BD&screenwidth=720" +
            "&network_type=wifi&appType=6&area=CN&latitude=31.247221&language=zh";

    public static  String MSG = "";


    /**
 *
 * @param string string
 * @return java.lang.String
 * @author 等什么柠檬君
 * @since 2020/8/21
 * @description    md5加密
 */

    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
}
