package org.sczs.auction.controller.frontend;


import com.alibaba.fastjson.JSONObject;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.sczs.auction.domain.PayUser;
import org.sczs.auction.service.PayUserService;
import org.sczs.auction.utils.MsgJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 *@description:
 *@author: SLJ
 *@create: 2018/9/11
 *@modifiedby:
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    PayUserService payUserService;

    @RequestMapping(value = "/userRegister",method = {RequestMethod.GET,RequestMethod.POST})
    public String userRegister(Model model){
        return "/frontend/register";
    }

    @RequestMapping(value = "/checkUsername",method = RequestMethod.POST)
    @ResponseBody
    public String checkUsername(@RequestParam(value = "username")String name){
        MsgJson msgJson = new MsgJson();
        PayUser condition = new PayUser();
        condition.setUserName(name);
        PayUser payUser = payUserService.getPayUserByCondition(condition);
        if (null != payUser){
            msgJson.setSuccess(false);
            msgJson.setMessage("该用户名已存在,不能使用");
        }else {
            msgJson.setSuccess(true);
        }
        return JSONObject.toJSONString(msgJson);
    }

    @RequestMapping(value = "/getRegister",method = RequestMethod.POST)
    public String getRegister(PayUser payUser){
        String psw = payUser.getPassword();
        payUser.setPassword(DigestUtils.md5DigestAsHex(psw.getBytes()));
        payUser.setRoleId(1);
        payUser.setCreateTime(new Date());
        payUserService.insert(payUser);
        return "redirect:/login/userLogin.html";
    }
}