package com.zb.service;

import com.sun.org.apache.regexp.internal.RE;
import com.zb.pojo.Record;
import com.zb.pojo.RecordHis;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 王效全
 * @Description TODO
 * @Date 2020/6/11
 * @Version V1.0
 */
public interface RecordHisService {
    /**
     * 根据id查历史任务
     * @param id
     * @return
     */
    public RecordHis getRecordHisById(@Param(value = "id") String id);
    /**
     * 根据订单号查询订单
     * @param orderno
     * @return
     */
    public RecordHis findRecordHisByOrderNo(String orderno);
    /**
     * 增加历史任务
     * @param recordHis
     * @return
     */
    public Integer insertRecordHis(RecordHis recordHis);

    /**
     * 修改历史任务
     * @param recordHis
     * @return
     */
    public Integer updateRecordHis(RecordHis recordHis);

    /**根据用户id查历史任务
     *
     * @param userid
     * @return
     */
    public  RecordHis  getRecordHisByUserId(@Param(value = "userid") Integer userid);

    /**根据商户id查历史任务
     *
     * @param companyid
     * @return
     */
    public RecordHis getRecordHisByCompanyId(@Param(value = "companyid") Integer companyid);

}
