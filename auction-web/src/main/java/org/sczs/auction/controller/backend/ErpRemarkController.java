package org.sczs.auction.controller.backend;

import org.sczs.auction.domain.PayUser;
import org.sczs.auction.domain.Product;
import org.sczs.auction.domain.Remark;
import org.sczs.auction.service.PayUserService;
import org.sczs.auction.service.ProductService;
import org.sczs.auction.service.RemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/erp")
public class ErpRemarkController {

    @Autowired
    private RemarkService remarkService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PayUserService payUserService;

    @RequestMapping(value = "/ShowRemarks")
    public String remarkList(Model model) {
        // 查询所有评价信息
        List<Remark> remarkList = remarkService.getRemarkList();
        // 查询所有评价对应的商品名称和竞拍者名称
        Map<Integer, String> productMap = new HashMap<>();
        Map<Integer, String> payUserMap = new HashMap<>();
        for (Remark remark: remarkList) {
            Product product = productService.queryOneProduct(remark.getProductId());
            if (product != null) {
                productMap.put(remark.getProductId(), product.getProductName());
            }
            PayUser payUser = payUserService.queryOnePayUser(remark.getPayUserId());
            if (payUser != null) {
                payUserMap.put(remark.getPayUserId(), payUser.getUserName());
            }
        }
        model.addAttribute("remarkList", remarkList);
        model.addAttribute("productMap", productMap);
        model.addAttribute("payUserMap", payUserMap);
        return "show_remark";
    }

    @RequestMapping(value = "/QueryRemark")
    public String queryOneRemark(Model model, Integer id, String productName, String payUserName) {
        // 查询单条评价信息
        Remark remark = remarkService.queryOneRemark(id);
        model.addAttribute("remark", remark);
        // 还需要物品ID
        model.addAttribute("productName", productName);
        model.addAttribute("payUserName", payUserName);
        return "edit_remark";
    }

    @RequestMapping(value = "/InsertRemark", method = RequestMethod.GET)
    public String insertOneRemark(Model model) {
        // 插入竞拍者评价
        // 查询依赖信息商品名称、竞拍者
        List<Product> productList = productService.getProductsList();
        List<PayUser> payUserList = payUserService.getPayUserList();
        model.addAttribute("productList", productList);
        model.addAttribute("payUserList", payUserList);
        return "add_remark";
    }

    @RequestMapping(value = "/InsertRemark", method = RequestMethod.POST)
    public String insertOneRemark(Remark remark) {
        // 插入竞拍者评价
        remarkService.insertOneRemark(remark);
        return "redirect:/erp/ShowRemarks.html";
    }

    @RequestMapping(value = "/UpdateRemark")
    public String updateOneRemark(Remark remark) {
        // 更新竞拍者评价
        remarkService.updateOneRemark(remark);
        return "redirect:/erp/ShowRemarks.html";
    }

    @RequestMapping(value = "/DeleteRemark")
    public String deleteOneRemark(Integer id) {
        // 删除竞拍者评价
        remarkService.deleteOneRemark(id);
        return "redirect:/erp/ShowRemarks.html";
    }
}
