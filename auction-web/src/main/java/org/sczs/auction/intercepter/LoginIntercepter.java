package org.sczs.auction.intercepter;

import org.apache.commons.lang.StringUtils;
import org.sczs.auction.domain.ErpUser;
import org.sczs.auction.domain.PayUser;
import org.sczs.auction.domain.RolePermission;
import org.sczs.auction.domain.SaleUser;
import org.sczs.auction.service.RolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class LoginIntercepter extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(LoginIntercepter.class);

    @Autowired
    RolePermissionService rolePermissionService;

    public LoginIntercepter() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        String contextPath = session.getServletContext().getContextPath();
        String uri = StringUtils.remove(request.getRequestURI(),contextPath);
        log.debug("uri是 ---->{}",uri);
        PayUser payUser = (PayUser) session.getAttribute("payUser");
        SaleUser saleUser = (SaleUser) session.getAttribute("saleUser");
        ErpUser erpUser = (ErpUser) session.getAttribute("erpUser");
        if(payUser == null){
            List<String> list_uri = initPayUserUri();
            for (String inListUri: list_uri) {
                if (uri.startsWith(inListUri)){
                    response.sendRedirect("login/userLogin.html");
                    return false;
                }
            }
        }
        if(saleUser == null){
            List<String> list_uri = initSaleUserUri();
            for (String inListUri: list_uri) {
                if (uri.startsWith(inListUri)){
                    response.sendRedirect("/sale_user_login.html");
                    return false;
                }
            }
        }
        if (payUser == null && saleUser == null && erpUser == null){
            for (String inListUri: initErpUserUri()) {
                if (uri.startsWith(inListUri)){
                    response.sendRedirect("/erp/Login.html");
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

    /*初始化竞拍者登陆拦截的uri*/
    public List<String> initPayUserUri(){
        List<String> listUri = new ArrayList<>();
        listUri.add("/offerPrice.html");
        listUri.add("/submitRemark.html");
        listUri.add("/pay_user_home.html");
        listUri.add("/order.html");
        return listUri;
    }

    /*初始化卖家登陆拦截的uri*/
    public List<String> initSaleUserUri(){
        List<String> listUri = new ArrayList<>();
        listUri.add("/sale_user_home.html");
        listUri.add("/release.html");
        return listUri;
    }

    /*初始化erp用户所有的uri*/
    public List<String> initErpUserUri(){
        List<String> listUri = new ArrayList<>();
        List<RolePermission> rolePermissionList = rolePermissionService.selectAll();
        for (RolePermission rolePermission : rolePermissionList) {
            listUri.add(rolePermission.getLink());
        }
        return listUri;
    }
}
