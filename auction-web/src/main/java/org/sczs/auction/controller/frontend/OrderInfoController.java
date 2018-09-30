package org.sczs.auction.controller.frontend;

import org.sczs.auction.domain.*;
import org.sczs.auction.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class OrderInfoController {

    @Autowired
    OrderInfoService orderInfoService;

    @Autowired
    ProductService productService;

    @Autowired
    AuctionInfoService auctionInfoService;

    @Autowired
    AuctionRecordService auctionRecordService;

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String fillOrderInfo(Model model, Integer id) {
        AuctionRecord auctionRecord = auctionRecordService.queryOneAuctionRecord(id);
        AuctionInfo auctionInfo = auctionInfoService.queryOneAuctionInfo(auctionRecord.getAuctionId());
        Product product = productService.queryOneProduct(auctionInfo.getProductId());
        model.addAttribute("auctionRecord", auctionRecord);
        model.addAttribute("auctionInfo", auctionInfo);
        model.addAttribute("product", product);
        return "/frontend/order";
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String insertOrderInfo(HttpSession session, OrderInfo orderInfo) {
        Integer id = ((PayUser)session.getAttribute("payUser")).getId();
        orderInfoService.insertOrderInfo(orderInfo);
        return "redirect:/pay_user_home.html?id=" + id;
    }

}
