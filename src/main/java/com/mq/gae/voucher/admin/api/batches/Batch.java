package com.mq.gae.voucher.admin.api.batches;

import com.google.api.server.spi.config.ApiResourceProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Author: Gennadii Cherniaiev
 * Date: 7/23/2015
 */
public class Batch implements Serializable{
    Long id;
    String name;
    Long generateCodesCount;
    Date createDate;
    Date startDate;
    Date endDate;
    String owner;

    public Batch() {
    }

    public Batch(Long id, String name, Long generateCodesCount, Date createDate, Date startDate, Date endDate, String owner) {
        this.id = id;
        this.name = name;
        this.generateCodesCount = generateCodesCount;
        this.createDate = createDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.owner = owner;
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
                '}';
    }
}
