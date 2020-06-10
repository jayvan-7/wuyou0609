package com.zb.contrller;

import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import com.zb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/userorder/{uid}")
    public Dto userorder(@PathVariable("uid") Integer uid) {
        List list = orderService.findOrderList(uid);
        return DtoUtil.returnSuccess("ok", list);
    }
}
