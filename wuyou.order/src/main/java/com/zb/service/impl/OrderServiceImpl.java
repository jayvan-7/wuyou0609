package com.zb.service.impl;

import com.zb.pojo.Order;
import com.zb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public List<Order> findOrderList(Integer uid) {
        return orderMapper.findOrderList(uid);
    }
}
