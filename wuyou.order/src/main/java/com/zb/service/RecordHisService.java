package com.zb.service;

import com.zb.pojo.RecordHis;
import org.apache.ibatis.annotations.Param;

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
    public RecordHis getRecordHisById(@Param(value = "id") Integer id);

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
}
