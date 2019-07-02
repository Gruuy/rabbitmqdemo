package com.rabbitmq.rabbitmqdemo.entity;

import java.io.Serializable;

public class Order implements Serializable {
    /** 订单id*/
    private String orderId;
    /**订单状态 0：未支付，1：已支付，2：订单已取消*/
    private Integer orderStatus;
    /** 订单名字*/
    private String orderName;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

}
