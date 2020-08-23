package com.chengzzz.stepservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * (Upsteps)表实体类
 *
 * @author 等什么柠檬君
 * @since 2020-08-21 20:56:44
 */
@SuppressWarnings("serial")
@TableName("upsteps")
public class Upsteps extends Model<Upsteps> {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String phone;

    private String password;

    private String steps;

    private Integer flag;

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Upsteps(Integer id, String phone, String password, String steps, Integer flag, String msg) {
        this.id = id;
        this.phone = phone;
        this.password = password;
        this.steps = steps;
        this.flag = flag;
        this.msg = msg;
    }

    public Upsteps(String phone, String password, String steps, Integer flag, String msg) {
        this.phone = phone;
        this.password = password;
        this.steps = steps;
        this.flag = flag;
        this.msg = msg;
    }

    public Upsteps(String phone, String password, String steps, Integer flag) {
        this.phone = phone;
        this.password = password;
        this.steps = steps;
        this.flag = flag;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.phone;
    }
}