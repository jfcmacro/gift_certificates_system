package com.epam.esm.models;

import java.math.BigDecimal;
import java.util.Date;

public class Gift {
    private int id;
    private String name;
    private BigDecimal price;
    private int duration;
    private Date createDate;
    private Date lastUpdateDate;

    public Gift() {
    }

    public int getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public BigDecimal getPrice() {
	return price;
    }

    public int getDuration() {
	return duration;
    }

    public Date getCreateDate() {
	return createDate;
    }

    public Date getLastUpdateDate() {
	return lastUpdateDate;
    }

    public void setId(int id) {
	this.id = id;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setPrice(BigDecimal price) {
	this.price = price;
    }

    public void setDuration(int duration) {
	this.duration = duration;
    }

    public void setCreateDate(Date createDate) {
	this.createDate = createDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
	this.lastUpdateDate = lastUpdateDate;
    }
}
