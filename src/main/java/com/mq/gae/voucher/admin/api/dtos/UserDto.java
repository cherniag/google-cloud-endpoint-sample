package com.mq.gae.voucher.admin.api.dtos;

/**
 * Author: Gennadii Cherniaiev
 * Date: 8/5/2015
 */
public class UserDto {
    public Long id;
    public String userName;


    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
