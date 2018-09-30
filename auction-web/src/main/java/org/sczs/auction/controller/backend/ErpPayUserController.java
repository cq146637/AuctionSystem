package org.sczs.auction.controller.backend;

import org.sczs.auction.domain.PayUser;
import org.sczs.auction.service.PayUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/erp")
public class ErpPayUserController {

    @Autowired
    private PayUserService payUserService;

    @RequestMapping(value = "/ShowPayUsers")
    public String payUserList(Model model) {
        // 查询所有的竞拍者信息
        List<PayUser> payUserList = payUserService.getPayUserList();
        model.addAttribute("payUserList", payUserList);
        return "show_pay_user";
    }
}
