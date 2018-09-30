package org.sczs.auction.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ExceptionResolver implements HandlerExceptionResolver {
    public static final Logger logger= LoggerFactory.getLogger(ExceptionResolver.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        //1.打印控制台
        ex.printStackTrace();
        //2.写日志
        logger.error("系统发生异常",ex);
        //4.给用户展示友好界面
        ModelAndView modelandView = new ModelAndView();
        modelandView.setViewName("frontend/error/404");
        return modelandView;
    }
}
