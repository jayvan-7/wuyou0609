package com.zb.service.impl;

import com.zb.mapper.RecordHisMapper;
import com.zb.pojo.Record;
import com.zb.pojo.RecordHis;
import com.zb.service.RecordHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 王效全
 * @Description TODO
 * @Date 2020/6/11
 * @Version V1.0
 */
@Service
public class RecordHisServiceImpl implements RecordHisService {

    @Autowired(required = false)
    private RecordHisMapper recordHisMapper;
    @Override
    public RecordHis getRecordHisById(String id) {
        return recordHisMapper.getRecordHisById(id);
    }

    @Override
    public RecordHis findRecordHisByOrderNo(String orderno) {
        return recordHisMapper.findRecordHisByOrderNo(orderno);
    }

    @Override
    public Integer insertRecordHis(RecordHis recordHis) {
        return recordHisMapper.insertRecordHis(recordHis);
    }

    @Override
    public Integer updateRecordHis(RecordHis recordHis) {
        return recordHisMapper.updateRecordHis(recordHis);
    }

    @Override
    public RecordHis getRecordHisByUserId(Integer userid) {
        return recordHisMapper.getRecordHisByUserId(userid);
    }

    @Override
    public RecordHis getRecordHisByCompanyId(Integer companyid) {
        return recordHisMapper.getRecordHisByCompanyId(companyid);
    }
}
