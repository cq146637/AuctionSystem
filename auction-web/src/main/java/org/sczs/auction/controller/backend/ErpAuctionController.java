package org.sczs.auction.controller.backend;

import org.sczs.auction.domain.AuctionInfo;
import org.sczs.auction.domain.AuctionRecord;
import org.sczs.auction.domain.PayUser;
import org.sczs.auction.domain.Product;
import org.sczs.auction.service.AuctionInfoService;
import org.sczs.auction.service.AuctionRecordService;
import org.sczs.auction.service.PayUserService;
import org.sczs.auction.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/erp")
public class ErpAuctionController {

    @Autowired
    private AuctionInfoService auctionInfoService;

    @Autowired
    private AuctionRecordService auctionRecordService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PayUserService payUserService;

    @RequestMapping(value = "/QueryIsTheAuction")
    public String queryIsTheAuction() {
        // 查询正在进行中的拍卖,预期目标
        return "";
    }

    @RequestMapping(value = "/QueryAllAuction")
    public String queryAllAuction(Model model) {
        // 查询所有拍卖物品和竞拍记录信息
        List<AuctionInfo> auctionInfoList = auctionInfoService.queryAllAuctionInfo();
        List<AuctionRecord> auctionRecordList = auctionRecordService.queryAllAuctionRecord();
        // 查询商品ID对应的名称
        Map<Integer, String> productMap = new HashMap<>();
        for (AuctionInfo auctionInfo:auctionInfoList) {
            Product product = productService.queryOneProduct(auctionInfo.getProductId());
            if (product != null) {
                productMap.put(auctionInfo.getId(), product.getProductName());
            }
        }
        // 查询竞拍者ID对应的名称
        Map<Integer, String> payUserMap = new HashMap<>();
        for (AuctionRecord auctionRecord:auctionRecordList) {
            PayUser payUser = payUserService.queryOnePayUser(auctionRecord.getPayUserId());
            if (payUser != null) {
                payUserMap.put(auctionRecord.getPayUserId(), payUser.getUserName());
            }
        }
        model.addAttribute("auctionInfoList", auctionInfoList);
        model.addAttribute("auctionRecordList", auctionRecordList);
        model.addAttribute("productMap", productMap);
        model.addAttribute("payUserMap", payUserMap);
        return "show_all_auction";
    }

    @RequestMapping(value = "/QueryAuctionInfo")
    public String queryOneAuctionInfo(Model model, Integer id, String productName) {
        // 查询单条拍卖的详细信息
        AuctionInfo auctionInfo = auctionInfoService.queryOneAuctionInfo(id);
        model.addAttribute("auctionInfo", auctionInfo);
        // 还需要物品ID
        model.addAttribute("productName", productName);
        return "edit_auction_info";
    }

    @RequestMapping(value = "/InsertAuctionInfo", method = RequestMethod.GET)
    public String insertOneAuctionInfo(Model model) {
        // 插入拍卖信息查
        // 填充拍卖依赖信息，产品信息
        List<Product> productList = productService.getProductsList();
        model.addAttribute("productList", productList);
        return "add_auction_info";
    }

    @RequestMapping(value = "/InsertAuctionInfo", method = RequestMethod.POST)
    public String insertOneAuctionInfo(AuctionInfo auctionInfo, String beginTime1, String endTime1) throws ParseException {
        // 插入拍卖信息
        if(null != beginTime1){
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            auctionInfo.setBeginTime(df.parse(beginTime1));
        }

        if(null != endTime1){
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            auctionInfo.setEndTime(df.parse(endTime1));
        }

        auctionInfoService.insertOneAuctionInfo(auctionInfo);
        return "redirect:/erp/QueryAllAuction.html";
    }

    @RequestMapping(value = "/UpdateAuctionInfo")
    public String updateOneActionInfo(AuctionInfo auctionInfo) {
        // 更新拍卖信息
        auctionInfoService.updateOneActionInfo(auctionInfo);
        return "redirect:/erp/QueryAllAuction.html";
    }

    @RequestMapping(value = "/DeleteAuctionInfo")
    public String deleteOneActionInfo(Integer id) {
        // 删除拍卖信息
        auctionInfoService.deleteOneActionInfo(id);
        return "redirect:/erp/QueryAllAuction.html";
    }

    @RequestMapping(value = "/QueryAuctionRecord")
    public String queryOneAuctionRecord(Model model, Integer id, String payUserName, String productName) {
        // 查询单条拍卖的详细信息
        AuctionRecord auctionRecord = auctionRecordService.queryOneAuctionRecord(id);
        model.addAttribute("auctionRecord", auctionRecord);
        // 还需要物品ID
        model.addAttribute("payUserName", payUserName);
        model.addAttribute("productName", productName);
        return "edit_auction_record";
    }

    @RequestMapping(value = "/InsertAuctionRecord", method = RequestMethod.GET)
    public String insertOneAuctionRecord(Model model) {
        // 插入拍卖信息
        // 填充拍卖依赖信息，产品信息、用户信息
        List<AuctionInfo> auctionInfoList = auctionInfoService.queryAllAuctionInfo();
        List<PayUser> payUserList = payUserService.getPayUserList();
        List<List<String>> productList = new ArrayList<>();
        for (AuctionInfo auctionInfo:auctionInfoList) {
            Product product = productService.queryOneProduct(auctionInfo.getProductId());
            if (product != null) {
                List<String> temp = new ArrayList<>();
                temp.add(String.valueOf(auctionInfo.getId()));
                temp.add(product.getProductName());
                productList.add(temp);
            }
        }
        model.addAttribute("productList", productList);
        model.addAttribute("payUserList", payUserList);
        return "add_auction_record";
    }

    @RequestMapping(value = "/InsertAuctionRecord", method = RequestMethod.POST)
    public String insertOneAuctionRecord(AuctionRecord auctionRecord) {
        // 插入竞拍记录
        auctionRecordService.insertOneAuctionRecord(auctionRecord);
        return "redirect:/erp/QueryAllAuction.html";
    }

    @RequestMapping(value = "/UpdateAuctionRecord")
    public String updateOneActionRecord(AuctionRecord auctionRecord) {
        // 更新竞拍信息
        auctionRecordService.updateOneActionRecord(auctionRecord);
        return "redirect:/erp/QueryAllAuction.html";
    }

    @RequestMapping(value = "/DeleteAuctionRecord")
    public String deleteOneActionRecord(Integer id) {
        // 删除竞拍信息
        auctionRecordService.deleteOneActionRecord(id);
        return "redirect:/erp/QueryAllAuction.html";
    }
}
