package com.alcohol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @GetMapping("/index.html")
    @ResponseBody
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("index");
        return mv;
    }

    /**
     * 登录
     * @return
     */
    @GetMapping("/login.html")
    public String login() {
        return "login";
    }
    /**
     * 我的U袋
     * @return
     */
    @GetMapping("/udai_welcome.html")
    public String udai_welcome() {
        return "udai_welcome";
    }
    /**
     * 个人资料
     * @return
     */
    @GetMapping("/udai_setting.html")
    public String udai_setting() {
        return "udai_setting";
    }
    /**
     * 收货地址
     * @return
     */
    @GetMapping("/udai_address.html")
    public String udai_address() {
        return "udai_address";
    }
    /**
     * 修改支付密码
     * @return
     */
    @GetMapping("/udai_paypwd_modify.html")
    public String udai_paypwd_modify() {
        return "udai_paypwd_modify";
    }
    /**
     * 修改支付密码--直接修改
     * @return
     */
    @GetMapping("/udai_modifypay_step.html")
    public String udai_modifypay_step() {
        return "udai_modifypay_step";
    }
    /**
     * 修改支付密码--输入旧密码
     * @return
     */
    @GetMapping("/udai_modifypay_step1.html")
    public String udai_modifypay_step1() {
        return "udai_modifypay_step1";
    }
    /**
     * 修改支付密码--输入新密码
     * @return
     */
    @GetMapping("/udai_modifypay_step2.html")
    public String udai_modifypay_step2() {
        return "udai_modifypay_step2";
    }
    /**
     * 修改支付密码--完成
     * @return
     */
    @GetMapping("/udai_modifypay_step3.html")
    public String udai_modifypay_step3() {
        return "udai_modifypay_step3";
    }
    /**
     * 修改登录密码
     * @return
     */
    @GetMapping("/udai_pwd_modify.html")
    public String udai_pwd_modify() {
        return "udai_pwd_modify";
    }
    /**
     * 修改登录密码--输入旧密码
     * @return
     */
    @GetMapping("/udai_modifypwd_step1.html")
    public String udai_modifypwd_step1() { return "udai_modifypwd_step1"; }
    /**
     * 修改登录密码--输入新密码
     * @return
     */
    @GetMapping("/udai_modifypwd_step2.html")
    public String udai_modifypwd_step2() { return "udai_modifypwd_step2"; }
    /**
     * 修改登录密码--完成
     * @return
     */
    @GetMapping("/udai_modifypwd_step3.html")
    public String udai_modifypwd_step3() { return "udai_modifypwd_step3"; }
    /**
     * 商品
     * @return
     */
    @GetMapping("/item_category.html")
    public String item_category() { return "item_category"; }
}
