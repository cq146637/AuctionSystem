package org.sczs.auction.controller.frontend;

import com.alibaba.fastjson.JSONObject;
import org.sczs.auction.domain.SaleUser;
import org.sczs.auction.service.SaleUserService;
import org.sczs.auction.utils.MsgJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
*@Description: 卖家登录
*@create: 2018/9/17
*@Author: SLJ
*@Param:
*@return:
*/

@Controller
@RequestMapping("/sale_login")
public class SaleUserLoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    SaleUserService saleUserService;

    /**
     *@Description: 登录判断
     *@create: 2018/9/11
     *@Author: SLJ
     *@Param:
     *@return:
     */
    @RequestMapping(value = "/getSaleUserLogin",method = RequestMethod.POST)
    @ResponseBody
    public String getSaleUserLogin(@RequestParam(value = "name")String name, @RequestParam(value = "password")String password,
                           HttpSession session){
        MsgJson msgJson = new MsgJson();
        String md5Psw = DigestUtils.md5DigestAsHex(password.getBytes());
        log.debug("md5密码为" + md5Psw);
        SaleUser condition = new SaleUser();
        condition.setUserName(name);
        SaleUser saleUser = saleUserService.getSaleUserByCondition(condition);
        //log.debug("用户名为:" + saleUser.getUserName() + "密码为：" + saleUser.getPassword());
        if (null == saleUser){
            msgJson.setSuccess(false);
            msgJson.setMessage("用户不存在");
            msgJson.setObject(null);
        }else if (!saleUser.getPassword().equals(md5Psw)){
            msgJson.setSuccess(false);
            msgJson.setMessage("密码错误");
            msgJson.setObject(null);
        }else {
            session.setAttribute("saleUser",saleUser);
            log.debug("用户名为:" + saleUser.getUserName());
            msgJson.setSuccess(true);
        }
        Object json = JSONObject.toJSON(msgJson);
        log.debug(JSONObject.toJSONString(json));
        return JSONObject.toJSONString(json);
    }

    /**
     *@Description: 退出
     *@create: 2018/9/12
     *@Author: SLJ
     *@Param:
     *@return:
     */
    @RequestMapping(value = "/saleUserQuit",method = {RequestMethod.GET,RequestMethod.POST})
    public String userQuit(HttpSession session){
        session.removeAttribute("saleUser");
        if (StringUtils.isEmpty(session.getAttribute("saleUser"))){
            log.debug("空的 ");
        }else {
            log.debug(session.getAttribute("saleUser").toString());
        }
        return "/frontend/sale_user_login";
    }
}
