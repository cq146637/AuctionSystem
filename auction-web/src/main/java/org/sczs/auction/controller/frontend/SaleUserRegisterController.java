package org.sczs.auction.controller.frontend;


import com.alibaba.fastjson.JSONObject;
import org.sczs.auction.domain.SaleUser;
import org.sczs.auction.service.SaleUserService;
import org.sczs.auction.utils.MsgJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/sale_register")
public class SaleUserRegisterController {
    @Autowired
    SaleUserService saleUserService;

    @RequestMapping(value = "/userRegister",method = {RequestMethod.GET,RequestMethod.POST})
    public String userRegister(Model model){
        return "/frontend/sale_user_register";
    }

    @RequestMapping(value = "/checkUsername",method = RequestMethod.POST)
    @ResponseBody
    public String checkUsername(@RequestParam(value = "username")String name){
        MsgJson msgJson = new MsgJson();
        SaleUser condition = new SaleUser();
        condition.setUserName(name);
        SaleUser saleUser = saleUserService.getSaleUserByCondition(condition);
        if (null != saleUser){
            msgJson.setSuccess(false);
            msgJson.setMessage("该用户名已存在,不能使用");
        }else {
            msgJson.setSuccess(true);
        }
        return JSONObject.toJSONString(msgJson);
    }

    @RequestMapping(value = "/getRegister",method = RequestMethod.POST)
    public String getRegister(SaleUser saleUser){
        String psw = saleUser.getPassword();
        saleUser.setPassword(DigestUtils.md5DigestAsHex(psw.getBytes()));
        saleUser.setRoleId(2);
        saleUser.setCreditLevel("100");
        saleUserService.insert(saleUser);
        return "redirect:/sale_user_login.html";
    }
}
