package org.sczs.auction.controller.backend;

import org.sczs.auction.domain.ErpUser;
import org.sczs.auction.service.ErpUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/erp")
public class ErrorController {

    @Autowired
    ErpUserService erpUserService;

    @RequestMapping(value = "/Lock" ,method = RequestMethod.GET)
    public String getLockPage() {
        return "error/lock";
    }

    @RequestMapping(value = "/Lock" ,method = RequestMethod.POST)
    public String returnErpMain(Model model, HttpSession session, String password) {
        ErpUser erpUser = (ErpUser)session.getAttribute("erpUser");
        erpUser.setPassword(password);
        if (erpUserService.getLoginResult(erpUser) != null) {
            return "redirect:/erp/main.html";
        }
        else {
            model.addAttribute("message", 1);
            return "error/lock";
        }
    }

    @RequestMapping(value = "/400")
    public String retrun400() {
        return "error/400";
    }

    @RequestMapping(value = "/500")
    public String retrun500() {
        return "error/500";
    }
}
