package com.mq.gae.voucher.admin.api.vouchers;

import com.google.appengine.repackaged.org.codehaus.jackson.annotate.JsonIgnore;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import com.mq.gae.voucher.admin.api.batches.Batch;

import java.util.Date;

/**
 * Author: Gennadii Cherniaiev
 * Date: 8/4/2015
 */
@Entity
public class Voucher {
    @Id
    public Long id;
    @JsonIgnore
    @Parent
    public Ref<Batch> batchRef;
    @Index
    public String code;
    public Date redeemedDate;
    public Long redeemedUserId;
    public String redeemedUserName;

    public Voucher(String code) {
        this.code = code;
    }

    public Voucher() {
    }

    public void redeem(long id, String email) {
        redeemedUserId = id;
        redeemedUserName = email;
        redeemedDate = new Date();
    }
}
