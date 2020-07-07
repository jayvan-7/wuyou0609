package com.zb.contrller;

import com.zb.pojo.RecordHis;
import com.zb.service.RecordHisService;
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
public class RecordHisController {
    @Autowired(required = false)
    private RecordHisService recordHisService;

    /**
     * 根据id查历史任务
     * @param id
     * @return
     */
    @GetMapping(value = "/getRecordHisById/{id}")
    public RecordHis getRecordHisById(@RequestParam("id")Integer id){
        return recordHisService.getRecordHisById(id);
    };

    /**
     * 根据用户id查历史任务
     * @param userid
     * @return
     */
    @PostMapping(value = "/getRecordHisByUserId")
    public RecordHis getRecordHisByUserId(@RequestParam("userid") Integer userid){
        return recordHisService.getRecordHisByUserId(userid);
    };

    /**
     * 根据商户id查历史任务根据商户id查历史任务
     * @param companyid
     * @return
     */
    @PostMapping(value = "/getRecordHisByCompanyId")
    public  RecordHis getRecordHisByCompanyId(@RequestParam("companyid")Integer companyid){
        return recordHisService.getRecordHisByCompanyId(companyid);
    };
    /**
     * 根据订单号查询订单
     * @param orderno
     * @return
     */
    public RecordHis findRecordHisByOrderNo(@RequestParam("orderno")String orderno){
        return recordHisService.findRecordHisByOrderNo(orderno);
    };
}
