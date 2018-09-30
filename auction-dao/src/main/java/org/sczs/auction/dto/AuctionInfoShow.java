package org.sczs.auction.dto;

import lombok.Data;

@Data
public class AuctionInfoShow {
    private String url;
    private String categoryName;
    private Integer productId;
    private String productName;
    private int price;
    private int basePrice;
    private String status;
}
