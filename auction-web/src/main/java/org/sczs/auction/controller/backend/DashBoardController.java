package org.sczs.auction.controller.backend;

import org.sczs.auction.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/erp")
public class DashBoardController {

    @Autowired
    OrderInfoService orderInfoService;

    @Autowired
    AuctionInfoService auctionInfoService;

    @Autowired
    PayUserService payUserService;

    @Autowired
    SaleUserService saleUserService;

    @RequestMapping(value = "/main")
    public String mainPage(Model model) {
        Integer orderInfoCount =  orderInfoService.getOrderInfoCount();
        Integer auctionInfoCount = auctionInfoService.getAuctionInfoCount();
        Integer payUserCount = payUserService.getPayUserCount();
        Integer saleUserCount = saleUserService.getSaleUserCount();
        model.addAttribute("orderInfoCount", orderInfoCount);
        model.addAttribute("auctionInfoCount", auctionInfoCount);
        model.addAttribute("userCount", payUserCount + saleUserCount);
        return "main";
    }
}
