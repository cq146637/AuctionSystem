package org.sczs.auction.controller.backend;

import org.sczs.auction.domain.Category;
import org.sczs.auction.domain.Product;
import org.sczs.auction.domain.SaleUser;
import org.sczs.auction.service.CategoryService;
import org.sczs.auction.service.ProductService;
import org.sczs.auction.service.SaleUserService;
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
public class ErpProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SaleUserService saleUserService;

    @RequestMapping(value = "/ShowProducts")
    public String productsList(Model model) {
        // 查询所有商品信息
        List<Product> productList = productService.getProductsList();
        // 查询所有商品对应的分类名称、和拍卖者名称
        Map<Integer, String> categoryMap = new HashMap<>();
        Map<Integer, String> saleUserMap = new HashMap<>();
        for (Product product: productList) {
            Category category = categoryService.queryOneCategory(product.getCategoryId());
            if (category != null) {
                categoryMap.put(product.getCategoryId(), category.getCategoryName());
            }
            SaleUser saleUser = saleUserService.queryOneSaleUser(product.getSaleUserId());
            if (saleUser != null) {
                saleUserMap.put(product.getSaleUserId(), saleUser.getUserName());
            }
        }
        model.addAttribute("productList", productList);
        model.addAttribute("categoryMap", categoryMap);
        model.addAttribute("saleUserMap", saleUserMap);
        return "show_product";
    }

    @RequestMapping(value = "/QueryProduct")
    public String queryOneProduct(Model model, Integer id, String categoryName, String saleUserName) {
        // 查询单条拍卖的详细信息
        Product product = productService.queryOneProduct(id);
        model.addAttribute("product", product);
        // 还需要物品ID
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("saleUserName", saleUserName);
        return "edit_product";
    }

    @RequestMapping(value = "/InsertProduct", method = RequestMethod.GET)
    public String insertOneAuctionRecord(Model model) {
        // 插入拍卖物品信息
        // 查询依赖信息
        List<Category> categoryList = categoryService.getCategoryList();
        List<SaleUser> saleUserList = saleUserService.selectAllSaleUser();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("saleUserList", saleUserList);
        return "add_product";
    }

    @RequestMapping(value = "/InsertProduct", method = RequestMethod.POST)
    public String insertOneProduct(Product product) {
        // 插入拍卖物品信息
        productService.insertOneProduct(product);
        return "redirect:/erp/ShowProducts.html";
    }

    @RequestMapping(value = "/UpdateProduct")
    public String updateOneProduct(Product product) {
        // 更新拍卖物品信息
        productService.updateOneProduct(product);
        return "redirect:/erp/ShowProducts.html";
    }

    @RequestMapping(value = "/DeleteProduct")
    public String deleteOneProduct(Integer id) {
        // 删除拍卖物品信息
        productService.deleteOneProduct(id);
        return "redirect:/erp/ShowProducts.html";
    }
}
