package com.mq.gae.voucher.admin.api.communities;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Author: Gennadii Cherniaiev
 * Date: 8/4/2015
 */
@Entity
public class Community {
    @Id
    public Long id;
    public String name;
}
