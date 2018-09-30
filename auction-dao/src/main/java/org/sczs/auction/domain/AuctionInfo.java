package org.sczs.auction.domain;

import java.util.Date;

public class AuctionInfo {
    private Integer id;

    private Integer productId;

    private Integer basePrice;

    private Integer topPrice;

    private Date beginTime;

    private Date endTime;

    private String status;

    private Integer payCount;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Integer basePrice) {
        this.basePrice = basePrice;
    }

    public Integer getTopPrice() {
        return topPrice;
    }

    public void setTopPrice(Integer topPrice) {
        this.topPrice = topPrice;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPayCount() {
        return payCount;
    }

    public void setPayCount(Integer payCount) {
        this.payCount = payCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "AuctionInfo{" +
                "id=" + id +
                ", productId=" + productId +
                ", basePrice=" + basePrice +
                ", topPrice=" + topPrice +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", status='" + status + '\'' +
                ", payCount=" + payCount +
                '}';
    }
}