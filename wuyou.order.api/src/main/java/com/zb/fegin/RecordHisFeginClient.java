package com.zb.fegin;


import com.zb.pojo.RecordHis;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 王效全
 * @Description TODO
 * @Date 2020/6/30
 * @Version V1.0
 */
@FeignClient("qgserver")
public interface RecordHisFeginClient {
    /**
     * 根据id查历史任务
     * @param id
     * @return
     */
    @GetMapping(value = "/getRecordHisById/{id}")
    public RecordHis getRecordHisById(@RequestParam("id") Integer id);
    /**
     * 根据用户id查历史任务
     * @param userid
     * @return
     */
    @PostMapping(value = "/getRecordHisByUserId")
    public RecordHis getRecordHisByUserId(@RequestParam("userid") Integer userid);
    /**
     * 根据商户id查历史任务根据商户id查历史任务
     * @param companyid
     * @return
     */
    @PostMapping(value = "/getRecordHisByCompanyId")
    public  RecordHis getRecordHisByCompanyId(@RequestParam("companyid") Integer companyid);
    /**
     * 根据订单号查询订单
     * @param orderno
     * @return
     */
    public RecordHis findRecordHisByOrderNo(@RequestParam("orderno") String orderno);
}
