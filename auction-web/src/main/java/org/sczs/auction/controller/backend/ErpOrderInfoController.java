package org.sczs.auction.controller.backend;

import org.apache.ibatis.jdbc.Null;
import org.sczs.auction.domain.*;
import org.sczs.auction.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/erp")
public class ErpOrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private PayUserService payUserService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AuctionInfoService auctionInfoService;

    @Autowired
    private AuctionRecordService auctionRecordService;

    @RequestMapping(value = "/ShowOrderInfo")
    public String orderInfoList(Model model) {
        // 查询所有订单信息
        List<OrderInfo> orderInfoList = orderInfoService.getOrderInfoList();
        // 查询所有订单对应的商品名称、买家名称
        Map<Integer, String> productMap = new HashMap<>();
        Map<Integer, String> payUserMap = new HashMap<>();
        for (OrderInfo orderInfo: orderInfoList) {
            AuctionRecord  auctionRecord = auctionRecordService.queryOneAuctionRecord(orderInfo.getAuctionRecordId());
            if (auctionRecord != null) {
                AuctionInfo auctionInfo = auctionInfoService.queryOneAuctionInfo(auctionRecord.getAuctionId());
                PayUser payUser = payUserService.queryOnePayUser(auctionRecord.getPayUserId());
                if (auctionInfo != null) {
                    Product product = productService.queryOneProduct(auctionInfo.getProductId());
                    if (product != null) {
                        productMap.put(orderInfo.getAuctionRecordId(), product.getProductName());
                    }
                }
                if (payUser != null) {
                    payUserMap.put(orderInfo.getAuctionRecordId(), payUser.getUserName());
                }
            }
        }
        model.addAttribute("orderInfoList", orderInfoList);
        model.addAttribute("productMap", productMap);
        model.addAttribute("payUserMap", payUserMap);
        return "show_order_info";
    }

    @RequestMapping(value = "/QueryOrderInfo")
    public String queryOneOrderInfo(Model model, Integer id, String productName, String payUserName) {
        // 查询单条订单信息
        OrderInfo orderInfo = orderInfoService.queryOneOrderInfo(id);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("productName", productName);
        model.addAttribute("payUserName", payUserName);
        return "edit_order_info";
    }

    @RequestMapping(value = "/InsertOrderInfo", method = RequestMethod.GET)
    public String insertOneOrderInfo(Model model) {
        // 插入竞拍者评价
        // 查询依赖信息，先查询所有的产品，根据产品分类查询竞拍信息，在根据竞拍信息查询竞拍记录
        List<List<String>> productAuctionList = new ArrayList<>(); // Integer是竞拍记录ID，String是产品名称
        List<Product> productList = productService.getProductsList();
        for (Product product: productList) {
            List<String> tempList = new ArrayList<>();
            AuctionInfo auctionInfo = auctionInfoService.queryAuctionInfoByProductId(product.getId());
            if (auctionInfo != null) {
                AuctionRecord auctionRecord = auctionRecordService.queryAuctionRecordByAuctionId(auctionInfo.getId());
                if (auctionRecord != null) {
                    Integer id = auctionRecord.getId();
                    tempList.add(product.getProductName());
                    tempList.add(String.valueOf(id));
                    productAuctionList.add(tempList);
                }
            }
        }
        model.addAttribute("productAuctionList", productAuctionList);
        return "add_order_info";
    }

    @RequestMapping(value = "/InsertOrderInfo", method = RequestMethod.POST)
    public String insertOrderInfo(OrderInfo orderInfo) {
        // 插入竞拍者评价
        orderInfoService.insertOrderInfo(orderInfo);
        return "redirect:/erp/ShowOrderInfo.html";
    }

    @RequestMapping(value = "/UpdateOrderInfo")
    public String updateOneOrderInfo(OrderInfo orderInfo) {
        // 更新竞拍者评价
        orderInfoService.updateOneOrderInfo(orderInfo);
        return "redirect:/erp/ShowOrderInfo.html";
    }

    @RequestMapping(value = "/DeleteOrderInfo")
    public String deleteOneOrderInfo(Integer id) {
        // 删除竞拍者评价
        orderInfoService.deleteOneOrderInfo(id);
        return "redirect:/erp/ShowOrderInfo.html";
    }
}
