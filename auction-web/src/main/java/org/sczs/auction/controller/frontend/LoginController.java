package org.sczs.auction.controller.frontend;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.sczs.auction.domain.PayUser;
import org.sczs.auction.service.PayUserService;
import org.sczs.auction.utils.MsgJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    PayUserService payUserService;

    /**
    *@Description:进入登录页面
    *@create: 2018/9/11
    *@Author: SLJ
    *@Param:
    *@return:
    */
    @RequestMapping(value = "/userLogin",method = {RequestMethod.GET,RequestMethod.POST})
    public String userLogin(Model model){
        /*model.addAttribute("name","slj");*/
        return "/frontend/login";
    }


    /**
    *@Description: 登录判断
    *@create: 2018/9/11
    *@Author: SLJ
    *@Param:
    *@return:
    */
    @RequestMapping(value = "/getPayUserLogin",method = RequestMethod.POST)
    @ResponseBody
    public String getLogin(@RequestParam(value = "name")String name, @RequestParam(value = "password")String password,
                            HttpSession session){
        MsgJson msgJson = new MsgJson();
        String md5Psw = DigestUtils.md5DigestAsHex(password.getBytes());
        log.debug("md5密码为" + md5Psw);
        PayUser condition = new PayUser();
        condition.setUserName(name);
        PayUser payUser = payUserService.getPayUserByCondition(condition);
        //log.debug("用户名为:" + payUser.getUserName() + "密码为：" + payUser.getPassword());
        if (null == payUser){
            msgJson.setSuccess(false);
            msgJson.setMessage("用户不存在");
            msgJson.setObject(null);
        }else if (!payUser.getPassword().equals(md5Psw)){
            msgJson.setSuccess(false);
            msgJson.setMessage("密码错误");
            msgJson.setObject(null);
        }else {
            session.setAttribute("payUser",payUser);
            log.debug("用户名为:" + payUser.getUserName());
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
    @RequestMapping(value = "/userQuit",method = {RequestMethod.GET,RequestMethod.POST})
    public String userQuit(HttpSession session){
        session.removeAttribute("payUser");
        if (StringUtils.isEmpty(session.getAttribute("payUser"))){
            log.debug("空的 ");
        }else {
            log.debug(session.getAttribute("payUser").toString());
        }
        return "/frontend/login";
    }
}
