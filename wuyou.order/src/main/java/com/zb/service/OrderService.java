package com.zb.service;

import com.zb.pojo.Order;

import java.util.List;

public interface OrderService {
    public List<Order> findOrderList(Integer uid);
}
