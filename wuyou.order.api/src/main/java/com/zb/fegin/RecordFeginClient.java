package com.zb.fegin;

import com.zb.pojo.Record;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 王效全
 * @Description TODO
 * @Date 2020/6/30
 * @Version V1.0
 */
@FeignClient("qgserver")
public interface RecordFeginClient {
    /**
     * 增加新的订单
     * @param record
     * @return
     */
    @PostMapping(value = "/insertRecord")
    public Integer insertRecord(Record record);
    //抢购方法
    @GetMapping(value = "/qg/{companyid}/{token}")
    public String qgRoom(@PathVariable("companyid") Integer companyid, @PathVariable("token") String token);
    //轮询方法
    @GetMapping(value = "/qgwhile/{companyid}/{token}")
    public String qgwhile(@PathVariable("companyid") Integer companyid, @PathVariable("token") String token);
    /**
     * 根据用户id查出用户的订单信息
     */
    @PostMapping(value = "/findOrderByUserid")
    public List<Record> findOrderByUserid(@RequestParam("userid") Integer userid);
    /**
     * 根据装修公司id查出用户的订单信息
     */
    @PostMapping(value = "/findOrderByCompanyid")
    public List<Record> findOrderByCompanyid(@RequestParam("companyid") Integer companyid);
    /**
     * 根据订单号查询订单
     */
    @PostMapping(value = "/findOrderByOrderNo")
    public Record findOrderByOrderNo(@RequestParam("orderNo") String orderNo);
   // 修改订单编号和状态
    @PostMapping(value = "/updateOrder")
    public Integer updateOrder(Record record);
}
