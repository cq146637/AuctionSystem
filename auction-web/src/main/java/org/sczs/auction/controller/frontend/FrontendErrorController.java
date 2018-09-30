package org.sczs.auction.controller.frontend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class FrontendErrorController {
    private static final Logger log = LoggerFactory.getLogger(FrontendErrorController.class);

    @RequestMapping(value = "/error")
    public String getNullError(Model model, HttpServletRequest request){
        model.addAttribute("errorCode",request.getAttribute("javax.servlet.error.status_code"));
        model.addAttribute("errorMessage",request.getAttribute("javax.servlet.error.message"));
        model.addAttribute("errorType",request.getAttribute("javax.servlet.error.exception_type"));
        return "frontend/error/error";
    }

}
