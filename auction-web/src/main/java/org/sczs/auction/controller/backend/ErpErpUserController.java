package org.sczs.auction.controller.backend;

import org.sczs.auction.domain.ErpUser;
import org.sczs.auction.service.ErpUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/erp")
public class ErpErpUserController {

    @Autowired
    ErpUserService erpUserService;

    @RequestMapping(value = "/ShowErpUsers")
    public String erpUserList(Model model) {
        // 查询所有的erp用户信息
        List<ErpUser> erpUserList = erpUserService.selectAllErpUser();
        model.addAttribute("erpUserList", erpUserList);
        return "show_erp_user";
    }
}
