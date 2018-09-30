package org.sczs.auction.controller.backend;

import org.sczs.auction.domain.ErpUser;
import org.sczs.auction.service.ErpUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/erp")
public class ErpLoginController {
    private static final Logger log = LoggerFactory.getLogger(ErpLoginController.class);

    @Autowired
    ErpUserService erpUserService;

    @RequestMapping(value = "/Login",method = RequestMethod.GET)
    public String getLogin(Model model, String message){
        model.addAttribute("message", message);
        return "login";
    }

    @RequestMapping(value = "/Login",method = RequestMethod.POST)
    public String getLogin(ErpUser erpUser, HttpSession session){
        ErpUser erpUser1 = erpUserService.getLoginResult(erpUser);
        if (erpUser1 != null) {
            session.setAttribute("erpUser",erpUser1);
            return "redirect:/erp/main.html";
        }
        else {
            return "redirect:/erp/Login.html?message='1'";
        }
    }
}
