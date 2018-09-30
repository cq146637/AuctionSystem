package org.sczs.auction.controller.frontend;

import org.sczs.auction.domain.*;
import org.sczs.auction.dto.AuctionInfoShow;
import org.sczs.auction.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
*@Description: 首页
*@create: 2018/9/12
*@Author: SLJ
*@Param:
*@return:
*/
@Controller
@RequestMapping("")
public class IndexController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    //商品展示
    @Autowired
    AuctionInfoShowService auctionInfoShowService;
    //商品详情
    @Autowired
    AuctionInfoService auctionInfoService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductImgService productImgService;
    @Autowired
    SaleUserService saleUserService;
    @Autowired
    AuctionRecordService auctionRecordService;
    @Autowired
    RemarkService remarkService;
    @Autowired
    PayUserService payUserService;
    @Autowired
    CategoryService categoryService;

    /**
    *@Description: 首页跳转
    *@create: 2018/9/13
    *@Author: SLJ
    *@Param:
    *@return:
    */
    @RequestMapping(value = ("/index"))
    public String getIndex(Model model, HttpSession session){
        List<AuctionInfoShow> auctionInfoShowingList = auctionInfoShowService.selectShowingAll();
        List<AuctionInfoShow> auctionInfoPreShowList = auctionInfoShowService.selectPreShowAll();
        List<AuctionInfoShow> HouseAuctionInfoList = auctionInfoShowService.selectAllByCategory("房产");
        List<AuctionInfoShow> CarAuctionInfoList = auctionInfoShowService.selectAllByCategory("机动车");
        List<AuctionInfoShow> BoatAuctionInfoList = auctionInfoShowService.selectAllByCategory("船舶");
        List<Category> categoryList = categoryService.getCategoryList();

        model.addAttribute("auctionInfoShowList",auctionInfoShowingList);
        model.addAttribute("auctionInfoPreShowList",auctionInfoPreShowList);
        model.addAttribute("HouseAuctionInfoList",HouseAuctionInfoList);
        model.addAttribute("CarAuctionInfoList",CarAuctionInfoList);
        model.addAttribute("BoatAuctionInfoList",BoatAuctionInfoList);
        session.setAttribute("categoryList",categoryList);
        return "frontend/index";
    }

    /**
    *@Description: 分类页跳转
    *@create: 2018/9/13
    *@Author: SLJ
    *@Param:
    *@return:
    */
    @RequestMapping(value = "/category")
    public String getCategory(@RequestParam(value = "categoryName")String categoryName, Model model){
        log.debug("参数分类名为：" + categoryName);
        List<AuctionInfoShow> auctionInfoList = auctionInfoShowService.selectAllByCategory(categoryName);
        model.addAttribute("categoryName",categoryName);
        model.addAttribute("auctionInfoList",auctionInfoList);
        return "frontend/category";
    }

    /**
    *@Description: 商品详情页跳转
    *@create: 2018/9/13
    *@Author: SLJ
    *@Param:
    *@return:
    */
    @RequestMapping(value = "/getProductDetails")
    public String getProductDetails(@RequestParam(value = "productId")Integer productId,Model model) throws ParseException {
        List<ProductImg> productImgList = productImgService.selectByProductId(productId);
        Product product = productService.queryOneProduct(productId);
        AuctionInfo auctionInfo = auctionInfoService.getAuctionInfoByProductId(productId);
        //将日期进行转化，详情倒计时
        auctionInfo.setBeginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(auctionInfo.getBeginTime())));
        auctionInfo.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(auctionInfo.getEndTime())));
        SaleUser saleUser = saleUserService.getSaleUserByProductId(productId);
        List<AuctionRecord> recordList = auctionRecordService.queryAuctionRecordByProductId(productId);
        List<Remark> remarkList = remarkService.getRemarkAllByProductId(productId);
        // 查询竞拍者ID对应的名称
        Map<Integer, String> payUserMap = new HashMap<>();
        for (Remark remark:remarkList) {
            PayUser payUser = payUserService.queryOnePayUser(remark.getPayUserId());
            if (payUser != null) {
                payUserMap.put(remark.getPayUserId(), payUser.getUserName());
            }
        }

        model.addAttribute("productImgList",productImgList);
        model.addAttribute("product",product);
        model.addAttribute("auctionInfo",auctionInfo);
        model.addAttribute("saleUser",saleUser);
        model.addAttribute("recordList",recordList);
        model.addAttribute("remarkList",remarkList);
        model.addAttribute("payUserMap",payUserMap);

        return "frontend/product_details";
    }

    @RequestMapping(value = "submitRemark")
    public String submitRemark(Remark remark,Model model){
        if (!StringUtils.isEmpty(remark.getPayUserId()) && !StringUtils.isEmpty(remark.getContent())){
            remark.setCreateTime(new Date());
            log.debug(remark.getPayUserId().toString()+remark.getProductId().toString());
            remarkService.insertOneRemark(remark);
            model.addAttribute("productId",remark.getProductId());
        }else {

        }
        return "redirect:getProductDetails.html";

    }

    @RequestMapping(value = "offerPrice")
    public String offerPrice(@RequestParam(value = "productId")Integer productId, AuctionRecord auctionRecord,Model model){
        if (!StringUtils.isEmpty(auctionRecord)){
            AuctionRecord leadRecord = auctionRecordService.queryAuctionRecordByAuctionId(auctionRecord.getAuctionId());
            if (!StringUtils.isEmpty(leadRecord)){
                leadRecord.setStatus("淘汰");
                auctionRecordService.updateOneActionRecord(leadRecord);
            }
            auctionRecordService.insertOneAuctionRecord(auctionRecord);
            model.addAttribute("productId",productId);
        }else {

        }
        return "redirect:getProductDetails.html";

    }

    @RequestMapping(value = "sale_user_login")
    public String getSaleUserLogin(){
        return "frontend/sale_user_login";
    }

    @RequestMapping(value = "searchProduct")
    public String searchProduct(Model model, HttpServletRequest request){
        List<Category> categoryList = categoryService.getCategoryList();
        Map<String,Integer> map = new HashMap<>();
        Map<String,Object> paraMap = new HashMap<>();
        for (Category category :categoryList) {
            map.put(category.getCategoryName(),category.getId());
        }
        if (!request.getParameter("selectCategoryName").equals("0")){
            paraMap.put("categoryId",map.get(request.getParameter("selectCategoryName")));
            paraMap.put("partName",request.getParameter("partProductName"));
            List<AuctionInfoShow> auctionInfoShowList = new ArrayList<>();
            List<Product> productList = productService.queryProductByCondition(paraMap);
            for (Product product:productList) {
                AuctionInfoShow auctionInfoShow = auctionInfoShowService.searchProductByCondition(product.getId());
                auctionInfoShowList.add(auctionInfoShow);
            }
            model.addAttribute("auctionInfoShowList",auctionInfoShowList);
        }else {
            paraMap.put("categoryId",10000);
            paraMap.put("partName",request.getParameter("partProductName"));
            List<AuctionInfoShow> auctionInfoShowList = new ArrayList<>();
            List<Product> productList = productService.queryProductByCondition(paraMap);
            for (Product product:productList) {
                AuctionInfoShow auctionInfoShow = auctionInfoShowService.searchProductByCondition(product.getId());
                auctionInfoShowList.add(auctionInfoShow);
            }
            model.addAttribute("auctionInfoShowList",auctionInfoShowList);
        }
        return "frontend/searchProducts";
    }
}
