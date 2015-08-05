package com.mq.gae.voucher.admin.api.batches;

import com.google.api.server.spi.config.ApiResourceProperty;
import com.google.appengine.repackaged.org.codehaus.jackson.annotate.JsonIgnore;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;
import com.googlecode.objectify.annotation.Parent;
import com.mq.gae.voucher.admin.api.campaigns.Campaign;
import com.mq.gae.voucher.admin.api.vouchers.Voucher;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Author: Gennadii Cherniaiev
 * Date: 7/23/2015
 */
@Entity
public class Batch implements Serializable {
    @Id
    Long id;
    @JsonIgnore
    @Parent
    Ref<Campaign> campaignRef;
    String name;
    Long generateCodesCount;
    Date createDate;
    Date startDate;
    Date endDate;
    String owner;
    Boolean isActive;

    public Batch() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGenerateCodesCount() {
        return generateCodesCount;
    }

    public void setGenerateCodesCount(Long generateCodesCount) {
        this.generateCodesCount = generateCodesCount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Ref<Campaign> getCampaignRef() {
        return campaignRef;
    }

    public void setCampaignRef(Ref<Campaign> campaignRef) {
        this.campaignRef = campaignRef;
    }

    @Override
    public String toString() {
        return "Batch{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", generateCodesCount=" + generateCodesCount +
                ", createDate=" + createDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", owner='" + owner + '\'' +
                ", isActive='" + isActive + '\'' +
                '}';
    }
}
