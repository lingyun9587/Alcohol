package com.alcohol.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 页面跳转 公共类
 */
@Controller
public class CommonController {

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
    @RequiresRoles(value = { "店家" , "管理员"},logical= Logical.AND)
    @GetMapping(value="/udai_setting.html")
    public String udai_setting() {
        return "udai_setting";
    }
    /**
     * 收货地址
     * @return
     */
    @RequiresRoles(value = { "用户"},logical= Logical.AND)
    @GetMapping( value="/udai_address.html")
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

    /**
     * 商品点击
     * @return
     */
    @GetMapping("/item_show.html")
    public String item_show() { return "item_show"; }

    /**
     * 我的订单
     * @return
     */
    @GetMapping("/udai_order.html")
    public String udai_order(Integer productId,HttpServletRequest request) {
        request.getSession().setAttribute("productId",productId);
        return "udai_order"; }

    /**
     * 积分平台
     * @return
     */
    @GetMapping("/udai_integral.html")
    public String udai_integral() { return "udai_integral"; }

    /**
     * 网店代购
     * @return
     */
    @GetMapping("/agent_level.html")
    public String agent_level() { return "agent_level"; }

    /**
     * 帮助中心
     * @return
     */
    @GetMapping("/temp_article/udai_article4.html")
    public String udai_article4() { return "/temp_article/udai_article4"; }

    /**
     * 企业简介
     * @return
     */
    @GetMapping("/temp_article/udai_article10.html")
    public String udai_article10() { return "/temp_article/udai_article10"; }

    /**
     * 新手上路
     * @return
     */
    @GetMapping("/temp_article/udai_article5.html")
    public String udai_article5() { return "/temp_article/udai_article5"; }

    /**
     * U袋学堂
     * @return
     */
    @GetMapping("/class_room.html")
    public String class_room() { return "class_room"; }

    /**
     * 企业账号
     * @return
     */
    @GetMapping("/enterprise_id.html")
    public String enterprise_id() { return "enterprise_id"; }

    /**
     * 诚信合约
     * @return
     */
    @GetMapping("/udai_contract.html")
    public String udai_contract() { return "udai_contract"; }

    /**
     * 实时下架
     * @return
     */
    @GetMapping("/item_remove.html")
    public String item_remove() { return "item_remove"; }

    /**
     * 加入U袋
     * @return
     */
    @GetMapping("/temp_article/udai_article11.html")
    public String udai_article11() { return "/temp_article/udai_article11"; }

    /**
     * 隐私说明
     * @return
     */
    @GetMapping("/temp_article/udai_article12.html")
    public String udai_article12() { return "/temp_article/udai_article12"; }

    /**
     * 售后服务
     * @return
     */
    @GetMapping("/temp_article/udai_article1.html")
    public String udai_article1() { return "/temp_article/udai_article1"; }

    /**
     * 配送服务
     * @return
     */
    @GetMapping("/temp_article/udai_article2.html")
    public String udai_article2() { return "/temp_article/udai_article2"; }

    /**
     * 用户协议
     * @return
     */
    @GetMapping("/temp_article/udai_article3.html")
    public String udai_article3() { return "/temp_article/udai_article3"; }

    /**
     * 代销商上架教程
     * @return
     */
    @GetMapping("/temp_article/udai_article6.html")
    public String udai_article6() { return "/temp_article/udai_article6"; }

    /**
     * 分销商常见问题
     * @return
     */
    @GetMapping("/temp_article/udai_article7.html")
    public String udai_article7() { return "/temp_article/udai_article7"; }

    /**
     * 付款账户
     * @return
     */
    @GetMapping("/temp_article/udai_article8.html")
    public String udai_article8() { return "/temp_article/udai_article8"; }

    /**
     * 联系我们
     * @return
     */
    @GetMapping("/udai_about.html")
    public String udai_about() { return "/udai_about"; }

    /**
     * 资金管理
     * @return
     */
    @GetMapping("/udai_treasurer.html")
    public String udai_treasurer() { return "/udai_treasurer"; }

    /**
     * 我的优惠劵
     * @return
     */
    @GetMapping("/udai_coupon.html")
    public String udai_coupon() { return "/udai_coupon"; }

    /**
     * 我的收藏
     * @return
     */
    @GetMapping("/udai_collection.html")
    public String udai_collection() { return "/udai_collection"; }

    /**
     * 我的购物车
     * @return
     */
    @GetMapping("/udai_shopcart.html")
    public String udai_shopcart() { return "/udai_shopcart"; }

    /**
     * 结算
     * @return
     */
    @GetMapping("/udai_shopcart_pay.html")
    public String udai_shopcart_pay() { return "/udai_shopcart_pay"; }


    /**
     * 联系客服
     * @return
     */
    @GetMapping("/issues.html")
    public String issues() { return "/issues"; }

    /**
     * 退款/退货
     * @return
     */
    @GetMapping("/udai_refund.html")
    public String udai_refund() { return "/udai_refund"; }

    /**
     * 物流查询
     * @return
     */
    @GetMapping("/udai_mail_query.html")
    public String udai_mail_query() { return "/udai_mail_query"; }

    /**
     * 热卖专场
     * @return
     */
    @GetMapping("/item_sale_page.html")
    public String item_sale_page() { return "/item_sale_page"; }

    /**
     * 平台公告
     * @return
     */
    @GetMapping("/udai_notice.html")
    public String udai_notice() { return "/udai_notice"; }
}
