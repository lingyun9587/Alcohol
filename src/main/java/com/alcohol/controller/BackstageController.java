package com.alcohol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BackstageController {

    /**
     * 后台首页
     * @return
     */
    @GetMapping("/backstage/index.html")
    public String index() { return "/backstage/index"; }

    /**
     *
     * @return
     */
    @GetMapping("/backstage/ClassInfo.html")
    public String ClassInfo() { return "/backstage/ClassInfo"; }
    /**
     *
     * @return
     */
    @GetMapping("/backstage/comment.html")
    public String comment() { return "/backstage/comment"; }
    /**
     *
     * @return
     */
    @GetMapping("/backstage/commodityAdd.html")
    public String commodityAdd() { return "/backstage/commodityAdd"; }


    /**
     *
     * @return
     */
    @GetMapping("/commodityAdd.html")
    public String commodityAdd1() { return "/commodityAdd"; }

    /**
     *
     * @return
     */
    @GetMapping("/backstage/evaluation.html")
    public String evaluation() { return "/backstage/evaluation"; }
    /**
     *
     * @return
     */
    @GetMapping("/backstage/goodsAnalysis.html")
    public String goodsAnalysis() { return "/backstage/goodsAnalysis"; }
    /**
     *
     * @return
     */
    @GetMapping("/backstage/kindeditor.html")
    public String kindeditor() { return "/backstage/kindeditor"; }
    /**
     *
     * @return
     */
    @GetMapping("/backstage/long.html")
    public String longs() { return "/backstage/long"; }

    /**
     *
     * @return
     */
    @GetMapping("/backstage/notice.html")
    public String notice() { return "/backstage/notice"; }
    /**
     *
     * @return
     */
    @GetMapping("/backstage/orderList.html")
    public String orderList() { return "/backstage/orderList"; }
    /**
     *
     * @return
     */
    @GetMapping("/backstage/productList.html")
    public String productList() { return "/backstage/productList"; }
    /**
     *
     * @return
     */
    @GetMapping("/backstage/salesReport.html")
    public String salesReport() { return "/backstage/salesReport"; }
    /**
     *
     * @return
     */
    @GetMapping("/backstage/shopList.html")
    public String shopList() { return "/backstage/shopList"; }
    /**
     *
     * @return
     */
    @GetMapping("/backstage/stock.html")
    public String stock() { return "/backstage/stock"; }
    /**
     *
     * @return
     */
    @GetMapping("/backstage/userList.html")
    public String userList() { return "/backstage/userList"; }
    /**
     *
     * @return
     */
    @GetMapping("/backstage/View_transaction .html")
    public String View_transaction() { return "/backstage/View_transaction"; }
    /**
     *
     * @return
     */
    @GetMapping("/backstage/wareRecord.html")
    public String wareRecord() { return "/backstage/wareRecord"; }

}
