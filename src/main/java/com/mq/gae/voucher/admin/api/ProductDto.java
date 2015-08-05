package com.mq.gae.voucher.admin.api;

/**
 * Author: Gennadii Cherniaiev
 * Date: 8/5/2015
 */
public class ProductDto {
    public Long id;

    public ProductDto(long redeem) {
        id = redeem;
    }

    public ProductDto() {
    }
}
