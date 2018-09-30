package org.sczs.auction.controller.frontend;

import org.sczs.auction.domain.*;
import org.sczs.auction.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PayUserController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductImgService productImgService;

    @Autowired
    AuctionInfoService auctionInfoService;

    @Autowired
    AuctionRecordService auctionRecordService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    OrderInfoService orderInfoService;

    @RequestMapping(value = "/pay_user_home")
    public String showPayUserHome(Model model, HttpSession session) {
        // 查到出用户的所有竞拍
        Integer id = ((PayUser) session.getAttribute("payUser")).getId();
        List<AuctionRecord> auctionRecordList = auctionRecordService.queryAuctionRecordByPayUserId(id);
        Map<Integer, Product> productMap = new HashMap<>();  // 历史竞拍记录
        Map<Integer, Product> productEndMap = new HashMap<>();  // 查找已结束的竞拍，且该用户为领先的竞拍，需要用户下订单了
        Map<Integer, Product> productJoinMap = new HashMap<>();  // 查找正在进行的竞拍，且该用户有参与的竞拍
        Map<Integer, ProductImg> productImgEndMap = new HashMap<>();
        Map<Integer, ProductImg> productImgJoinMap = new HashMap<>();
        Map<Integer, AuctionInfo> auctionInfoMap = new HashMap<>();
        Map<Integer, AuctionInfo> auctionInfoEndMap = new HashMap<>();
        Map<Integer, AuctionInfo> auctionInfoJoinMap = new HashMap<>();
        Map<Integer, Category> categoryEndMap = new HashMap<>();
        Map<Integer, Category> categoryJoinMap = new HashMap<>();
        Map<Integer, Category> categoryMap = new HashMap<>();


        for (AuctionRecord auctionRecord: auctionRecordList) {
            AuctionInfo auctionInfo = auctionInfoService.queryOneAuctionInfo(auctionRecord.getAuctionId());
            if (auctionInfo != null) {
                Product product = productService.queryOneProduct(auctionInfo.getProductId());
                if (product != null) {
                    ProductImg productImg = productImgService.getOneProductImgByProductId(product.getId());
                    Category category = categoryService.queryOneCategory(product.getCategoryId());
                    List<OrderInfo> orderInfoList = orderInfoService.queryOrderInfoByAuctionRecordId(auctionRecord.getId());
                    if (productImg != null) {
                        if (auctionRecord.getStatus().equals("领先") && auctionInfo.getStatus().equals("已结束") && orderInfoList.size() == 0) {
                            // 查找已结束的竞拍，且该用户为领先的竞拍，需要用户下订单了
                            productEndMap.put(auctionRecord.getId(), product);
                            productImgEndMap.put(auctionRecord.getId(), productImg);
                            auctionInfoEndMap.put(auctionRecord.getId(), auctionInfo);
                            categoryEndMap.put(auctionRecord.getId(), category);
                        } else if (auctionInfo.getStatus().equals("正在进行")) {
                            // 查找正在进行的竞拍，且该用户有参与的竞拍
                            productJoinMap.put(auctionRecord.getId(), product);
                            productImgJoinMap.put(auctionRecord.getId(), productImg);
                            auctionInfoJoinMap.put(auctionRecord.getId(), auctionInfo);
                            categoryJoinMap.put(auctionRecord.getId(), category);
                        }
                    }
                    // 所有竞拍记录
                    productMap.put(auctionRecord.getId(), product);
                    auctionInfoMap.put(auctionRecord.getId(), auctionInfo);
                    categoryMap.put(auctionRecord.getId(), category);
                }
            }
        }
        model.addAttribute("auctionRecordList", auctionRecordList);

        model.addAttribute("productMap", productMap);
        model.addAttribute("productEndMap", productEndMap);
        model.addAttribute("productJoinMap", productJoinMap);

        model.addAttribute("productImgEndMap", productImgEndMap);
        model.addAttribute("productImgJoinMap", productImgJoinMap);

        model.addAttribute("auctionInfoMap", auctionInfoMap);
        model.addAttribute("auctionInfoEndMap", auctionInfoEndMap);
        model.addAttribute("auctionInfoJoinMap", auctionInfoJoinMap);

        model.addAttribute("categoryEndMap", categoryEndMap);
        model.addAttribute("categoryJoinMap", categoryJoinMap);
        model.addAttribute("categoryMap", categoryMap);

        model.addAttribute("joinMapSize", productJoinMap.size());
        model.addAttribute("endMapSize", productEndMap.size());
        return "frontend/pay_user_center";
    }
}
