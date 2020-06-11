package com.zb.service.impl;

import com.zb.mapper.RecordMapper;
import com.zb.pojo.Record;
import com.zb.service.RecordService;
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
public class RecordServiceImpl implements RecordService {

    @Autowired(required = false)
    private RecordMapper recordMapper;

    @Override
    public int insertOrder(Record record) {
        return 0;
    }

    @Override
    public Record findOrderByid(Integer id) {
        return null;
    }

    @Override
    public int updateOrderstate(String orderno, String tradeno) {
        return 0;
    }

    @Override
    public Record findOrderByUserid(Integer userid) {
        return null;
    }

    @Override
    public Record findOrderByOrderNo(String orderno) {
        return null;
    }

    @Override
    public List<Record> findByUpdateTimeBefore() {
        return null;
    }

    @Override
    public int findTaskByidAndversion(Integer id, Integer version) {
        return 0;
    }

    @Override
    public void publishTask(Record record) {

    }

    @Override
    public void finishTask(Record record) {

    }

    @Override
    public String qgRoom(Integer companyid, String token) {
        return null;
    }

    @Override
    public String qgwhile(Integer companyid, String token) {
        return null;
    }
}
