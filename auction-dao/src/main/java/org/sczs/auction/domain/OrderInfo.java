package org.sczs.auction.domain;

import java.util.Date;

public class OrderInfo {
    private Integer id;

    private Integer auctionRecordId;

    private String acceiptAddress;

    private String phone;

    private String deliverType;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuctionRecordId() {
        return auctionRecordId;
    }

    public void setAuctionRecordId(Integer auctionRecordId) {
        this.auctionRecordId = auctionRecordId;
    }

    public String getAcceiptAddress() {
        return acceiptAddress;
    }

    public void setAcceiptAddress(String acceiptAddress) {
        this.acceiptAddress = acceiptAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDeliverType() {
        return deliverType;
    }

    public void setDeliverType(String deliverType) {
        this.deliverType = deliverType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}