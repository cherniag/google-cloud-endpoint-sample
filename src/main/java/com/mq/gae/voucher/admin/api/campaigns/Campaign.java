package com.mq.gae.voucher.admin.api.campaigns;

import com.google.appengine.repackaged.org.codehaus.jackson.annotate.JsonIgnore;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;
import com.mq.gae.voucher.admin.api.communities.Community;

/**
 * Author: Gennadii Cherniaiev
 * Date: 8/4/2015
 */
@Entity
public class Campaign {
    @Id
    public Long id;
    public String name;
    @JsonIgnore
    @Parent
    public Ref<Community> communityRef;
    public Long productId;
    public Boolean isActive;


    @Override
    public String toString() {
        return "Campaign{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", communityRef=" + communityRef +
                ", productId=" + productId +
                ", isActive=" + isActive +
                '}';
    }
}
