package com.zb.contrller;

import com.zb.pojo.Record;
import com.zb.service.RecordService;
import com.zb.util.IdWorker;
import com.zb.util.OrderCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 王效全
 * @Description TODO
 * @Date 2020/6/11
 * @Version V1.0
 */
@RestController
public class RecordController {
    @Autowired(required = false)
    private RecordService recordService;
    /**
     * 增加新的订单
     * @param record
     * @return
     */
    @PostMapping(value = "/insertRecord")
    public Integer insertRecord(Record record){
        record.setId(Long.parseLong(IdWorker.getId()));
        record.setOrderno(OrderCode.getOrderCode());
        return recordService.insertOrder(record);
    };

    //抢购方法
    @GetMapping(value = "/qg/{companyid}/{token}")
    public String qgRoom(@PathVariable("companyid") Integer companyid, @PathVariable("token")String token) {
        return recordService.qg(companyid, token);
    }

    //轮询方法
    @GetMapping(value = "/qgwhile/{companyid}/{token}")
    public String qgwhile(@PathVariable("companyid") Integer companyid, @PathVariable("token")String token){
        return recordService.qgwhile(companyid, token);
    }
    /**
     * 根据用户id查出用户的订单信息
     */
    @PostMapping(value = "/findOrderByUserid")
    public List<Record> findOrderByUserid(@RequestParam("userid") Integer userid){
        return recordService.findOrderByUserid(userid);
    };
    /**
     * 根据装修公司id查出用户的订单信息
     */
    @PostMapping(value = "/findOrderByCompanyid")
    public List<Record> findOrderByCompanyid(@RequestParam("companyid") Integer companyid){
        return recordService.findOrderByCompanyid(companyid);
    };
    /**
     * 根据订单号查询订单
     */
    @PostMapping(value = "/findOrderByOrderNo")
    public Record findOrderByOrderNo(@RequestParam("orderNo")String orderNo){
        return recordService.findOrderByOrderNo(orderNo);
    };
}
