package org.sczs.auction.controller.backend;

import org.sczs.auction.domain.SaleUser;
import org.sczs.auction.service.SaleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/erp")
public class ErpSaleUserController {

    @Autowired
    private SaleUserService saleUserService;

    @RequestMapping(value = "/ShowSaleUsers")
    public String saleUserList(Model model) {
        // 查询所有的拍卖者信息
        List<SaleUser> saleUserList = saleUserService.selectAllSaleUser();
        model.addAttribute("saleUserList", saleUserList);
        return "show_sale_user";
    }
}
