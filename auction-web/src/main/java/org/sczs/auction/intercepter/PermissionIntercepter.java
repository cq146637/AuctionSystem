package org.sczs.auction.intercepter;

import org.apache.commons.lang.StringUtils;
import org.sczs.auction.domain.ErpUser;
import org.sczs.auction.domain.PayUser;
import org.sczs.auction.domain.RolePermission;
import org.sczs.auction.domain.SaleUser;
import org.sczs.auction.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class PermissionIntercepter extends HandlerInterceptorAdapter {

    @Autowired
    RolePermissionService rolePermissionService;

    public PermissionIntercepter() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        String contextPath = session.getServletContext().getContextPath();
        String uri = StringUtils.remove(request.getRequestURI(),contextPath);
        PayUser payUser = (PayUser) session.getAttribute("payUser");
        SaleUser saleUser = (SaleUser) session.getAttribute("saleUser");
        ErpUser erpUser = (ErpUser) session.getAttribute("erpUser");

        if (erpUser == null && (payUser != null || saleUser != null)){
            if (uri.startsWith("erp")){
                return false;
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
