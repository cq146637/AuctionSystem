package org.sczs.auction.controller.frontend;

import org.apache.commons.lang.StringUtils;
import org.sczs.auction.domain.*;
import org.sczs.auction.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SaleUserController {

    static private String prefixPath = "../assets/image/product_img/";

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductImgService productImgService;

    @Autowired
    AuctionInfoService auctionInfoService;

    @Autowired
    AuctionRecordService auctionRecordService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/sale_user_home")
    public String showPayUserHome(Model model, HttpSession session) {
        Integer id = ((SaleUser) session.getAttribute("saleUser")).getId();
        // 查出用户的拍卖品
        List<Product> productList = productService.queryProductBySaleUserId(id);
        Map<Integer, AuctionInfo> auctionInfoMap = new HashMap<>();  // 已结束的竞拍商品
        Map<Integer, AuctionInfo> auctionInfoNotStartMap = new HashMap<>();  // 未开始的竞拍商品
        Map<Integer, AuctionInfo> auctionInfoAuctioningMap = new HashMap<>();  // 正在进行的竞拍商品

        Map<Integer, ProductImg> ProductImgNotStartMap = new HashMap<>();
        Map<Integer, ProductImg> ProductImgAuctioningMap = new HashMap<>();

        Map<Integer, Category> categoryNotStartMap = new HashMap<>();
        Map<Integer, Category> categoryAuctioningMap = new HashMap<>();
        Map<Integer, Category> categoryMap = new HashMap<>();

        Map<Integer, AuctionRecord> auctionRecordMap = new HashMap<>();
        Map<Integer, AuctionRecord> auctionRecordAuctioningMap = new HashMap<>();

        Map<Integer, String> auctionRecordResMap = new HashMap<>(); // 已经结束的竞拍商品结果，竞拍完成or流局



        for (Product product: productList) {
            AuctionInfo auctionInfo = auctionInfoService.getAuctionInfoByProductId(product.getId());
            if (auctionInfo != null) {
                ProductImg productImg = productImgService.getOneProductImgByProductId(product.getId());
                Category category = categoryService.queryOneCategory(product.getCategoryId());
                AuctionRecord auctionRecord = auctionRecordService.queryAuctionRecordByAuctionId(auctionInfo.getId());
                if (productImg != null && category != null) {
                    if (auctionInfo.getStatus().equals("正在进行")) {
                        auctionInfoAuctioningMap.put(product.getId(), auctionInfo);
                        ProductImgAuctioningMap.put(product.getId(), productImg);
                        categoryAuctioningMap.put(product.getId(), category);
                        if (auctionRecord != null) {
                            auctionRecordAuctioningMap.put(product.getId(), auctionRecord);
                        }
                    }
                    else if (auctionInfo.getStatus().equals("未开始")) {
                        auctionInfoNotStartMap.put(product.getId(), auctionInfo);
                        ProductImgNotStartMap.put(product.getId(), productImg);
                        categoryNotStartMap.put(product.getId(), category);
                    }
                    else if (auctionInfo.getStatus().equals("已结束")) {
                        auctionInfoMap.put(product.getId(), auctionInfo);
                        categoryMap.put(product.getId(), category);
                        // 已经结束的竞拍商品，需要判断结果，竞拍完成or流局
                        if (auctionRecord != null) {
                            auctionRecordResMap.put(product.getId(), "已完成");
                            auctionRecordMap.put(product.getId(), auctionRecord);
                        }
                        else {
                            auctionRecordResMap.put(product.getId(), "已流局");
                        }
                    }
                }
            }
        }
        model.addAttribute("productList", productList);
        model.addAttribute("auctionInfoMap", auctionInfoMap);
        model.addAttribute("auctionInfoNotStartMap", auctionInfoNotStartMap);
        model.addAttribute("auctionInfoAuctioningMap", auctionInfoAuctioningMap);

        model.addAttribute("ProductImgNotStartMap", ProductImgNotStartMap);
        model.addAttribute("ProductImgAuctioningMap", ProductImgAuctioningMap);

        model.addAttribute("categoryNotStartMap", categoryNotStartMap);
        model.addAttribute("categoryAuctioningMap", categoryAuctioningMap);
        model.addAttribute("categoryMap", categoryMap);

        model.addAttribute("auctionRecordResMap", auctionRecordResMap);
        model.addAttribute("auctionRecordMap", auctionRecordMap);
        model.addAttribute("auctionRecordAuctioningMap", auctionRecordAuctioningMap);

        model.addAttribute("notStartMapSize", auctionInfoNotStartMap.size());
        model.addAttribute("auctioningMapSize", auctionInfoAuctioningMap.size());

        return "frontend/sale_user_center";
    }

    @RequestMapping(value = "/release", method = RequestMethod.GET)
    public String releaseOneProduct(Model model) {
        // 拍卖者发布商品
        List<Category> categoryList = categoryService.getCategoryList();
        model.addAttribute("categoryList", categoryList);
        return "frontend/release";
    }

    @RequestMapping(value = "/release", method = RequestMethod.POST)
    public String releaseOneProduct(HttpSession session, Product product, ProductImg productImg, AuctionInfo auctionInfo, String url, String urlList, String beginTime1, String endTime1) throws Exception {
        // 拍卖者发布商品
        // 1. 首先存储图，并获取自动生成的productId，用于下面的存储
        product.setSaleUserId(((SaleUser) session.getAttribute("saleUser")).getId());
        productService.insertOneProduct(product);
        Integer productId = productService.queryProductIdByCondition(product);
        // 2. 处理开始时间和结束时间字符串，转换成Date对象，并存储AuctionInfo对象
        auctionInfo.setProductId(productId);
        if(!StringUtils.isBlank(beginTime1)){
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            auctionInfo.setBeginTime(df.parse(beginTime1));
        }

        if(!StringUtils.isBlank(endTime1)){
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            auctionInfo.setEndTime(df.parse(endTime1));
        }
        auctionInfoService.insertOneAuctionInfo(auctionInfo);
        // 3. 处理图片上传的url，并存储ProducImg对象
        if (!StringUtils.isBlank(url)) {
            productImg.setUrl(prefixPath + url);
            productImg.setImgType("展示图");
            productImg.setProductId(productId);
            productImgService.insertOneProductImg(productImg);
        }
        if (!StringUtils.isBlank(urlList)) {
            String [] detail_url = urlList.split(",");
            for (int i = 1; i < detail_url.length; i++) {
                productImg.setUrl(prefixPath + detail_url[i]);
                productImg.setImgType("细节图");
                productImg.setProductId(productId);
                productImgService.insertOneProductImg(productImg);
            }
        }
        return "redirect:/sale_user_home.html";
    }

    @RequestMapping(value = "/release_cancel")
    public String cancelReleaseProduct(HttpServletRequest req, String url, String urlList) {
        if (!StringUtils.isBlank(url)) {
            // 删除图片源文件
            String deleteFileName = (prefixPath + url).replace("../", "").replace("/", "\\");
            String saveFilePath = req.getServletContext().getRealPath("assets/");
            String [] array = saveFilePath.split("\\\\");
            saveFilePath = array[0];
            for (int i = 1; i < array.length; i++) {
                if (array[i].equals("target")){
                    break;
                }
                saveFilePath += "\\" + array[i];
            }
            saveFilePath += "\\src\\main\\webapp\\";
            File deleteFile = new File(saveFilePath + "\\" + deleteFileName);
            deleteFile.delete();
        }
        if (!StringUtils.isBlank(urlList)) {
            String [] detail_url = urlList.split(",");
            for (int i = 1; i < detail_url.length; i++) {
                // 删除图片源文件
                String deleteFileName = (prefixPath + detail_url[i]).replace("../", "").replace("/", "\\");
                String saveFilePath = req.getServletContext().getRealPath("assets/");
                String [] array = saveFilePath.split("\\\\");
                saveFilePath = array[0];
                for (int j = 1; j < array.length; j++) {
                    if (array[j].equals("target")){
                        break;
                    }
                    saveFilePath += "\\" + array[j];
                }
                saveFilePath += "\\src\\main\\webapp\\";
                File deleteFile = new File(saveFilePath + "\\" + deleteFileName);
                deleteFile.delete();
            }
        }
        return "redirect:/sale_user_home.html";
    }

}
