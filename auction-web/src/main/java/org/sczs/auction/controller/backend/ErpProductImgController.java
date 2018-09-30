package org.sczs.auction.controller.backend;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.sczs.auction.domain.Product;
import org.sczs.auction.domain.ProductImg;
import org.sczs.auction.service.ProductImgService;
import org.sczs.auction.service.ProductService;
import org.sczs.auction.utils.MsgJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("/erp")
public class ErpProductImgController {

    static private String prefixPath = "../assets/image/product_img/";

    @Autowired
    private ProductImgService productImgService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/ShowProductImg")
    public String getProductImgList(Model model) {
        List<ProductImg> productImgList = productImgService.getProductImgList();
        Map<Integer, String> productMap = new HashMap<>();
        for (ProductImg productImg: productImgList) {
            Product product = productService.queryOneProduct(productImg.getProductId());
            if (product != null) {
                productMap.put(productImg.getProductId(), product.getProductName());
            }
        }
        model.addAttribute("productImgList", productImgList);
        model.addAttribute("productMap", productMap);
        return "show_product_img";
    }

    @RequestMapping(value = "/StoreProductImg")
    @ResponseBody
    public String sotreProductImg(HttpServletRequest req , MultipartFile showPictureFile) throws IOException {
        // 原始名称
        String oldFileName = showPictureFile.getOriginalFilename(); // 获取上传文件的原名
        // 存储图片的虚拟本地路径（这里需要配置tomcat的web模块路径，双击猫进行配置）
        String saveFilePath = req.getServletContext().getRealPath("assets/");
        String targetPath = req.getServletContext().getRealPath("assets/image/product_img/");
        String [] array = saveFilePath.split("\\\\");
        saveFilePath = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].equals("target")){
                break;
            }
            saveFilePath += "\\" + array[i];
        }
        saveFilePath += "\\src\\main\\webapp\\assets\\image\\product_img\\";
        MsgJson msgJson =  new MsgJson();
        // 上传图片
        if (showPictureFile != null && oldFileName != null && oldFileName.length() > 0) {
            // 新的图片名称
            String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
            // 新图片
            File newFile = new File(saveFilePath + "\\" + newFileName);
            // 将内存中的数据写入磁盘
            showPictureFile.transferTo(newFile);
            ErpProductImgController.copyFile(saveFilePath + "\\" + newFileName, targetPath + "\\" + newFileName);
            // 将新图片名称返回到前端
            msgJson.setSuccess(true);
            msgJson.setMessage(newFileName);
            return JSONObject.toJSONString(msgJson);
        } else {
            msgJson.setSuccess(false);
            msgJson.setMessage("上传失败");
            return JSONObject.toJSONString(msgJson);
        }
    }

    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
                fs.close();
            }
        }
        catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/AddProductImg", method = RequestMethod.GET)
    public String insertOneProductImg(Model model) {
        List<Product> productList = productService.getProductsList();
        model.addAttribute("productList", productList);
        return "add_product_img";
    }

    @RequestMapping(value = "/AddProductImg", method = RequestMethod.POST)
    public String insertOneProductImg(ProductImg productImg, String url, String urlList) {
        if (!StringUtils.isBlank(url)) {
            productImg.setUrl(prefixPath + url);
            productImg.setImgType("展示图");
            productImgService.insertOneProductImg(productImg);
        }
        if (!StringUtils.isBlank(urlList)) {
            String [] detail_url = urlList.split(",");
            for (int i = 1; i < detail_url.length; i++) {
                productImg.setUrl(prefixPath + detail_url[i]);
                productImg.setImgType("细节图");
                productImgService.insertOneProductImg(productImg);
            }
        }
        // 写入数据库
        return "redirect:/erp/ShowProductImg.html";
    }

    @RequestMapping(value = "DeleteProductImg")
    public String deleteOneProductImg(HttpServletRequest req, Integer id) {
        // 查询图片信息
        ProductImg productImg = productImgService.getOneProductImg(id);
        // 删除图片信息
        productImgService.deleteOneProductImg(id);
        // 删除图片源文件
        String deleteFileName = productImg.getUrl().replace("../", "").replace("/", "\\");
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

        return "redirect:/erp/ShowProductImg.html";
    }

    @RequestMapping(value = "QueryProductImg")
    public String queryOneProductImg(Model model, Integer id,  String productName) {
        // 查询单条图片信息
        ProductImg productImg = productImgService.getOneProductImg(id);
        model.addAttribute("productImg", productImg);
        model.addAttribute("productName", productName);
        return "edit_product_img";
    }

    @RequestMapping(value = "UpdateProductImg")
    public String updateOneProductImg(HttpServletRequest req, ProductImg productImg) {
        // 修改单条图片信息
        // 首先判断url有没有修改
        String oldFileName = productImgService.getOneProductImg(productImg.getId()).getUrl();
        if (!oldFileName.equals(prefixPath + productImg.getUrl())){
            productImg.setUrl(prefixPath + productImg.getUrl());
            productImgService.updateOneProductImg(productImg);
            // 删除文件
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
            oldFileName = oldFileName.replace("../", "").replace("/", "\\");
            File deleteFile = new File(saveFilePath + "\\" + oldFileName);
            deleteFile.delete();
        }
        return "redirect:/erp/ShowProductImg.html";
    }
}
