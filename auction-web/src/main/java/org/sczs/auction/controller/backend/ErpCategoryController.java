package org.sczs.auction.controller.backend;

import org.sczs.auction.domain.Category;
import org.sczs.auction.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/erp")
public class ErpCategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/ShowCategorys")
    public String categoryList(Model model) {
        // 查询所有分类数据
        List<Category> categoryList = categoryService.getCategoryList();
        model.addAttribute("categoryList", categoryList);
        return "show_category";
    }

    @RequestMapping(value = "/InsertCategory", method = RequestMethod.GET)
    public String insertOneCategory() {
        // 插入分类信息
        return "add_category";
    }

    @RequestMapping(value = "/InsertCategory", method = RequestMethod.POST)
    public String insertOneCategory(Category category) {
        // 插入分类记录
        categoryService.insertOneCategory(category);
        return "redirect:/erp/ShowCategorys.html";
    }

    @RequestMapping(value = "/UpdateCategory")
    public String updateOneCategory(Category category) {
        // 更新分类信息
        categoryService.updateOneCategory(category);
        return "redirect:/erp/ShowCategorys.html";
    }

    @RequestMapping(value = "/DeleteCategory")
    public String deleteOneCategory(Integer id) {
        // 删除分类信息
        categoryService.deleteOneCategory(id);
        return "redirect:/erp/ShowCategorys.html";
    }
}
